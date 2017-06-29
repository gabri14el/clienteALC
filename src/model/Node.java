package model;

import java.awt.geom.Point2D;


/**
 * Classe representa um <Node> do osm xml. 
 * Classe herda de Point2D para poder ser usada nos algoritmos de 
 * busca por localização otima
 * @author gabri14el
 */
public class Node extends Point2D{

	int id; 
	double lat; 
	double lon;
	
	String amenity;

	@Override
	public double getX() {
		return lat;
	}

	@Override
	public double getY() {
		return lon;
	}

	@Override
	public void setLocation(double x, double y) {
		lat = x;
		lon = y;
		
	}
	
	@Override
	public String toString() {
		return "amenity: "+amenity+ " lat: "+lat+" lon: "+lon;
	}
	
	@Override
	public boolean equals(Object obj) {
		Node f = (Node) obj; 
		return f.lat == this.lat && f.lon == this.lon;
	}
}
