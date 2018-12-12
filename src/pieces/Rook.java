package pieces;
import pieces.*;

import board.*;

public class Rook extends Piece{
	
	public Rook(String color){
		super("Rook",color);
	}
	
	
	public Boolean isValidMoveRook(ChessBoard chessBoard, Location src, Location dst){
		if ((src.isSameRow(dst) || src.isSameColumn(dst)) && (chessBoard.isDiffColor(src, dst))){
			if (src.isSameRow(dst)){
				if (src.getColumn() < dst.getColumn()){
					int j = src.getColumn()+1;
					while (chessBoard.getBoard()[src.getRow()][j].getName().equals("null") && j<dst.getColumn()){
						j = j+1;
					}
					if (j==dst.getColumn())
						return true;
				}
				else {
					int j = src.getColumn()-1;
					while (chessBoard.getBoard()[src.getRow()][j].getName().equals("null") && j>dst.getColumn()){
						j = j-1;
					}
					if (j==dst.getColumn()){
						return true;
					}
				}
			}
			if (src.isSameColumn(dst)){
				if (src.getRow() < dst.getRow()){
					int i = src.getRow()+1;
					while (chessBoard.getBoard()[i][src.getColumn()].getName().equals("null") && i<dst.getRow()){
						i =i+1;
					}
					if (i==dst.getRow()){
						return true;
					}
				}
				else {
					int j = src.getRow()-1;
					while (chessBoard.getBoard()[j][src.getColumn()].getName().equals("null") && j>dst.getRow()){
						j = j-1;
					}
					if (j==dst.getRow()){
						return true;
					}
				}				
			}
		}
		
		return false;
		
		
	}
}