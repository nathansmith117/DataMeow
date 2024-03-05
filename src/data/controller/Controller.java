package data.controller;

import data.model.InternetCat;
import data.view.DataFrame;

public class Controller
{
	
	public Controller()
	{
		
	}
	
	public void start()
	{
		String[] tags = {"cute", "cuddly"};
		InternetCat demo = new InternetCat(tags, "image/jpeg", 123, "", "", "garbage");
		
		System.out.println(demo);
		
		System.out.println("Here is the createdAt value " + demo.createdAt());
		
		System.out.println("For constrast, a non prebuilt toString() -> " + this);
	}
}
