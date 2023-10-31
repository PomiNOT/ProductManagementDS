package ui;

import java.util.ArrayList;

public class TableView {
	private class Column {
		String header;
		int size;

		public Column(String header, int size) {
			this.header = header;
			this.size = size;
		}
	}

	private Column[] columns;
	private ArrayList<String[]> rows;

	public TableView(String[] headers) {
		rows = new ArrayList<>();
		columns = new Column[headers.length];

		for (int i = 0; i < headers.length; i++) {
			columns[i] = new Column(headers[i], 10);
		}

		addRow(headers);
	}

	public void addRow(String[] rowItems) {
		if (rowItems.length > columns.length) {
			throw new IllegalArgumentException("rowItems length is greater than columns length");
		}

		for (int i = 0; i < rowItems.length; i++) {
			columns[i].size = Math.max(rowItems[i].length(), columns[i].size);
		}

		rows.add(rowItems);
	}

	public void display() {
		// build %s %s ... format string
		StringBuilder printfFormat = new StringBuilder();
		for (Column column : columns) {
			printfFormat.append("%-").append(column.size).append("s ");
		}
		String formatString = printfFormat.toString().trim() + "\n";

		for (String[] row : rows) {
			System.out.printf(formatString, (Object[]) row);
		}
	}
}
