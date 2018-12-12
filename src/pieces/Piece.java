package pieces;

public class Piece {
	
	private String name="null";
	private String color="null";
	
	public Piece(String name, String color){
		this.name = name;
		this.color = color;
	}
	
	public String getColor(){
		return color;
	}
	
	public String getName(){
		return name;
	}
	
	
}

