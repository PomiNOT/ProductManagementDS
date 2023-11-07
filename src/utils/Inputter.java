package utils;

import java.util.Scanner;

public class Inputter {
	private static Scanner sc = new Scanner(System.in);

	public static interface StringSetter {
		void set(String value);
	}

	public static interface IntSetter {
		void set(int value);
	}

	public static int getInt(String prompt, IntSetter setter) {
		while (true) {
			System.out.print(prompt);
			try {
				String rawInput = sc.nextLine();
				int value = Integer.parseInt(rawInput);
				setter.set(value);
				return value;
			} catch (NumberFormatException e) {
				System.out.println("ERROR: Input must be an integer");
			} catch (IllegalArgumentException e) {
				System.out.printf("ERROR: %s\n", e.getMessage());
			}
		}
	}

	public static int getInt(String prompt) {
		return getInt(prompt, (int _un) -> {});
	}

	public static String getString(String prompt, StringSetter setter) {
		while (true) {
			System.out.print(prompt);
			try {
				String rawInput = sc.nextLine().trim();
				setter.set(rawInput);
				return rawInput;
			} catch (IllegalArgumentException e) {
				System.out.printf("ERROR: %s\n", e.getMessage());
			}
		}
	}

	public static String getString(String prompt) {
		return getString(prompt, (_unused) -> {});
	}

	public static boolean getBoolean(String prompt) {
		while (true) {
			System.out.print(prompt);
			String input = sc.nextLine().trim().toLowerCase();

			switch (input) {
				case "y":
					return true;
				case "n":
					return false;
				default:
					System.out.println("ERROR: Enter y (yes), n (no)");
			}
		}
	}
}
