package data.view;

import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Image;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

import javax.imageio.ImageIO;

import data.controller.Controller;
import javax.swing.JPanel;

public class DataPanel extends JPanel
{
	private Controller app;
	
	private SpringLayout layout;
	private JPanel menuPanel;
	private JPanel webPanel;
	private JPanel dataPanel;
	private JScrollPane dataPane;
	private JTextArea dataArea;
	private JLabel displayLabel;
	private JTextField inputField;
	
	// C is for cat, meow.
	private JButton catButton;
	private JButton linearButton;
	private JButton compareButton;
	private JButton iteratorButton;
	private JButton setButton;
	private JButton hashMapButton;
	private JButton treeButton;
	private JButton twoDButton;
	private JButton textButton;
	
	public DataPanel(Controller app)
	{
		super();
		
		this.app = app;
		
		layout = new SpringLayout();
		
		menuPanel = new JPanel(new GridLayout(1, 2));
		webPanel = new JPanel(new GridLayout(0, 1));
		dataPanel = new JPanel(new GridLayout(0, 1));
		
		dataPane = new JScrollPane();
		dataArea = new JTextArea();
		displayLabel = new JLabel("The most import internet content!");
		inputField = new JTextField("Input");
		catButton = new JButton("cat");
		linearButton = new JButton("linear");
		compareButton = new JButton("compare");
		iteratorButton = new JButton("iter");
		setButton = new JButton("set");
		hashMapButton = new JButton("hash");
		treeButton = new JButton("trees");
		twoDButton = new JButton("2D");
		textButton = new JButton("text");
		
		setupPanel();
		setupListeners();
		setupLayout();
	}
	
	private void setupPanel()
	{
		
	}
	
	private void setupListeners()
	{
		
	}
	
	private void setupLayout()
	{
		
	}
}
