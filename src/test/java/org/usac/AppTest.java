package org.usac;

import org.junit.Test;

import static org.usac.App.binary;

/**
 * Unit test for simple App.
 */
public class AppTest {

	@Test
	public void testBinary() {
		System.out.println(binary(0));
		System.out.println(binary(1));
		System.out.println(binary(4));
		System.out.println(binary(5));
		System.out.println(binary(7));
		Integer.toBinaryString(1);
	}

}
