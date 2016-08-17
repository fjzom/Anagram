package test.java;

import static org.junit.Assert.*;

import org.junit.Test;

import main.java.AnagramaREST;

public class JUnitServiceTest {

	@Test
	public void test() throws Exception {
	AnagramaREST objAnagramaService =  new AnagramaREST();
		
		try {
			assertEquals(" amor maro moar mora omar oram orma roam roma", objAnagramaService.findWords("ramo"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
