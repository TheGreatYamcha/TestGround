package org.usac;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Hello world
 */
public class App {

	public static void main(String[] args) {
		System.out.println("Hello World!");
	}

	static String binary(int a) {
		StringBuilder stringBuilder = new StringBuilder("");
		while (a >= 2) {
			int remainder = a % 2;
			stringBuilder.insert(0, remainder);
			a = a >> 1;
		}
		stringBuilder.insert(0, a);
		return stringBuilder.toString();
	}

	static List<String> removeInForloop() {
		//		List<String> nums = Arrays.asList("1", "2", "3");
		List<String> nums = new LinkedList<>();
		nums.add("1");
		nums.add("2");
		nums.add("3");
		//		for (String num : nums) {
		//			nums.remove(num);
		//		}
		for (Iterator<String> iterator = nums.iterator(); iterator.hasNext(); ) {
			String next = iterator.next();
//			nums.remove(next);
			iterator.remove(); //iterator.remove only safe way to remove
		}
		//ListIterator<E> listIterator(int index)
		//Returns a list iterator over the elements in this list (in proper sequence),
		// starting at the specified position in the list. The specified index indicates the first element that would be returned by an initial call to next.
		// An initial call to previous would return the element with the specified index minus one.
		return nums;
	}

	static boolean armstrong(int num) {
		int cubeSum = 0;
		for (int digit : digitize(num)) {
			cubeSum += (int) Math.pow(digit, 3);
		}
		return cubeSum == num;
	}

	/**
	 * a function that takes a list of strings an prints them, one per line, in a rectangular frame.
	 * For example the list ["Hello", "World", "in", "a", "frame"] gets printed as:
	 * <p>
	 * *********
	 * * Hello *
	 * * World *
	 * * in    *
	 * * a     *
	 * * frame *
	 * *********
	 */
	static void printSquare(List<String> words) {
		List<String> lines = new LinkedList<>();
		int maxLength = words.stream().mapToInt(String::length).max().getAsInt();
		for (String word : words) {
			if (word.length() > maxLength) maxLength = word.length();
		}
		for (String word : words) {
			String line = "* " + word;
			while (line.length() < maxLength + 2) {
				line +=" ";
			}
			line += " *";
			lines.add(line);
		}
		StringBuilder header = new StringBuilder();
		for (int i = 0; i < lines.get(0).length(); i++) {
			header.append("*");
		}
		lines.add(0, header.toString());
		lines.add(header.toString());
		for (String line :
				lines) {
			System.out.println(line);
		}
	}

	private static List<Integer> digitize(int num) {
		List<Integer> result = new LinkedList<>();
		do {
			result.add(num % 10);
			num = num / 10;
		} while (num > 0);
		return result;
	}
}
