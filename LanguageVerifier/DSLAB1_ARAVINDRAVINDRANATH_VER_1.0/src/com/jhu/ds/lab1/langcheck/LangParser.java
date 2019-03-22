/**
 * 
 *
 */
package com.jhu.ds.lab1.langcheck;

import com.jhu.ds.lab1.reuse.*;

/** 
 * @author  ARAVIND RAVINDRANATH
 * @version 1.0
 * 
 * This class contains the necessary attributes and methods to analyze a string and 
 * map it to a known defined language with a set pattern. The string cannot contain anything else
 * than the characters A,B,C and D. The logic implemented can map the string to 7 defined languages
 * if there is a match. This class can be enhanced to handle more languages by enhancing Enum 
 * EnumLanguageType and creating a new method to match the pattern. 
 * 
 */

public class LangParser {

	private LinkedStack stackStr;   //main stack which stores all characters from the input string
	private LinkedStack stackA;     //stack storing A's from the string
	private LinkedStack stackB;     //stack storing B's from the string
	private LinkedStack stackC;     //stack storing C's from the string
	private LinkedStack stackD;     //stack storing D's from the string
	private LinkedStack stackBckup; //Backup Stack for restore when mains stack entries are popped
	private final char chara = 'A'; 
	private final char charb = 'B';
	private final char charc = 'C';
	private final char chard = 'D';
	

	public LangParser() {
		stackStr = new LinkedStack();
		stackBckup = new LinkedStack();
		stackA = new LinkedStack();
		stackB = new LinkedStack();
		stackC = new LinkedStack();
		stackD = new LinkedStack();
	}

	/*
	 * This method pushes the input character value into the stacks by calling
	 * individual stack push methods.
	 * 
	 * @param c		Character Value to be stored in the stack. Obtained by iterating from
	 *              the string
	 */
	
	public void fill_stacks(char c){
		add_to_stackStr(c);
		add_to_stackA(c);
		add_to_stackB(c);
		add_to_stackC(c);
		add_to_stackD(c);
	}
	
	/*
	 * This method pushes the input character value into the main stack and the backup
	 * stack.
	 * 
	 * @param c		Character Value to be stored in the stack. 
	 *              
	 */
	public void add_to_stackStr(char c) {

		stackStr.push(c);
		stackBckup.push(c);
	}

	/*
	 * This method pushes the input character value into the stack and allows only character
	 * 'A'.
	 * 
	 * @param c		Character Value to be stored in the stack. 
	 *              
	 */
	public void add_to_stackA(char c) {
		if (c == chara) {
			stackA.push(c);
		}
	}

	/*
	 * This method pushes the input character value into the stack and allows only character
	 * 'B'.
	 * 
	 * @param c		Character Value to be stored in the stack. 
	 *              
	 */
	public void add_to_stackB(char c) {
		if (c == charb) {
			stackB.push(c);
		}
	}

	/*
	 * This method pushes the input character value into the stack and allows only character
	 * 'C'.
	 * 
	 * @param c		Character Value to be stored in the stack. 
	 *              
	 */
	public void add_to_stackC(char c) {
		if (c == charc) {
			stackC.push(c);
		}
	}
	
	/*
	 * This method pushes the input character value into the stack and allows only character
	 * 'D'.
	 * 
	 * @param c		Character Value to be stored in the stack. 
	 *              
	 */
	public void add_to_stackD(char c) {
		if (c == chard) {
			stackD.push(c);
		}
	}	
	
	/*
	 * This method deletes all the entries of the backup Stack. 
	 *              
	 */
	public void clearStackBckup() {
		stackBckup.clear();
	}

	/*
	 * This method makes calls to other methods, each of which analyzes whether a string
	 * exhibits characteristics of a particular defined language L1, L2, L2, ....
	 * The individual methods return a boolean and if true the integer assigned to the Enum 
	 * EnumLanguageType is passed to an array of integers, otherwise its a zero.
	 * An array of integers is returned to the caller
	 * An example of return array:  [1, 2, 0, 4, 0, 0, 0]. This can be interpreted as that the 
	 * analyzed string is valid for languages L1, L2 and L4. The 0th index represents L1, 
	 * 1st index represents L2 and so on. If the value at a particular index is 0, then 
	 * the string is not valid in the corresponding language.
	 * 
	 *              
	 */
	public int[] checkNDetectLangType() {

		int[] ltypes = new int[7]; //array holding a non-zero value in the indices
		                           // where the language pattern holds good.

		ltypes[0] = is_L1() ? EnumLanguageType.L1.getLangType() : 0;
		restore();
		ltypes[1] = is_L2() ? EnumLanguageType.L2.getLangType() : 0;
		restore();
		ltypes[2] = is_L3() ? EnumLanguageType.L3.getLangType() : 0;
		restore();
		ltypes[3] = is_L4() ? EnumLanguageType.L4.getLangType() : 0;
		restore();
		ltypes[4] = is_L5() ? EnumLanguageType.L5.getLangType() : 0;
		restore();
		ltypes[5] = is_L6() ? EnumLanguageType.L6.getLangType() : 0;
		restore();		
		ltypes[6] = is_L7() ? EnumLanguageType.L7.getLangType() : 0;
		restore();			
		clear_all_stacks();
		stackBckup.clear();

		return ltypes;
	}

