package gui;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import board.*;
import management.PieceDragAndDrop;

public class ChessBoardUi {
	
	private static ChessBoardUi chessBoardUi = null;
	
	private final JPanel gui = new JPanel(new BorderLayout(3,3));
	private static JButton[][] squares = new JButton[8][8];
	private JPanel chessPanel;
	JPanel extra = new JPanel(new GridLayout(8,1));
	
	JLabel nextPlayer = new JLabel("Next Player",JLabel.CENTER);
	JLabel blankLabel = new JLabel("");
	static JLabel statusLabel = new JLabel("",JLabel.CENTER);
	static JLabel imageLabel = new JLabel("",JLabel.CENTER);
	
	private static final String cols = "ABCDEFGH";
	public static int squareDimension = 80;
	ChessBoard chessBoard = ChessBoard.getInstance();

	ChessBoardUi() {
		prepareGUI();
	}
	
	public JComponent getGui(){
		return gui;
	}
	
	public static JButton[][] getSquares(){
		return squares;
	}
	
	public static ChessBoardUi getInstance(){
		if (chessBoardUi == null){
			chessBoardUi = new ChessBoardUi();
		}
		return chessBoardUi;
	}
	
	public static void setStatus(String s){
		statusLabel.setText(s);
	}
	
	public static void setImage(String s){
		if (s.equals("White")){
			ImageIcon image = new ImageIcon("src/images/" + "White/" + "Pawn.png");
			imageLabel.setIcon(image);
		}
		else {
			ImageIcon image = new ImageIcon("src/images/" + "Black/" + "Pawn.png");
			imageLabel.setIcon(image);
		}
	}
	
	public static void restart(){
		chessBoardUi = new ChessBoardUi();
	}
	
	public void addImages(){
		for (int i : chessBoard.pieceRows){
			for (int j=0; j < 8; j++){
				if (i==0){
					squares[i][j].setIcon(new ImageIcon("src/images/White/" + chessBoard.pieces[j] + ".png"));
				}
				else {
					squares[i][j].setIcon(new ImageIcon("src/images/Black/" + chessBoard.pieces[j] + ".png"));
				}
			}
		}
		
		for (int i : chessBoard.pawnRows){
			for (int j=0; j<8; j++){
				if (i==1) {
					squares[i][j].setIcon(new ImageIcon("src/images/" + "White/" + "Pawn.png"));
				}	
				else {
					squares[i][j].setIcon(new ImageIcon("src/images/" + "Black/" + "Pawn.png"));
				}
			}
		}
		
	}
	
	public final void addRow(){
		chessPanel.add(new JLabel(""));
		for (int i = 0; i < 8; i++) {
        	JLabel l = new JLabel(cols.substring(i, i + 1),SwingConstants.CENTER);
            chessPanel.add(l);
        }
		chessPanel.add(new JLabel(""));
	}
	
	
	public final void prepareGUI(){
		
		gui.setBorder(new EmptyBorder(5,5,5,5));
		gui.setBackground(Color.BLACK);
		
		extra.setPreferredSize(new Dimension(200,200));
		
		nextPlayer.setForeground(Color.CYAN);
		nextPlayer.setFont(new Font("Monotype Corsiva",Font.ITALIC,25));
		statusLabel.setForeground(Color.RED);
		statusLabel.setFont(new Font("Arial", Font.BOLD, 25));
		
		extra.setBackground(Color.BLACK);
		extra.add(nextPlayer);
		extra.add(imageLabel);
		extra.add(blankLabel);
		extra.add(blankLabel);
		extra.add(blankLabel);
		extra.add(statusLabel);
		
		chessPanel = new JPanel(new GridLayout(0,10));
		chessPanel.setBorder(new LineBorder(Color.BLACK));
		gui.add(chessPanel);
		
		Insets squareMargins = new Insets(0,0,0,0);
		for (int i=0; i< squares.length; i++){
			for (int j=0; j< squares[i].length; j++){
				JButton b = new JButton();
				b.setMargin(squareMargins);
				b.setPreferredSize(new Dimension(squareDimension,squareDimension));
				if ((i%2 == 0 && j%2 ==0) || (i%2 ==1 && j%2==1)){
					b.setBackground(Color.WHITE);
				}
				else {
					b.setBackground(Color.BLACK);
				}
				b.setOpaque(true);
				b.setBorderPainted(false);
				
				b.putClientProperty("location", chessBoard.getLocation(i, j));
				b.addMouseMotionListener(new PieceDragAndDrop());
				b.addMouseListener(new PieceDragAndDrop());
				squares[i][j]=b;
			}
		}
		
		this.addImages();
		
		this.addRow();
		for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                switch (j) {
                    case 0:
                        chessPanel.add(new JLabel("" + (i + 1),
                                SwingConstants.CENTER));
                    default:
                        chessPanel.add(squares[i][j]);
                }
            }
            chessPanel.add(new JLabel("" + (i+1),SwingConstants.CENTER));
        }
		this.addRow();
		
		gui.add(extra,BorderLayout.LINE_END);
	}

    	
}
