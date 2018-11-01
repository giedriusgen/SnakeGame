package com.zetcode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Database { 

	Connection connection;
	Statement statement;

	Board board;

	public Database(Board board) {
		this.board = board;
	}

	public void insertValues() throws SQLException {

		try {

			connection = DriverManager.getConnection("jdbc:sqlite:snakeGameResults.db");
			Statement statement = connection.createStatement(); // naudoti prepared statement, jeinori placeholderius
																
			statement.setQueryTimeout(30);

			// statement.executeUpdate("drop table if exists results");
			// statement.executeUpdate("create table results (name string, score integer,
			// time integer)");
			statement.executeUpdate("insert into test values('" + board.enterName.name + "', " + board.points + " , "
					+ board.scoreLabel.timeToDB + " )");

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

			ResultSet rs = statement.executeQuery("select * from test order by score DESC, time DESC limit 15");

			JTable table = new JTable(buildTableModel(rs));

			JOptionPane.showMessageDialog(null, new JScrollPane(table));

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
