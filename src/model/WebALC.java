package model;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WebALC {

	public static String CLIENTES;
	public static String OSM;
	
	private List<Point2D> candidatos;
	private Parser parser;
	private Point2D alc; 
	private Point2D minsum;
	private Point2D maxsum;
	private Point2D minmax;
	
	public WebALC(String clientes, String osm) {
		CLIENTES = clientes;
		OSM = osm;
		candidatos = new ArrayList<>();
	}
	
	
	public boolean minMaxEqualsMinSum(){
		return minsum.equals(minmax);
	}
	
	public List<Point2D> getClientes(){
		return parser.getClientes();
	}
	
	public List<Point2D> getCandidatos(){
		return candidatos;
	}
	
	public List<Point2D> getFacilities(){
		return parser.facilities;
	}
	
	public void init(String amenity, double maxlon, double minlon, double maxlat, double minlat){
		parser = new Parser();
		parser.init(OSM, amenity);
		parser.defineAlcance(maxlon, minlon, maxlat, minlat);
        parser.leClientes(CLIENTES);
        
	}
	
	public void addCandidato(Double lat, Double lng){
		Node e = new Node();
		e.lat = lat;
		e.lon = lng;
		candidatos.add(e);
	}
	public void sorteiaCandidatos(){
		int random = (int) (parser.getNodes().size()*Math.random());
        candidatos.add(parser.getNodes().get(random));
        random = (int) (parser.getNodes().size()*Math.random());
        candidatos.add(parser.getNodes().get(random));
        random = (int) (parser.getNodes().size()*Math.random());
        candidatos.add(parser.getNodes().get(random));  
	}

	public void run(){
		alc = ALC.getLC(candidatos, parser.getFacilities(), parser.getClientes());
		minsum = ALC.getMinSum(candidatos, parser.getFacilities(), parser.getClientes());
		minmax = ALC.getMinMax(candidatos, parser.getFacilities(), parser.getClientes());
	}
	
	public void runOnlyALC(){
		alc = ALC.getLC(candidatos, parser.getFacilities(), parser.getClientes());
	}
	public Point2D getAlc() {
		return alc;
	}

	public Point2D getMinsum() {
		return minsum;
	}

	public Point2D getMaxsum() {
		return maxsum;
	}

	public Point2D getMinmax() {
		return minmax;
	}
	
	
	
	
}
