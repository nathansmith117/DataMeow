package data.tests;

/**
 * Project imports
 */
import data.view.*;
import data.controller.Controller;
/**
 * Reflection imports
 */
import java.lang.reflect.*;

import javax.swing.JFrame;

/**
 * Testing imports
 */

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FrameTest
{
	private DataFrame testedFrame;

	@BeforeEach
	void setUp() throws Exception
	{
		this.testedFrame = new DataFrame(new Controller());
	}

	@AfterEach
	void tearDown() throws Exception
	{
		this.testedFrame = null;
	}

	@Test
	void testDataFrame()
	{
		assertTrue(testedFrame instanceof JFrame, "DataFrame needs to extend JFrame");
		Method[] methods = testedFrame.getClass().getDeclaredMethods();
		assertTrue(methods.length == 1, "You need exactly 1 method in the DataFrame");
		assertTrue(methods[0].getName().equals("setupFrame"), "The XYZFrame needs to have a method named setupFrame");
		assertTrue(testedFrame.getTitle().length() > 5, "Your title needs at least 6 letters");
		assertTrue(!testedFrame.isResizable(), "Your DataFrame should NOT be resizable!");
		assertTrue(testedFrame.getTitle().toLowerCase().contains("data"), "Your title needs to have data in it");
		assertTrue(testedFrame.getContentPane() instanceof DataPanel, "Your DataFrame needs to have a DataPanel inside");
	}
}
