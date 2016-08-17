package main.java;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Path("anagrama")
public class AnagramaREST   {

	@GET
	@Produces("text/plain")
	public String getText() {
		return "My First RESTful Servivce....";
	}

	@GET
	@Path("findWords/{description}")
	@Produces(MediaType.TEXT_PLAIN)
	public String findWords(@PathParam("description") String pWord) throws Exception {

        Dictionary dic =  Dictionary.getInstance();
		String strmatch;
		StringBuilder strmatchbuilder = new StringBuilder();
		StringBuilder builder = new StringBuilder();
		StringBuilder word = new StringBuilder(Stream.of(pWord.split("")).sorted().collect(Collectors.joining())); 
		ArrayList<String> dictionary = dic.getDictionary("http://www.math.sjsu.edu/~foster/dictionary.txt");

		
		for (int i = 1; i < dictionary.size(); i++) {
		
			if(dictionary.get(i).length() == word.length()){
			builder.append(Stream.of(dictionary.get(i).split("")).sorted().collect(Collectors.joining()));

			if (builder.toString().equals(word.toString()))
				strmatchbuilder.append(" " + dictionary.get(i));

			builder.delete(0, builder.length());
			}
		}

		strmatch = strmatchbuilder.toString();

		return strmatch;
	}
	
	@GET
	@Path("findWords2/{description}")
	@Produces(MediaType.TEXT_PLAIN)
	public String findWords2(@PathParam("description") String pWord) throws Exception {


        ExecutorService exe = Executors.newFixedThreadPool(5);
		
		MyRunnable myRunnable = new MyRunnable(pWord);
		
		Future<String> future = exe.submit(myRunnable);		
		
		exe.shutdown();
		exe.awaitTermination(Long.MAX_VALUE , TimeUnit.NANOSECONDS);
		
		
		return String.valueOf(future.get()) ;
		 

	}

}
