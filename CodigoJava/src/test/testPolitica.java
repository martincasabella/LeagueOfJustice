/**
 * 
 */
package test;

import static org.junit.Assert.*;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class testPolitica {
	private int[] listaTest;
	private monitor.Politica politica;
	private int[] prioridadesBajada={1,2,3,4};
	private int[] prioridadesSubida={1,2,3,4};
	
	
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
		listaTest=new int[5];
		
		politica=new monitor.Politica(1);
		listaTest[0]=1;
		listaTest[1]=0;
		listaTest[2]=0;
		listaTest[3]=0;
		listaTest[4]=1;
		politica.setPrioridades(prioridadesSubida, prioridadesBajada);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		//listaTest.clear();
	}

	/**
	 * Test method for {@link monitor.Politica#Politica(int)}.
	 */
	@Test
	public void testgetModo() {
		politica.setModo(1);
		assertEquals(politica.getModo(), 1);
	}
	
	/**
	 * Test method for {@link monitor.Politica#cualDisparar(java.util.List)}.
	 */
	@Test
	public void testCualDispararCon1ElementoEnLista() {
		//listaTest.clear();
		listaTest=new int[6];
		listaTest[0]=1; //Esta es la unica transicion que se puede disparar
		listaTest[1]=0;
		listaTest[2]=0;
		listaTest[3]=0;
		listaTest[4]=0;
		listaTest[5]=0;
		politica.setModo(0);
		for(int i=0;i<10000;i++){ //Pruebo 10000 veces
			try{
				  int indice= politica.cualDisparar(listaTest);
				  if(indice!=0){ 
					  //Eligio otra
					  fail("Indice distinto de cero");
				  }
				  
			}
			catch(IndexOutOfBoundsException e){
				fail("Genero error");
			}
		}
		assertEquals(politica.cualDisparar(listaTest),0);
	}

	/**
	 * Test method for {@link monitor.Politica#cualDisparar(java.util.List)}.
	 */
	@Test
	public void testCualDispararModo0() {
		politica.setModo(0);
		assertEquals(listaTest.length,5);
		//En modo aleatorio puede disparar la 0 o la 4
		int indice=0;
		for(int i=0;i<5000000;i++){
			try{
				  indice = politica.cualDisparar(listaTest);
				  if(indice!=0 & indice != 4){
					  //No eligio ni la 0 ni la 4
					  fail("Indice distinto de cero o cuatro");
				  }
				  
			}
			catch(IndexOutOfBoundsException e){
				fail("Genero error");
			}
		}
		if(indice==0){ 
			assertEquals(indice,0);
		}
		else if(indice==4){
		    assertEquals(indice,4);
		}
		else{
			System.out.println(indice);
		}
	}
	
	/**
	 * Test method for {@link monitor.Politica#cualDisparar(java.util.List)}.
	 */
	@Test
	public void testCualDispararModo1() { //Primero suben.
		politica.setModo(1);
		assertEquals(listaTest.length,5);
		//Debe disparar unicamente la 4. Porque es de mayor prioridad que la 0
		int indice=0;
		for(int i=0;i<100000;i++){
			try{
				  indice = politica.cualDisparar(listaTest);
				  if(indice != 4){
					  //No eligio la 4
					  fail("Indice distinto de cuatro");
				  }
				  
			}
			catch(IndexOutOfBoundsException e){
				fail("Genero error");
			}
		}
	
		    assertEquals(indice,4);
		
	}
	
	/**
	 * Test method for {@link monitor.Politica#cualDisparar(java.util.List)}.
	 */
	@Test
	public void testCualDispararModo2() { //Primero bajan. //Idem a primero suben el funcionamiento del test
		politica.setModo(2);
		assertEquals(listaTest.length,5);
		int indice=0;
		for(int i=0;i<100000;i++){
			try{
				  indice = politica.cualDisparar(listaTest);
				  if(indice != 4){
					  fail("Indice distinto de cuatro");
				  }
				  
			}
			catch(IndexOutOfBoundsException e){
				fail("Genero error");
			}
		}
	
		    assertEquals(indice,4);
	}
	
	
	
	
	/**
	 * Test method for {@link monitor.Politica#cualDisparar(java.util.List)}.
	 */
	@Test
	public void testCualDispararModo1Aleatorio() { //Primero suben pero ejecuta aleatorio por no tener sensibilizadas transiciones prioritarias.
		politica.setModo(1); //Primero suben
		//listaTest.clear();
		listaTest=new int[5];
		listaTest[0]=1; //Esta se debe disparar 
		listaTest[1]=0;
		listaTest[2]=0;
		listaTest[3]=0;
		listaTest[4]=0;		
		assertEquals(listaTest.length,5);
		int indice=0;
		for(int i=0;i<100000;i++){
			try{
				  indice = politica.cualDisparar(listaTest);
				  if(indice != 0){
					  //No eligio la cero
					  fail("Indice distinto de cuatro");
				  }
				  
			}
			catch(IndexOutOfBoundsException e){
				fail("Genero error");
			}
		}
	
		    assertEquals(indice,0);
		
	}
	
	

}
