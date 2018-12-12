package board;
import java.util.*;
import board.*;
import pieces.Piece;

public class Location{
	// Class describing location of a piece on board
	
	private int row;
	private int column;
	
	/*
	 * Constructs a location based on row and column values
	 * @param row
	 * @param column
	 */
	public Location(int row, int column){
		this.row = row;
		this.column = column;
	}
	
	/*
	 * Construct a new location based on the source Location
	 * @param source location 
	 */
	public Location(Location source){
		this.row = source.row;
		this.column = source.column;
	}
	
	public void changeLocation(Location source){
		this.row = source.row;
		this.column = source.column;
	}
	
	public int getRow(){
		return row;
	}
	public int getColumn(){
		return column;
	}
	
	public Boolean isSameRow(Location dst){
		if (this.row == dst.getRow())
			return true;
		return false;
	}
	
	public Boolean isSameColumn(Location dst){
		if (this.column == dst.getColumn())
			return true;
		return false;
	}
		
	
}


