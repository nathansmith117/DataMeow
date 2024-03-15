package data.controller;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;

import java.net.URL;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.NullPointerException;
import java.io.FileNotFoundException;

import data.model.InternetCat;

public class IOController
{
	public static Object readSingleJSON(Controller app, String urlBase, String appended)
	{
		ObjectMapper mapper = new ObjectMapper();
		
		try
		{
			if (urlBase.contains("cat"))
			{
				InternetCat demo = mapper.readValue(new URL(urlBase + appended), InternetCat.class);
				return demo;
			}
		}
		catch (IOException error)
		{
			app.handleError(error);
		}
		
		return null;
	}
	
	public static String readTextFromFile(Controller app, String path)
	{
		String text = "";
		
		try (Scanner fileScanner = new Scanner(new File(path)))
		{
			while (fileScanner.hasNextLine())
			{
				text += fileScanner.nextLine();
			}
		}
		catch (NullPointerException | FileNotFoundException error)
		{
			app.handleError(error);
		}
		
		return text;
	}
	
	public static void writeTextToFile(Controller app, String text, String path)
	{
		try (PrintWriter writer = new PrintWriter(new File(path));
				Scanner stringScanner = new Scanner(text))
		{
			while (stringScanner.hasNextLine())
			{
				writer.println(stringScanner.nextLine());
			}
		}
		catch (FileNotFoundException error)
		{
			app.handleError(error);
		}
	}
}
