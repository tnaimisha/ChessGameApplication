package pieces;
import pieces.*;
import board.*;
import java.util.*;

public class Knight extends Piece{
	
	public Knight(String color){
		super("Knight",color);
	}
	
	
	public Boolean isValidMove(ChessBoard chessBoard, Location src, Location dst){
		int r1= src.getRow(); int r2 = dst.getRow();
		int c1= src.getColumn(); int c2= dst.getColumn();
		int[][] offsets = new int[][]{{1,2},{1,-2},{-1,2},{-1,-2},{2,1},{2,-1},{-2,1},{-2,-1}};
		if (chessBoard.isDiffColor(src, dst)){
			int[] offset = new int[]{r2-r1,c2-c1};
			for (int i=0; i<offsets.length; i++){
				if (Arrays.equals(offsets[i],offset)){
					return true;
				}
			}
		}
		return false;
	}
}