package data.controller;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;

import java.util.ArrayList;

import java.net.URL;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.core.JsonProcessingException;

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
				text += fileScanner.nextLine() + "\n";
			}
		}
		catch (NullPointerException | FileNotFoundException error)
		{
			app.handleError(error);
		}
		
		return text;
	}
	
	public static ArrayList<?> readJSONListFromFile(Controller app, String path, String type)
	{
		ObjectMapper mapper = new ObjectMapper();
		
		if (type.equals("InternetCat"))
		{
			ArrayList<InternetCat> cats = null;
			
			String source = readTextFromFile(app, path);
			
			try
			{
				cats = mapper.readValue(source, new TypeReference<ArrayList<InternetCat>>() {});
			}
			catch (JsonProcessingException error)
			{
				cats = new ArrayList<InternetCat>();
				app.handleError(error);
			}
			
			return cats;
		}
		
		return null;
	}
	
	public static void writeListToJSONFile(Controller app, ArrayList<?> list, String path)
	{
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		
		try
		{
			mapper.writeValue(new File(path), list);
		}
		catch (IOException error)
		{
			app.handleError(error);
		}
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
