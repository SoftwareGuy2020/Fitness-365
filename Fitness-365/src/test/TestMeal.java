/**
 * Class to test Meal.java
 */
package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import model.Meal;

public class TestMeal {
	private static Meal meal1;
	private static Meal meal2;

	/**
	 * Creates meals
	 * 
	 * @throws Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		meal1 = new Meal("name1", 1, 100, 10, 30, 20, "Beef products");

		meal2 = new Meal("name2", 3, 200, 30, 20, 10, "Fruits");
	}

	/**
	 * 
	 * @throws Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * 
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Tests protein
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetProtein() {
		double protein1 = meal1.getProtein();
		double protein2 = meal2.getProtein();

		assertTrue("Testing protein.", protein1 == 20);
		assertTrue("Testing protein.", protein2 == 10);
	}

	/**
	 * Tests carbs
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetCarbs() {
		double carbs1 = meal1.getCarbs();
		double carbs2 = meal2.getCarbs();

		assertTrue("Testing carbs.", carbs1 == 30);
		assertTrue("Testing carbs.", carbs2 == 20);
	}

	/**
	 * Tests name
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetName() {
		String name1 = meal1.getName();
		String name2 = meal2.getName();

		assertTrue("Testing name.", name1.equalsIgnoreCase("Name1"));
		assertTrue("Testing name.", name2.equalsIgnoreCase("Name2"));
	}

}
