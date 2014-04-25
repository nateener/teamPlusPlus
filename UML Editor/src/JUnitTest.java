/**
 * File: JUnitTest.java
 * Project: UML Editor
 * Iteration: 3
 * Description: Must be run in a JUnit debugger via your IDE! Standard unit testing driver.
 */

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Iterator;

import javax.swing.JButton;

import org.junit.Before;
import org.junit.Test;

public class JUnitTest {

	@Before
	public void setUp() {
		Controller.nodes = new ArrayList<Node>();
		Controller.rels = new ArrayList<Relationship>();
		Controller.view = new View();
		Controller.history = new UndoRedoStack();
	}

	@Test
	public void nodeTest() {

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
	public void nodeControllerTest() {

		Node node;
		int x = 50;
		int y = 50;

		// Test createNode grabNode
		NodeController.createNode(x, y);
		node = NodeController.grabNode(x, y);
		assertNotNull(node);

		// Test isNodeFull
		assertTrue(NodeController.isNodeFull(x, y));
		assertFalse(NodeController.isNodeFull(1000, 1000));

		// Test deleteNode
		NodeController.deleteNode(x, y);
		node = NodeController.grabNode(x, y);
		assertNull(node);
	}

	@Test
	public void nodeInfoTest() {

		int x = 10;
		int y = 10;
		int width = 80;
		int height = 80;
		String name = "Name";
		ArrayList<String> attributes = new ArrayList<String>();
		ArrayList<String> methods = new ArrayList<String>();

		// Test constructor
		NodeInfo info = new NodeInfo(x, y, width, height, name, attributes,
				methods);
		assertNotNull(info);

		// Test setxCoor getxCoor
		x = 500;
		info.setxCoor(x);
		assertEquals(x, info.getxCoor());

		// Test setyCoor getyCoor
		y = 800;
		info.setyCoor(y);
		assertEquals(y, info.getyCoor());

		// Test setWidth getWidth
		width = 100;
		info.setWidth(width);
		assertEquals(width, info.getWidth());

		// Test setHeight getHeight
		height = 150;
		info.setHeight(height);
		assertEquals(height, info.getHeight());

		// Test getName
		assertEquals(name, info.getName());

		// Test setAttributes getAttributes
		attributes = new ArrayList<String>();
		for (int i = 0; i < 10; i++) {
			attributes.add(Integer.toString(i));
		}
		info.setAttributes(attributes);
		ArrayList<String> tmpAtt = info.getAttributes();
		Iterator<String> itrAtt = attributes.iterator();
		Iterator<String> itrTmpAtt = tmpAtt.iterator();
		while (itrAtt.hasNext() && itrTmpAtt.hasNext()) {
			assertEquals(itrAtt.next(), itrTmpAtt.next());
		}

		// Test setAttributes getAttributes
		methods = new ArrayList<String>();
		for (int i = 0; i < 10; i++) {
			methods.add(Integer.toString(i));
		}
		info.setMethods(methods);
		ArrayList<String> tmpMth = info.getMethods();
		Iterator<String> itrMth = methods.iterator();
		Iterator<String> itrTmpMth = tmpMth.iterator();
		while (itrMth.hasNext() && itrTmpMth.hasNext()) {
			assertEquals(itrMth.next(), itrTmpMth.next());
		}

		// Test setHigh isHigh
		boolean high = true;
		info.setHigh(high);
		assertTrue(info.isHigh());
		high = false;
		info.setHigh(high);
		assertFalse(info.isHigh());
	}

	@Test
	public void relationshipTest() {

		int x = 10;
		int y = 10;
		String type = "type";
		Node node1 = new Node(x, y);

		// Test constructor
		Relationship rel = new Relationship(node1, type);
		rel.printRelationship();
		assertNotNull(rel);

		// Test getStartX getStartY
		x = x + node1.getWidth() / 2;
		y = y + node1.getHeight();
		assertEquals(x, rel.getStartX());
		assertEquals(y, rel.getStartY());

		// Test getStartNode getEndNode
		x = 100;
		y = 100;
		Node node2 = new Node(x, y);
		rel.setEndpoint(node2);
		assertTrue(rel.getStartNode().equals(node1));
		assertTrue(rel.getEndNode().equals(node2));

		// Test getRelationshipType
		assertEquals(type, rel.getRelationshipType());

		// Test involvesNode
		assertTrue(rel.involvesNode(node1));

		// Test isSelfRel
		rel.setEndpoint(node1);
		assertTrue(rel.isSelfRel());
	}

	@Test
	public void relInfoTest() {

		int x1 = 10;
		int y1 = 10;
		int x2 = 50;
		int y2 = 50;
		boolean self = true;
		String type = "type";
		String startDetail = "Start";
		String endDetail = "End";

		// Test constructor
		RelInfo info = new RelInfo(x1, y1, x2, y2, self, type, startDetail,
				endDetail, "test");
		assertNotNull(info);

		// Test setStartX getStartX
		x1 = 100;
		info.setStartX(x1);
		assertEquals(x1, info.getStartX());

		// Test setStartY getStartY
		y1 = 100;
		info.setStartY(y1);
		assertEquals(y1, info.getStartY());

		// Test setEndX getEndX
		x2 = 200;
		info.setEndX(x2);
		assertEquals(x2, info.getEndX());

		// Test setEndY getEndY
		y2 = 200;
		info.setEndY(y2);
		assertEquals(y2, info.getEndY());

		// Test setRelType getRelType
		type = "test";
		info.setRelType(type);
		assertEquals(type, info.getRelType());

		// Test setSelfRel isSelfRel
		self = false;
		info.setSelfRel(self);
		assertFalse(info.isSelfRel());
	}

	@Test
	public void controllerTest() {

		// Test selectorButton
		Controller.selectorButton(new JButton());
		assertTrue(Controller.clickValue == 0);

		// Test classButton
		Controller.classButton(new JButton());
		assertTrue(Controller.clickValue == 1);

		// Test deleteButton
		Controller.deleteButton(new JButton());
		assertTrue(Controller.clickValue == 2);

		// Test aggButton
		Controller.aggButton(new JButton());
		assertTrue(Controller.clickValue == 3);

		// Test compButton
		Controller.compButton(new JButton());
		assertTrue(Controller.clickValue == 4);

		// Test genButton
		Controller.genButton(new JButton());
		assertTrue(Controller.clickValue == 5);

		// Test assButton
		Controller.assButton(new JButton());
		assertTrue(Controller.clickValue == 6);

		// Test dependButton
		Controller.dependButton(new JButton());
		assertTrue(Controller.clickValue == 7);

		// Test impButton
		Controller.impButton(new JButton());
		assertTrue(Controller.clickValue == 8);

		// Test basicButton
		Controller.basicButton(new JButton());
		assertTrue(Controller.clickValue == 9);

		// Test hasUndo
		assertFalse(Controller.history.hasUndoStates());

	}

	@Test
	public void contextMenuTest() {

		// Test constructor
		Node n = new Node(5, 5);
		ContextMenu menu = new ContextMenu(n);
		assertNotNull(menu);

		// Test show
		menu.show(Controller.view.drawPanel, 5, 5);
	}

	@Test
	public void undoRedoStackTest() {

		UndoRedoStack.State s;
		ArrayList<Node> testNodes = new ArrayList<Node>();
		ArrayList<Relationship> testRels = new ArrayList<Relationship>();
		Node n1 = new Node(10, 10);
		Node n2 = new Node(500, 500);
		Relationship r1 = new Relationship(n1, "test");
		r1.setEndpoint(n2);

		// Test constructor
		UndoRedoStack stack = new UndoRedoStack();
		assertNotNull(stack);

		// Test undoPush
		s = stack.undoPush(testNodes, testRels);
		assertTrue(s.getNodes().equals(testNodes));
		assertTrue(s.getRelations().equals(testRels));

		// Test redoPush
		s = stack.redoPush(testNodes, testRels);
		assertTrue(s.getNodes().equals(testNodes));
		assertTrue(s.getRelations().equals(testRels));

		// Test undoPop
		s = stack.undoPop();
		assertTrue(s.getNodes().equals(testNodes));
		assertTrue(s.getRelations().equals(testRels));
		assertNull(stack.undoPop());

		// Test redoPop
		s = stack.redoPop();
		assertTrue(s.getNodes().equals(testNodes));
		assertTrue(s.getRelations().equals(testRels));
		assertNull(stack.redoPop());

		// Test undoPeek
		boolean b = false;
		try {
			stack.undoPeek();
		} catch (EmptyStackException e) {
			b = true;
		}
		assertTrue(b);

		// Test redoPeek
		b = false;
		try {
			stack.undoPeek();
		} catch (EmptyStackException e) {
			b = true;
		}
		assertTrue(b);

		// Test undoClear
		stack.undoPush(testNodes, testRels);
		stack.undoClear();
		b = false;
		try {
			stack.redoPeek();
		} catch (EmptyStackException e) {
			b = true;
		}
		assertTrue(b);

		// Test redoClear
		stack.redoPush(testNodes, testRels);
		stack.redoClear();
		b = false;
		try {
			stack.undoPeek();
		} catch (EmptyStackException e) {
			b = true;
		}
		assertTrue(b);

		// Test hasUndoStates
		stack.undoPush(testNodes, testRels);
		assertTrue(stack.hasUndoStates());
		stack.undoClear();
		assertFalse(stack.hasUndoStates());

		// Test hasRedoStates
		stack.redoPush(testNodes, testRels);
		assertTrue(stack.hasRedoStates());
		stack.redoClear();
		assertFalse(stack.hasRedoStates());

	}

}
