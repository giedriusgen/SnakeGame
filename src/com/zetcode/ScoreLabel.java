package com.zetcode;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ScoreLabel extends JPanel {

	Board board;

	private JLabel score;

	public int timeToDB; // getting time (count) from TimeLabel and adding to Database

	ScoreLabel(Board board) {
		this.board = board;

		score = new JLabel();
		score.setText("Score: 0");
		score.setForeground(Color.WHITE);

		add(score);

	}

	public void updateScoreLabel(Board board) {
		score.setText("Score: " + Integer.toString(board.getPoints()));
	}

}
