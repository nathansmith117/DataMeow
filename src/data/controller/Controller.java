package data.controller;

import data.model.InternetCat;
import java.util.ArrayList;
import data.view.DataFrame;
import javax.swing.JOptionPane;
import java.net.MalformedURLException;
import java.net.URL;

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
		String[] tags = {"cute", "cuddly"};
		InternetCat demo = new InternetCat(tags, "image/jpeg", 123, "", "", "garbage");
		
		System.out.println(demo);
		
		System.out.println("Here is the createdAt value " + demo.createdAt());
		
		System.out.println("For constrast, a non prebuilt toString() -> " + this);
		
		InternetCat catJSON = (InternetCat)IOController.readSingleJSON(this, catURLBase, "cute?json=true");
		System.out.print(catJSON);
	}
	
	public void handleError(Exception error)
	{
		JOptionPane.showMessageDialog(null, error.getMessage(), "Ooooops lmao", JOptionPane.ERROR_MESSAGE);
	}
	
	public String addCat()
	{
		String appended = "";
		
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
}
