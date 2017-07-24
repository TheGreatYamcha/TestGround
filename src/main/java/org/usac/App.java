package org.usac;

/**
 * Hello world
 *
 */
public class App
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
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

	static boolean armstrong(int num) {
    	int cubeSum = 0;
		int digit = num % 10;
		cubeSum = (int) Math.pow(digit, 3);
		return cubeSum == num;
		// take a digit from the left
		// cube it
		// add it to result
		// repeat until no more digits left
		// return if result == num
	}
}
