/**
 * 
 */
package com.jhu.ds.lab1.langcheck;

/**
 * @author  ARAVIND RAVINDRANATH
 * @version 1.0
 * 
 * 			Enumerated representation of Languages against which a string is 
 *          analyzed to see if it is a legal and valid. More languages can be added to Enum 
 *          type making it extensible.
 */
public enum EnumLanguageType {
	L1(1), //{ w: w contains equal numbers of A's and B's (in any order) and no other characters} 
    L2(2), //{ w: w is of the form AnBn, for some n > 0 } 
    L3(3), //{ w: w is of the form AnB2n, for some n > 0 } 
    L4(4), //{ w: w is of the form (AnBm)p, for some m,n,p > 0 } 
    L5(5), //{ w: w contains equal numbers of C's and D's (in any order) and no other characters} 
    L6(6), //{ w: w is of the form CnDn for some n > 0 }
	L7(7); //{ w: w is of the form BnAn for some n > 0 }
	
	private int id = 0;
	private EnumLanguageType(int i){
		this.id = i;
	}
	
	@Override
	public String toString() {
		return new Integer(this.id).toString();
	}	

	public static EnumLanguageType getLangType(int i) {
		EnumLanguageType langType = null;
		switch (i){
			case 0:
				langType = EnumLanguageType.L1;
				break;
			case 1:
				langType = EnumLanguageType.L2;				
				break;
			case 2:
				langType = EnumLanguageType.L3;				
				break;
			case 3:
				langType = EnumLanguageType.L4;				
				break;
			case 4:
				langType = EnumLanguageType.L5;				
				break;
			case 5:
				langType = EnumLanguageType.L6;				
				break;	
			case 6:
				langType = EnumLanguageType.L7;				
				break;					
			default:
				break;
		}
		return langType;
	}	

	public int getLangType(){
		return this.id;
	}
}
