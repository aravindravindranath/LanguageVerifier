/**
 * @author  ARAVIND RAVINDRANATH
 * @version 1.0
 * 
 * Exception to handle Empty Stack Operation.
 * 
 */
package com.jhu.ds.lab1.reuse;

public class StackEmptyException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5022683557828403336L;

	public StackEmptyException(String message){
		super(message);
	}
}
