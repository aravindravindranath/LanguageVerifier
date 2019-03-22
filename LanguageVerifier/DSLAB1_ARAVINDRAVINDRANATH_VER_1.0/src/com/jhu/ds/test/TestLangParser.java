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

import com.jhu.ds.lab1.langcheck.EnumLanguageType;
import com.jhu.ds.lab1.langcheck.LangParser;

/**
 * 
 * @author 	ARAVIND RAVINDRANATH
 * @version 1.0
 */

public class TestLangParser {

	LangParser cut = new LangParser();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		cut.clear_all_stacks();
	}
	
	
	//Helper class to fill stacks from strings
	private void populate_stacks(String s){
		for (int i = 0; i < s.length(); i++) {
			cut.fill_stacks(s.charAt(i));
		}
	}
	
	@Test
	public final void testCheckNDetectLangTypeL1() {
		
		populate_stacks("AABABB");
		assertArrayEquals( new int[]{ 1, 0, 0, 0, 0, 0, 0 }, 
								cut.checkNDetectLangType());
		//fail("Not yet implemented"); // TODO
	}


    @Test
	public final void testCheckNDetectLangTypeL1notL2() {

    	populate_stacks("AABBAABB");			
		assertArrayEquals( new int[]{ 1, 0, 0, 4, 0, 0, 0 }, 
								cut.checkNDetectLangType());
		//fail("Not yet implemented"); // TODO
	}

    
	@Test
	public final void testNotCheckNDetectLangTypeL1L2() {

		populate_stacks("AABBAABBB");
		assertArrayEquals( new int[]{ 0, 0, 0, 0, 0, 0, 0 }, 
								cut.checkNDetectLangType());
		//fail("Not yet implemented"); // TODO
	}
	
	@Test
	public final void testCheckNDetectLangTypeL4() {

		populate_stacks("AABBBBAABBBB");
		assertArrayEquals( new int[]{ 0, 0, 0, 4, 0, 0, 0 }, 
								cut.checkNDetectLangType());
		//fail("Not yet implemented"); // TODO
	}
	
	@Test
	public final void testCheckNDetectLangTypeL2() {

		populate_stacks("AAAAABBBBB");
		assertArrayEquals( new int[]{ 1, 2, 0, 4, 0, 0, 0 }, 
								cut.checkNDetectLangType());
		//fail("Not yet implemented"); // TODO
	}	

	@Test
	public final void testCheckNDetectLangTypeL4_booleancall(){
		populate_stacks("AABB");
		assertTrue(cut.checkNDetectLangType(EnumLanguageType.L4));
		cut.clear_all_stacks();
		cut.clearStackBckup();
		
		populate_stacks("AABBBBAABBBB");	
		assertTrue(cut.checkNDetectLangType(EnumLanguageType.L4));
		cut.clear_all_stacks();
	}

	@Test
	public final void testCheckNDetectLangTypeL3() {
		
		populate_stacks("AABBBB");
		assertArrayEquals( new int[]{ 0, 0, 3, 4, 0, 0, 0 }, 
								cut.checkNDetectLangType());
		//fail("Not yet implemented"); // TODO
	}	
	
	
	@Test
	public final void testCheckNDetectLangTypeL5_booleancall(){
		populate_stacks("CCDD");
		assertTrue(cut.checkNDetectLangType(EnumLanguageType.L5));
		cut.clear_all_stacks();
		cut.clearStackBckup();
		
		populate_stacks("CCDDDDCCDDDD");
		assertTrue(!cut.checkNDetectLangType(EnumLanguageType.L5));
		cut.clear_all_stacks();
	}

	@Test
	public final void testCheckNDetectLangTypeL6_booleancall(){
		populate_stacks("CCDD");
		assertTrue(cut.checkNDetectLangType(EnumLanguageType.L6));
		cut.clear_all_stacks();
		cut.clearStackBckup();
		
		populate_stacks("CCDDDDCCDDDD");
		assertTrue(!cut.checkNDetectLangType(EnumLanguageType.L6));
		cut.clear_all_stacks();
	
		populate_stacks("CDCD");
		assertTrue(!cut.checkNDetectLangType(EnumLanguageType.L6));
		cut.clear_all_stacks();
		cut.clearStackBckup();		
		
	}
	
	@Test
	public final void testCheckNDetectLangTypeL3_booleancall(){
		populate_stacks("ABABABBBB");
		assertTrue( !cut.checkNDetectLangType(EnumLanguageType.L3));
		cut.clear_all_stacks();
		cut.clearStackBckup();
		
		cut.add_to_stackB('B');
		cut.add_to_stackB('B');		
		cut.add_to_stackB('B');
		cut.add_to_stackB('B');
		cut.add_to_stackStr('B');
		cut.add_to_stackStr('B');
		cut.add_to_stackStr('B');
		cut.add_to_stackStr('B');
		assertTrue( !cut.checkNDetectLangType(EnumLanguageType.L3));
		cut.clear_all_stacks();
		cut.clearStackBckup();
		
	}
	
	@Test
	public final void testCheckNDetectLangTypeL7_booleancall(){
		populate_stacks("ABABABBBB");
		assertTrue( !cut.checkNDetectLangType(EnumLanguageType.L7));
		cut.clear_all_stacks();
		cut.clearStackBckup();
		
		populate_stacks("BBBAAA");
		assertTrue( cut.checkNDetectLangType(EnumLanguageType.L7));
	}	
	
	@Test
	public final void testCheckNDetectLangTypeL1alt_booleancall(){
		populate_stacks("ABAB");
		assertTrue( cut.checkNDetectLangType(EnumLanguageType.L1));
		cut.clear_all_stacks();
		cut.clearStackBckup();
		
		populate_stacks("ABABA");
		assertFalse( cut.checkNDetectLangType(EnumLanguageType.L1));
		cut.clear_all_stacks();
		cut.clearStackBckup();
		
		//empty string
		assertFalse( cut.checkNDetectLangType(EnumLanguageType.L1));
		cut.clear_all_stacks();
		cut.clearStackBckup();
		
		populate_stacks("CDCD");
		assertFalse( cut.checkNDetectLangType(EnumLanguageType.L1));
		cut.clear_all_stacks();
		cut.clearStackBckup();
	}
	
	@Test
	public final void testCheckNDetectLangTypeL5alt_booleancall(){
		populate_stacks("CCDD");
		assertTrue(cut.checkNDetectLangType(EnumLanguageType.L5));
		cut.clear_all_stacks();
		cut.clearStackBckup();
		
		populate_stacks("CCDDDDCCDDDD");		
		assertTrue(!cut.checkNDetectLangType(EnumLanguageType.L5));
		cut.clear_all_stacks();
		cut.clearStackBckup();
		
		//empty string
		assertTrue(!cut.checkNDetectLangType(EnumLanguageType.L5));
		cut.clear_all_stacks();
		cut.clearStackBckup();
		
		
		populate_stacks("AABB");
		assertTrue(!cut.checkNDetectLangType(EnumLanguageType.L5));
		cut.clear_all_stacks();
		cut.clearStackBckup();
	}
	
}
