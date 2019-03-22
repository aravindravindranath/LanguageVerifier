/**
 * 
 */
package com.jhu.ds.test;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.jhu.ds.lab1.reuse.*;

/**
 * 
 * @author 	ARAVIND RAVINDRANATH
 * @version 1.0
 */

public class TestLinkedStack {
    
	LinkedStack stack = null;
	SNode node1 = null;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		stack = new LinkedStack();
		stack.push('A');
		stack.push('B');
		stack.push('C');
		stack.push('D');
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testSize() {
		assertEquals((long) 4, (long) stack.size());
	}

	@Test
	public final void testIsEmpty() {
		assertTrue((!stack.isEmpty()));
		try {
			stack.pop();
			stack.pop();
			stack.pop();
			stack.pop();			
		} catch (StackEmptyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertTrue(stack.isEmpty());
		
	}

	@Test
	public final void testPeek() {
		try {
			assertTrueCharacters( stack.peek(), 'D');
			stack.pop();
			assertTrueCharacters( stack.peek(), 'C');
		} catch (StackEmptyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testPop() {
		try {
			stack.pop();
			assertEquals((long) 3, (long) stack.size());
			stack.pop();
			stack.pop();
			stack.pop();
			assertEquals((long) 0, (long) stack.size());
			stack.pop();
		} catch (StackEmptyException e) {
			// TODO Auto-generated catch block
			assertNotNull("Exception object e not null", e);
		}

	}

	@Test
	public final void testClear(){
		stack.clear();
		assertEquals((long) 0, (long) stack.size());
	}
	
	@Test
	public final void testDeepCopyReversed(){
		LinkedStack rStack = stack.deepCopyReversed();
		try {
			assertEquals( 'A', rStack.pop());
			rStack.pop();
			rStack.pop();
			assertEquals( 'D', rStack.pop());
			assertEquals( 'D', stack.pop());
		} catch (StackEmptyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
    private void assertTrueCharacters( char actual, char expected){
    	Character c1 = new Character( actual );
    	Character c2 = new Character( expected );
    	assertEquals(c2, c1);
    }

}
