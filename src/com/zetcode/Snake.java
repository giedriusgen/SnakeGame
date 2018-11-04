package com.zetcode;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

public class Snake extends JFrame {

	private ScoreLabel scoreLabel;
	private Board board;
	private TimeLabel timeLabel;
	private Buttons buttons;
	private Snake newSnake;
	private Snake boardSnake;
	private Snake enterNameFrame;
	private Snake instructionsFrame;
	private Snake gameModeFrame;

	String playerName;

	boolean gameWithBorders;

	private static final ImageIcon SNAKE_BACKGROUND = new ImageIcon("resources/snakeCover.png");
	private static final int IFW = JComponent.WHEN_IN_FOCUSED_WINDOW;

	public Snake() {
		initUI();
	}

	private void initUI() {

		setResizable(false);
		pack();

		setTitle("Snake");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public void startSnakeGame() {

		boardSnake = new Snake();

		scoreLabel = new ScoreLabel(board);
		board = new Board(buttons, timeLabel, scoreLabel, enterNameFrame, boardSnake, gameModeFrame);
		timeLabel = new TimeLabel(board, scoreLabel);
		buttons = new Buttons(board, timeLabel, boardSnake, gameModeFrame);

		JPanel boardFunctionality = new JPanel();
		boardFunctionality.add(board);

		board.getInputMap(IFW).put(KeyStroke.getKeyStroke("SPACE"), "pressed");

		board.getActionMap().put("pressed", new AbstractAction("menu") {
			public void actionPerformed(ActionEvent evt) {
				board.stopTimer();
				if (board.isInGame()) {
					boardSnake.add(BorderLayout.SOUTH, buttons);
					buttons.gameButtons.setVisible(true);
				}

			}

		});

		scoreLabel.setBackground(Color.BLACK);
		timeLabel.setBackground(Color.BLACK);
		buttons.setBackground(Color.BLACK);
		boardFunctionality.setBackground(Color.BLACK);

		JPanel addLabels = new JPanel(new GridLayout(2, 2));
		addLabels.add(timeLabel);
		addLabels.add(scoreLabel);

		boardSnake.setSize(400, 430);
		boardSnake.add(BorderLayout.CENTER, boardFunctionality);
		boardSnake.add(BorderLayout.NORTH, addLabels);
		boardSnake.setVisible(true);

	}

	public void startGame() {

		EventQueue.invokeLater(() -> {

			Snake openingFrame = new Snake();
			JLabel background = new JLabel(SNAKE_BACKGROUND);
			openingFrame.add(background);
			background.setLayout(null);
			JButton playButton = new JButton("Play");
			JButton instructionsButton = new JButton("Instructions");

			playButton.setBounds(120, 290, 185, 40);
			instructionsButton.setBounds(120, 340, 185, 40);

			background.add(playButton);
			background.add(instructionsButton);

			openingFrame.setSize(400, 430);
			openingFrame.setVisible(true);

			playButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					openingFrame.dispose();
					newSnake = new Snake();
					newSnake.addEnterNameFrame();

				}
			});
			instructionsButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					openingFrame.dispose();
					addInstructionsFrame();

				}
			});
		});
	}

	public void addEnterNameFrame() {

		enterNameFrame = new Snake();
		JButton submitButton = new JButton("Submit");
		submitButton.setBounds(140, 100, 140, 40);

		JLabel nameLabel = new JLabel();
		nameLabel.setText("Enter Name :");
		nameLabel.setBounds(50, 10, 100, 100);

		JTextField nameTextfield = new JTextField();
		nameTextfield.setBounds(140, 50, 140, 30);

		enterNameFrame.add(nameTextfield);
		enterNameFrame.add(nameLabel);
		enterNameFrame.add(submitButton);
		enterNameFrame.setSize(400, 430);
		enterNameFrame.setLayout(null);
		enterNameFrame.setVisible(true);
		enterNameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		submitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				enterNameFrame.playerName = nameTextfield.getText();
				enterNameFrame.dispose();
				addGameModeFrame();
			}
		});
	}

	public void addGameModeFrame() {

		JLabel gameMode = new JLabel("SELECT GAME MODE");
		gameMode.setFont(new Font("Dialog", Font.BOLD, 20));
		gameMode.setForeground(Color.white);

		gameModeFrame = new Snake();
		JLabel background = new JLabel(SNAKE_BACKGROUND);
		gameModeFrame.add(background);
		background.setLayout(null);
		JButton buttonWithBorders = new JButton("Game with borders");
		JButton buttonWithoutBorders = new JButton("Game without borders");

		gameMode.setBounds(110, 240, 240, 40);
		buttonWithBorders.setBounds(120, 290, 185, 40);
		buttonWithoutBorders.setBounds(120, 340, 185, 40);

		background.add(buttonWithBorders);
		background.add(buttonWithoutBorders);
		background.add(gameMode);

		gameModeFrame.setSize(400, 430);
		gameModeFrame.setVisible(true);

		buttonWithBorders.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				gameModeFrame.gameWithBorders = true;
				gameModeFrame.dispose();

				startSnakeGame();
			}
		});

		buttonWithoutBorders.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				gameWithBorders = false;
				gameModeFrame.dispose();

				startSnakeGame();

			}
		});
	}

	public void addInstructionsFrame() {
		instructionsFrame = new Snake();
		JLabel instructionsLabel = new JLabel();
		instructionsLabel.setText("<html> You need to select game mode. \r\n<br/><br/> "
				+ "Use your cursor keys: up, left, right, and down.\r\n<br/><br/>"
				+ "Eat the green apples to gain points.\r\n<br/><br/>"
				+ "Keyboard \"SPACE\" may also be used for pause the game.\r\n<br/><br/>  <html>");
		instructionsLabel.setBounds(20, 20, 350, 400);

		JButton playButton2 = new JButton("Play");
		playButton2.setBounds(140, 20, 100, 50);
		playButton2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				instructionsFrame.dispose();
				newSnake = new Snake();
				newSnake.addEnterNameFrame();

			}
		});

		instructionsFrame.add(instructionsLabel);
		instructionsFrame.add(playButton2);
		instructionsFrame.setSize(400, 430);
		instructionsFrame.setLayout(null);
		instructionsFrame.setVisible(true);
		instructionsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public static void main(String[] args) {
		Snake snake = new Snake();
		snake.startGame();

	}

}
