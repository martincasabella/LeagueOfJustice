/**
 * 
 */
package test;

import static org.junit.Assert.*;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import logueo.Logger;
import monitor.Cola;
import monitor.LogicaTemporal;
import monitor.Monitor;
import monitor.OperacionesMatricesListas;
import monitor.RedDePetri;


public class testRedDePetri {
	private RedDePetri redTest;
	private OperacionesMatricesListas operaciones;
	private int[] transicionesSensibilizadasTest;
	private String redExcel1="./RedesParaTest/RedTest1/testExcel.xls"; //Path para Linux.
	private String redExcel2="./RedesParaTest/RedTest2/testExcel5.xls"; //Path para Linux.
	private String redExcel3="./RedesParaTest/TestInvariantes1/testExcelRed2Invariantes.xls"; //Path para Linux.
	private String redExcel4="./RedesParaTest/TestInvariantes2/testExcelRed2Invariantes2.xls"; //Path para Linux.
	private String redExcel5="./RedesParaTest/redTemporal/testExcel.xls"; //Path para Linux.
	private Logger log;
	
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
		log=new Logger(3);
		if((System.getProperty("os.name")).equals("Windows 10")){
			if(System.getProperty("user.name").equals("kzAx")){
				 
				    this.redExcel1="..\\src\\RedesParaTest\\RedTest1\\testExcel.xls";
					this.redExcel2="..\\src\\RedesParaTest\\RedTest2\\testExcel5.xls";
					this.redExcel3="..\\src\\RedesParaTest\\TestInvariantes1\\testExcelRed2Invariantes.xls";
					this.redExcel4="..\\src\\RedesParaTest\\TestInvariantes2\\testExcelRed2Invariantes2.xls";
					this.redExcel5="..\\src\\RedesParaTest\\redTemporal\\testExcel.xls"; 
			}
			else{
				this.redExcel1="..\\..\\LeagueOfJustice\\CodigoJava\\src\\RedesParaTest\\RedTest1\\testExcel.xls";
				this.redExcel2="..\\..\\LeagueOfJustice\\CodigoJava\\src\\RedesParaTest\\RedTest2\\testExcel5.xls";
				this.redExcel3="..\\..\\LeagueOfJustice\\CodigoJava\\src\\RedesParaTest\\TestInvariantes1\\testExcelRed2Invariantes.xls";
				this.redExcel4="..\\..\\LeagueOfJustice\\CodigoJava\\src\\RedesParaTest\\TestInvariantes2\\testExcelRed2Invariantes2.xls";
				this.redExcel5="..\\..\\LeagueOfJustice\\CodigoJava\\src\\RedesParaTest\\redTemporal\\testExcel.xls"; 
			}
			
		}
		transicionesSensibilizadasTest=new int[5];
		transicionesSensibilizadasTest[0]=1;
		transicionesSensibilizadasTest[1]=0;
		transicionesSensibilizadasTest[2]=0;
		transicionesSensibilizadasTest[3]=0;
		transicionesSensibilizadasTest[4]=0;
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link monitor.RedDePetri#RedDePetri(java.lang.String)}.
	 */
	@Test
	public void testRedDePetri() {
		redTest= new RedDePetri(this.redExcel1, log);
		assertEquals(redTest.getCantTransiciones(), 4);
	}

	/**
	 * Test method for {@link monitor.RedDePetri#getCantTransiciones()}.
	 */
	@Test
	public void testGetCantTransiciones() {
		redTest= new RedDePetri(this.redExcel1, log);
		assertEquals(redTest.getCantTransiciones(), 4);
		redTest= new RedDePetri(this.redExcel2, log);
		assertTrue(redTest.getCantTransiciones()==5);
		assertFalse(redTest.getCantTransiciones()==4);
	}

	/**
	 * Test method for {@link monitor.RedDePetri#getSensibilizadas()}.
	 */
	@Test
	public void testGetSensibilizadas() {
		redTest= new RedDePetri(this.redExcel1, log);
		assertEquals(redTest.getSensibilizadas()[0], transicionesSensibilizadasTest[0]);
		assertEquals(redTest.getSensibilizadas()[1], transicionesSensibilizadasTest[1]);
		assertEquals(redTest.getSensibilizadas()[2], transicionesSensibilizadasTest[2]);
		assertEquals(redTest.getSensibilizadas()[3], transicionesSensibilizadasTest[3]);
	}

