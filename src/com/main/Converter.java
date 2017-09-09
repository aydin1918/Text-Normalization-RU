package com.main;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Converter extends Settings {
	
	protected String Result = ""; 

	public Converter() {

	}

	public Converter(String text) {
		DetectType(text);
	}

	/*
	 * Detect class of the string DATE, PRICE, DIGITS
	 */
	private void DetectType(String text) {
		if (text.contains(" ") == true) {
			//Utils.DEBUG("yes");
		} else {
			if (DetectChar(text) == true)
			{
				ConvertSimple(text);
			}
			else
			{
				Result = "";
			}
		}
	}

	private void ConvertSimple(String text) {
		String result = "";
		
		text = ChangeSomeChar(text);
		
		String[] array = text.trim().split("");
		
		if (array.length == 0)
		{
			result = "";
		}
		if (array.length == 1)
		{
			try
			{
			  result = SimpleDigit[Integer.parseInt(array[0])];
			}
			catch (Exception e) 
			{
			   result = "";
			}
		}
		if (array.length == 2)
		{
			if (array[0].equals("1")) {
				result = decadeFirst[Integer.parseInt(array[1])];
				
			} else {
				result = decade[Integer.parseInt(array[0])] + " "
						+ SimpleDigit[Integer.parseInt(array[1])];
			}
		}
		if (array.length == 3)
		{
			result = hundred[Integer.parseInt(array[0])] + " "
					+ decade[Integer.parseInt(array[1])] + " "
					+ SimpleDigit[Integer.parseInt(array[2])];
			result = result.replace("  ", " ");
		}
		if (array.length == 4)
		{
			 if (array[0].equals("1"))
			 {
				 result =  thousand[Integer.parseInt(array[0])] + " "
					        + hundred[Integer.parseInt(array[1])] + " "
							+ decade[Integer.parseInt(array[2])] + " "
							+ SimpleDigit[Integer.parseInt(array[3])];
					result = result.replace("   ", " ");
					result = result.replace("  ", " ");
			 }
			 else
			 {
				 result =  hundred[Integer.parseInt(array[0])] + " "
					        + hundred[Integer.parseInt(array[0])] + " "
							+ decade[Integer.parseInt(array[1])] + " "
							+ SimpleDigit[Integer.parseInt(array[2])];
					result = result.replace("  ", " ");
			 }
		}

		//Utils.DEBUG(result);
		Result = result;
	}

	
	private String ChangeSomeChar(String text)
	{
		text = text.replace("-", "");
		return text;
	}
	
	private boolean DetectChar(String text)
	{
		Pattern p = Pattern.compile("[0-9]+");  
        Matcher m = p.matcher(text);  
        return m.matches();
	}
}
