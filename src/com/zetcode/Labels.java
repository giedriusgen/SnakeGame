package com.zetcode;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Labels extends JPanel {

	Board lenta;
	private int count;

	Labels(Board lenta) {
		this.lenta = lenta;

		JLabel obuoliuSkaicius = new JLabel();// nepavyksta tasku padaryti
		obuoliuSkaicius.setText("Parodyti obuoliu skaiciu: " + Integer.toString(lenta.taskai));
		obuoliuSkaicius.setForeground(Color.WHITE);

		JLabel laikas = new JLabel("...");
		laikas.setForeground(Color.WHITE);

		Timer timer;
		timer = new Timer(1000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (lenta.isInGame()) {
					count++;

					// if (count < 100000) {
					laikas.setText("Laikas: " + Integer.toString(count));
				} else {
					laikas.setText("Laikas: " + count);
					// ((Timer) (e.getSource())).stop();
				}
			}
		});
		timer.setInitialDelay(0);
		timer.start();

		JPanel informacija = new JPanel(new GridLayout(2, 2));

		informacija.add(obuoliuSkaicius);
		informacija.add(laikas);
		informacija.setBackground(Color.BLACK);

		add(informacija);

	}

}
