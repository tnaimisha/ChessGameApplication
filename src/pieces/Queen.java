package pieces;
import pieces.*;
import board.*;

public class Queen extends Piece{
	
	public Queen(String color){
		super("Queen",color);
	}
	
	
	public Boolean isValidMove(ChessBoard chessBoard, Location src, Location dst){
		int r1=src.getRow(); 
		int c1= src.getRow(); 
		String color = chessBoard.getBoard()[r1][c1].getColor();
		Rook r = new Rook(color);
		Bishop b = new Bishop(color);
		if (r.isValidMoveRook(chessBoard, src, dst) || b.isValidMoveBishop(chessBoard, src, dst))
			return true;
		return false;
		
	}
}