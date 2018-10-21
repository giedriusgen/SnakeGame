package com.zetcode;

import java.awt.GridLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.sun.prism.paint.Color;

public class Buttons extends JPanel {

	Board lenta;
	Labels labels;

	private boolean isPaused = false; 
	


	Buttons(Board lenta) {
		this.lenta = lenta;

		JButton b = new JButton("Pause");
		b.setFocusable(false);
		JButton b2 = new JButton("Þaisti");
		b2.setFocusable(false);
		JButton b3 = new JButton("Statistika");
		b3.setFocusable(false);

		JPanel mygtukai = new JPanel(new GridLayout(1, 3));
		mygtukai.add(b);
		mygtukai.add(b2);
		mygtukai.add(b3);

		b2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(lenta.taskai);
			

			}

		}); 

		b.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// uzbluokuoti klaviatura taip pat 
				if (isPaused == false) {
					lenta.stopTimer();
					b.setText("Continue");
					isPaused = true;
					
					
				} else if (isPaused == true) {
					lenta.startTimer();
					b.setText("Pause");
					isPaused = false;
				}

			}

		});

		add(mygtukai);
	}

	public boolean isPaused() {
		return isPaused;
	}

	public void setPaused(boolean isPaused) {
		this.isPaused = isPaused;
	}
}
