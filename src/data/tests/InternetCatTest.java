package data.tests;

/**
 * Project imports
 */
import data.model.InternetCat;
import java.util.ArrayList;
import java.util.Arrays;
import java.time.LocalDateTime;

/**
 * Reflection imports
 */
import java.lang.reflect.*;
/**
 * Testing imports
 */
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InternetCatTest
{
	private InternetCat testedCat;

	@BeforeEach
	public void setUp() throws Exception
	{
		this.testedCat = new InternetCat(null, "",0,"","","");
	}

	@AfterEach
	public void tearDown() throws Exception
	{
		this.testedCat = null;
	}

	@Test
	public void testToString()
	{
		String result = testedCat.toString();
		assertTrue(result.indexOf("data.model.InternetCat@") == -1, "The toString method must be overwritten");
	}

	@Test
	public void testConstructor()
	{
		Constructor [] catConstructors = testedCat.getClass().getDeclaredConstructors();
		assertTrue(catConstructors.length == 1, "You have one constructor");
		for (Constructor current : catConstructors)
		{
			int params = current.getParameterCount();
			assertTrue(params == 6, "You need six parameters in InternetCat constructor");
		}
	}
	
	@Test
	public void testStructure()
	{
		Method [] methods = testedCat.getClass().getDeclaredMethods();
		assertTrue(methods.length == 9, "You need 9 methods in your InternetCat!");

		assertTrue(testedCat instanceof Record, "Your InternetCat must be a Record");
	}
}
