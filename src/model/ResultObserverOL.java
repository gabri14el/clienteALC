package model;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public interface ResultObserverOL {
	
	
	
	public void addClienteAtraido(int indice, Point2D c);
	public void setIndiceMelhorPonto(int i);
	public void setScoreMelhorPonto(double d);
}
