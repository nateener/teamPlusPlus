/**
 * File: UndoRedoStack.java
 * Project: UML Editor
 * Iteration: 3
 * Description: Manages stacks of previous states to allow for undo and redo functionality
 */

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.Stack;

public class UndoRedoStack {
	private Stack<State> undoStack;
	private Stack<State> redoStack;

	/**
	 * Creates a new UndoRedoStack.
	 */
	public UndoRedoStack() {
		undoStack = new Stack<State>();
		redoStack = new Stack<State>();
	}

	/**
	 * Creates a state and adds it as an undo state
	 * 
	 * @param nodes
	 *            Node state to be pushed
	 * @param rels
	 *            Relationship state to be pushed
	 * @return The State object that was placed on the stack.
	 */
	public State undoPush(ArrayList<Node> nodes, ArrayList<Relationship> rels) {
		System.out.println("Undo Push");
		Iterator<Node> nodeItr = nodes.iterator();
		Iterator<Relationship> relItr = rels.iterator();
		ArrayList<Node> newNodes = new ArrayList<Node>();
		ArrayList<Relationship> newRels = new ArrayList<Relationship>();

		while (relItr.hasNext()) {
			Relationship curRel = relItr.next();
			Node start = curRel.getStartNode().copy();
			Node end = curRel.getEndNode().copy();

			Relationship newRel = curRel.copy(start, end);

			newRels.add(newRel);
			newNodes.add(start);
			newNodes.add(end);
		}

		while (nodeItr.hasNext()) {
			Node copyNode = nodeItr.next().copy();

			if (newNodes.contains(copyNode)) {
				break;
			}

			newNodes.add(copyNode);
		}

		return undoStack.push(new State(newNodes, newRels));
	}

	/**
	 * Creates a state and adds it as an redo state
	 * 
	 * @param nodes
	 *            Node state to be pushed
	 * @param rels
	 *            Relationship state to be pushed
	 * @return The State object that was placed on the stack.
	 */
	public State redoPush(ArrayList<Node> nodes, ArrayList<Relationship> rels) {
		System.out.println("Redo Push");
		Iterator<Node> nodeItr = nodes.iterator();
		Iterator<Relationship> relItr = rels.iterator();
		ArrayList<Node> newNodes = new ArrayList<Node>();
		ArrayList<Relationship> newRels = new ArrayList<Relationship>();

		while (relItr.hasNext()) {
			Relationship curRel = relItr.next();
			Node start = curRel.getStartNode().copy();
			Node end = curRel.getEndNode().copy();

			Relationship newRel = curRel.copy(start, end);

			newRels.add(newRel);
			newNodes.add(start);
			newNodes.add(end);
		}

		while (nodeItr.hasNext()) {
			Node copyNode = nodeItr.next().copy();

			if (newNodes.contains(copyNode)) {
				break;
			}

			newNodes.add(copyNode);
		}

		return redoStack.push(new State(newNodes, newRels));
	}

	/**
	 * Removes an undo state and returns it.
	 * 
	 * @return The state that was removed or null if there is nothing to return.
	 */
	public State undoPop() {
		try {
			return undoStack.pop();
		} catch (EmptyStackException e) {
			return null;
		}
	}

	/**
	 * Removes an redo state and returns it.
	 * 
	 * @return The state that was removed or null if there is nothing to return.
	 */
	public State redoPop() {
		try {
			return redoStack.pop();
		} catch (EmptyStackException e) {
			return null;
		}
	}

	/**
	 * Returns the most recently added undo state.
	 * 
	 * @return The most recently added undo state.
	 */
	public State undoPeek() {
		return undoStack.peek();
	}

	/**
	 * Returns the most recently added redo state.
	 * 
	 * @return The most recently added redo state.
	 */
	public State redoPeek() {
		return redoStack.peek();
	}

	/**
	 * Removes all undo states.
	 */
	public void undoClear() {
		undoStack.clear();
	}

	/**
	 * Removes all redo states.
	 */
	public void redoClear() {
		redoStack.clear();
	}

	/**
	 * Tests if any undo states have been saved.
	 * 
	 * @return True if there are undo states saved.
	 */
	public boolean hasUndoStates() {
		return !undoStack.empty();
	}

	/**
	 * Tests if any redo states have been saved.
	 * 
	 * @return True if there are redo states saved.
	 */
	public boolean hasRedoStates() {
		return !redoStack.empty();
	}

	/**
	 * Represents a given state of the UML diagram
	 * 
	 * @author Josh
	 */
	public class State {
		private ArrayList<Node> nodes;
		private ArrayList<Relationship> rels;

		/**
		 * Creates a new State object.
		 * 
		 * @param nodes
		 *            ArrayList representing the state of the nodes.
		 * @param rels
		 *            ArrayList representing the state of the relationships.
		 */
		public State(ArrayList<Node> nodes, ArrayList<Relationship> rels) {
			this.nodes = nodes;
			this.rels = rels;
		}

		public ArrayList<Node> getNodes() {
			return nodes;
		}

		public ArrayList<Relationship> getRelations() {
			return rels;
		}
	}
}
