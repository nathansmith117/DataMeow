package data.tests;


/*
 * Project imports
 */
import data.controller.IOController;
import data.model.InternetCat;
import data.controller.Controller;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;

/*
 * Reflection imports
 */
import java.lang.reflect.*;

/*
 * Testing imports
 */

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class IOControllerTest
{
	private IOController tested;
	private Controller app;
	
	@BeforeEach
	void setUp() throws Exception
	{
		this.tested = new IOController();
		this.app = new Controller();
	}

	@AfterEach
	void tearDown() throws Exception
	{
		this.tested = null;
		this.app = null;
	}
	
	@Test
	void testMethodStructure()
	{
		Method[] ioMethods = tested.getClass().getDeclaredMethods();
		assertTrue(ioMethods.length >= 6, "There should be at least two methods in your IOController class");
		
		for (Method currentMethod : ioMethods)
		{
			int methodModifiers = currentMethod.getModifiers();
			assertTrue(Modifier.isPublic(methodModifiers), "The IOController methods must be public");
			assertTrue(Modifier.isStatic(methodModifiers), "The IOController methods need to be static!");
			
			if (currentMethod.getName().equals("readSingleJSONFromURL"))
			{
				assertTrue(currentMethod.getReturnType().equals(Object.class), "The method needs to be a Object method!");
				assertTrue(currentMethod.getParameterCount() == 3, "The readSingleJSONFromURL method must have exactly 3 parameters");
			}
			
			if (currentMethod.getName().equals("readJSONListFromURL"))
			{
				assertTrue(currentMethod.getReturnType().equals(ArrayList.class), "The method needs to be a Object method!");
				assertTrue(currentMethod.getParameterCount() == 3, "The readSingleJSONFromURL method must have exactly 3 parameters");
			}
			
			if (currentMethod.getName().equals("readTextFromFile"))
			{
				assertTrue(currentMethod.getReturnType().equals(String.class), "The method needs to be a String method!");
				assertTrue(currentMethod.getParameterCount() == 2, "The readSingleJSONFromURL method must have exactly 2 parameters");
			}
			
			if (currentMethod.getName().equals("writeTextToFile"))
			{
				assertTrue(currentMethod.getParameterCount() == 3, "The readSingleJSONFromURL method must have exactly 3 parameters");
				assertTrue(currentMethod.getReturnType().equals(Void.TYPE), "The method needs to be a void method!");
			}
			
			if (currentMethod.getName().equals("writeListToJSONFile"))
			{
				assertTrue(currentMethod.getParameterCount() == 3, "The writeListToJSONFile method must have exactly 3 parameters");
				assertTrue(currentMethod.getReturnType().equals(Void.TYPE), "The method needs to be a void method!");
			}
			
		}
	}
	
	@Test
	void testReadListURL()
	{
		String path ="https://cataas.com/api/cats?tags=cute";
		ArrayList<InternetCat> cats = (ArrayList<InternetCat>) IOController.readJSONListFromURL(app, path, "");
		
		assertNotNull(cats, "This should not be null");
	}
	
	@Test
	void testWriteText()
	{
		String sampleText = "nom nom nom";
		String filename = "writeme.txt";
		
		IOController.writeTextToFile(app, sampleText, filename);
		
		File result = new File(filename);
		
		try (Scanner fileScanner = new Scanner(result))
		{
			assertTrue(result.exists(), "File should exist!");
			String contents = "";
			
			while (fileScanner.hasNextLine())
			{
				contents += fileScanner.nextLine();
			}
			
			assertEquals(sampleText, contents, "The contents should match the parameter");
			Files.deleteIfExists(result.toPath());
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	void testReadText()
	{
		String sampleText = "nom nom nom";
		String filename = "readme.txt";
		
		IOController.writeTextToFile(app, sampleText, filename);
		String results = IOController.readTextFromFile(app, filename).trim();
		assertEquals(sampleText, results, "Content should match");
		try
		{
			Files.deleteIfExists( (new File(filename)).toPath());
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	void testReadCats() 
	{
		ArrayList<InternetCat> tempList = new ArrayList<InternetCat>();
		InternetCat tempCat = new InternetCat(null, "", 4, "", "", "first");
		tempList.add(tempCat);
		tempCat = new InternetCat(null, "", 4, "", "", "second");
		tempList.add(tempCat);
		tempCat = new InternetCat(null, "", 4, "", "", "third");
		tempList.add(tempCat);
		
		String filename = "readcats.json";
		
		IOController.writeListToJSONFile(app, tempList,filename);
		
		ArrayList<InternetCat> returned = (ArrayList<InternetCat>) IOController.readJSONListFromFile(app, filename, "InternetCat");
		assertNotNull(returned, "You need to return an actual ArrayList");
		assertTrue(returned.size() == 3, "There should be three cats in the list");
		
		try
		{
			Files.deleteIfExists( (new File(filename)).toPath());
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Test
	void testWriteCats()
	{
		ArrayList<InternetCat> tempList = new ArrayList<InternetCat>();
		InternetCat tempCat = new InternetCat(null, "", 4, "", "", "first");
		tempList.add(tempCat);
		tempCat = new InternetCat(null, "", 4, "", "", "second");
		tempList.add(tempCat);
		tempCat = new InternetCat(null, "", 4, "", "", "third");
		tempList.add(tempCat);
		String filename = "writecats.json";
		IOController.writeListToJSONFile(app, tempList,filename);
		File result = new File(filename);
		
		try (Scanner fileScanner = new Scanner(result))
		{	
			assertTrue(result.exists(), "The saved file should exist");
			int idCount = 0;
			
			while (fileScanner.hasNextLine())
			{
				String line = fileScanner.nextLine();
				if (line.indexOf("_id") >= 0)	
				{
					idCount++;
				}
			}
			
			assertTrue(idCount == 3, "There should be three cats saved");
			Files.deleteIfExists(result.toPath());
			
		}
		catch(Exception error)
		{
			error.printStackTrace();
		}
	}

}
