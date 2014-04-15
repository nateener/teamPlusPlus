import java.util.ArrayList;
import java.util.Stack;


/**
 * Manages stacks of previous states to allow for undo and redo functionality
 * @author Josh
 *
 */
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
	 * @param nodes
	 * 		Node state to be pushed
	 * @param rels
	 * 		Relationship state to be pushed
	 * @return
	 * 		The State object that was placed on the stack.
	 */
	public State undoPush(ArrayList<Node> nodes, ArrayList<Relationship> rels) {
		/*TODO: Create a copy of nodes/rels to push instead of just pushing them.
		Figure out if there's a less painful way to copy relationships. 
		Also: Maybe use a static long in Node and Relationship to create unique ids for them to ease equality checking*/
		return undoStack.push(new State(nodes, rels));
	}
	
	/**
	 * Creates a state and adds it as an redo state
	 * @param nodes
	 * 		Node state to be pushed
	 * @param rels
	 * 		Relationship state to be pushed
	 * @return
	 * 		The State object that was placed on the stack.
	 */
	public State redoPush(ArrayList<Node> nodes, ArrayList<Relationship> rels) {
		return redoStack.push(new State(nodes, rels));
	}
	
	/**
	 * Removes an undo state and returns it.
	 * @return
	 * 		The state that was removed.
	 */
	public State undoPop() {
		return undoStack.pop();
	}
	
	/**
	 * Removes an redo state and returns it.
	 * @return
	 * 		The state that was removed.
	 */
	public State redoPop() {
		return redoStack.pop();
	}
	
	/**
	 * Returns the most recently added undo state.
	 * @return
	 * 		The most recently added undo state.
	 */
	public State undoPeek() {
		return undoStack.peek();
	}
	
	/**
	 * Returns the most recently added redo state.
	 * @return
	 * 		The most recently added redo state.
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
	 * Represents a given state of the UML diagram
	 * @author Josh
	 */
	public class State {
		private ArrayList<Node> nodes;
		private ArrayList<Relationship> rels;
		
		/**
		 * Creates a new State object.
		 * @param nodes
		 * 		ArrayList representing the state of the nodes.
		 * @param rels
		 * 		ArrayList representing the state of the relationships.
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
