package org.usac;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

import static org.usac.App.*;

/**
 * Unit test for simple App.
 */
public class AppTest {
	@Test
	public void testPrintSquare() throws Exception {
		printSquare(Arrays.asList("Hello", "World", "in", "a", "frame"));
	}

	@Test
	public void testArmstrong() throws Exception {
		Assert.assertTrue(armstrong(0));
		Assert.assertTrue(armstrong(1));
		Assert.assertFalse(armstrong(10));
		Assert.assertTrue(armstrong(371));
	}

	@Test
	public void testRemoveInForloop() throws Exception {
		Assert.assertTrue(removeInForloop().isEmpty());
	}

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
