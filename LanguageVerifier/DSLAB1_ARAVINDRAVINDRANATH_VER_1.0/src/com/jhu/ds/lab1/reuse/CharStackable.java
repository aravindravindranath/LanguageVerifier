package com.jhu.ds.lab1.reuse;

/**
 * @author  ARAVIND RAVINDRANATH
 * @version 1.0
 *  
 * Interface which contains methods to manipulate a stack of characters 
 * This includes inserting, deleting and reading from the stack
 *
 */
public interface CharStackable {
	
	//Returns the size of the stack which will be the number of characters stored
	public int size();
	
	//returns true if the stack is empty and false otherwise
	public boolean isEmpty();
	
	//returns the value of the top node of the stack without deleting it
	public char peek() throws StackEmptyException;
	
	//deletes the topmost node from the stack and sets the preceding node as top
	public char pop() throws StackEmptyException;
	
	/*
	 * Method which pushes a character into the stack
	 * 
	 * @param c		The character value which needs to be pushed into the stack
	 * 
	 * 
	*/
	public void push(char c);
}
