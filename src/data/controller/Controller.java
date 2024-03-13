package data.controller;

import data.model.InternetCat;
import java.util.ArrayList;
import data.view.DataFrame;
import javax.swing.JOptionPane;
import java.net.MalformedURLException;
import java.net.URL;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

public class Controller
{
	private String catURLBase;
	private ArrayList<InternetCat> catList;
	private DataFrame window;
	
	public Controller()
	{
		this.catURLBase = "https://cataas.com/cat/";
		this.catList = new ArrayList<InternetCat>();
		this.window = new DataFrame(this);
	}
	
	public void start()
	{
		setItUp();
		hashItOut();
	}
	
	public void handleError(Exception error)
	{
		JOptionPane.showMessageDialog(null, error.getMessage(), "Ooooops lmao", JOptionPane.ERROR_MESSAGE);
	}
	
	public String addCat()
	{
		String appended = "cute?json=true";
		
		String catDetails = "";
		
		InternetCat currentCat = (InternetCat)IOController.readSingleJSON(this, catURLBase, appended);
		catList.add(currentCat);
		
		catDetails = currentCat.toString();
		
		return catDetails;
	}
	
	public URL getCatImageURL(String details)
	{
		String path = "";
		
		int index = details.indexOf("_id=") + 4;
		path = details.substring(index, details.length() - 1);
		
		URL catImageURL = null;
		
		try
		{
			catImageURL = new URL(catURLBase + path + ".jpeg");
		}
		catch (MalformedURLException error)
		{
			handleError(error);
		}
		
		return catImageURL;
	}
	
	public String load(String path)
	{
		String results = IOController.readTextFromFile(this, path);
		
		return results;
	}
	
	private void setItUp()
	{
		HashSet<String> textSetExample = new HashSet<String>();
		
		textSetExample.add("Adding text");
		textSetExample.add("Other text");
		String sample = "text";
		textSetExample.add(sample);
		textSetExample.add(sample);
		
		JOptionPane.showMessageDialog(window, "There are " + textSetExample.size() + " items in the set!");
		
		for (String data : textSetExample)
		{
			JOptionPane.showMessageDialog(window, data);
		}
	}
	
	private void hashItOut()
	{
		
	}
}
