/**
 * 
 */
package UnitTests;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import Logueo.LogDeEventos;


public class testLogDeEventos {
	private LogDeEventos log;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		log=new LogDeEventos(3);
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		
	}



	/**
	 * Test method for {@link Logueo.LogDeEventos#createMessage(java.lang.String, int)}.
	 */
	@Test
	public void testCreateMessage() {
		log.createMessage("hola", 0);
		log.createMessage("chau", 1);
		assertEquals(log.getMessage(0),"hola");
		assertEquals(log.getMessage(1),"chau");
	}

	/**
	 * Test method for {@link Logueo.LogDeEventos#addMessage(java.lang.String, int)}.
	 */
	@Test
	public void testAddMessage() {
		log.createMessage("chau", 0);
		log.addMessage("hola", 0);
		log.addMessage("hola", 1);
		log.createMessage("comoAndas", 1);
		assertEquals(log.getMessage(0),"chauhola");
		assertEquals(log.getMessage(1),"comoAndas");
	}

	

	

	/**
	 * Test method for {@link Logueo.LogDeEventos#flushBufferToFile(int)}.
	 */
	@Test
	public void testFlushBufferToFile() {
		
		log.createMessage(new String("hola"), 1);
		log.flushBufferToFile(1);
		try{
			File file=new File("..\\..\\LeagueOfJustice\\CodigoJava\\src\\Logueo\\logFileB.txt");
			
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String texto = br.readLine();
			//System.out.println(texto);
			assertEquals(texto, "hola");
			br.close();
			fr.close();
			
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Test method for {@link Logueo.LogDeEventos#flushBufferToFile(int)}.
	 */
	@Test
	public void testFlushBufferToFileWithOverwrite() {
		this.testFlushBufferToFile();
		log.createMessage(new String("comoandas"), 1);
		log.flushBufferToFile(1);
		try{
			File file=new File("..\\..\\LeagueOfJustice\\CodigoJava\\src\\Logueo\\logFileB.txt");
			
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String texto = br.readLine();
			//System.out.println(texto);
			assertEquals(texto, "comoandas");
			br.close();
			fr.close();
			
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	
	
	


}