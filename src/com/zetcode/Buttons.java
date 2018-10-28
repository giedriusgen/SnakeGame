package com.zetcode;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.sun.prism.paint.Color;

public class Buttons extends JPanel {

	Board board;
	TimeLabel timeLabel;
	Snake boardSnake;

	JPanel gameButtons;

	Buttons(Board board, TimeLabel timeLabel, Snake boardSnake) {
		this.board = board;
		this.timeLabel = timeLabel;
		this.boardSnake = boardSnake;

		JButton b = new JButton("Continue game");
		b.setFocusable(false);
		JButton b2 = new JButton("Restart game");
		b2.setFocusable(false);
		JButton b3 = new JButton("View results");
		b3.setFocusable(false);

		gameButtons = new JPanel(new GridLayout(1, 3));
		gameButtons.add(b);
		gameButtons.add(b2);
		gameButtons.add(b3);

		b2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				boardSnake.dispose();

				Snake snake = new Snake();
				snake.startGame();

			}

		});

		b.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// uzbluokuoti klaviatura taip pat
				board.startTimer();
				timeLabel.clockTimer.start();
				gameButtons.setVisible(false);
			}

		});

		add(gameButtons);
	}

}
