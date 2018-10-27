package com.zetcode;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.Timer;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class Snake extends JFrame {

	Board board;
	TimeLabel timeLabel;
	Buttons buttons;
	JPanel boardFunctionality;
	Snake boardSnake;

	static Snake newSnake;

	JPanel labels;

	ScoreLabel scoreLabel;

	private static final int IFW = JComponent.WHEN_IN_FOCUSED_WINDOW;

	public Snake() {
		initUI();
	}

	public Snake(Board board) {
		this.board = board;

		initUI();
	}

	public void startSnakeGame() {

		boardSnake = new Snake();

		scoreLabel = new ScoreLabel(board);

		board = new Board(buttons, timeLabel, scoreLabel);
		timeLabel = new TimeLabel(board);
		buttons = new Buttons(board, timeLabel, boardSnake);

		boardFunctionality = new JPanel();
		boardFunctionality.add(board);

		board.getInputMap(IFW).put(KeyStroke.getKeyStroke("ENTER"), "pressed");
		

		board.getActionMap().put("pressed", new AbstractAction("menu") {
			public void actionPerformed(ActionEvent evt) {
				board.stopTimer();
				boardSnake.add(BorderLayout.NORTH, buttons);
				buttons.mygtukai.setVisible(true);

			}

		});

		scoreLabel.setBackground(Color.BLACK);
		timeLabel.setBackground(Color.BLACK);
		buttons.setBackground(Color.BLACK);
		boardFunctionality.setBackground(Color.BLACK);

		labels = new JPanel(new GridLayout(2, 2));
		labels.add(timeLabel);
		labels.add(scoreLabel);

		boardSnake.setSize(400, 430);
		boardSnake.add(BorderLayout.CENTER, boardFunctionality);
		boardSnake.add(BorderLayout.SOUTH, labels);

		// ex.add(BorderLayout.NORTH, buttons);

		boardSnake.setVisible(true);

	}

	private void initUI() {

		setResizable(false);
		pack();

		setTitle("Snake");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public void startGame() {

		EventQueue.invokeLater(() -> {

			Snake pradziosLangas = new Snake();
			pradziosLangas.setLayout(new BorderLayout());
			JLabel background = new JLabel(new ImageIcon("src/resources/snakeCover.png"));
			pradziosLangas.add(background);
			background.setLayout(new FlowLayout());
			JButton zaidimoMygtukas = new JButton("Þaisti");
			background.add(zaidimoMygtukas);
			pradziosLangas.setSize(400, 430);
			pradziosLangas.setVisible(true);
			zaidimoMygtukas.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					pradziosLangas.dispose();
					Snake newSnake = new Snake();
					newSnake.startSnakeGame();

				}

			});

		});

	}

	public static void main(String[] args) {
		Snake snake = new Snake();
		snake.startGame();

	}

}
