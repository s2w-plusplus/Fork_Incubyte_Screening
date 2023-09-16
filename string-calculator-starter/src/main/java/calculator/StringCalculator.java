package calculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class StringCalculator {
	//static variable to maintain state between calls
	private static int callCount=0;
	
	
    public int add(String input) {    
    		callCount++; //incremented for each call to add()
    		String delimiter="," ;  	
    		int length=input.length();
    		int [] array_of_numbers=new int[0];
    
    		//simple regexp to detect use of custom delimiters
    		String regexp="^//.+\n.*";
    		if(input.matches(regexp)) {
    			//these methods are called only when custom delimiters are used
    			input=getModifiedInput(input,extractDelimiters(input));
    		}
    	
    		try{
    				if(length>0) { 
    					 array_of_numbers = Arrays.stream(input.trim().split(delimiter))
    							 .map(String::trim)
    							 .mapToInt(Integer::parseInt)
    							 .toArray();
    				}else return 0;	
    		}catch(NumberFormatException nfe) {
    				nfe.printStackTrace();
    				//to account for unexpected input apart from delimiters
    				throw new RuntimeException("Invalid input");
    		}
    	
    		//building error messages to add negative values which caused the errors
    		StringBuilder exceptionMessage=
    				new StringBuilder("Negative Number(s) not allowed. Unaccepted inputs: ");
    		if(!Arrays.stream(array_of_numbers).allMatch(e -> e>=0)) { 
    				Arrays.stream(array_of_numbers)
    					.filter(e -> e<0)
    					.forEach(e -> exceptionMessage.append(e+" ") );
    				throw new IllegalArgumentException(exceptionMessage.toString());
    		}else return Arrays.stream(array_of_numbers).filter(e -> e<=1000).sum(); //no error case
    }

    //function to return callCount
    public int GetCalledCount() {
		return callCount;
	}
	
    //method to get custom delimiter(s) passed in the input string through the list
    public static List<String> extractDelimiters(String input){
		
    	List<String> delimiters=new ArrayList<>();
		int inputIndex=2;
		int delimitIndex=-1;
		char currentDelimiter=',';
		char currentChar;
		while(input.charAt(inputIndex)!='\n') {
				currentChar=input.charAt(inputIndex);
				
				if( currentChar == currentDelimiter) //when same delimiter encountered -> append to the string in the same entry 
					delimiters.set(delimitIndex,delimiters.get(delimitIndex)
							.concat(Character.toString(currentChar)));
				else { 
					++delimitIndex; //when new delimiter encountered -> new entry in the list
					delimiters.add(Character.toString(currentChar));
					currentDelimiter=currentChar; 
				}
				inputIndex++; //take the next input character
		}
		return delimiters;
	}
	
    public static String getModifiedInput(String inputString,List<String> validDelimiters) {
		
			int start=2; 
			String delimiter;
			for(String str: validDelimiters)
				start+=str.length(); // calculating the offset for skipping-over newLine
			inputString=inputString.substring(start+1);
			while(!validDelimiters.isEmpty()) {
				delimiter=validDelimiters.remove(0);
				inputString=inputString.replace(delimiter,","); //replacing given delimiters with default "," in each iteration
			}
			return inputString; //returning modified string to the previously written code
	}
}