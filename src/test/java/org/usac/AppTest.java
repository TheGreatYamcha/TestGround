package org.usac;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest{

	@Test
	public void test() {
		binary(1234);

	}

	void binary(int a) {
		StringBuilder stringBuilder = new StringBuilder("");
		while (a >= 0) {
			int remainder = a % 2;
			stringBuilder.insert(0, remainder);
			a = a >> 1;
		}
		stringBuilder.insert(0, a);
		System.out.println(stringBuilder.toString());
	}
}
