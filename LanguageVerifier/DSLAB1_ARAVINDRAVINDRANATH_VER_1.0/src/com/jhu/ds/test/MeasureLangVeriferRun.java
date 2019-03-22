package com.jhu.ds.test;

import com.jhu.ds.lab1.langcheck.EnumLanguageType;
import com.jhu.ds.lab1.langcheck.LangParser;

/**
 * @author Aravind Ravindranath
 * @version 1.0
 * 
 * This class contains methods which run language verifier methods with
 * strings of different sizes and then take runtime measurements in 
 * nanoseconds.
 * 
 */

public class MeasureLangVeriferRun {

	//private StackRuntime[] runtime = new StackRuntime[100];
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MeasureLangVeriferRun mLVRun = new MeasureLangVeriferRun();
		mLVRun.measure_method_run(EnumLanguageType.L1);
		mLVRun.measure_method_run(EnumLanguageType.L2);
		mLVRun.measure_method_run(EnumLanguageType.L3);
		mLVRun.measure_method_run(EnumLanguageType.L4);
		mLVRun.measure_method_run(EnumLanguageType.L7);
	}

	
	private void measure_method_run(EnumLanguageType ltype){
        StackRuntime[] runtime = new StackRuntime[100];
        LangParser cut = new LangParser();
        for (int i = 0; i < 16; i++) {
        	measureRuntime(runtime, cut, ltype, i, build_string((int) Math.pow(2, i+1)));
		}
		display_metrics(ltype, runtime, 16);
		clear_metrics(runtime);
	}

	/**
	 * @param runtime
	 * @param cut
	 */
	private void measureRuntime(StackRuntime[] runtime, LangParser cut, EnumLanguageType ltype, 
								int i, String s) {
		long start;
		long end;
		long totalTime;
		populate_stacks(s, cut);
		start = System.nanoTime();
		cut.checkNDetectLangType(ltype);
		end = System.nanoTime();
		totalTime = end - start;
		runtime[i] = new StackRuntime(s.length(), totalTime);
		cut.clear_all_stacks();
		cut.clearStackBckup();
	}
	
	//Helper class to fill stacks from strings
	private void populate_stacks(String s, LangParser lp){
		for (int i = 0; i < s.length(); i++) {
			lp.fill_stacks(s.charAt(i));
		}
	}
	
	private String build_string(int length){
		String str = "";
		for (int i = 0; i < length; i++) {
			if( Math.random()/2 == 0){
				str+="A";
			}else {
				str+="B";
			}
		}
		return str;
	}
	
	private void display_metrics(EnumLanguageType ltype, StackRuntime[] runtime, int max){
		System.out.println("Language Type L" + ltype.toString());
		for (int i = 0; i < max; i++) {
			System.out.println("Size: " + runtime[i].getSize() + 
						" Runtime: " + runtime[i].getRuntime()); ;
			
		}
		System.out.println("\n");
	}
	
	private void clear_metrics(StackRuntime runtime[]){
		for (int i = 0; i < runtime.length; i++) {
			runtime[i] = null;
		}
	}
	
}
