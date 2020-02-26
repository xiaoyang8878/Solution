package Solution.Solution;

import java.util.ArrayList;

public class Solution {
	
	public static void main(String[] args) {
//		System.out.println(wordBreakRec("ilikesamsung"));
//		System.out.println(wordBreakDP("ilikesamsung"));
		wordBreakPrintAll("ilikesamsung");
//		System.out.println(wordBreakRec("samsungandmango"));
//		System.out.println(wordBreakDP("samsungandmango"));
//		wordBreakPrintAll("samsungandmango");
//		System.out.println(wordBreakRec("samsungandmangok"));
//		System.out.println(wordBreakDP("samsungandmangok"));
//		wordBreakPrintAll("samsungandmangok");
	}
	
	public static boolean wordBreakRec(String s){
		int len = s.length();
		if(len == 0){
			return true;
		}
		
		// DFS
		// Try all prefixes of lengths from 1 to size
		for(int i=1; i<=len; i++){
			// The parameter for dictionaryContains is s.substring(0, i)
	        // s.substring(0, i) which is prefix (of input string) of
	        // length 'i'. We first check whether current prefix is in
	        // dictionary. Then we recursively check for remaining string
	        // s.substring(i) which is suffix of length size-i
			if(dictionaryContains(s.substring(0, i)) && wordBreakRec(s.substring(i))){
				return true;
			}
		}
		
		// If we have tried all prefixes and none of them worked
		return false;
	}
	
	// 打印出所有組合，因為要打印出所有組合而不只是判斷能否，所以只能用dfs
	public static void wordBreakPrintAll(String s){
		ArrayList<String> al = new ArrayList<String>();
		wordBreakRec2(s, al);
	}

	public static void wordBreakRec2(String s, ArrayList<String> al){
		int len = s.length();
		if(len == 0){
			System.out.println(al);
			return;
		}
		
		// DFS
		for(int i=1; i<=len; i++){
			String substr = s.substring(0, i);
			if(dictionaryContains(substr)){
				al.add(substr);
				wordBreakRec2(s.substring(i), al);
				al.remove(al.size()-1);
			}
		}
	}
	
	private static boolean dictionaryContains(String word){
		String[] dict = {"mobile","samsung","sam","sung","man","mango",
                			"icecream","and","go","i","like","ice","cream"};
		for(int i=0; i<dict.length; i++){
			if(dict[i].equals(word)){
				return true;
			}
		}
		return false;
	}
	
	// Returns true if string can be segmented into space separated
	// words, otherwise returns false
	public static boolean wordBreakDP(String s){
		int len = s.length();
		if(len == 0){
			return true;
		}
		
		// Create the DP table to store results of subproblems. The value wb[i]
	    // will be true if s[0..i-1] can be segmented into dictionary words,
	    // otherwise false.
		boolean[] wb = new boolean[len+1];
		for(int i=1; i<=len; i++){
			// if wb[i] is false, then check if current prefix can make it true.
	        // Current prefix is "s.substring(0, i)"
			if(wb[i]==false && dictionaryContains(s.substring(0, i))){
				wb[i] = true;
			}
			
			// wb[i] is true, then check for all substrings starting from
	        // (i+1)th character and store their results.
			if(wb[i] == true){
				if(i == len){		// If we reached the last prefix
					return true;
				}
				for(int j=i+1; j<=len; j++){
					// Update wb[j] if it is false and can be updated
	                // Note the parameter passed to dictionaryContains() is
	                // substring starting from index 'i' to index 'j-1'
					if(wb[j]==false && dictionaryContains(s.substring(i, j))){
						wb[j] = true;
					}
					if(j==len && wb[j]==true){	// If we reached the last character
						return true;
					}
				}
			}
		}
		
//		for(int i=1; i<=len; i++){
//			System.out.print(wb[i] + " ");
//		}
		
		// If we have tried all prefixes and none of them worked
		return false;
	}
	
}
