package pieces;
import pieces.*;
import board.*;

public class Bishop extends Piece{
	
	public Bishop(String color){
		super("Bishop",color);
	}
	
	
	public Boolean isValidMoveBishop(ChessBoard chessBoard, Location src, Location dst){
		int r1= src.getRow();
		int c1 = src.getColumn();
		int r2 = dst.getRow();
		int c2 = dst.getColumn();
		if ((Math.abs(r1-r2) == Math.abs(c1-c2)) && (chessBoard.isDiffColor(src, dst))){
			//45 degrees movement from low to high
			if (r2> r1 && c2 > c1){
				int i = r1+1; int j = c1+1;
				while((i<r2 && j<c2) && chessBoard.getBoard()[i][j].getName().equals("null")){
					i = i+1; j=j+1;
				}
				if (i== r2 && j==c2){
					return true;
				}
			}
			//135 degrees movement form low to high
			if (r2> r1 && c2 < c1){
				int i = r1+1; int j = c1-1;
				while((i<r2 && j>c2) && chessBoard.getBoard()[i][j].getName().equals("null")){
					i = i+1; j=j-1;
				}
				if (i== r2 && j==c2){
					return true;
				}
					
			}
			//135 degrees from high to low
			if (r2< r1 && c2 > c1){
				int i = r1-1; int j = c1+1;
				while((i>r2 && j<c2) && chessBoard.getBoard()[i][j].getName().equals("null")){
					i = i-1; j=j+1;
				}
				if (i== r2 && j==c2){
					return true;
				}
			}
			//45 degrees high to low
			if (r2< r1 && c2 < c1){
				int i = r1-1; int j = c1-1;
				while((i>r2 && j>c2) && chessBoard.getBoard()[i][j].getName().equals("null")){
					i = i-1; j=j-1;
				}
				if (i== r2 && j==c2){
					return true;
				}
				
			}
			
		}

		return false;
		
	}
}