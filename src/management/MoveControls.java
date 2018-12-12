package management;
import javax.swing.*;
import java.util.*;
import board.ChessBoard;
import board.Location;
import pieces.Piece;
import gui.ChessBoardUi;

public class MoveControls {
	
	ChessBoard chessBoard = ChessBoard.getInstance();
	static LinkedList<Location[]> moves = new LinkedList<Location[]>();
	static LinkedList<Piece[]> pieceItems = new LinkedList<Piece[]>();
	
	public static void restart(){
		moves.clear();
		pieceItems.clear();
	}
	
	public static Boolean ifCreatesCheck(ChessBoard chessBoard, Location src, Location dst){
		Piece srcPiece = chessBoard.getPiece(src);
    	Piece dstPiece = chessBoard.getPiece(dst);
		chessBoard.updateKingLocation(src,dst);
		chessBoard.getBoard()[dst.getRow()][dst.getColumn()] = srcPiece;
		chessBoard.getBoard()[src.getRow()][src.getColumn()] = new Piece("null","null");
		
		Boolean isCheckThere =  detectedCheck(chessBoard);
		chessBoard.updateKingLocation(dst,src);
		chessBoard.getBoard()[dst.getRow()][dst.getColumn()] = dstPiece;
		chessBoard.getBoard()[src.getRow()][src.getColumn()] = srcPiece;
		
		if (isCheckThere){
			ChessBoardUi.setStatus("<html>Not Allowed<br>King at risk</html>");
			return true;
		}
		else {
			ChessBoardUi.setStatus("");
			return false;
		}
		
	}
	
	public static Boolean detectedCheck(ChessBoard chessBoard){
		String kingColor = chessBoard.getPlayer();
		Location kingLocation;
		String threatColor;
		if (kingColor.equals("White")){
			threatColor = "Black";
			kingLocation = new Location(ChessBoard.WhiteKing);
		}
		else {
			threatColor = "White";
			kingLocation = new Location(ChessBoard.BlackKing);
		}
		
		for (int i=0; i< 8; i++) {
			for (int j=0; j<8; j++){
				Location src = new Location(i,j);
				if (chessBoard.getPiece(src).getColor().equals(threatColor)){
					if (chessBoard.isValidPieceMove(src,kingLocation)){
						
						return true;
					}
				}
			}
		}
		
		return false;
	}
	
	public static Boolean detectedCheckMate(ChessBoard chessBoard){
		String kingColor = chessBoard.getPlayer();
		
		if (detectedCheck(chessBoard)){
			for (int i=0; i< 8; i++) {
				for (int j=0; j<8; j++){
					Location src = new Location(i,j);
					Piece srcPiece = chessBoard.getPiece(src);
					if (chessBoard.getPiece(src).getColor().equals(kingColor)){
						for (int x=0; x<8; x++){
							for (int y=0; y<8; y++){
								Location dst = new Location(x,y);
								if (chessBoard.isValidPieceMove(src,dst)){
									
									Piece dstPiece = chessBoard.getPiece(dst);
									chessBoard.updateKingLocation(src,dst);
									chessBoard.getBoard()[dst.getRow()][dst.getColumn()] = srcPiece;
									chessBoard.getBoard()[src.getRow()][src.getColumn()] = new Piece("null","null");
									
									Boolean isCheckThere = detectedCheck(chessBoard);
									chessBoard.updateKingLocation(dst,src);
									chessBoard.getBoard()[src.getRow()][src.getColumn()] = srcPiece;
									chessBoard.getBoard()[dst.getRow()][dst.getColumn()] = dstPiece;
									
									if (!isCheckThere){
										System.out.println(src.getRow() + "," + src.getColumn());
										System.out.println(dst.getRow() + "," + dst.getColumn());
										return false;
									}
									
								}
							}
						}
						
					}
				}
			}
			
			return true;
			
		}
		
		return false;
	}
		
