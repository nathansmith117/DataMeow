package data.controller;
import java.io.IOException;

import java.net.URL;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

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
}