	/*
	 * This method is similar to method checkNDetectLangType, only that the string is
	 * checked against one language per call and returns true if pattern is valid otherwise
	 * it's a false. This method is a utility method not productively, but used in unit testing
	 * individual language check methods.
	 * 
	 * @param LType		EnumLanguageType represents language type for which a check on the 
	 * 				    language string is performed.
	 * 
	 */
	
	public boolean checkNDetectLangType(EnumLanguageType LType) {
		boolean bCheck = false;
		switch (LType.getLangType()) {
		case 1:
			bCheck = is_L1();
			break;
		case 2:
			bCheck = is_L2();
			break;
		case 3:
			bCheck = is_L3();
			break;
		case 4:
			bCheck = is_L4();
			break;
		case 5:
			bCheck = is_L5();
			break;
		case 6:
			bCheck = is_L6();
			break;	
		case 7:
			bCheck = is_L7();
			break;				
		default:
			System.out.println("Invalid Input");
			break;

		}
		clear_all_stacks();
		return bCheck;

	}

	/*
	 * This method calls methods to clear all the stacks except the backup Stack
	 */
	
	public void clear_all_stacks() {
		stackStr.clear();
		stackA.clear();
		stackB.clear();
		stackC.clear();
		stackD.clear();
	}

	
	/*
	 * This method checks the string to see if it maps to Language L1. 
	 * The string should consist of equal no. of A's and B's in any order and
	 * no other character. Returns true if L1 else false.  
	 */
	
	private boolean is_L1() {
		return checkCountEqual(chara, charb);
	}

	/**
	 * This method checks if a stack of characters contain an equal amount of
	 * two expected characters. An empty check stack is used to push the characters and then 
	 * a pop operation is executed. An example with string AABABB. The empty stack gets A,A
	 * first as per the logic. Then it encounters B, a pop is performed. The stack has only A now. 
	 * Again it encounters an A, stack becomes A,A. Now two B's lead to two pops and the stack 
	 * is empty. It returns true if the empty check stack remains empty at the end of the 
	 * execution else false.
	 * 
	 * 
	 * @param c1		One of the characters whose count needs to be checked
	 *        c2		The other character whose count is equal to the first ones
	 */
	private boolean checkCountEqual(char c1, char c2) {
		LinkedStack ls = new LinkedStack();
		LinkedStack charStack = new LinkedStack(); 
		boolean bL1 = true;
		if (stackStr.isEmpty()){
			return false;
		}
		ls = stackStr.deepCopyReversed();
		while(!ls.isEmpty()){
			try {
				char c = ls.pop();
				if (c != c1 && c!= c2){
					bL1 = false;
					break;
				}
 				if ( charStack.isEmpty() || c == charStack.peek() ){
					charStack.push(c);
				}else{
					charStack.pop();
				}
			} catch (StackEmptyException e) {
				bL1 = false;
				break;
			}
		}
		if (charStack.isEmpty() && bL1){
			return true;
		}else{
			return false;
		}
	}

	/*
	 * This method checks the string to see if it maps to Language L2. 
	 * The string should consist of AnBn, for some n > 0, no other character.
	 * For string to be a valid L2, it has to be a valid L1 as well.
	 * Returns true if L2 else false. 
	 */
	
	private boolean is_L2() {
		boolean bRule = true;
		LinkedStack rStack = null; // Stack to hold values in reverse  
		LinkedStack stackA1 = new LinkedStack(); //Stack holding A Values
		LinkedStack stackB1 = new LinkedStack(); //Stack holding B Values
		char revc = '\u0000'; //variable to fold popped value from the stack
		boolean toggle = true; //flag to check correct repetition 

		// count of A's and B's in the string should match. For
		// pattern AnBn the count should be same as L1.
		if (!is_L1()) {
			bRule = false;
			return bRule;
		}

		/*
		 * Basically it checks for equal number of A's followed by
		 * equal number of B's. If there is a break in this pattern 
		 * then its false. If the rStack has AABABB, in the 4th run
		 * of the while loop the last conditional statement will be 
		 * satisfied since no. of A's more than that of B's and also 
		 * the next character is A. If it is AAABBBAB, then also in the 
		 * 7th run the same condition will be satisfied as it finds an A
		 * again.
		 */
		rStack = stackStr.deepCopyReversed();
		while (!rStack.isEmpty() && bRule) {
			try {
				revc = rStack.pop();
			} catch (StackEmptyException e) {
				// This error should not happen as the empty check is done
				System.out.println(e.toString());
				e.printStackTrace();
			}
			if (revc == chara && toggle) {
				stackA1.push(revc);
			} else if (revc == charb) {
				stackB1.push(revc);
				toggle = false;
			} else if (stackA1.size() != stackB1.size() || revc == chara) {
				bRule = false;
			}
		}
		return bRule;
	}

