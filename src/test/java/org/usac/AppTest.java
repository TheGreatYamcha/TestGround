package org.usac;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.usac.App.*;

/**
 * Unit test for simple App.
 */
public class AppTest {

	@Test
	public void testParallelScatterGatherFutureTask() throws Exception{
		parallelScatterGatherWithFutureTask();
	}

	@Test
	public void testParallelScatterGather() throws Exception{
		parallelScatterGather();
	}


	@Test
	public void testStringIntern() throws Exception {
		Assert.assertTrue("asdf" == "asd" + "f");
		String f = "f";
		Assert.assertTrue("asdf" != "asd" + f && Objects.equals("asdf", "asd" + f));
		Assert.assertFalse("asdf" == "asd" + new String("f"));
		Assert.assertTrue("asdf" == ("asd" + new String("f")).intern());
	}

	@Test
	public void testGenericsMakeClassesRedundant() throws Exception {
		genericsMakeClassesRedundant(new ArrayList<>(Arrays.asList("asdf")));
	}

	@Test
	public void testEmulateRecursion() throws Exception {
		assertThat(fibonacci(1), is(1));
		assertThat(fibonacci(2), is(1));
		assertThat(fibonacci(10), is(55));
	}

	@Test
	public void testPrintWeirdCombinations() throws Exception {
		printWeirdCombinations();
	}

	@Test
	public void testAlternateCombine() throws Exception {
		assertThat(alternateCombine(newArrayList("1", "2", "3"), newArrayList("a", "b", "c")),
					is(asList("1", "a", "2", "b", "3", "c")));
	}

	@Test
	public void testRotateList() throws Exception {
		assertThat(rotateList(new ArrayList<>(asList("1", "2", "3", "4", "5", "6")), 2),
					is(asList("3", "4", "5", "6", "1", "2")));
	}

	@Test
	public void testPrintSquare() throws Exception {
		printSquare(asList("Hello", "World", "in", "a", "frame"));
	}

	@Test
	public void testClassFromGeneric() throws Exception {
		getInstanceOfT(new LinkedList<String>() {
		});
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
