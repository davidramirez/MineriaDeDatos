package packCluster;

import packInstancias.Instancia;
import packInstancias.ListaInstancias;

public class Cluster {
 private Instancia centroide;
 private ListaInstancias listaInstancias;
 private double error;
 
 public Cluster(ListaInstancias pListaInstancias)
 {
	 this.setListaInstancias(pListaInstancias);
	 this.centroide=calcularCentroide();
	 this.error=calcularError();
 }

private double calcularError() {
	// TODO Auto-generated method stub
	return 0;
}

private Instancia calcularCentroide() {
	// TODO Auto-generated method stub
	return null;
}


/*
 * GETTERS & SETTERS
 */

public Instancia getCentroide() {
	return centroide;
}


public void setCentroide(Instancia centroide) {
	this.centroide = centroide;
}

public ListaInstancias getListaInstancias() {
	return listaInstancias;
}

public void setListaInstancias(ListaInstancias listaInstancias) {
	this.listaInstancias = listaInstancias;
}

public double getError() {
	return error;
}

public void setError(double error) {
	this.error = error;
}
}
