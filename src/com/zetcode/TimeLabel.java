package com.zetcode;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class TimeLabel extends JPanel {

	Board board;
	ScoreLabel scoreLabel;

	Timer clockTimer;

	private int count;

	TimeLabel(Board board, ScoreLabel scoreLabel) {
		this.board = board;
		this.scoreLabel = scoreLabel;

		JLabel time = new JLabel("...");
		time.setForeground(Color.WHITE);
		clockTimer = new Timer(1000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (board.isInGame()) {
					count++;
					time.setText("Time: " + Integer.toString(count));

					scoreLabel.timeToDB = getCount();

				}
				if (board.isCountTime() == true) {
					clockTimer.stop();

				}

			}
		});
		clockTimer.setInitialDelay(0);
		clockTimer.start();
		add(time);

	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
