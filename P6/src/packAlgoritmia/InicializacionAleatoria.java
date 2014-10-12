package packAlgoritmia;

import java.util.Vector;

public class InicializacionAleatoria extends KMeans {
	
	Instancia[] centroides;
	private int k;
	private Distancia distancia;
	private ListaInstancias instancias;
	private int numIteraciones;
	private double delta;
	
	

	public InicializacionAleatoria(int pK, Distancia pDistancia, ListaInstancias pListaInstancias, int pNumIt, double pDelta)
	{
		super(pK,pDistancia,pListaInstancias,pNumIt,pDelta);
	}
	
	
public void inicializar()
{
	double maximos[];
	double minimos[];
	
	maximos=this.getInstancias().getMaximos();
	minimos=this.getInstancias().getMinimos();
	
	int dimension=this.getInstancias().getListaInstancia().get(0).dimension();
	double instanciaAleatoria[] = new double[this.getInstancias().dimension()];
	Vector<Double> vectorTemporal = new Vector<Double>();
	ListaInstancias centroides=new ListaInstancias();
	for(int i=0;i<this.k;i++)
	{
		for(int j=0;j<dimension;j++)
		{
			instanciaAleatoria[j] = Math.random()*(maximos[j]-minimos[j])+minimos[j];
		}
		for(int k=0;k<dimension;k++)
		{
			vectorTemporal.add(instanciaAleatoria[k]);
		}
		centroides.getListaInstancia().add(new Instancia(vectorTemporal));
	}
}

public Instancia[] getCentroides() {
	return centroides;
}


public void setCentroides(Instancia[] centroides) {
	this.centroides = centroides;
}


public int getK() {
	return k;
}


public void setK(int k) {
	this.k = k;
}


public Distancia getDistancia() {
	return distancia;
}


public void setDistancia(Distancia distancia) {
	this.distancia = distancia;
}


public ListaInstancias getInstancias() {
	return instancias;
}


public void setInstancias(ListaInstancias instancias) {
	this.instancias = instancias;
}


public int getNumIteraciones() {
	return numIteraciones;
}


public void setNumIteraciones(int numIteraciones) {
	this.numIteraciones = numIteraciones;
}


public double getDelta() {
	return delta;
}


public void setDelta(double delta) {
	this.delta = delta;
}

	
}
