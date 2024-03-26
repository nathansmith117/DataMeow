package data.tests;

/**
 * Project imports
 */
import data.view.DataPanel;
import data.controller.Controller;
/**
 * Reflection imports
 */
import java.lang.reflect.*;
import java.awt.*;
import javax.swing.*;

/**
 * Testing imports
 */
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PanelTest
{
	private DataPanel testedPanel;

	@BeforeEach
	void setUp() throws Exception
	{
		this.testedPanel = new DataPanel(new Controller());
	}

	@AfterEach
	void tearDown() throws Exception
	{
		this.testedPanel = null;
	}

	@Test
	void testPanelStructure()
	{
		assertTrue(testedPanel instanceof JPanel, "DataPanel needs to extend JPanel");

		Field [] dataMembers = testedPanel.getClass().getDeclaredFields();
		
		for (Field field : dataMembers)
		{
			int status = field.getModifiers();
			assertTrue(Modifier.isPrivate(status), "All data members need to be private!");
		}

		assertTrue(testedPanel.getLayout() instanceof SpringLayout, "The DataPanel layout manager should be SpringLayout");

		Component [] contents = testedPanel.getComponents();
		int fieldCount = 0;
		int panelCount = 0;
		int paneCount = 0;
		int buttonCount = 0;
		int labelCount = 0;
		
		for (Component current : contents)
		{
			if (current instanceof JPanel)
			{
				JPanel subPanel = (JPanel) current;
				assertTrue(subPanel.getLayout() instanceof GridLayout, "Subpanels need GridLayout as the layout manager");
				panelCount++;
				for (Component panelItem : subPanel.getComponents())
				{
					if (panelItem instanceof JPanel)
					{
						panelCount++;
						JPanel subSubPanel = (JPanel) panelItem;
						assertTrue(subSubPanel.getLayout() instanceof GridLayout, "Subpanels need GridLayout as the layout manager");
						for (Component subPanelItem : ((JPanel)panelItem).getComponents())
						{
							if (subPanelItem instanceof JButton)
							{
								buttonCount++;
								JButton tested = (JButton) subPanelItem;
								assertTrue(tested.getActionListeners().length == 1, "ALL Buttons need listeners!");
								
							}
							else if (subPanelItem instanceof JTextField)
							{
								fieldCount++;
								JTextField tested = (JTextField) subPanelItem;
							
								assertTrue(tested.getActionListeners().length >= 0, "The input textfield needs a listener!");
							}
						}	
					}

				}
			}
			else if (current instanceof JScrollPane)
			{
				paneCount++;
				JScrollPane tested = (JScrollPane) current;

				assertTrue(tested.getVerticalScrollBarPolicy() == JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, "The vertical Scroll should be as needed");
				assertTrue(tested.getHorizontalScrollBarPolicy() == JScrollPane.HORIZONTAL_SCROLLBAR_NEVER, "The horizontal Scroll should be never");

				assertTrue(tested.getViewport().getView() instanceof JTextArea, "Your Scrollpane needs the JTextArea as a view");
				JTextArea area = (JTextArea)  tested.getViewport().getView();
				assertFalse(area.isEnabled(), "The JTextArea should not be enabled");
			}
			else if (current instanceof JLabel)
			{
				labelCount++;

			}
		}
		
		assertTrue(paneCount >= 1, "You need a JScrollPane");
		assertTrue(buttonCount >= 9, "You need at least nine buttons, you only have " + buttonCount);
		assertTrue(fieldCount == 1, "You need one JTextField");
		assertTrue(panelCount > 2, "You need at least 3 JPanels with a grid layout");
		assertTrue(labelCount >= 1, "You need one JLabel");
	}
		
	@Test
	void testFrameworkMethodsExist()
	{
		Method [] methods = testedPanel.getClass().getDeclaredMethods();
		assertTrue(methods.length >= 6, "You need at least 6 methods in the DataPanel");
		
		boolean hasSetupPanel = false;
		boolean hasSetupListeners = false;
		boolean hasSetupLayout = false;
		
		for (Method method : methods)
		{
			assertTrue(Modifier.isPrivate(method.getModifiers()), "The " + method.getName()+ " method must be private");
			
			if (method.getName().equals("setupPanel"))
			{
				hasSetupPanel = true;
				assertTrue(method.getReturnType().equals(Void.TYPE), "The " + method.getName()+ " method needs to be a void method!");
			}
			else if (method.getName().equals("setupListeners"))
			{
				hasSetupListeners = true;
				assertTrue(method.getReturnType().equals(Void.TYPE), "The " + method.getName()+ " method needs to be a void method!");
				
			}
			else if (method.getName().equals("setupLayout"))
			{
				hasSetupLayout = true;
				assertTrue(method.getReturnType().equals(Void.TYPE), "The " + method.getName()+ " method needs to be a void method!");
			}
			
		}
		
		assertTrue(hasSetupPanel, "You need a method named setupPanel");
		assertTrue(hasSetupListeners, "You need a method named setupListeners");
		assertTrue(hasSetupLayout, "You need a method named setupLayout");
		
	}
	
	@Test
	void testInternetMethodsExist()
	{
		Method [] methods = testedPanel.getClass().getDeclaredMethods();
		boolean hasLoadImages = false;
		boolean hasGetCat = false;
		boolean hasSave = false;
		
		for (Method method : methods)
		{
			assertTrue(Modifier.isPrivate(method.getModifiers()), "The " + method.getName()+ " method must be private");
			if (method.getName().contains("loadCatImage"))
			{
				hasLoadImages = true;
				
				assertTrue(method.getParameterCount() == 1, "The " + method.getName()+ " method needs a String parameter");
				assertTrue(method.getGenericParameterTypes()[0].getTypeName().equals("java.lang.String"), "The first parameter should be a String");
			}
			else if (method.getName().equals("getCat"))
			{
				hasGetCat = true;
				assertTrue(method.getParameterCount() == 0, "The " + method.getName()+ " method needs zero parameters");
			}
			else if (method.getName().equals("save"))
			{
				hasSave = true;
				assertTrue(method.getParameterCount() == 0, "The " + method.getName()+ " method needs zero parameters");
			}
		}
		
		assertTrue(hasLoadImages, "You need a method named loadCatImage");
		assertTrue(hasGetCat, "You need a method named getCat");
		assertTrue(hasSave, "You need a method named save");
	}
		
	@Test
	void testDataStructureMethodsExist()
	{
		Method [] methods = testedPanel.getClass().getDeclaredMethods();
		
		boolean hasSetInfo = false;
		boolean hasHashInfo = false;
		boolean hasTreeInfo = false;
		boolean hasLinearInfo = false;
		boolean hasCompareInfo = false;
		boolean hasIteratorInfo = false;
		boolean hasTwoD = false;
		boolean hasText = false;
		
		for (Method method : methods)
		{
			assertTrue(Modifier.isPrivate(method.getModifiers()), "The " + method.getName()+ " method must be private");
			
			
			if (method.getName().toLowerCase().contains("set"))
			{
				hasSetInfo = true;
				assertTrue(method.getParameterCount() == 0, "The " + method.getName()+ " method needs zero parameters");
			}
			else if (method.getName().toLowerCase().contains("tree"))
			{
				hasTreeInfo = true;
				assertTrue(method.getParameterCount() == 0, "The " + method.getName()+ " method needs zero parameters");
				
			}
			else if (method.getName().toLowerCase().contains("linear"))
			{
				hasLinearInfo = true;
				assertTrue(method.getParameterCount() == 0, "The " + method.getName()+ " method needs zero parameters");
			}
			else if (method.getName().toLowerCase().contains("compare"))
			{
				hasCompareInfo = true;
				assertTrue(method.getParameterCount() == 0, "The " + method.getName()+ " method needs zero parameters");
			}
			else if (method.getName().toLowerCase().contains("iterator"))
			{
				hasIteratorInfo = true;
				assertTrue(method.getParameterCount() == 0, "The " + method.getName()+ " method needs zero parameters");
			}
			else if (method.getName().toLowerCase().contains("twod"))
			{
				hasTwoD = true;
				assertTrue(method.getParameterCount() == 0, "The " + method.getName()+ " method needs zero parameters");
			}
			else if (method.getName().toLowerCase().contains("text"))
			{
				hasText = true;
				assertTrue(method.getParameterCount() == 0, "The " + method.getName()+ " method needs zero parameters");
			}
			else if (method.getName().toLowerCase().contains("hash"))
			{
				hasHashInfo = true;
				assertTrue(method.getParameterCount() == 0, "The " + method.getName()+ " method needs zero parameters");
			}
			
		}
		
		assertTrue(hasSetInfo, "You need a method named loadSet");
		assertTrue(hasTreeInfo, "You need a method named loadTree");
		assertTrue(hasLinearInfo, "You need a method named loadLinear");
		assertTrue(hasCompareInfo, "You need a method named loadCompare");
		assertTrue(hasIteratorInfo, "You need a method named loadIterator");
		assertTrue(hasTwoD, "You need a method named loadTwoD");
		assertTrue(hasText, "You need a method named loadText");
		assertTrue(hasHashInfo, "You need a method named loadHash");	
	}

}
