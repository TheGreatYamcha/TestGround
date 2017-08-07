package org.usac;

import java.util.*;

/**
 * Hello world
 */
public class App {

	 /**
	  * Write a program that outputs all possibilities to put + or - or nothing between the numbers 1,2,…,9 (in this order) such that the result is 100.
	  * For example 1 + 2 + 3 - 4 + 5 + 6 + 78 + 9 = 100.
	  */
	 static void printWeirdCombinations() {
		  ArrayList<String> digits = new ArrayList<>();
		  for (int i = 1; i <= 4; i++) {
				digits.add(String.valueOf(i));
		  }
		  traverseCombination(digits, 0, "");
	 }

	 private static void traverseCombination(ArrayList<String> digits, int offset, String currentExpression) {
		  if (offset == digits.size() - 2) { //one more element remaining
				// can't do offset++?
				evalExpression(currentExpression + digits.get(offset) + "+" + digits.get(offset+1));
				evalExpression(currentExpression + digits.get(offset) + "-" + digits.get(offset+1));
				evalExpression(currentExpression + digits.get(offset) + "" + digits.get(offset+1));
		  } else {
				traverseCombination(digits, offset + 1, currentExpression + digits.get(offset) + "+");
				traverseCombination(digits, offset + 1, currentExpression + digits.get(offset) + "-");
				traverseCombination(digits, offset + 1, currentExpression + digits.get(offset) + "");
		  }
	 }

	 private static void evalExpression(String currentExpression) {
		  System.out.println(currentExpression);
	 }

	 /**
	  * Write a function that combines two lists by alternatingly taking elements, e.g. [a,b,c], [1,2,3] → [a,1,b,2,c,3].
	  */
	 static <T> List<T> alternateCombine(List<T> l1, List<T> l2) {
		  for (ListIterator<T> iterator = l1.listIterator(), iterator2 = l2.listIterator(); iterator.hasNext(); ) {
				iterator.next();
				iterator.add(iterator2.next());
		  }
		  return l1;
	 }

	 /**
	  * Write a function that rotates a list by k elements. For example [1,2,3,4,5,6] rotated by two becomes [3,4,5,6,1,2]
	  */
	 static <T> List<T> rotateList(List<T> list, int k) {
		  //		for (int i = 0; i < k; i++) {
		  //			T leftMostElement = list.remove(0);
		  //			list.add(leftMostElement);
		  //		}
		  while (--k >= 0) {
				ListIterator<T> listIterator = list.listIterator();
				T next = listIterator.next();
				listIterator.remove();
				list.listIterator(list.size()).add(next); //!!! listIterator is from 0 to list.size()
		  }
		  return list;
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
				if (word.length() > maxLength) {
					 maxLength = word.length();
				}
		  }
		  for (String word : words) {
				String line = "* " + word;
				while (line.length() < maxLength + 2) {
					 line += " ";
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
		  for (String line : lines) {
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
