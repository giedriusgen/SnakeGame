package com.zetcode;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class Board extends JPanel implements ActionListener {

	private final int B_WIDTH = 300;
	private final int B_HEIGHT = 300;
	private final int DOT_SIZE = 10;
	private final int ALL_DOTS = 900;
	private final int RAND_POS = 29;
	private final int DELAY = 140;

	private final int x[] = new int[ALL_DOTS];
	private final int y[] = new int[ALL_DOTS];

	private int dots;
	public int points; // buvo private

	private int apple_x;
	private int apple_y;

	private boolean leftDirection = false;
	private boolean rightDirection = true;
	private boolean upDirection = false;
	private boolean downDirection = false;
	private boolean inGame = true;
	private boolean countTime = false;

	private static final ImageIcon IID = new ImageIcon("resources/dot.png");
	private static final ImageIcon IIA = new ImageIcon("resources/apple.png");
	private static final ImageIcon IIH = new ImageIcon("resources/head.png");

	private Timer timer;
	private Image ball;
	private Image apple;
	private Image head;

	Buttons buttons;
	TimeLabel timeLabel;
	ScoreLabel scoreLabel;

	Snake enterName;

	JLabel score;
	JLabel time;
	Timer clockTimer;
	Snake boardSnake;
	Board board;
	Snake openingFrame;

	ResultSet rs;

	public Board(Buttons buttons, TimeLabel timeLabel, ScoreLabel scoreLabel, Snake enterName, Snake boardSnake,
			Snake openingFrame) {
		initBoard();
		this.buttons = buttons;
		this.timeLabel = timeLabel;
		this.scoreLabel = scoreLabel;
		this.enterName = enterName;
		this.boardSnake = boardSnake;
		this.openingFrame = openingFrame;
	}

	private void initBoard() {

		addKeyListener(new TAdapter());
		setBackground(Color.BLACK);
		setFocusable(true);
		setDoubleBuffered(true);

		setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
		setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLUE)); // pridejau rema
		loadImages();
		initGame();

	}

	private void loadImages() {

		// ImageIcon iid = new ImageIcon("resources/dot.png");
		ball = IID.getImage();

//		ImageIcon iia = new ImageIcon("resources/apple.png");
		apple = IIA.getImage();

		// ImageIcon iih = new ImageIcon("resources/head.png");
		head = IIH.getImage();
	}

	public void startTimer() {
		timer.start();
		countTime = false;

	}

	public void stopTimer() {

		timer.stop();
		countTime = true;

	}

	private void initGame() {

		dots = 3;

		for (int z = 0; z < dots; z++) {
			x[z] = 50 - z * 10;
			y[z] = 50;
		}

		locateApple();

		timer = new Timer(DELAY, this);

		timer.start();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		doDrawing(g);
	}

	private void doDrawing(Graphics g) {

		if (inGame) {

			g.drawImage(apple, apple_x, apple_y, this);

			for (int z = 0; z < dots; z++) {
				if (z == 0) {
					g.drawImage(head, x[z], y[z], this);
				} else {
					g.drawImage(ball, x[z], y[z], this);
				}
			}

			Toolkit.getDefaultToolkit().sync();

		} else {

			gameOver(g);
		}
	}

	private void gameOver(Graphics g) {

		String msg = "Game Over";
		Font small = new Font("Helvetica", Font.BOLD, 14);
		FontMetrics metr = getFontMetrics(small);

		g.setColor(Color.white);
		g.setFont(small);
		g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2, B_HEIGHT / 2);
	}

	public void updateScoreView() {

		scoreLabel.score.setText("Score: " + Integer.toString(points));

	}

	private void checkApple() {

		if ((x[0] == apple_x) && (y[0] == apple_y)) {

			dots++;

			points++;

			locateApple();

			updateScoreView();

		}
	}

	private void move() {

		for (int z = dots; z > 0; z--) {
			x[z] = x[(z - 1)];
			y[z] = y[(z - 1)];
		}

		if (leftDirection) {
			x[0] -= DOT_SIZE;
		}

		if (rightDirection) {
			x[0] += DOT_SIZE;
		}

		if (upDirection) {
			y[0] -= DOT_SIZE;
		}

		if (downDirection) {
			y[0] += DOT_SIZE;
		}
	}

	private void checkCollision() {

		for (int z = dots; z > 0; z--) {    //padaryti kad jeigu susiduria ne tik su galva

			if ((z > 4) && (x[0] == x[z]) && (y[0] == y[z])) {
				inGame = false;
			}
		}

		if (y[0] >= B_HEIGHT) {

			if (openingFrame.gameWithBorders == false) {
				y[0] = 0;
			} else if (openingFrame.gameWithBorders == true) {
				inGame = false;
			}

		}

		if (y[0] < 0) {

			if (openingFrame.gameWithBorders == false) {
				y[0] = B_HEIGHT;
			} else if (openingFrame.gameWithBorders == true) {
				inGame = false;
			}

		}

		if (x[0] >= B_WIDTH) {

			if (openingFrame.gameWithBorders == false) {
				x[0] = 0;
			} else if (openingFrame.gameWithBorders == true) {
				inGame = false;
			}

		}

		if (x[0] < 0) {

			if (openingFrame.gameWithBorders == false) {
				x[0] = B_WIDTH;
			} else if (openingFrame.gameWithBorders == true) {
				inGame = false;
			}

		}

		if (!inGame) {
			timer.stop();

			Database d = new Database(this, boardSnake, openingFrame);

			try {
				d.insertValues();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			d.showFinalResults();

		}

	}

	private void locateApple() {

		int r = (int) (Math.random() * RAND_POS);
		apple_x = ((r * DOT_SIZE));

		r = (int) (Math.random() * RAND_POS);
		apple_y = ((r * DOT_SIZE));
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (inGame) {

			checkApple();
			checkCollision();
			move();

		}

		repaint();

	}

	private class TAdapter extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {

			int key = e.getKeyCode();

			if ((key == KeyEvent.VK_LEFT) && (!rightDirection)) {
				leftDirection = true;
				upDirection = false;
				downDirection = false;
			}

			if ((key == KeyEvent.VK_RIGHT) && (!leftDirection)) {
				rightDirection = true;
				upDirection = false;
				downDirection = false;
			}

			if ((key == KeyEvent.VK_UP) && (!downDirection)) {
				upDirection = true;
				rightDirection = false;
				leftDirection = false;
			}

			if ((key == KeyEvent.VK_DOWN) && (!upDirection)) {
				downDirection = true;
				rightDirection = false;
				leftDirection = false;
			}
		}
	}

	public boolean isInGame() {
		return inGame;
	}

	public void setInGame(boolean inGame) {
		this.inGame = inGame;
	}

	public boolean isCountTime() {
		return countTime;
	}

	public void setCountTime(boolean countTime) {
		this.countTime = countTime;
	}

}
