/*	Derek Snyder
*	3/8/2018
*	Algorithms HW 4
*
*	I did the bonus problem, but evaluate() won't throw an exception
*	for a variable expression :c
*
*/

import java.util.Stack;

public class PostfixTree {

	private Stack<Node> stack;

	private enum IS {
		NUMBER, 		// a leaf
		VARIABLE,		// a special leaf
		ADD,
		MULTIPLY,
		SUBTRACT,
		DIVIDE			// branches
	}
	private static double varVal;
	private class Node {
		String contents; // leaves and branches have contents,
						 //	i.e. "3.14" or "+"
		IS what; // need this??
		Node left;
		Node right;

		double eval() {
			switch(this.what) {
				case NUMBER: 	return Double.parseDouble(this.contents);
				case VARIABLE: 	return varVal;
				case ADD:		return this.right.eval() + this.left.eval();
				case MULTIPLY:	return this.right.eval() * this.left.eval();
				case SUBTRACT:	return this.right.eval() - this.left.eval();
				case DIVIDE:	return this.right.eval() / this.left.eval();
				default:		return 0;
			}
		}
		int height() {
			switch(this.what) {
				case NUMBER:				
				case VARIABLE: 	return 0;
				
				case ADD:						
				case MULTIPLY:	
				case SUBTRACT:					
				case DIVIDE:	return 1+
					( (this.right.height()>this.left.height() ) ?
						this.right.height() : this.left.height() );
				
				default:		return 0;
			}
		}
		String inorder() {
			switch(this.what) {
				case NUMBER: 	
				case VARIABLE:	return this.contents;

				case ADD:
				case MULTIPLY:
				case SUBTRACT:
				case DIVIDE:	return String.format("(%s %s %s)", 
									this.right.inorder(), this.contents, this.left.inorder());
				
				default:	return "";
			}
			
		}
	
		// Clone function to preserve tree when calling eval(), height(), etc
		void cloner(Node clone) {
			clone.contents=this.contents;
			clone.what=this.what;
			clone.left=this.left;
			clone.right=this.right;

			return;
		}
	}

	public PostfixTree() {
		// do I actually need anything here?
		this.stack = new Stack<Node>();
	}
	
	public void pushNumber(double d) {
		Node tempNode = new Node();
		tempNode.what = IS.NUMBER;
		tempNode.contents = ""+ d;
		tempNode.left = null;
		tempNode.right = null;
		
		this.stack.push(tempNode);
	}

	// push nodes representing corresponding ops
	public void pushAdd() {
		Node tempNode = new Node();
		tempNode.what = IS.ADD;
		tempNode.contents = "+";
		tempNode.left = this.stack.pop();
		tempNode.right = this.stack.pop();
		
		this.stack.push(tempNode);
	}
	public void pushMultiply() {
		Node tempNode = new Node();
		tempNode.what = IS.MULTIPLY;
		tempNode.contents = "*";
		tempNode.left = this.stack.pop();
		tempNode.right = this.stack.pop();
		
		this.stack.push(tempNode);
	}
	public void pushSubtract() {
		Node tempNode = new Node();
		tempNode.what = IS.SUBTRACT;
		tempNode.contents = "-";
		tempNode.left = this.stack.pop();
		tempNode.right = this.stack.pop();

		this.stack.push(tempNode);
	}
	public void pushDivide() {
		Node tempNode = new Node();
		tempNode.what = IS.DIVIDE;
		tempNode.contents = "/";
		tempNode.left = this.stack.pop();
		tempNode.right = this.stack.pop();

		this.stack.push(tempNode);
	}

	//
	public double evaluate() throws IndexOutOfBoundsException {
		Node clone=new Node();
		this.stack.peek().cloner(clone);

		return clone.eval();
	}
	public String inorder() {
		Node clone=new Node();
		this.stack.peek().cloner(clone);

		return clone.inorder();
	}
	public int height() {
		Node clone=new Node();
		this.stack.peek().cloner(clone);
		
		return clone.height();
	}
	
	// B O N U S  R O U N D
	public void pushVariable() {
		Node tempNode = new Node();
		tempNode.what = IS.VARIABLE;
		tempNode.contents = "VAR";
		tempNode.left=null;
		tempNode.right=null;

		this.stack.push(tempNode);
	}
	public double evaluate(double variableVal) {
		varVal=variableVal; //ha...I feel like I cheated
		Node clone=new Node();
		this.stack.peek().cloner(clone);

		return clone.eval();
	}

}
