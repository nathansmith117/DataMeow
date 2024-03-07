package data.controller;

import data.model.InternetCat;
import java.util.ArrayList;
import data.view.DataFrame;
import javax.swing.JOptionPane;

public class Controller
{
	private String catURLBase;
	private ArrayList<InternetCat> catList;
	private DataFrame window;
	
	public Controller()
	{
		this.catURLBase = "";
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
	}
	
	public void handleError(Exception error)
	{
		JOptionPane.showMessageDialog(null, error.getMessage(), "Ooooops lmao", JOptionPane.ERROR_MESSAGE);
	}
}
