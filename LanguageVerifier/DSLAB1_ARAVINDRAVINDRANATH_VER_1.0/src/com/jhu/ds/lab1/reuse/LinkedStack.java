/**
 * 
 * 
 */
package com.jhu.ds.lab1.reuse;

import com.jhu.ds.lab1.reuse.CharStackable;
import com.jhu.ds.lab1.reuse.SNode;
import com.jhu.ds.lab1.reuse.StackEmptyException;

/**
 * @author  ARAVIND RAVINDRANATH
 * @version 1.0
 *
 * This class contains the required attributes and methods to create an 
 * object to hold a linked stack of characters. It implements all the methods
 * of the interface CharStackable and two more convenience methods to help manage the linked
 * stack.
 *
 */
public class LinkedStack implements CharStackable{
	
	private SNode top = null;  //holds the reference to the top node of the stack
	private int size = 0;      //keeps track of the number of nodes in the stack
	
	/*
	 * (non-Javadoc)
	 * @see com.jhu.ds.lab1.reuse.CharStackable#size()
	 */
	@Override
	public int size() {
		return this.size;
	}

	/*
	 * (non-Javadoc)
	 * @see com.jhu.ds.lab1.reuse.CharStackable#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return (this.top == null);
	}

	/*
	 * (non-Javadoc)
	 * @see com.jhu.ds.lab1.reuse.CharStackable#peek()
	 */
	@Override
	public char peek()  throws StackEmptyException{
		if (this.isEmpty()){
			throw new StackEmptyException("Empty Stack, Peek does not work");
		}else {
			char temp = pop();
			push(temp);
			return temp;
		}

	}
    
	/*
	 * (non-Javadoc)
	 * @see com.jhu.ds.lab1.reuse.CharStackable#pop()
	 */
	@Override
	public char pop() throws StackEmptyException{
		if (this.isEmpty()){
			throw new StackEmptyException("Empty Stack, Pop does not work");
		}else {
			SNode temp = top.getNode();
			top = temp.getNextNode();
			this.size--;  // One node reduced, therefore the size needs to reflect this
			temp.setNextNode(null); 
			return temp.getData();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.jhu.ds.lab1.reuse.CharStackable#push(char)
	 */
	@Override
	public void push(char c) {
		SNode newItem = new SNode(c, this.top);
		this.top = newItem;
		this.size++;
	}

	/*
	 * Method which deletes all the contents of the stack and makes it empty
	 * 
	 */
	public void clear() {
		while( !this.isEmpty()){
			try {
				this.pop();
			} catch (StackEmptyException e) {
				System.out.println(e.toString());
				//e.printStackTrace();
			}
		}
	}
	
	/*
	 * Method which copies the content of one stack and returns another stack with
	 * the contents reversed.
	 * 
	 * If a stack contains {1, 2, 3, 4, 5}, a call to this method would return another stack
	 * which contains {5, 4, 3, 2, 1} 
	 * 
	 */
	public LinkedStack deepCopyReversed() {
		LinkedStack oStack = new LinkedStack();
		LinkedStack rStack = new LinkedStack();
		char temp;
		while( !this.isEmpty()){
			try {
				temp = this.pop();
				rStack.push(temp);
				oStack.push(temp);
			} catch (StackEmptyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		while( !oStack.isEmpty()){
			try {
				this.push(oStack.pop());
			} catch (StackEmptyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return rStack;
	}	
	
}
