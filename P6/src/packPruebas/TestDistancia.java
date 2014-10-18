package packPruebas;

import static org.junit.Assert.*;

import java.util.Vector;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import packDistancias.Distancia;
import packDistancias.DistanciaChebyshev;
import packDistancias.DistanciaMinkowski;
import packInstancias.Instancia;

public class TestDistancia {

	Vector<Double> val1;
	Vector<Double> val2;
	Vector<Double> val3, val4, val5;
	Distancia d1, d2, d3;
	Instancia i1,i2,i3,i4,i5;
	@Before
	public void setUp() throws Exception {
		val1 = new Vector<Double>();
		val1.add(1.0);
		val1.add(1.0);
		
		val2 = new Vector<Double>();
		val2.add(2.0);
		val2.add(2.0);
		
		val3 = new Vector<Double>();
		val3.add(0.0);
		val3.add(0.0);
		
		val5 = new Vector<Double>();
		val5.add(4.0);
		val5.add(3.0);
		
		val4 = new Vector<Double>();
		val4.add(7.0);
		val4.add(9.0);
		
		i1 = new Instancia(val1);
		i2 = new Instancia(val2);
		i3 = new Instancia(val3);
		i4 = new Instancia(val4);
		i5 = new Instancia(val5);
		
		d1 = new DistanciaMinkowski(2);
		d2 = new DistanciaMinkowski(1);
		d3 = new DistanciaChebyshev();
	}

	@After
	public void tearDown() throws Exception {
		val1  = null;
		val2 = null;
		val3 = val4 = val5 = null;
		
		d1 =null;
		d2=null;
		d3=null;
		
		i1 = i2 = i3 = i4 = i5 = null;
	}

	@Test
	public void testDistanciaEuclidea() {
		try {
			assertEquals(0.0, d1.distancia(i1, i1),0.0);
			assertEquals(Math.sqrt(2), d1.distancia(i1, i2),0.0);
			assertEquals(Math.sqrt(8), d1.distancia(i3, i2),0.0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testDistanciaManhattan(){
		try {
			assertEquals(0.0, d2.distancia(i1, i1),0.0);
			assertEquals(2, d2.distancia(i1, i2),0.0);
			assertEquals(4, d2.distancia(i3, i2),0.0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testDistanciaChebyshev(){
		try {
			assertEquals(6.0, d3.distancia(i4, i5),0.0);
			assertEquals(7.0, d3.distancia(i4, i2),0.0);
			assertEquals(4.0, d3.distancia(i3, i5),0.0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
