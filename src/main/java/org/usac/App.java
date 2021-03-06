package org.usac;

import java.lang.reflect.ParameterizedType;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SuppressWarnings("WeakerAccess")
public class App {

	private static Logger logger = Logger.getLogger(App.class.getName());

	static void parallelScatterGatherWithFutureTask() throws Exception {
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		LinkedList<FutureTask<String>> futureTasks = new LinkedList<>();
		for (int i = 0; i < 10; i++) {
			int finalI = i;
			FutureTask<String> task = new FutureTask<>(() -> generateTask(finalI));
			futureTasks.add(task);
			executorService.submit(task);
		}
		for (FutureTask<String> futureTask : futureTasks) {
			logger.info(futureTask.get());
		}
	}

	static void parallelScatterGather() throws Exception {
		List<String> list =
					IntStream.range(0, 10).parallel().boxed()
								.map(App::generateTask)
								.collect(Collectors.toList());
		logger.info(list.toString());
	}
	private static String generateTask(int i) {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		return i + "-" + "test";
	}

	static <T extends Collection<String>, K extends App> void genericsMakeClassesRedundant(T arg){
		arg.add("asf");
		K.binary(3);
	}

	static int fibonacci(int fibNumber) {
		Deque<CallContext> callStack = new LinkedList<>();
		callStack.add(new CallContext(new Object[]{fibNumber}));
		Object lastReturn = null; //value of last object returned (when stack frame was dropped)
		while (!callStack.isEmpty()) {
			CallContext callContext = callStack.peekLast();
			Object[] args = callContext.args;
			//actual logic starts here
			int arg = (int) args[0];
			if (arg == 0 || arg == 1) {
				lastReturn = arg;
				callStack.removeLast();
			} else {
				switch (callContext.resumePoint) {
					case 0: //calculate fib(n-1)
						callStack.add(new CallContext(new Object[]{arg - 1}));
						callContext.resumePoint++;
						break;
					case 1: //calculate fib(n-2)
						callContext.vars.add(lastReturn); //fib1
						callStack.add(new CallContext(new Object[]{arg - 2}));
						callContext.resumePoint++;
						break;
					case 2: // fib(n-1) + fib(n-2)
						callContext.vars.add(lastReturn); //fib2
						lastReturn = (int) callContext.vars.get(0) + (int) callContext.vars.get(1);
						callStack.removeLast();
						break;
				}
			}
		}
		return (int) lastReturn;
	}

	/**
	 * a,b,c,d
	 * b,c,d,a
	 * d,c,b,a
	 *
	 * @param letters a,b,c,d
	 */
	static void permutate(String... letters) {
	}

	static <T> T getInstanceOfT(List<T> o) throws IllegalAccessException, InstantiationException {
		LinkedList s = (LinkedList) o;
		ParameterizedType genericSuperclass = (ParameterizedType) o.getClass().getGenericSuperclass();
		Class<T> type = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
		return type.newInstance();
	}

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
		currentExpression = currentExpression + digits.get(offset++);
		if (offset == digits.size()) { //no more elements remaining
			evalExpression(currentExpression);
		} else {
			traverseCombination(digits, offset, currentExpression + "+");
			traverseCombination(digits, offset, currentExpression + "-");
			traverseCombination(digits, offset, currentExpression + "");
		}
	}

	private static void traverseCombination(ArrayList<String> digits, int offset, int currentSum, int lastNumber) {
		int newSum = Integer.parseInt(currentSum + digits.get(offset++));
		if (offset == digits.size()) { //no more elements remaining
			if (newSum == 100) {
				System.out.println("found");
			}
		} else {
			traverseCombination(digits, offset, newSum, lastNumber);
			//			traverseCombination(digits, offset, currentExpression + "-");
			//			traverseCombination(digits, offset, currentExpression + "");
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

	static class CallContext {

		Object[] args;

		List<Object> vars = new LinkedList<>();

		Object $return;

		int resumePoint = 0;

		public CallContext(Object[] args) {
			this.args = args;
		}

	}
}
