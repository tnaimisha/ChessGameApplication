package gui;

import javax.swing.*;

import board.ChessBoard;
import management.MoveControls;
import management.PieceDragAndDrop;

import java.awt.*;

public class GameLauncher{
	
	private static ChessBoardUi board; 
	private static JComponent gui;
	private static JComponent toolBar;
	private static JFrame frame;
	private static GameLauncher gameLauncher = null;
	
	public static void main(String[] args){
		
		Runnable r = new Runnable(){
			public void run(){
				
				GameLauncher.getGameState();
				
			}
		};
		SwingUtilities.invokeLater(r);
		
	}
	
	public GameLauncher(){
		
		board = ChessBoardUi.getInstance();
		gui = board.getGui();
		GameControls controls = new GameControls();
		toolBar = controls.getToolBar();
		ChessBoardUi.setImage("White");
		frame = new JFrame();
		frame.add(gui);
		frame.add(toolBar,BorderLayout.PAGE_START);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLocationByPlatform(true);
		frame.setMinimumSize(frame.getSize());
		frame.setVisible(true);
		
	}
	
	public static GameLauncher getGameState(){
		if (gameLauncher==null){
			gameLauncher = new GameLauncher();
		}
		return gameLauncher;
	}
	
	public static void restart(){

		PieceDragAndDrop.ifCheckMate=false;
		PieceDragAndDrop.isKingAtRisk = false;
		
		frame.remove(gui);
		frame.remove(toolBar);
		
		ChessBoard.restart();
		ChessBoardUi.restart();
		MoveControls.restart();
		
		GameControls gameControls = new GameControls();
		toolBar = gameControls.getToolBar();
		board = ChessBoardUi.getInstance();
		gui = board.getGui();
		ChessBoardUi.setStatus("");
		ChessBoardUi.setImage("White");
		
		frame.add(gui);
		frame.add(toolBar,BorderLayout.PAGE_START);
		frame.revalidate();
		frame.repaint();
		
	}
	
}
