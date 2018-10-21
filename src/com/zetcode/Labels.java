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

public class Labels extends JPanel  {

	Board lenta;
	Timer clockTimer;
	Buttons buttons; 
	
	
	
	
	private int count;
	static public JLabel obuoliuSkaicius; // padaryti ne static
	
	public void stopClockTimer() {
		clockTimer.stop();
	}


	Labels(Board lenta, Buttons buttons) {
		
		
		this.lenta = lenta;
		
		

		obuoliuSkaicius = new JLabel();// nepavyksta tasku padaryti
		obuoliuSkaicius.setText("Score: " + Integer.toString(lenta.taskai));
		obuoliuSkaicius.setForeground(Color.WHITE);

		JLabel time = new JLabel("...");
		time.setForeground(Color.WHITE);

		
		clockTimer = new Timer(1000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (lenta.isInGame() && !(lenta.isCountTime() == true) ) {
					count++;

					time.setText("Time: " + Integer.toString(count));
				} else {
					time.setText("Time: " + count);
					// ((Timer) (e.getSource())).stop();
				}
			}
		});
		clockTimer.setInitialDelay(0);
		clockTimer.start();

		JPanel informacija = new JPanel(new GridLayout(2, 2));

		informacija.add(obuoliuSkaicius);
		informacija.add(time);
		informacija.setBackground(Color.BLACK);

		add(informacija);

	}


}