	/**
	 * Test method for {@link monitor.RedDePetri#disparar(int)}.
	 */
	@Test
	public void testDisparar() {
		redTest= new RedDePetri(this.redExcel1, log);
		//System.out.println(redTest.esDisparoValido(redTest.getMarcadoSiguiente(0)));
		assertEquals(redTest.getSensibilizadas()[0],transicionesSensibilizadasTest[0]);
		assertEquals(redTest.getSensibilizadas()[1],transicionesSensibilizadasTest[1]);
		assertEquals(redTest.getSensibilizadas()[2],transicionesSensibilizadasTest[2]);
		assertEquals(redTest.getSensibilizadas()[3],transicionesSensibilizadasTest[3]);
		boolean k=redTest.disparar(0);
		//System.out.println(k);
		transicionesSensibilizadasTest[0]=0;
		transicionesSensibilizadasTest[1]=1;
		assertEquals(redTest.getSensibilizadas()[0],transicionesSensibilizadasTest[0]);
		assertEquals(redTest.getSensibilizadas()[1],transicionesSensibilizadasTest[1]);
		assertEquals(redTest.getSensibilizadas()[2],transicionesSensibilizadasTest[2]);
		assertEquals(redTest.getSensibilizadas()[3],transicionesSensibilizadasTest[3]);
	}

	/**
	 * Test method for {@link monitor.RedDePetri#getMarcadoSiguiente(int)}.
	 */
	@Test
	public void testGetMarcadoSiguiente() {
		int[][] a = { {0}, {2}, {0}, {0} };
		int[][] b = { {1}, {0}, {0}, {0} };
		redTest= new RedDePetri(this.redExcel1, log);
		assertEquals(redTest.getMarcadoSiguiente(0), a);
		redTest= new RedDePetri(this.redExcel2, log);
		//System.out.println(Arrays.deepToString(redTest.getMarcadoSiguiente(4)));
		assertEquals(redTest.getMarcadoSiguiente(4),b);	
	}
	
	@Test (expected=IllegalArgumentException.class) public void testGetMarcadoSiguienteException() {
		int[][] b = { {2}, {0}, {0}, {-2} };
		redTest= new RedDePetri(this.redExcel1, log);
		assertEquals(redTest.getMarcadoSiguiente(4),b);
	}

