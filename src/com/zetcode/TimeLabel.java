package com.zetcode;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.sun.javafx.event.EventQueue;

public class TimeLabel extends JPanel {

	Board board;
	JLabel time;
	Timer clockTimer;

	private int count;

	TimeLabel(Board board) {
		this.board = board;

		time = new JLabel("...");
		time.setForeground(Color.WHITE);
		clockTimer = new Timer(1000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (board.isInGame()) {
					count++;
					time.setText("Time: " + Integer.toString(count));
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

}
