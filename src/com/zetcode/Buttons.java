package com.zetcode;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Buttons extends JPanel {

	Board board;
	TimeLabel timeLabel;
	Snake boardSnake;
	Snake openingFrame;

	JPanel gameButtons;

	Buttons(Board board, TimeLabel timeLabel, Snake boardSnake, Snake openingFrame) {
		this.board = board;
		this.timeLabel = timeLabel;
		this.boardSnake = boardSnake;
		this.openingFrame = openingFrame;

		JButton buttonContinue = new JButton("Continue game");
		buttonContinue.setFocusable(false);
		JButton buttonRestart = new JButton("Restart game");
		buttonRestart.setFocusable(false);
		JButton buttonResults = new JButton("View results");
		buttonResults.setFocusable(false);

		gameButtons = new JPanel(new GridLayout(1, 3));
		gameButtons.add(buttonContinue);
		gameButtons.add(buttonRestart);
		gameButtons.add(buttonResults);

		buttonContinue.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				board.startTimer();
				timeLabel.clockTimer.start();
				gameButtons.setVisible(false);

			}
		});

		buttonRestart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				boardSnake.dispose();

				Snake snake = new Snake();
				snake.startGame();

			}
		});

		buttonResults.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Database d = new Database(board, boardSnake, openingFrame);
				d.showResults();

			}
		});

		add(gameButtons);
	}
}
