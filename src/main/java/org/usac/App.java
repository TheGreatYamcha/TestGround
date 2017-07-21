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
}
