package com.zetcode;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Database {

	Connection connection;
	Statement statement;

	Board board;
	Snake boardSnake;
	Snake openingFrame;
	ResultSet rs;
	
	int action;

	public Database(Board board, Snake boardSnake, Snake openingFrame) {
		this.board = board;
		this.boardSnake = boardSnake;
		this.openingFrame = openingFrame;
	}

	public void insertValues() throws SQLException {

		try {

			connection = DriverManager.getConnection("jdbc:sqlite:snakeGameResults.db");
			Statement statement = connection.createStatement(); // naudoti prepared statement, jeinori placeholderius

			statement.setQueryTimeout(30);

			// statement.executeUpdate("drop table if exists results");
			// statement.executeUpdate("create table results (name string, score integer,
			// time integer)");

			if (openingFrame.gameWithBorders == true) {
				statement.executeUpdate("insert into test values('" + board.enterName.name + "', " + board.points
						+ " , " + board.scoreLabel.timeToDB + " )");
			}

			if (openingFrame.gameWithBorders == false) {
				statement.executeUpdate("insert into results values('" + board.enterName.name + "', " + board.points
						+ " , " + board.scoreLabel.timeToDB + " )");
			}

		} catch (SQLException e) {

			System.err.println(e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				System.err.println(e);
			}
		}
	}

	public void showResults() { 
								
		try {

			connection = DriverManager.getConnection("jdbc:sqlite:snakeGameResults.db");
			statement = connection.createStatement(); // naudoti prepared statement, jeinori placeholderius naudoti
			statement.setQueryTimeout(30);

			if (openingFrame.gameWithBorders == true) {

				rs = statement.executeQuery("select * from test order by score DESC, time DESC limit 20");
			}

			if (openingFrame.gameWithBorders == false) {

				rs = statement.executeQuery("select * from results order by score DESC, time DESC limit 20");
			}

			JTable table = new JTable(buildTableModel(rs));
			
			JScrollPane scrollPane = new JScrollPane(table);
			scrollPane.setPreferredSize(new Dimension(400, 343));
			
			
			if (openingFrame.gameWithBorders == true) {
				 
				JOptionPane.showMessageDialog(boardSnake, scrollPane, "RESULTS OF GAME WITH BORDERS", JOptionPane.PLAIN_MESSAGE);
			}
			
			if (openingFrame.gameWithBorders == false) {
				 
				JOptionPane.showMessageDialog(boardSnake, scrollPane, "RESULTS OF GAME WITHOUT BORDERS", JOptionPane.PLAIN_MESSAGE);
			}
			

		} catch (SQLException e1) {

			System.err.println(e1.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e1) {
				System.err.println(e1);
			}
		}
	}

	

	public void showFinalResults() {
		try {

			connection = DriverManager.getConnection("jdbc:sqlite:snakeGameResults.db");
			statement = connection.createStatement(); // naudoti prepared statement, jeinori placeholderius naudoti
			statement.setQueryTimeout(30);

			if (openingFrame.gameWithBorders == true) {

				rs = statement.executeQuery("select * from test order by score DESC, time DESC limit 20");
			}

			if (openingFrame.gameWithBorders == false) {

				rs = statement.executeQuery("select * from results order by score DESC, time DESC limit 20");
			}


			JTable table = new JTable(buildTableModel(rs));
			JScrollPane scrollPane = new JScrollPane(table);
			scrollPane.setPreferredSize(new Dimension(400, 343));

			JPanel panel = new JPanel();
			JLabel label = new JLabel();
			label.setText("Would you like to play new game?"); // zaidejo vieta nurodyti
			panel.add(label);

			JPanel panel2 = new JPanel(new BorderLayout());
			panel2.add(BorderLayout.CENTER, scrollPane);
			panel2.add(BorderLayout.SOUTH, panel);
			
			if (openingFrame.gameWithBorders == true) {
				 action = JOptionPane.showOptionDialog(boardSnake, panel2, "RESULTS OF GAME WITH BORDERS", JOptionPane.YES_NO_OPTION,
							JOptionPane.PLAIN_MESSAGE, null, null, table);
				
			}
			
			if (openingFrame.gameWithBorders == false) {
				 action = JOptionPane.showOptionDialog(boardSnake, panel2, "RESULTS OF GAME WITHOUT BORDERS", JOptionPane.YES_NO_OPTION,
							JOptionPane.PLAIN_MESSAGE, null, null, table);
				
			}

	
			if (action == JOptionPane.YES_OPTION) {
				boardSnake.dispose();
				Snake snake = new Snake();
				snake.startGame();
			}

		} catch (SQLException e1) {

			System.err.println(e1.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e1) {
				System.err.println(e1);
			}
		}
	}
	
	

	public DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {

		ResultSetMetaData metaData = rs.getMetaData();

		Vector<String> columnNames = new Vector<String>();
		int columnCount = metaData.getColumnCount();
		for (int column = 1; column <= columnCount; column++) {
			columnNames.add(metaData.getColumnName(column));
		}

		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		while (rs.next()) {
			Vector<Object> vector = new Vector<Object>();
			for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
				vector.add(rs.getObject(columnIndex));
			}
			data.add(vector);
		}

		return new DefaultTableModel(data, columnNames);

	}

}
