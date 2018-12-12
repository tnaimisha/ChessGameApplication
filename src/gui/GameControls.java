package gui;

import javax.swing.*;
import java.awt.event.*;
import management.*;

public class GameControls {
	
	private JToolBar bar = new JToolBar();
	MoveControls moveControls = new MoveControls();
	
	public GameControls(){
		setToolBar();
	}
	
	public void setToolBar(){
		bar.setFloatable(false);		
		JButton Restart = new JButton("Restart");
		Restart.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				GameLauncher.restart();
			}
		});
		JButton Save = new JButton("Save");
		Save.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
			}
		});
		JButton Restore = new JButton("Restore");
		Restore.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
			}
		});
		
		JButton Undo = new JButton("Undo");
		Undo.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				moveControls.undoOperation();
			}
		});
		
		bar.add(Restart);
		bar.add(Save);
		bar.add(Restore);
		bar.add(Undo);
				
	}

	public JComponent getToolBar(){
		return this.bar;
	}
	
	
}