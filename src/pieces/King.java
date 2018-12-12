package pieces;
import pieces.*;
import board.*;

public class King extends Piece{
	
	public King(String color){
		super("King",color);
	}
	
	
	public Boolean isValidMove(ChessBoard chessBoard, Location src, Location dst){
		if (Math.abs(src.getRow()-dst.getRow())<=1 && Math.abs(src.getColumn()-dst.getColumn()) <=1 && (chessBoard.isDiffColor(src, dst))){
			return true;
		}
		return false;
		
	}
}