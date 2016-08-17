package main.java;

import java.net.URL;
import java.net.URLConnection;
import java.io.*;
import java.util.ArrayList;

public class Dictionary  {

	private static final Dictionary instance = new Dictionary(); 
	
	
	private Dictionary() {
	}

	
	public static Dictionary getInstance(){
		
		return instance;
		
	}
	
	public ArrayList<String> getDictionary(String url) throws Exception {
		URL website = new URL(url);
		ArrayList<String> dictionaryList = new ArrayList<>();
		URLConnection connection = website.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

		StringBuilder response = new StringBuilder();
		String inputLine;

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
			dictionaryList.add(inputLine);
		}
		in.close();

		return dictionaryList;
	}

}
