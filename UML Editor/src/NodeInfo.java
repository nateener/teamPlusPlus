/**
 * File: NodeInfo.java
 * Project: UML Editor
 * Iteration: 3
 * Description: A data structure for passing node information to the View formated for rendering graphically.
 */

import java.util.ArrayList;

public class NodeInfo {
	private int xCoor;
	private int yCoor;
	private int width;
	private int height;
	private String name;
	private ArrayList<String> attributes;
	private ArrayList<String> methods;
	private boolean high;

	/**
	 * NodeInfo constructor
	 * 
	 * @param x
	 *            x-coordinate of the node
	 * @param y
	 *            y-coordinate of the node
	 * @param w
	 *            width
	 * @param h
	 *            height
	 * @param n
	 *            name
	 * @param a
	 *            attributes
	 * @param m
	 *            methods
	 */
	public NodeInfo(int x, int y, int w, int h, String n, ArrayList<String> a,
			ArrayList<String> m) {

		xCoor = x;
		yCoor = y;
		width = w;
		height = h;
		name = n;
		attributes = a;
		methods = m;
		setHigh(false);

	}

	/**
	 * Gets the x coordinate
	 * 
	 * @return The x coordinate
	 */
	public int getxCoor() {

		return xCoor;

	}

	/**
	 * Sets the x coordinate
	 * 
	 * @param xCoor
	 *            The new x coordinate
	 */
	public void setxCoor(int xCoor) {

		this.xCoor = xCoor;

	}

	/**
	 * Gets the y coordinate
	 * 
	 * @return The y coordinate
	 */
	public int getyCoor() {

		return yCoor;

	}

	/**
	 * Sets the x coordinate
	 * 
	 * @param yCoor
	 *            The new x coordinate
	 */
	public void setyCoor(int yCoor) {

		this.yCoor = yCoor;

	}

	/**
	 * Gets the width
	 * 
	 * @return The width
	 */
	public int getWidth() {

		return width;

	}

	/**
	 * Sets the width
	 * 
	 * @param width
	 *            The new width
	 */
	public void setWidth(int width) {

		this.width = width;

	}

	/**
	 * Gets the height
	 * 
	 * @return The height
	 */
	public int getHeight() {

		return height;

	}

	/**
	 * Sets the height
	 * 
	 * @param height
	 *            The new height
	 */
	public void setHeight(int height) {

		this.height = height;

	}

	/**
	 * Gets the name
	 * 
	 * @return The name
	 */
	public String getName() {

		return name;

	}

	/**
	 * Returns if the node is highlighted
	 * 
	 * @return True if the node is highlighted
	 */
	public boolean isHigh() {

		return high;

	}

	/**
	 * Sets if the node should be highlighted
	 * 
	 * @param high
	 *            If the node should be highlighted
	 */
	public void setHigh(boolean high) {

		this.high = high;

	}

	/**
	 * Gets the attributes of the node
	 * 
	 * @return The attributes
	 */
	public ArrayList<String> getAttributes() {
		return attributes;
	}

	/**
	 * Sets the attributes of the node
	 * 
	 * @param attributes
	 *            The new attributes
	 */
	public void setAttributes(ArrayList<String> attributes) {
		this.attributes = attributes;
	}

	/**
	 * Gets the methods of the node
	 * 
	 * @return The methods
	 */
	public ArrayList<String> getMethods() {
		return methods;
	}

	/**
	 * Sets the methods of the node
	 * 
	 * @param methods
	 *            The new methods
	 */
	public void setMethods(ArrayList<String> methods) {
		this.methods = methods;
	}

}