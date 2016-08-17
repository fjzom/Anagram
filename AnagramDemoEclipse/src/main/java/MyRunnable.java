package main.java;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MyRunnable implements Callable<String> {

	public String pWord;
	
	public MyRunnable(String pWord) {
		// TODO Auto-generated constructor stub
		
		this.pWord = pWord;		
		
	}
	
	@Override
	public  String call() throws Exception {
        Dictionary dic =  Dictionary.getInstance();
		String strmatch;
		StringBuilder strmatchbuilder = new StringBuilder();
		StringBuilder builder = new StringBuilder();
		StringBuilder word = new StringBuilder(Stream.of(pWord.split("")).sorted().collect(Collectors.joining())); 
		ArrayList<String> dictionary = dic.getDictionary("http://www.math.sjsu.edu/~foster/dictionary.txt");

		for (int i = 0; i < dictionary.size(); i++) {
			builder.append(Stream.of(dictionary.get(i).split("")).sorted().collect(Collectors.joining()));

			if (builder.toString().equals(word.toString()))
				strmatchbuilder.append(" " + dictionary.get(i));

			builder.delete(0, builder.length());
		}

		strmatch = strmatchbuilder.toString();

		return strmatch;
	}
 

}
