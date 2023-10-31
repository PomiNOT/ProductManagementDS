package io;

import bo.ProductManager;
import entities.Product;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class ProductFile {
	File file;

	public ProductFile(String filename) {
		file = new File(filename);
	}

	public int readFromFile(ProductManager manager) throws Exception {
		Scanner sc = new Scanner(file);

		int count = 0;
		while (sc.hasNext()) {
			String[] tokens = sc.nextLine().split(",");
			int id = Integer.parseInt(tokens[0]);
			String name = tokens[1].trim();
			int quantity = Integer.parseInt(tokens[2]);
			int price = Integer.parseInt(tokens[3]);

			manager.add(new Product(id, name, quantity, price));
			count++;
		}

		sc.close();
		return count;
	}

	public void writeToFile(ProductManager manager) throws Exception {
		FileWriter writer = new FileWriter(file);
		BufferedWriter pen = new BufferedWriter(writer);

		for (Product product : manager.getAll()) {
			System.out.println(product.toString());
			pen.write(product.toString());
			pen.newLine();
		}

		pen.close();
	}
}
