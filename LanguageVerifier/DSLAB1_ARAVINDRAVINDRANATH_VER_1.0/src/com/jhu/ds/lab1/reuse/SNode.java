/**
 * 
 */
package com.jhu.ds.lab1.reuse;

/**
 * @author  ARAVIND RAVINDRANATH
 * @version 1.0
 *
 * Class defining the structure of a data element or data node which will be stored in a stack.
 * 
 * Consists of two attributes, one a character type which is the relevant information and 
 * a pointer to the next data element.
 * 
 * Also contains methods which provides read and write access to the attributes
 * 
 */

public class SNode {
	private char data;  //holds the character which is the info needed
	private SNode next; //holds a reference to the next data node
	
	/*
	 * Constructor method to set the attributes
	 * 
	 * @param c		The character value which is the actual relevant data
	 * @param next  Reference to the next node required to build a stack 	
	 */
	public SNode(char c, SNode next){
		this.data = c;
		this.next = next;
	}
	
	//method which returns the current instance of the class
	public SNode getNode(){
		return this;
	}

	/*
	 * Method which sets the reference to the next node
	 * 
	 * @param node		Reference to the next node required to build a stack 
	 */
	public void setNextNode(SNode node){
		this.next = node;
	}
	
	//Method which provides the reference to the next node
	public SNode getNextNode(){
		return this.next;
	}
	
	/*
	 * Method which sets the character value in the data part of the node
	 * 
	 * @param c		The character value which is the actual relevant data 
	 */	
	public void setData(char c){
		this.data = c;
	}
	
	//method which returns the data part of the node
	public char getData(){
		return this.data;
	}
		
	
}
