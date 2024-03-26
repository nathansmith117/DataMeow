package data.tests;

/**
 * Project imports
 */
import data.controller.Controller;
import data.controller.IOController;

import java.io.File;
import java.io.IOException;
/**
 * Reflection imports
 */
import java.lang.reflect.*;
import java.net.URL;
import java.nio.file.Files;

/**
 * Testing imports
 */

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ControllerTest
{
	private Controller testedController;

	@BeforeEach
	void setUp() throws Exception
	{
		this.testedController = new Controller();
	}

	@AfterEach
	void tearDown() throws Exception
	{
		this.testedController = null;
	}

	@Test
	void testDataMembers()
	{
		Field [] fields = testedController.getClass().getDeclaredFields();
		assertTrue(fields.length > 2, "You need at least 3 data members in your Controller");
		
		boolean hasCatList = false;
		boolean hasFrame = false;

		for (Field field : fields)
		{
			assertTrue(Modifier.isPrivate(field.getModifiers()), "All data members must be private!");

			String name = field.getType().getSimpleName();
			
			if (name.equals("ArrayList"))
			{
				hasCatList = true;
			}
			else if (name.equals("DataFrame"))
			{
				hasFrame = true;
			}
		}
		assertTrue(hasFrame, "You need a DataFrame as a data member");
		assertTrue(hasCatList, "You need an ArrayList as a data member");
	}

	@Test
	void testControllerMethods()
	{
		Method [] methods = testedController.getClass().getDeclaredMethods();
		assertTrue(methods.length >= 9, "You need at least nine methods in the controller");
		
		boolean hasStart = false;
		
		boolean hasHandle = false;
		boolean hasSave = false;
		boolean hasLoad = false;
		boolean hasHash = false;
		boolean hasSet = false;
		boolean hasUnused = false;
		boolean hasCatURL = false;
		boolean hasAddCat = false;
		
		
		for (Method method : methods)
		{
			Type[] types = method.getGenericParameterTypes();
			if (method.getName().equals("handleError"))
			{
				hasHandle = true;
				assertTrue(Modifier.isPublic(method.getModifiers()), "The " + method.getName()+ " method must be public");
				assertTrue(types[0].getTypeName().equals("java.lang.Exception"), "The parameter type needs to be: Exception");
			}
			else if (method.getName().equals("start"))
			{
				hasStart = true;
				assertTrue(Modifier.isPublic(method.getModifiers()), "The " + method.getName()+ " method must be public");
				assertTrue(types.length == 0, "Start has no parameters!");
				assertTrue(method.getReturnType().equals(Void.TYPE), "The " + method.getName()+ " method needs to be a void method!");
			}
			else if (method.getName().equals("save"))
			{
				hasSave = true;
				assertTrue(Modifier.isPublic(method.getModifiers()), "The " + method.getName()+ " method must be public");
				assertTrue(types.length == 2, "Save has two parameters!");
				assertTrue(method.getReturnType().equals(Void.TYPE), "The " + method.getName()+ " method needs to be a void method!");
			}
			else if (method.getName().equals("load"))
			{
				hasLoad = true;
				assertTrue(Modifier.isPublic(method.getModifiers()), "The " + method.getName()+ " method must be public");
				assertTrue(types.length == 1, "Load has no parameter!");
				assertTrue(method.getReturnType().equals(String.class), "The " + method.getName()+ " method should return a String");
			}
			else if (method.getName().equals("addCat"))
			{
				hasAddCat = true;
				assertTrue(Modifier.isPublic(method.getModifiers()), "The " + method.getName()+ " method must be public");
				assertTrue(types.length == 0, "The addCat method has no parameter!");
				assertTrue(method.getReturnType().equals(String.class), "The " + method.getName()+ " method should return a String");
			}
			else if (method.getName().contains("unused"))
			{
				hasUnused = true;
				assertTrue(Modifier.isPrivate(method.getModifiers()), "The " + method.getName()+ " method must be private");
				assertTrue(types.length == 0, "The unused method has no parameter!");
				assertTrue(method.getReturnType().equals(Void.TYPE), "The " + method.getName()+ " method needs to be a void method!");
			}
			else if (method.getName().equals("getCatImageURL"))
			{
				hasCatURL = true;
				assertTrue(Modifier.isPublic(method.getModifiers()), "The " + method.getName()+ " method must be public");
				assertTrue(types.length == 1, "The getCatImageURL method has one parameter!");
				assertTrue(method.getReturnType().equals(URL.class), "The " + method.getName()+ " method should return a String");
			}
			else if (method.getName().equals("hashItOut"))
			{
				hasHash = true;
				assertTrue(Modifier.isPrivate(method.getModifiers()), "The " + method.getName()+ " method must be private");
				assertTrue(types.length == 0, "hashItOut has no parameters!");
				assertTrue(method.getReturnType().equals(Void.TYPE), "The " + method.getName()+ " method needs to be a void method!");
			}
			else if (method.getName().equals("setItUp"))
			{
				hasSet = true;
				assertTrue(Modifier.isPrivate(method.getModifiers()), "The " + method.getName()+ " method must be private");
				assertTrue(types.length == 0, "hashItOut has no parameters!");
				assertTrue(method.getReturnType().equals(Void.TYPE), "The " + method.getName()+ " method needs to be a void method!");
			}
		}

		assertTrue(hasHandle, "You need a method named handleError");
		assertTrue(hasStart, "You need a method named start");
		assertTrue(hasSave, "You need a method named save");
		assertTrue(hasLoad, "You need a method named load");
		assertTrue(hasSet, "You need a method named setItUp");
		assertTrue(hasHash, "You need a method named hashItOut");
		assertTrue(hasUnused, "You need a method named unusedCode....");
		assertTrue(hasCatURL, "You need a method named getCatImageURL");
		assertTrue(hasAddCat, "You need a method named addCat");
	}

	@Test
	void testAddCat()
	{
		String result = testedController.addCat();
		assertNotNull(result, "You should have a String even if the cat is null");
	}
	
	@Test
	void testGetCatImageURL()
	{
		String result = testedController.addCat();
		URL toVisit = testedController.getCatImageURL(result);
		assertEquals(toVisit.getAuthority(), "cataas.com", "The URL should go to cataas.com");
		assertEquals(toVisit.getProtocol().toLowerCase(), "https", "This should be an https protocol");
		assertTrue(toVisit.getPath().contains("/cat/"), "This URL should specify the cat directory");
		assertTrue(toVisit.getPath().contains(".jpeg"), "This URL should specify a jpeg");
	}
	
	@Test
	void testHandleError()
	{
		assertTrue(true, "This is a gimme");
	}
	
	@Test
	void testLoad()
	{
		String text = "content exists";
		String file = "deleteLoad.txt";
		IOController.writeTextToFile(testedController, text, file);
		String results = IOController.readTextFromFile(testedController, file).trim();
		String testedResults = testedController.load(file).trim();
		assertEquals(results, testedResults, "Content from file should match");
		
		try
		{
			Files.deleteIfExists((new File(file)).toPath());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	@Test
	void testSave()
	{
		
	}
}
