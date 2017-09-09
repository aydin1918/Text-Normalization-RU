package com.main;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils extends Converter {
	
	public static String PathRoot = System.getProperty("user.dir");
	public static String folderName = "\\data\\";

	public Utils() throws FileNotFoundException, IOException 
	{
		ReadFile(PathRoot + folderName + "ru_test.txt"); //ru_test.txt
	}

	private void ReadFile(String path) throws FileNotFoundException, IOException {
		
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "Cp1251"))) {

			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
				//System.out.println("count: " + count + " -- " + sCurrentLine);
				ParseString(sCurrentLine);
				
			}
		}
	}
	
	private void ParseString(String text)
	{
		String[] first = text.replace("\"", "").split(",",3);
		//DEBUG(1);
		PrintArray(first);
	}
	
	public static void DEBUG(Object o)
	{
		System.out.println(o);
	}
	
	private static boolean CheckString(String userNameString){  
        Pattern p = Pattern.compile("[^?0-9]+");  
        Matcher m = p.matcher(userNameString);  
        return m.matches();  
    }  
	
	protected static void PrintArray(String[] array)
	{
		
		if (CheckString(array[2]) == true)
		{
			DEBUG("\"" +array[0] + "_" + array[1] + "\",\"" + array[2] + "\"");
		}
		else
		{
			Converter objC = new Converter(array[2]);
			DEBUG("\"" +array[0] + "_" + array[1] + "\",\"" + objC.Result + "\"");
		}
		
		//DEBUG(checkWithRegExp(array[2]));
	}
	
}
