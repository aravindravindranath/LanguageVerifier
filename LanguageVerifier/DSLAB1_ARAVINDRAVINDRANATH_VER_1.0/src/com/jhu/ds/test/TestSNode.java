/**
 * 
 * 
 */
package com.jhu.ds.test;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.jhu.ds.lab1.reuse.SNode;

/**
 * @author  ARAVIND RAVINDRANATH
 * @version 1.0
 * 
 * Test Suite to test Class SNode
 */
public class TestSNode {
    
	SNode node1 = null;
	SNode node2 = null;
	SNode node3 = null;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		node1 = new SNode('A', null);
		node2 = new SNode('B', null);
		node1.setNextNode(node2);
		node3 = new SNode('C', null);
		node2.setNextNode(node3);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.jhu.ds.lab1.reuse.SNode#getNode()}.
	 */
	@Test
	public final void testGetNode() {
		assertEquals(node1, node1.getNode());
		assertNotEquals(node1, node2.getNode());
	}

	/**
	 * Test method for {@link com.jhu.ds.lab1.reuse.SNode#getNextNode()}.
	 */
	@Test
	public final void testGetNextNode() {
		assertEquals(node2, node1.getNextNode());
		assertNotEquals(node2, node2.getNextNode());
	}

	/**
	 * Test method for {@link com.jhu.ds.lab1.reuse.SNode#getData()}.
	 */
	@Test
	public final void testGetData() {
		assertEquals('C', node3.getData());
		assertNotEquals('A', node2.getData());		
	}

}
