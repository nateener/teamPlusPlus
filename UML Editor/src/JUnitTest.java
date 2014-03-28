import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Random;

import org.junit.Test;

public class JUnitTest {

	@Test
	public void nodeConstantTest() {
		
		int x = 10;
		int y = 10;
		int width = 80;
		int height = 80;
		String name = "Class";
		ArrayList<String> attributes = new ArrayList<String>();
		ArrayList<String> methods = new ArrayList<String>();
		
		// Test constructor
		Node node = new Node(x, y);
		assertNotNull(node);
		node.printNodeInfo();
		
		// Test getX getY
		assertEquals(x - (x % 10), node.getX());
		assertEquals(y - (y % 10), node.getY());
		
		// Test setPosition
		x = 50;
		y = 50;
		node.setPosition(x, y);
		assertEquals(x - (x % 10), node.getX());
		assertEquals(y - (y % 10), node.getY());
		
		// Test negative value
		x = -50;
		y = -50;
		node.setPosition(x, y);
		assertEquals(x - (x % 10), node.getX());
		assertEquals(y - (y % 10), node.getY());
		
		// Test getWidth getHeight
		assertEquals(width, node.getWidth());
		assertEquals(height, node.getHeight());
		
		// Test setWidth
		width = 100;
		node.setWidth(width);
		assertEquals(width, node.getWidth());
		
		// Test getName getAttributes getMethods
		assertEquals(name, node.getName());
		assertEquals(attributes, node.getAttributes());
		assertEquals(methods, node.getMethods());
		
		// Test setName setAttributse setMethods
		name = "testName";
		attributes = new ArrayList<String>();
		methods = new ArrayList<String>();
		node.setName(name);
		node.setAttributes(attributes);
		node.setMethods(methods);
		assertEquals(name, node.getName());
		assertEquals(attributes, node.getAttributes());
		assertEquals(methods, node.getMethods());
	}
	
	@Test
	public void nodeRNGTest() {
		
		Random rng = new Random();
		int x = rng.nextInt(1000);
		int y = rng.nextInt(1000);
		int width = 80;
		int height = 80;
		String name = "Class";
		ArrayList<String> attributes = new ArrayList<String>();
		ArrayList<String> methods = new ArrayList<String>();
		
		// Test constructor
		Node node = new Node(x, y);
		assertNotNull(node);
		node.printNodeInfo();
		
		// Test getX getY
		assertEquals(x - (x % 10), node.getX());
		assertEquals(y - (y % 10), node.getY());
		
		// Test setPosition
		x = rng.nextInt(1000);
		y = rng.nextInt(1000);
		node.setPosition(x, y);
		assertEquals(x - (x % 10), node.getX());
		assertEquals(y - (y % 10), node.getY());
		
		// Test getWidth getHeight
		assertEquals(width, node.getWidth());
		assertEquals(height, node.getHeight());
		
		// Test setWidth
		width = rng.nextInt(200);
		if(width < 80)
			width = 80;
		node.setWidth(width);
		assertEquals(width, node.getWidth());
		
		// Test getName getAttributes getMethods
		assertEquals(name, node.getName());
		assertEquals(attributes, node.getAttributes());
		assertEquals(methods, node.getMethods());
		
		// Test setName setAttributse setMethods
		name = ("" + rng.nextInt(1000));
		//attributes = ("" + rng.nextInt(1000));
		//methods = ("" + rng.nextInt(1000));
		node.setName(name);
		node.setAttributes(attributes);
		node.setMethods(methods);
		assertEquals(name, node.getName());
		assertEquals(attributes, node.getAttributes());
		assertEquals(methods, node.getMethods());
	}
	
	@Test
	public void nodeControllerConstantTest() {
		
		// Test constructor
		NodeController nc = new NodeController();
		assertNotNull(nc);
	}

}
