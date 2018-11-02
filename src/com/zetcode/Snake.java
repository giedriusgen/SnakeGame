package com.zetcode;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
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
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class Snake extends JFrame {

	ScoreLabel scoreLabel;
	Board board;
	TimeLabel timeLabel;
	Buttons buttons;
	Snake boardSnake;
	Snake enterName;
	Snake newSnake;
	Snake instructionsSnake;

	String name;
	Snake openingFrame;
	
	boolean gameWithBorders;

	private static final ImageIcon SNAKE_BACKGROUND = new ImageIcon("resources/snakeCover.png");

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
		board = new Board(buttons, timeLabel, scoreLabel, enterName, boardSnake, openingFrame);
		timeLabel = new TimeLabel(board, scoreLabel);
		buttons = new Buttons(board, timeLabel, boardSnake, openingFrame);

		JPanel boardFunctionality = new JPanel();
		boardFunctionality.add(board);

		board.getInputMap(IFW).put(KeyStroke.getKeyStroke("SPACE"), "pressed");

		board.getActionMap().put("pressed", new AbstractAction("menu") {
			public void actionPerformed(ActionEvent evt) {
				board.stopTimer();
				if (board.isInGame()) {
					boardSnake.add(buttons); // TODO padaryti kad per viduri mygtukai ir prigestu/uzjuoduotu
					buttons.gameButtons.setVisible(true);
				}

			}

		});

		scoreLabel.setBackground(Color.BLACK);
		timeLabel.setBackground(Color.BLACK);
		buttons.setBackground(Color.BLACK);
		boardFunctionality.setBackground(Color.BLACK);

		JPanel labels = new JPanel(new GridLayout(2, 2));
		labels.add(timeLabel);
		labels.add(scoreLabel);

		boardSnake.setSize(400, 430);
		boardSnake.add(BorderLayout.CENTER, boardFunctionality);
		boardSnake.add(BorderLayout.SOUTH, labels);

		// ex.add(BorderLayout.NORTH, buttons);

		boardSnake.setVisible(true);

	}

	public void showFirstFrames() {

		enterName = new Snake();
		JButton b = new JButton("Submit");
		b.setBounds(140, 100, 140, 40);

		JLabel label = new JLabel();
		label.setText("Enter Name :");
		label.setBounds(50, 10, 100, 100);

		JTextField textfield = new JTextField();
		textfield.setBounds(140, 50, 140, 30);

		enterName.add(textfield);
		enterName.add(label);
		enterName.add(b);
		enterName.setSize(400, 430);
		enterName.setLayout(null);
		enterName.setVisible(true);
		enterName.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		b.addActionListener(new ActionListener() { //prideti teksta: select game mode. gal viska nukelti i apacia

			@Override
			public void actionPerformed(ActionEvent arg0) {

				enterName.name = textfield.getText();
				enterName.dispose();
				
				JLabel gameMode = new JLabel("SELECT GAME MODE"); 
				gameMode.setFont(new Font("Dialog", Font.BOLD, 20));
				gameMode.setForeground(Color.white);

				openingFrame = new Snake();
				openingFrame.setLayout(new BorderLayout());
				JLabel background = new JLabel(SNAKE_BACKGROUND);
				openingFrame.add(background);
				background.setLayout(null);
				JButton withBorders = new JButton("Game with borders");
				JButton withoutBorders = new JButton("Game without borders");
				
				gameMode.setBounds(110, 240, 240, 40);
				withBorders.setBounds(120, 290, 185, 40);
				withoutBorders.setBounds(120, 340, 185, 40);
				
				
				background.add(withBorders);
				background.add(withoutBorders);
				background.add(gameMode);
				
				openingFrame.setSize(400, 430);
				openingFrame.setVisible(true);

				withBorders.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						openingFrame.gameWithBorders = true;		

						openingFrame.dispose();

						startSnakeGame();

					}

				});
				
				withoutBorders.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						gameWithBorders = false;

						openingFrame.dispose();

						startSnakeGame();

					}

				});

			}

		});
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

			Snake openingFrame = new Snake();
			openingFrame.setLayout(new BorderLayout());
			JLabel background = new JLabel(SNAKE_BACKGROUND);
			openingFrame.add(background);
			background.setLayout(new FlowLayout());
			JButton playButton = new JButton("Play");
			JButton instructionsButton = new JButton("Instructions");
			background.add(playButton);
			background.add(instructionsButton);
			openingFrame.setSize(400, 430);
			openingFrame.setVisible(true);
			playButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					openingFrame.dispose();
					newSnake = new Snake();
					newSnake.showFirstFrames();

				}

			});
			instructionsButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					openingFrame.dispose();

					instructionsSnake = new Snake();
					JLabel label = new JLabel();
					label.setText("<html>Don't run the snake into the wall, or his own tail: you die.\r\n<br/><br/>"
							+ "Use your cursor keys: up, left, right, and down.\r\n<br/><br/>"
							+ "Eat the green apples to gain points.\r\n<br/><br/>"
							+ "Keyboard \"SPACE\" may also be used for pause the game. <html>");
					label.setBounds(20, 20, 350, 400);

					JButton playButton2 = new JButton("Play");
					playButton2.setBounds(140, 20, 100, 50);
					playButton2.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent arg0) {
							instructionsSnake.dispose();
							newSnake = new Snake();
							newSnake.showFirstFrames();

						}

					});

					instructionsSnake.add(label);
					instructionsSnake.add(playButton2);
					instructionsSnake.setSize(400, 430);
					instructionsSnake.setLayout(null);
					instructionsSnake.setVisible(true);
					instructionsSnake.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				}

			});

		});

	}

	public static void main(String[] args) {
		Snake snake = new Snake();
		snake.startGame();

	}

}
