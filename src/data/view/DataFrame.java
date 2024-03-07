package data.view;

import data.controller.Controller;
import javax.swing.JFrame;

public class DataFrame extends JFrame
{
	private Controller app;
	private DataPanel panel;
	
	public DataFrame(Controller app)
	{
		super();
		this.app = app;
		this.panel = new DataPanel(this.app);
		
		setupFrame();
	}
	
	private void setupFrame()
	{
		setContentPane(panel);
		setSize(1024, 768);
		setTitle("Meow lmao");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}
}
