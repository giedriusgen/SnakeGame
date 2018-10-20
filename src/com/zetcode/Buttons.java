package com.zetcode;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.sun.prism.paint.Color;

public class Buttons extends JPanel {

	Board lenta;

	Buttons(Board lenta) {
		this.lenta = lenta;

		JButton b = new JButton("Þaisti");
		b.setFocusable(false);
		JButton b2 = new JButton("Pauzë");
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

		}); // mygtuko funkcionalumas. taskus gerai skaiciuoja
		
		b.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				//TODO
				
				
			
				
			}
			
		});

		add(mygtukai);
	}
}
