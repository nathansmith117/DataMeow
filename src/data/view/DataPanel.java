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

import java.awt.Color;
import data.controller.Controller;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;

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
		displayLabel = new JLabel("The most importent internet content!");
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
		setBackground(Color.MAGENTA);
		setLayout(layout);
		
		this.add(displayLabel);
		this.add(dataPane);
		this.add(menuPanel);
		
		menuPanel.add(webPanel);
		menuPanel.add(dataPanel);
		
		dataPane.setViewportView(dataArea);
		dataArea.setEnabled(false);
		dataArea.setLineWrap(true);
		dataArea.setWrapStyleWord(true);
		
		displayLabel.setVerticalTextPosition(JLabel.BOTTOM);
		displayLabel.setHorizontalTextPosition(JLabel.CENTER);
		
		webPanel.add(inputField);
		webPanel.add(catButton);
		
		dataPanel.add(linearButton);
		dataPanel.add(compareButton);
		dataPanel.add(iteratorButton);
		dataPanel.add(setButton);
		dataPanel.add(hashMapButton);
		dataPanel.add(treeButton);
		dataPanel.add(twoDButton);
		dataPanel.add(textButton);
	}
	
	private void setupListeners()
	{
		catButton.addActionListener(click -> getCat());
		linearButton.addActionListener(click -> loadLinear());
		hashMapButton.addActionListener(click -> loadHash());
		setButton.addActionListener(click -> loadSet());
		iteratorButton.addActionListener(click -> loadIterator());
		treeButton.addActionListener(click -> loadTree());
		compareButton.addActionListener(click -> loadCompare());
		twoDButton.addActionListener(click -> loadTwoDArray());
	}
	
	private void setupLayout()
	{
		layout.putConstraint(SpringLayout.NORTH, displayLabel, 20, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, displayLabel, 20, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.EAST, displayLabel, 700, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.SOUTH, displayLabel, 500, SpringLayout.NORTH, this);
		
		layout.putConstraint(SpringLayout.NORTH, dataPane, 20, SpringLayout.SOUTH, displayLabel);
		layout.putConstraint(SpringLayout.WEST, dataPane, 0, SpringLayout.WEST, displayLabel);
		layout.putConstraint(SpringLayout.EAST, dataPane, 0, SpringLayout.EAST, displayLabel);
		layout.putConstraint(SpringLayout.SOUTH, dataPane, -20, SpringLayout.SOUTH, this);
		
		layout.putConstraint(SpringLayout.NORTH, menuPanel, 0, SpringLayout.NORTH, displayLabel);
		layout.putConstraint(SpringLayout.WEST, menuPanel, 50, SpringLayout.EAST, displayLabel);
		layout.putConstraint(SpringLayout.EAST, menuPanel, -20, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.SOUTH, menuPanel, 0, SpringLayout.SOUTH, dataPane);
	}
	
	private void loadCatImage(String details)
	{
		ImageIcon catPicture = null;
		
		try
		{
			BufferedImage image = ImageIO.read(app.getCatImageURL(details));
			
			if (image.getHeight() > 500 || image.getWidth() > 500)
			{
				Image temp = image.getScaledInstance(500, -1, Image.SCALE_SMOOTH);
				BufferedImage resized = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB);
				Graphics2D imageGraphics = resized.createGraphics();
				imageGraphics.drawImage(temp, 0, 0, null);
				imageGraphics.dispose();
				
				image = resized;
			}
			
			catPicture = new ImageIcon(image);
		}
		catch (IOException error)
		{
			app.handleError(error);
		}
		
		displayLabel.setIcon(catPicture);
	}
	
	private void getCat()
	{
		String results = app.addCat();
		dataArea.setText(results);
		loadCatImage(results);
	}
	
	private void loadLinear()
	{
		String contents = app.load("linear.txt");
		dataArea.setText(contents);
		dataArea.setBackground(Color.GREEN);
	}
	
	private void loadHash()
	{
		String contents = app.load("hash.txt");
		dataArea.setText(contents);
		dataArea.setBackground(Color.GREEN);
	}
	
	private void loadSet()
	{
		String contents = app.load("set.txt");
		dataArea.setText(contents);
		dataArea.setBackground(Color.GREEN);
	}
	
	private void loadIterator()
	{
		String contents = app.load("iterator.txt");
		dataArea.setText(contents);
		dataArea.setBackground(Color.GREEN);
	}
	
	private void loadTree()
	{
		String contents = app.load("tree.txt");
		dataArea.setText(contents);
		dataArea.setBackground(Color.GREEN);
	}
	
	private void loadCompare()
	{
		String contents = app.load("compare.txt");
		dataArea.setText(contents);
		dataArea.setBackground(Color.GREEN);
	}
	
	private void loadTwoDArray()
	{
		String contents = app.load("twoDArray.txt");
		dataArea.setText(contents);
		dataArea.setBackground(Color.GREEN);
	}
}
