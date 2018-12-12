package board;
import pieces.*;
import gui.*;
import management.*;

public class ChessBoard {
	
	private static ChessBoard chessBoard = null;
	
	private Piece[][] board = new Piece[8][8];
	private Location[][] locations = new Location[8][8];
	public static String player = "White";
	public static Location WhiteKing= new Location(0,3);
	public static Location BlackKing= new Location(7,3);
	public final String[] pieces = {"Rook","Knight","Bishop","King","Queen","Bishop","Knight","Rook"}; 
	public final int[] pieceRows = {0,7};
	public final int[] pawnRows = {1,6};
	
	
	public ChessBoard(){
		
		for (int i=0; i<8; i++){
			for (int j=0; j<8; j++){
				this.board[i][j]= new Piece("null","null");
			}
		}
		
		for (int i : pieceRows){
			for (int j=0; j<8; j++){
				if (i==0)
					this.board[i][j] = new Piece(pieces[j],"White");
				else 
					this.board[i][j] = new Piece(pieces[j],"Black");
			}
		}
		for (int i : pawnRows){
			for (int j=0; j<8; j++){
				if (i==1)
					this.board[i][j] = new Piece("Pawn","White");
				else 
					this.board[i][j] = new Piece("Pawn","Black");
			}
		}
		
		for (int i=0; i< 8; i++){
			for (int j=0; j<8; j++){
				this.locations[i][j] = new Location(i,j);
			}
		}
		
	}
	
	public Location getLocation(int row, int column){
		return this.locations[row][column];
	}
	
	public Piece[][] getBoard(){
		return this.board;
	}
	
	/*
	 * Saves the new king location if king is moved.
	 */
	public void updateKingLocation(Location startL, Location endL){
		Piece srcPiece = this.getPiece(startL);
		
		if (srcPiece.getName().equals("King") && srcPiece.getColor().equals("White")){
    		WhiteKing.changeLocation(endL);
    	}
    	if (srcPiece.getName().equals("King") && srcPiece.getColor().equals("Black")){
    		BlackKing.changeLocation(endL);
    		
    	}			
	}
	
	/*
	 * Initializes the (8,8) chessboard
	 * returns chessboard
	 */
	public static ChessBoard getInstance(){
		if (chessBoard == null){
			chessBoard = new ChessBoard();
		}
		return chessBoard;
	}
	
	public static void restart(){
		chessBoard = new ChessBoard();
		player = "White";
		WhiteKing.changeLocation(new Location(0,3));
		BlackKing.changeLocation(new Location(7,3));
	}
	
	public Piece getPiece(Location location){
		return this.getBoard()[location.getRow()][location.getColumn()];
		
	}
	
	public Boolean isDiffColor(Location src, Location dst){
		if (this.getBoard()[dst.getRow()][dst.getColumn()].getColor().equals(this.getBoard()[src.getRow()][src.getColumn()].getColor())){
			return false;
		}
		return true;
	}
	
	public String getPlayer(){
		return player;
	}
	
	public void setNextPlayer(Piece srcPiece){
		
		if (srcPiece.getColor().equals("White")){
    		player="Black";
    		//System.out.println("Next Player= "+ this.player);
    	}
    	else {
    		player="White";
    		//System.out.println("Next Player= "+ this.player);
    	}
	}
		
	/*
	 * @param source and destination locations
	 * 
	 */
	public Boolean isValidPlayerMove(Location src, Location dst){
		
		if (PieceDragAndDrop.ifCheckMate){
			return false;
		}
		
		if (this.getPiece(src).getColor().equals(this.getPlayer())){
			Boolean value = this.isValidPieceMove(src, dst);
			if (value){
				if (!MoveControls.ifCreatesCheck(chessBoard,src,dst)){
					ChessBoardUi.setStatus("");
					return true;
				}
				else
					return false;
			}
			else{
				//System.out.println("Not a valid move");
				ChessBoardUi.setStatus("Invalid move");
				return value;
			}
		}
		else {
			ChessBoardUi.setStatus("Not your turn");
			//System.out.println("It's not your turn");
			return false;
		}		
	}
	
	public Boolean isValidPieceMove(Location src, Location dst){
		int row = src.getRow();
		int column = src.getColumn();
		Piece piece = this.board[row][column];
		switch (piece.getName()){
			case ("Rook"): 
				Rook rook = new Rook(piece.getColor());
				return rook.isValidMoveRook(this,src,dst);
			case ("Bishop"): 
				Bishop bishop = new Bishop(piece.getColor());
				return bishop.isValidMoveBishop(this,src,dst);
			case ("King"): 
				King king = new King(piece.getColor());
				return king.isValidMove(this,src,dst);
			case ("Queen"): 
				Queen queen = new Queen(piece.getColor());
				return queen.isValidMove(this,src,dst);
			case ("Knight"): 
				Knight knight = new Knight(piece.getColor());
				return knight.isValidMove(this,src,dst);
			case ("Pawn"): 
				Pawn pawn = new Pawn(piece.getColor());
				return pawn.isValidMove(this,src,dst);
			default:
				return false;
			
		}
		
	}
	
	
}