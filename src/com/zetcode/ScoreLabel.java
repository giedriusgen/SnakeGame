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

	ScoreLabel(Board board) {
		this.board = board;

		score = new JLabel();
		score.setText("Score: 0");
		score.setForeground(Color.WHITE);
		
		add(score);


	}

}