	/**
	 * Test method for {@link monitor.RedDePetri#esDisparoValido(int[][])}.
	 */
	@Test
	public void testEsDisparoValido() {
		redTest= new RedDePetri(this.redExcel1, log);
		//System.out.println(redTest.esDisparoValido(redTest.getMarcadoSiguiente(0)));
		assertEquals(redTest.esDisparoValido(redTest.getMarcadoSiguiente(0)),true);
		assertEquals(redTest.esDisparoValido(redTest.getMarcadoSiguiente(1)),false);
		assertEquals(redTest.esDisparoValido(redTest.getMarcadoSiguiente(2)),false);
		assertEquals(redTest.esDisparoValido(redTest.getMarcadoSiguiente(3)),false);
		redTest.disparar(0);
		assertEquals(redTest.esDisparoValido(redTest.getMarcadoSiguiente(0)),false);
		assertEquals(redTest.esDisparoValido(redTest.getMarcadoSiguiente(1)),true);
		assertEquals(redTest.esDisparoValido(redTest.getMarcadoSiguiente(2)),false);
		assertEquals(redTest.esDisparoValido(redTest.getMarcadoSiguiente(3)),false);
	}
	

	
	/**
	 * Test method for {@link monitor.RedDePetri#verificarTInvariantes()}.
	 * @throws InterruptedException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * 
	 * El objetivo es probar que el marcado antes de disparar todas las transiciones 
	 * que se encuentren en el vector tinvariant es igual al marcado que se obtiene 
	 * despues de haber disparado dichas transiciones
	 */
	@Test
	public void testVerificarTInvariantes() throws InterruptedException {
		
		redTest=new RedDePetri(this.redExcel3, log); 	//configuro la red a testear los Tinvariantes
		
		int[][] tinvariant=redTest.getTInv(); 		//Guardo en una variable local los Tinvariantes de la red configurada
		int[][] primerMarcado=redTest.getMatrizM();	//Obtengo el marcado actual de la red configurada y lo guardo en la variable local "marcaActual" 
		
		for(int j = 0; j<tinvariant.length; j++){ 	//Recorro todas las filas del vector tinvariant
			while(operaciones.isNotAllZeros(tinvariant[j])){ //Si hay un elemento distinto de cero en esa fila del tinvariant, repetir
		        for(int i = 0; i<tinvariant[j].length; i++){	//Recorro las columnas de esa fila de tinvariant
		        	if(redTest.esDisparoValido(redTest.getMarcadoSiguiente(i))&&(tinvariant[j][i]!=0)&&redTest.getLogicaTemporal().isInWindowsTime(i)){ //Si la transicion i se encuentra sensibilizada y ademas pertenece al vector tinvariant, disparar la transicion
		        		redTest.disparar(i);
		        		tinvariant[j][i]=tinvariant[j][i]-1; //una vez disparada la transicion i, se marca esa transicion como cero en el vector tinvariant
		        	}
		        }
	        } // si tinvariant tiene toda la columna en cero, no hay mas transiciones que disparar, se sale del while.
		
			int[][] marcaActual=redTest.getMatrizM(); //se prueba que el nuevo marcado es igual al marcado anterior, antes de disparar las transiciones
			assertEquals(primerMarcado,marcaActual);
		}
		
	}
	
	
	/**
	 * Test method for {@link monitor.RedDePetri#RedDePetri(java.lang.String)}.
	 */
	@Test
	public void testMatrizH() {
		redTest= new RedDePetri(this.redExcel1,log);
		try{
			Method getMatrizH  = RedDePetri.class.getDeclaredMethod("getMatrizH", null);
			getMatrizH.setAccessible(true);
			int H[][] =(int[][]) getMatrizH.invoke(redTest);
			for(int fila=0; fila<H.length;fila++){
				for(int col=0;col<H[0].length;col++){
					if(H[fila][col]!=0){
						assertEquals(H[fila][col],0);
					}
				}
				
			}
			
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		
	}
	
	
	/**
	 * Test method for {@link monitor.RedDePetri#setTransicionesInmediatas}.
	 */
	@Test
	public void testSetTransicionesInmediatas() {
		
		try{
			redTest= new RedDePetri(this.redExcel1, log);
			int T[] =redTest.getVectorTransicionesInmediatas();
			for(int i=0; i<redTest.getCantTransiciones(); i++){
				if(T[i] != 0){
					//System.out.println(T[i]);
					assertEquals(T[i],1);
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		
	}
	
	
	
	

	/**
	 * Test method for getCantTransiciones - temporal
	 */
	@Test
	public void testTemporalgetCantTransiciones() {
		
		try{
			redTest= new RedDePetri(this.redExcel5, log);
			
			Field f = redTest.getClass().getDeclaredField("logica_temporal");
			f.setAccessible(true);
			LogicaTemporal testPrivateReflection = (LogicaTemporal)f.get(redTest);
			
			Field f2 = testPrivateReflection.getClass().getDeclaredField("cantidad_de_transiciones");
			f2.setAccessible(true);
			int testPrivateReflection2 = (int)f2.get(testPrivateReflection);
			
			assertEquals(testPrivateReflection2,6);
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Fallo por excepcion");
		}
	}
	
	
	/**
	 * Test method for getVectorDeIntervalos - temporal
	 */
	@Test
	public void testTemporalgetVectorDeIntervalos() {
		
		try{
			redTest= new RedDePetri(this.redExcel5, log);
			assertEquals(redTest.getLogicaTemporal().getVectorDeIntervalos()[0][0],0);
			assertEquals(redTest.getLogicaTemporal().getVectorDeIntervalos()[0][1],-1);//[columna][fila]
			assertEquals(redTest.getLogicaTemporal().getVectorDeIntervalos()[1][0],200);
			assertEquals(redTest.getLogicaTemporal().getVectorDeIntervalos()[1][1],-1);
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Fallo por excepcion");
		}
	}
	
	/**
	 * Test method for getVectorDeIntervalos - temporal
	 */
	@Test
	public void testUpdateVectorZ() {
		
		try{
			redTest= new RedDePetri(this.redExcel5, log);
			redTest.getLogicaTemporal().updateVectorZ(redTest.getConjuncionEAndB());
			assertEquals(redTest.getLogicaTemporal().getVectorZ_Actualizado(redTest.getConjuncionEAndB())[0],1);
			assertEquals(redTest.getLogicaTemporal().getVectorZ_Actualizado(redTest.getConjuncionEAndB())[1],0);
			assertEquals(redTest.getLogicaTemporal().getVectorZ_Actualizado(redTest.getConjuncionEAndB())[2],0);
			assertEquals(redTest.getLogicaTemporal().getVectorZ_Actualizado(redTest.getConjuncionEAndB())[3],0);
			assertEquals(redTest.getLogicaTemporal().getVectorZ_Actualizado(redTest.getConjuncionEAndB())[4],0);
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Fallo por excepcion");
		}
	}
	
	
	/**
	 * Test method for getVectorDeIntervalos - temporal
	 */
	@Test
	public void testTemporalgetVectorEstados() {
		
		try{
			redTest= new RedDePetri(this.redExcel5, log);
			int[] tsensandtime=redTest.getLogicaTemporal().getVectorZ_Actualizado(redTest.getSensibilizadas());
			assertEquals(tsensandtime[0],1);
			assertEquals(tsensandtime[1],0);
			assertEquals(tsensandtime[2],0);
			assertEquals(tsensandtime[3],0);
			redTest.disparar(0);
			tsensandtime=redTest.getLogicaTemporal().getVectorZ_Actualizado(redTest.getSensibilizadas());
			assertEquals(tsensandtime[0],0);
			assertEquals(tsensandtime[1],0);
			assertEquals(tsensandtime[2],0);
			assertEquals(tsensandtime[3],0);
			TimeUnit.MILLISECONDS.sleep(300);
			tsensandtime=redTest.getLogicaTemporal().getVectorZ_Actualizado(redTest.getSensibilizadas());
			assertEquals(tsensandtime[0],0);
			assertEquals(tsensandtime[1],1);
			assertEquals(tsensandtime[2],0);
			assertEquals(tsensandtime[3],0);
		}
		catch(Exception e){
			e.printStackTrace();
			fail("Fallo por excepcion");
		}
	}
	
	
	/**
	 * Test method for getVectorDeIntervalos - temporal
	 */
	@Test
	public void testTemporalupdateTimeStamp() {
		
		try{
			redTest= new RedDePetri(this.redExcel5, log);
			int[] transTemporales= new int[redTest.getCantTransiciones()];
			transTemporales=OperacionesMatricesListas.andVector(redTest.getSensibilizadas(), redTest.getLogicaTemporal().construirVectorTransicionesInmediatas());
			redTest.getLogicaTemporal().updateTimeStamp(transTemporales, transTemporales, -1);
			assertEquals(redTest.getLogicaTemporal().getVectorDeIntervalos()[0][0],0);
			assertEquals(redTest.getLogicaTemporal().getVectorDeIntervalos()[0][1],-1);//[columna][fila]
			assertEquals(redTest.getLogicaTemporal().getVectorDeIntervalos()[1][0],200);
			assertEquals(redTest.getLogicaTemporal().getVectorDeIntervalos()[1][1],-1);
			assertEquals(redTest.getLogicaTemporal().isInWindowsTime(0),true);
			assertEquals(redTest.getLogicaTemporal().isInWindowsTime(1),false);
			assertEquals(redTest.getLogicaTemporal().isInWindowsTime(2),false);
			assertEquals(redTest.getLogicaTemporal().isInWindowsTime(3),false);
			assertEquals(redTest.getLogicaTemporal().isInWindowsTime(4),true);
			redTest.disparar(1);
			redTest.disparar(0);
			redTest.disparar(1);
			assertEquals(redTest.getLogicaTemporal().isInWindowsTime(0),true);
			assertEquals(redTest.getLogicaTemporal().isInWindowsTime(1),false);
			assertEquals(redTest.getLogicaTemporal().isInWindowsTime(2),false);
			assertEquals(redTest.getLogicaTemporal().isInWindowsTime(3),false);
			assertEquals(redTest.getLogicaTemporal().isInWindowsTime(4),true);
			TimeUnit.MILLISECONDS.sleep(300);
			assertEquals(redTest.getLogicaTemporal().isInWindowsTime(0),true);
			assertEquals(redTest.getLogicaTemporal().isInWindowsTime(1),true);
			assertEquals(redTest.getLogicaTemporal().isInWindowsTime(2),false);
			assertEquals(redTest.getLogicaTemporal().isInWindowsTime(3),false);
			assertEquals(redTest.getLogicaTemporal().isInWindowsTime(4),true);
			redTest.disparar(1);
			assertEquals(redTest.getLogicaTemporal().isInWindowsTime(0),true);
			assertEquals(redTest.getLogicaTemporal().isInWindowsTime(1),false);
			assertEquals(redTest.getLogicaTemporal().isInWindowsTime(2),false);
			assertEquals(redTest.getLogicaTemporal().isInWindowsTime(3),false);
			assertEquals(redTest.getLogicaTemporal().isInWindowsTime(4),true);
			TimeUnit.MILLISECONDS.sleep(600);
			assertEquals(redTest.getLogicaTemporal().isInWindowsTime(0),true);
			assertEquals(redTest.getLogicaTemporal().isInWindowsTime(1),false);
			assertEquals(redTest.getLogicaTemporal().isInWindowsTime(2),true);
			assertEquals(redTest.getLogicaTemporal().isInWindowsTime(3),false);
			assertEquals(redTest.getLogicaTemporal().isInWindowsTime(4),true);

		}
		catch(Exception e){
			e.printStackTrace();
			fail("Fallo por excepcion");
		}
	}
	
	
	
	/**
	 * Test method for {@link monitor.RedDePetri#getVectorQ()}.
	 */
	@Test
	public void testGetVectorQ() {
		
		try{
			redTest= new RedDePetri(this.redExcel1, log);
			Method getVectorQ_Actualizado  = RedDePetri.class.getDeclaredMethod("getVectorQ_Actualizado", null);
			getVectorQ_Actualizado.setAccessible(true);
			int Q[][]=(int[][])getVectorQ_Actualizado.invoke(redTest);
			assertEquals(Q[0][0],1);
			assertEquals(Q[1][0],0);
			assertEquals(Q[2][0],0);
			assertEquals(Q[3][0],0);
		
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
			
	
	}
	
	/**
	 * Test method for {@link monitor.RedDePetri#getMatrizB()}.
	 */
	@Test
	public void testGetMatrizB() {
		
		try{
			redTest= new RedDePetri(this.redExcel1, log);
			Method getMatrizB_Actualizada  = RedDePetri.class.getDeclaredMethod("getMatrizB_Actualizada", null);
			getMatrizB_Actualizada.setAccessible(true);
			int B[][]=(int[][])getMatrizB_Actualizada.invoke(redTest);
			for(int i=0;i<B.length;i++){
				for(int j=0;j<B[0].length;j++){
					if(B[i][j]!=1){ //Matriz H es cero
						assertEquals(B[i][j],1);
					}
				}
			}
			
			redTest= new RedDePetri(this.redExcel5, log);
			getMatrizB_Actualizada = RedDePetri.class.getDeclaredMethod("getMatrizB_Actualizada", null);
			getMatrizB_Actualizada.setAccessible(true);
			B=(int[][])getMatrizB_Actualizada.invoke(redTest);
			
			Method getMatrizH  = RedDePetri.class.getDeclaredMethod("getMatrizH", null);
			getMatrizH.setAccessible(true);
			int H[][]=(int[][])getMatrizH.invoke(redTest);
			
			Method getVectorQ_Actualizado  = RedDePetri.class.getDeclaredMethod("getVectorQ_Actualizado", null);
			getVectorQ_Actualizado.setAccessible(true);
			int Q[][]=(int[][])getVectorQ_Actualizado.invoke(redTest);
			
			int Htranspuesta[][]=OperacionesMatricesListas.transpuesta(H);
			int Baux[][]=OperacionesMatricesListas.productoMatrices(Htranspuesta, Q);
			for(int i=0; i<Baux.length;i++){
	    		if(Baux[i][0]==0){
	    			Baux[i][0]=1;
	    		}
	    		else{
	    			Baux[i][0]=0;
	    		}
	    	}
			ArrayList<Integer> listaPosiciones=new ArrayList<>();
			
			for(int z=0; z<Baux.length;z++){
				if(Baux[z][0]!=1){
					listaPosiciones.add(z);
				}
			}
			
			for(int i=0;i<B.length;i++){
				for(int j=0;j<B[0].length;j++){
					if(B[i][j]!=1){ 
						if(!listaPosiciones.contains(i)){
							assertEquals(B[i][j],1);
						}
						
					}
				}
			}
		
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
			
	
	}
	
}