	/*
	 * This method checks the string to see if it maps to Language L3. 
	 * The string should consist of the form AnB2n, for some n > 0 , 
	 * no other character. Returns true if L3 else false.
	 *  
	 */
	private boolean is_L3() {
		boolean bRule = true;
		char oric = '\u0000';
		boolean toggle = true;
		LinkedStack stackA1 = new LinkedStack();
		LinkedStack stackB1 = new LinkedStack();

		// Since the pattern is AnB2n then number of B's should be perfectly
		// divisble by number of A's. The total number of A's and B's should
		// also be equal to the stack size.
		// 
		if ((stackStr.isEmpty()) || stackA.isEmpty() || stackA.isEmpty() ||
				!(stackStr.size() == (stackA.size() + stackB.size())) && 
				(!stackStr.isEmpty()) || (stackB.size() / stackA.size() != 2) || 
				(stackB.size() % stackA.size() != 0) ) {
			bRule = false;
			return bRule;
		}

		// Very similar to the logic in is_l2, the check is done for AnB2n 
		// pattern. The main stack is used ( not reversed ) as it is and the check is 
		// done for B2nAn. Assuming a string AABABB, the check will happen for BBABAA 
		// as the stack gets populated that way. In the 4th pass of the while loop 
		// the last condition will be executed as the next character B comes
		// after A and is an incorrect L3.
		while (!stackStr.isEmpty() && bRule) {
			try {
				oric = stackStr.pop();
				if (toggle && oric == charb) {
					stackB1.push(oric);
				} else if ( oric == chara) {
					stackA1.push(oric);
					toggle = false;
				} else if ( ( stackB1.size() / stackA1.size() != 2 ) || 
							(stackB.size() % stackA.size() != 0) || oric == charb){
					bRule = false;
				}
			} catch (StackEmptyException e) {
				bRule = false;
			}

		}

		return bRule;
	}

	/*
	 * This method checks the string to see if it maps to Language L4. 
	 * The string should consist of the form (AnBm)p, for some m,n,p > 0 , 
	 * no other character. Returns true if L4 else false.
	 *  
	 */
	private boolean is_L4() {
		boolean bRule = true;
		int repeatFactorB = 0; //no. of times B repeats, same as m
		int repeatFactorA = 0; //no. of times A repeats, same as n
		char oric = '\u0000';
		boolean toggle = true;
		int counter = 0;
		LinkedStack stackA1 = new LinkedStack();
		LinkedStack stackB1 = new LinkedStack();

		if (!(stackStr.size() == (stackA.size() + stackB.size())) && 
				(!stackStr.isEmpty()) || stackStr.isEmpty()) {
			bRule = false;
			return bRule;
		}
		
		/*
		 * CRITICAL!!!
		 * 
		 * The sequence of the call should not be modified. stackStr is modified 
		 * in the findRepeatPattern call and is done on purpose to find the repeating
		 * pattern of B and then of A.
		 */
		repeatFactorB = findRepeatPattern(false);
		repeatFactorA = findRepeatPattern(false);
		restore();
		/*
		 * check comment above
		 */

		if (repeatFactorA == 0 || repeatFactorB == 0) {
			bRule = false;
			return bRule;
		}
		
		/*
		 * After finding the n and m of the form (AnBm), the stack is tested for repeating 
		 * pattern of AnBn. It works on the main stack, by checking the B's first and checking if
		 * it is equal to m, then checks the no. of A's to see if it is same as and so forth. If 
		 * at any point there is a discrepancy, then it is not L4.
		 */
		while (!stackStr.isEmpty() && bRule) {
			stackA1.clear();
			stackB1.clear();
			counter = toggle ? repeatFactorB : repeatFactorA;
			try {
				for (int i = 0; i < counter; i++) {
					oric = stackStr.pop();
					if (toggle && oric == charb) {
						stackB1.push(oric);
					} else if (!toggle && oric == chara) {
						stackA1.push(oric);
					}
				}
				toggle = !toggle;
				if (stackA1.size() != repeatFactorA && stackB1.size() != repeatFactorB) {
					bRule = false;
				}
			} catch (StackEmptyException e) {
				// This situation should not occur as the empty state is checked.
				bRule = false;
				System.out.println(e.getMessage());
				//e.printStackTrace();
			}

		}

		return bRule;
	}

