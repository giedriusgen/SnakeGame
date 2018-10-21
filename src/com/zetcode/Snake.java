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

public class Snake extends JFrame  {
 
	Board lenta;
	Labels labels;
	Buttons buttons;
	private JFrame ex;
	
	
	
	private static final int IFW = JComponent.WHEN_IN_FOCUSED_WINDOW; 

	public Snake() {
		initUI();
	}

	public Snake(Board lenta) {
		this.lenta = lenta;

		initUI();
	}
	
	
	
	


	public void startSnakeGame() {
		JFrame ex = new Snake(lenta);
		lenta = new Board(buttons, labels);

		JPanel gyvatele = new JPanel();
		gyvatele.add(lenta);
	 //	gyvatele.addKeyListener(listener); //
	 //	gyvatele.setFocusable(true); // problema neaktyvi lenta tada buna  pasidometi keybindings. tada focuso gali nereiketi
	
	 	

		buttons = new Buttons(lenta);
		labels = new Labels(lenta, buttons);
		
		
		
		labels.getInputMap(IFW).put(KeyStroke.getKeyStroke("SPACE"), "showMenu");
		labels.getActionMap().put("showMenu", new AbstractAction("menu") {
			public void actionPerformed( ActionEvent evt) {
				System.out.println("sfsaf" );
			//	ex.add(BorderLayout.NORTH, buttons);
				lenta.stopTimer();   //jeigu nuimu lenta.stopTImer, gaunu mygtukus, kitu atveju - ne
				
				
			}
		});
		
		
		

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
	
/*	KeyListener listener = new KeyListener() {

		@Override

		public void keyPressed(KeyEvent event) {

			char ch = event.getKeyChar();

			if (ch == 'a') {

				System.out.println(" test" + event.getKeyChar());

				
				}
			int keyCode = event.getKeyCode();
			if (keyCode == 32) {
				System.out.println("space " + event.getKeyCode());
			}}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
			};
			*/
			

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

