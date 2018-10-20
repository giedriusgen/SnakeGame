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

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.Timer;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class Snake extends JFrame {

	static Board lenta;
	private JFrame ex;

	public Snake() {
		initUI();
	}

	public Snake(Board lenta) {
		this.lenta = lenta;

		initUI();
	}

	public void startSnakeGame() {
		JFrame ex = new Snake(lenta);
		lenta = new Board();

		JPanel gyvatele = new JPanel();
		gyvatele.add(lenta);

		Buttons buttons = new Buttons(lenta);
		Labels labels = new Labels(lenta);

		labels.setBackground(Color.BLACK);
		buttons.setBackground(Color.BLACK);
		gyvatele.setBackground(Color.BLACK);

		ex.setSize(400, 430);

		ex.add(BorderLayout.SOUTH, labels);
		ex.add(BorderLayout.CENTER, gyvatele);
		ex.add(BorderLayout.NORTH, buttons);

		ex.setVisible(true);

	}

	private void initUI() {

		// add(new Board()); //cia buvo pirminiame kode tas

		setResizable(false);
		pack();

		setTitle("Snake");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public static void main(String[] args) {

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
					pradziosLangas.dispose(); // sunaikinu objekta
					Snake newSnake = new Snake();
					newSnake.startSnakeGame();
				}
				

			});

			

		});
	}
}