	/*
	 * This method checks the frequency of a character in the stack until another
	 * character is popped. For checking repeating patterns of more than 1 character
	 * the method should be called consecutively. An integer representing the frequency 
	 * is returned.
	 * 
	 * @param bRestoreStack		If true, then the main stack is restored, otherwise not.
	 */
	
	private int findRepeatPattern(boolean bRestoreStack) {
		LinkedStack patternFinder = new LinkedStack();
		char oric = '\u0000';
		char temp = '\u0000';
		int repeatFactor = 0;

		try {
			oric = stackStr.peek();
			while ((!stackStr.isEmpty()) && oric == (temp = stackStr.pop())) {
				repeatFactor++;
				patternFinder.push(temp);
			}
			stackStr.push(temp); //the character popped is different from oric.
			if (bRestoreStack) {
				while (!patternFinder.isEmpty()) {
					stackStr.push(patternFinder.pop());
				}
			}
		} catch (StackEmptyException e1) {
			repeatFactor = 0;
			System.out.println(e1.toString());
			//e1.printStackTrace();
		}

		return repeatFactor;
	}

	/*
	 * This method restores the main stack by reading from the backup stack
	 */
	private void restore() {
		stackStr.clear();
		stackStr = (stackBckup.deepCopyReversed()).deepCopyReversed();
	}

	
	/*
	 * This method checks the string to see if it maps to Language L5. 
	 * The string should consist of equal no. of C's and D's in any order and
	 * no other character. Returns true if L5 else false.  
	 */
	private boolean is_L5() {
		return checkCountEqual(charc, chard);
	}
	
	/*
	 * This method checks the string to see if it maps to Language L6. 
	 * The string should consist of CnDn, for some n > 0, no other character.
	 * For string to be a valid L6, it has to be a valid L5 as well.
	 * Returns true if L6 else false. The algorithm is same as that in 
	 * method is_L2()
	 */	
	private boolean is_L6() {
		boolean bRule = true;
		LinkedStack rStack = null;
		LinkedStack stackC1 = new LinkedStack();
		LinkedStack stackD1 = new LinkedStack();
		char revc = '\u0000'; //character coming from reversed stack
		boolean toggle = true;

		// count of C's and D's in the string should match. For
		// pattern CnDn the count should be same as L1.
		if (!is_L5()) {
			bRule = false;
			return bRule;
		}

		rStack = stackStr.deepCopyReversed();
		while (!rStack.isEmpty() && bRule) {
			try {
				revc = rStack.pop();
			} catch (StackEmptyException e) {
				//This should not happen as check for empty is done before reaching here
				bRule = false;
				e.printStackTrace();
			}
			if (revc == charc && toggle) {
				stackC1.push(revc);
			} else if (revc == chard) {
				stackD1.push(revc);
				toggle = false;
			} else if (stackC1.size() != stackD1.size() || revc == charc) {
				bRule = false;
			}
		}
		return bRule;
	}	
	
	/*
	 * This method checks the string to see if it maps to Language L7. 
	 * The string should consist of BnAn, for some n > 0, no other character.
	 * For string to be a valid L7, it has to be a valid L1 as well.
	 * Returns true if L7 else false. The algorithm is same as that in 
	 * method is_L2()
	 */
	private boolean is_L7() {
		boolean bRule = true;
		LinkedStack rStack = null;
		LinkedStack stackA1 = new LinkedStack();
		LinkedStack stackB1 = new LinkedStack();
		char revc = '\u0000';
		boolean toggle = true;

		// count of A's and B's in the string should match. For
		// pattern AnBn the count should be same as L1.
		if (!is_L1()) {
			bRule = false;
			return bRule;
		}

		rStack = stackStr.deepCopyReversed();
		while (!rStack.isEmpty() && bRule) {
			try {
				revc = rStack.pop();
			} catch (StackEmptyException e) {
				//This should not happen as check for empty is done before reaching here
				bRule = false;
				e.printStackTrace();
			}
			if (revc == charb && toggle) {
				stackB1.push(revc);
			} else if (revc == chara) {
				stackA1.push(revc);
				toggle = false;
			} else if (stackA1.size() != stackB1.size() || revc == charb) {
				bRule = false;
			}
		}
		return bRule;
	}
	
}
