package model;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public interface ResultObserverOL {
	
	public List<Point2D> [] clientes_atraidos = new List[3];
	
	public void setIndiceMelhorPonto(int i);
	public void setScoreMelhorPonto(double d);
}
