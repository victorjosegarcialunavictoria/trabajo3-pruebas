package etsisi.ems2020.trabajo3.lineadehorizonte;

import java.io.PrintWriter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import junit.framework.TestCase;

public class CiudadTest extends TestCase{
	
	static final PrintWriter OUT = null;

	public CiudadTest(String sTestName) {
		super(sTestName);
	}

	@Before
	public void setUp() throws Exception {		
		
	}

	@Test
	public void testGetLineaHorizonte1() {
		
		LineaHorizonte linea;
		Ciudad c;
		
		try {			
			
			c = new Ciudad();
			c.addEdificio(1,4,3);
			c.addEdificio(2,7,9);
			c.addEdificio(4,4,12);
			c.addEdificio(6,9,8);
			c.addEdificio(11,6,13);
			c.addEdificio(14,2,15);		

			
			linea = c.getLineaHorizonte();			
			assertTrue(linea.getPunto(0).getX()== 1  && linea.getPunto(0).getY()==4);
			assertTrue(linea.getPunto(1).getX()== 2  && linea.getPunto(1).getY()==7);
			assertTrue(linea.getPunto(2).getX()== 6  && linea.getPunto(2).getY()==9);
			assertTrue(linea.getPunto(3).getX()== 8  && linea.getPunto(3).getY()==7);   
			assertTrue(linea.getPunto(4).getX()== 9  && linea.getPunto(4).getY()==4);
			assertTrue(linea.getPunto(5).getX()== 11 && linea.getPunto(5).getY()==6);
			assertTrue(linea.getPunto(6).getX()== 13 && linea.getPunto(6).getY()==0);
			assertTrue(linea.getPunto(7).getX()== 14  && linea.getPunto(7).getY()==2);
			assertTrue(linea.getPunto(8).getX()== 15  && linea.getPunto(8).getY()==0);
			
		} catch (Exception e) {			
			fail("Test failed");
		}
	}
	
	@Test
	public void testGetLineaHorizonte2() {
		LineaHorizonte linea;
		Ciudad c;
		
		try {			
			
			c = new Ciudad();
			c.addEdificio(3,5,6);
			c.addEdificio(4,3,9);
			
			linea = c.getLineaHorizonte();			
			assertTrue(linea.getPunto(0).getX()== 3  && linea.getPunto(0).getY()==5);
			assertTrue(linea.getPunto(1).getX()== 6  && linea.getPunto(1).getY()==3);
			assertTrue(linea.getPunto(2).getX()== 9  && linea.getPunto(2).getY()==0);			
			
		} catch (Exception e) {			
			fail("Test failed");
		}
	}


	@After
	public void tearDown() throws Exception {
	}
	
	public static void main(String args[]) {
		Result result = JUnitCore.runClasses(CiudadTest.class);
		for (Failure failure : result.getFailures()) {
			OUT.println(failure.toString());
		}

	}


}
