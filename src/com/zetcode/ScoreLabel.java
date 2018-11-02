package com.zetcode;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class ScoreLabel extends JPanel {

	Board board;

	JLabel score;

	public int timeToDB; // getting time from TimeLabel and adding to the DB in Board

	ScoreLabel(Board board) {
		this.board = board;

		score = new JLabel();
		score.setText("Score: 0");
		score.setForeground(Color.WHITE);

		add(score);
		
		

	}
	
	/*public void up () {
		score.setText("Score: " + Integer.toString(board.points));
	} // pabandyti atskira metoda sukurti sitoje klaseje
*/
}
