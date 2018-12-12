package pieces;
import pieces.*;
import board.*;

public class Pawn extends Piece{
	
	public Pawn(String color){
		super("Pawn",color);
	}
	
	/*
	 * This function is based on the assumption that initially whites are positioned in 0,1 rows and blacks in 6,7 rows.
	 * For white pawns, dst row must be greater than src row.
	 * For black pawns, dst row must be less than src row.
	 */
	public Boolean isValidMove(ChessBoard chessBoard, Location src, Location dst){
		int r1= src.getRow(); int r2 = dst.getRow();
		int c1= src.getColumn(); int c2= dst.getColumn();
		String color = chessBoard.getBoard()[r1][c1].getColor();
		//white pawn movement
		if (r1==1 && color.equals("White")){
			if (c1==c2 && chessBoard.getBoard()[r2][c2].getName().equals("null")){
				if (r2-r1 == 1 || (r2-r1==2 && chessBoard.getBoard()[r1+1][c1].getName().equals("null"))){
					return true;
				}
			}
			else if (r2-r1== 1 && Math.abs(c2-c1)==1 && chessBoard.getBoard()[r2][c2].getColor().equals("Black")){
				return true;
			}
		}
		//black pawn movement
		if (r1==6 && color.equals("Black")){
			if (c1==c2 && chessBoard.getBoard()[r2][c2].getName().equals("null")){
				if (r1-r2 == 1 || (r1-r2==2 && chessBoard.getBoard()[r1-1][c1].getName().equals("null"))){
					return true;
				}
			}
			else if (r1-r2==1 && Math.abs(c2-c1)==1 && chessBoard.getBoard()[r2][c2].getColor().equals("White")){
				return true;
			}	
		}
		else {
			if (color.equals("White")){
				if (r2-r1==1 && Math.abs(c2-c1)==1 && c2!=c1 && chessBoard.getBoard()[r2][c2].getColor().equals("Black")){
					return true;
				}
				else if (r2-r1 ==1 && c2==c1 && chessBoard.getBoard()[r2][c2].getColor().equals("null")){
					return true;
				}
			}
			if (color.equals("Black")){
				if (r1-r2==1 && Math.abs(c2-c1)==1 && c2!=c1 && chessBoard.getBoard()[r2][c2].getColor().equals("White")){
					return true;
				}
				else if (r1-r2 ==1 && c2==c1 && chessBoard.getBoard()[r2][c2].getColor().equals("null")){
					return true;
				}
			}
			
		}
		
		return false;
		
	}
}