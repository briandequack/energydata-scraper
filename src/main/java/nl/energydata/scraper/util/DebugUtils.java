package nl.energydata.scraper.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class DebugUtils {

	
	// Depreciated
    public static BigDecimal numStrToBigDec(String numString) {
        try {
            BigDecimal number = new BigDecimal(numString.replace(",", "."));
            return number;
        } catch (NumberFormatException e) {
            return null;
        }
    }
	// Depreciated
    public static BigDecimal getNumberBetweenLabels(String text, String start, String end) {
        return getNumberBetweenLabels(text, start, end, 1);
    }

 // Depreciated
    public static BigDecimal getNumberBetweenLabels(String text, String start, String end, int occurrence) {
        String sanitizedStart = start.replaceAll("\\s+", ""); 
        String sanitizedEnd = end.replaceAll("\\s+", ""); 

        Pattern pattern = Pattern.compile("(?s)" + Pattern.quote(sanitizedStart) + "\\s*(.*?)\\s*" + Pattern.quote(sanitizedEnd));
        Matcher matcher = pattern.matcher(text.replace(" ", "")); 

        int count = 0;
        while (matcher.find()) {
            count++;
            if (count == occurrence) {
                String numberAsString = matcher.group(1).trim();
                try {
                    // replace comma with period if necessary
                    numberAsString = numberAsString.replace(',', '.');
                    BigDecimal number = new BigDecimal(numberAsString);
                    System.out.println("Number found : " + number);
                    return number;
                } catch (NumberFormatException e) {
                    System.out.println("The found text is not a number: " + numberAsString);
                    return null;
                }
            }
        }
        System.out.println("Text format changed :");
        return null;
    }


	
	public static List<String> getClassAndMethodNamesInRange(int startIndex, int endIndex) {
	    List<String> result = new ArrayList<>();
	    StackTraceElement[] stElements = Thread.currentThread().getStackTrace();

	    int count = 0;
	    for (int i = 1; i < stElements.length; i++) {
	        StackTraceElement ste = stElements[i];
	        String mainPackageName = MainPackageSingleton.getInstance().getMainPackageName();
	        if (ste.getClassName().startsWith(mainPackageName) && !ste.getClassName().equals(DebugUtils.class.getName()) && ste.getClassName().indexOf("java.lang.Thread") != 0) {
	            if (count >= startIndex && count <= endIndex) {
	                String className = ste.getClassName().substring(ste.getClassName().lastIndexOf(".") + 1);
	                String methodName = ste.getMethodName();
	                String classNameAndMethodName = className + "." + methodName;
	                result.add(classNameAndMethodName);            
	            }
	            count++;
	        }
	    }

	    Collections.reverse(result); 

	    return result;
	}

    
	public static String joinListWithSeparator(List<String> list, String separator) {
	    return String.join(separator, list);
	}
	
    public static void randomSleep(int min, int max) {
        Random rand = new Random();
        try {
        	int maxMil = max*1000;
        	int minMil = min*1000;
        	
            int sleepTime = rand.nextInt((maxMil - minMil) + 1) + minMil; 
    
            
            System.out.println("Sleeping for " + sleepTime + " seconds...");
            Thread.sleep(sleepTime);
            System.out.println("Awake after sleeping for " + sleepTime + " seconds");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
	
    
    



}

