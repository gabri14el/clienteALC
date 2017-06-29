package model;

import java.awt.geom.Point2D;
import java.util.Date;


public class Tweet extends Point2D{

	int id;
	double lat;
	double lon;
	double peso;
	Date data; 
	String texto;
	
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
		return texto;
	}

}
