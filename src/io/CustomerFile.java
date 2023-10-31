package io;

import bo.CustomerManager;
import entities.Customer;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class CustomerFile {
	File file;

	public CustomerFile(String filename) {
		file = new File(filename);
	}

	public int readFromFile(CustomerManager manager) throws Exception {
		Scanner sc = new Scanner(file);

		int count = 0;
		while (sc.hasNext()) {
			String[] tokens = sc.nextLine().split(",");
			int id = Integer.parseInt(tokens[0]);
			String name = tokens[1].trim();
			String phone = tokens[2].trim();

			manager.add(new Customer(id, name, phone));
			count++;
		}

		sc.close();
		return count;
	}

	public void writeToFile(CustomerManager manager) throws Exception {
		FileWriter writer = new FileWriter(file);
		BufferedWriter pen = new BufferedWriter(writer);

		for (Customer product : manager.getAll()) {
			System.out.println(product.toString());
			pen.write(product.toString());
			pen.newLine();
		}

		pen.close();
	}

}
