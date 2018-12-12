package management;

import javax.swing.*;
import gui.ChessBoardUi;
import board.ChessBoard;
import board.Location;
import pieces.Piece;

import java.awt.*;
import java.awt.event.*;

public class PieceDragAndDrop implements MouseListener, MouseMotionListener{
	
	private static int ex,ey;
	private static Location startL;
	private static Location endL;
	public static Boolean isKingAtRisk=false;
	public static Boolean ifCheckMate=false;
	ChessBoard chessBoard = ChessBoard.getInstance();
	MoveControls moveControls = new MoveControls();
	
	public void mouseDragged(MouseEvent e) {
		Point point = e.getPoint();
		ex=point.x;
		ey=point.y;
		
      }
	
	public void mouseMoved(MouseEvent e) {
		//nothing
      }
	
	public void mousePressed(MouseEvent e) {
		JButton startSq = (JButton)e.getSource();
		startL = (Location)startSq.getClientProperty("location");
		
    }
	
	
	public void mouseReleased(MouseEvent e){
		
		int endx,endy;
    	if (ex > 0){
    		endy = startL.getColumn()+ex/ChessBoardUi.squareDimension;
    	}
    	else {
    		endy = startL.getColumn()-1+ex/ChessBoardUi.squareDimension;
    	}
    	if (ey > 0){
    		endx = startL.getRow()+ey/ChessBoardUi.squareDimension;
    	}
    	else {
    		endx = startL.getRow()-1+ey/ChessBoardUi.squareDimension;
    	}
    	endL = new Location(endx, endy);
    	
    	Piece srcPiece = chessBoard.getPiece(startL);
    	Piece dstPiece = chessBoard.getPiece(endL);
    	
		if (isKingAtRisk){
			
			if (chessBoard.isValidPlayerMove(startL, endL)){
				chessBoard.updateKingLocation(startL,endL);
				chessBoard.getBoard()[endL.getRow()][endL.getColumn()] = srcPiece;
				chessBoard.getBoard()[startL.getRow()][startL.getColumn()] = new Piece("null","null");
				
				
				if (MoveControls.detectedCheck(chessBoard)){
					//System.out.println("The move is not accepted as your king is at risk");
					ChessBoardUi.setStatus("<html>Not allowed<br>King still at risk</html>");
					
					//Brings back king to the original position
					chessBoard.updateKingLocation(endL,startL);
					chessBoard.getBoard()[startL.getRow()][startL.getColumn()] = srcPiece;
					chessBoard.getBoard()[endL.getRow()][endL.getColumn()] = dstPiece;
					
				}
				else {
					isKingAtRisk = false;
					ChessBoardUi.getSquares()[endL.getRow()][endL.getColumn()].setIcon(new ImageIcon("src/images/" + srcPiece.getColor() + "/" + srcPiece.getName() + ".png"));
					ChessBoardUi.getSquares()[startL.getRow()][startL.getColumn()].setIcon(null);
					chessBoard.setNextPlayer(srcPiece);
					MoveControls.storeLocation(startL, endL);
					MoveControls.storePieces(srcPiece, dstPiece);
					moveControls.notifyIfCheck(chessBoard);
					moveControls.notifyIfCheckMate(chessBoard);
					ChessBoardUi.setImage(chessBoard.getPlayer());
				}
			}
			
		}
		else {
			if (chessBoard.isValidPlayerMove(startL, endL)){
				chessBoard.setNextPlayer(srcPiece);
				moveControls.movePiece(startL, endL);
				MoveControls.storeLocation(startL, endL);
				MoveControls.storePieces(srcPiece, dstPiece);
				moveControls.notifyIfCheck(chessBoard);
				moveControls.notifyIfCheckMate(chessBoard);
				ChessBoardUi.setImage(chessBoard.getPlayer());
			}
		}
		
	}
	
	public void mouseClicked(MouseEvent e) {
		
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // nothing
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // nothing
    }
    
    
}