	public void movePiece(Location strt, Location dst){
    	
    	Piece srcPiece = chessBoard.getPiece(strt);
    	if (!chessBoard.getPiece(dst).getName().equals("null")){
    			
    	}
    	chessBoard.updateKingLocation(strt,dst);
	    ChessBoardUi.getSquares()[dst.getRow()][dst.getColumn()].setIcon(new ImageIcon("src/images/" + srcPiece.getColor() + "/" + srcPiece.getName() + ".png"));
	    chessBoard.getBoard()[dst.getRow()][dst.getColumn()] = srcPiece;
	    ChessBoardUi.getSquares()[strt.getRow()][strt.getColumn()].setIcon(null);
	    chessBoard.getBoard()[strt.getRow()][strt.getColumn()] = new Piece("null","null");
	    
    	
    }
	
	public static void storeLocation(Location strt, Location dst){
		Location[] move = new Location[2];
    	move[0]=strt;
    	move[1]=dst;
    	moves.add(move);
	}
	
	public static void storePieces(Piece srcPiece, Piece dstPiece){
		Piece[] pieceItem = new Piece[2];
		pieceItem[0] = srcPiece;
		pieceItem[1] = dstPiece;
		pieceItems.add(pieceItem);
	}
	
	public void notifyIfCheck(ChessBoard chessBoard){
		if (detectedCheck(chessBoard)){
			PieceDragAndDrop.isKingAtRisk = true;
			String kingColor = chessBoard.getPlayer();
			String s = kingColor.equals("White") ? "<html>Check for<br>White king</html>" : 
				"<html>Check for<br>Black king</html>";
			ChessBoardUi.setStatus(s);
			//System.out.println(kingColor + " king at Check");
		}
		else {
			PieceDragAndDrop.isKingAtRisk = false;
			ChessBoardUi.setStatus("");
		}
	}
	
	public void notifyIfCheckMate(ChessBoard chessBoard){
		if (detectedCheckMate(chessBoard)){
			String kingColor = chessBoard.getPlayer();
			//System.out.println("Checkmate for " + kingColor + " king." + " GAME OVER");
			String s = kingColor.equals("White") ? "<html>Checkmate for<br>White king.<br>GAME OVER!!!</html>" : 
				"<html>Checkmate for<br>Black king.<br>GAME OVER!!!</html>";
			PieceDragAndDrop.ifCheckMate = true;
			ChessBoardUi.setStatus(s);
		}
		else {
			PieceDragAndDrop.ifCheckMate = false;
		}
		
	}
	
	public void movePieceForUndo(Location[] location, Piece[] pieceItem){
		Location strtLoc = location[0];
		Location endLoc = location[1];
		Piece strtPiece = pieceItem[0];
		Piece endPiece = pieceItem[1];
		
		chessBoard.updateKingLocation(endLoc,strtLoc);
		ChessBoardUi.getSquares()[endLoc.getRow()][endLoc.getColumn()].setIcon(new ImageIcon("src/images/" + endPiece.getColor() + "/" + endPiece.getName() + ".png"));
	    chessBoard.getBoard()[endLoc.getRow()][endLoc.getColumn()] = endPiece;
	    ChessBoardUi.getSquares()[strtLoc.getRow()][strtLoc.getColumn()].setIcon(new ImageIcon("src/images/" + strtPiece.getColor() + "/" + strtPiece.getName() + ".png"));
	    chessBoard.getBoard()[strtLoc.getRow()][strtLoc.getColumn()] = strtPiece;
	    
	}
	
	public void undoOperation() {
		try{
			Location[] move = moves.pollLast();
			Piece[] pieceItem = pieceItems.pollLast();
			Piece strtPiece = pieceItem[0];
			movePieceForUndo(move,pieceItem);
			ChessBoard.player = strtPiece.getColor();
			
			notifyIfCheck(chessBoard);
			notifyIfCheckMate(chessBoard);
			ChessBoardUi.setImage(chessBoard.getPlayer());
		} catch(NullPointerException e){
			ChessBoardUi.setStatus("<html>Chess board at<br>initial state</html>");
			//System.out.println("Chess Board already at initial state");
		}
		
	}
	
	
}
