package model;
import java.awt.PointerInfo;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collection;

public class OLFactory {

	private ResultObserverOL observer;
	public OLFactory(ResultObserverOL observer){
		this.observer = observer;
	}
	public Point2D getLC(Collection<Point2D> pontos, Collection<Point2D> faclities, Collection<Point2D> clientes){
		double score = 0; 
		double melhor_score = 0;
		Point2D melhor_ponto = null;
		boolean menor;
		int i = 0; //indice do ponto
		for (Point2D ponto : pontos) {
			score = 0;
			for (Point2D cliente : clientes) {
				for (Point2D facility : faclities) {
					double distancia_cliente_ponto = Point2D.distance(cliente.getX(), cliente.getY(), ponto.getX(), ponto.getY());
					double distancia_cliente_facility = Point2D.distance(cliente.getX(), cliente.getY(), facility.getX(), facility.getY());
					menor = true;
					if(distancia_cliente_facility < distancia_cliente_ponto){
						menor = false;
					}
					if(menor){
						score++; //considerando w(c) = 1
						if(observer.candidato[i] == null) {
							observer.candidato[i] = new ArrayList<>();
							observer.candidato[i].add(cliente);
						}
						else{
							if(!observer.candidato[i].contains(cliente)){
								observer.candidato[i].add(cliente);
							}
						}
					}
				}
			}
			if(score > melhor_score){
				melhor_score = score;
				melhor_ponto = ponto;
				
			}
			i++; 
		}
		return melhor_ponto;
	}
	
	public Point2D getMinSum(Collection<Point2D> pontos, Collection<Point2D> faclities, Collection<Point2D> clientes){
		double melhor_ponto_wad = 0;
		Point2D melhor_ponto = null; 
		double wad;
		double dist_ponto_cliente;
		double dist_facility_cliente;
		for (Point2D ponto : pontos) {
			wad = 0; 
			for (Point2D cliente : clientes) {
				dist_ponto_cliente = Point2D.distance(ponto.getX(), ponto.getY(), cliente.getX(), cliente.getY());
				for (Point2D facility : faclities) {
					dist_facility_cliente = Point2D.distance(facility.getX(), facility.getY(), cliente.getX(), cliente.getY());
					
					if(dist_ponto_cliente < dist_facility_cliente){
						wad+= dist_ponto_cliente; //considerando w(c) = 1 
					}
					else{
						wad+= dist_facility_cliente;
					}
				}
			}
		
		if(wad > melhor_ponto_wad){
			melhor_ponto_wad = wad;
			melhor_ponto = ponto;
		}
		}
		
		return melhor_ponto; 
		
	}
	
	public Point2D getMinMax(Collection<Point2D> pontos, Collection<Point2D> faclities, Collection<Point2D> clientes){
		double melhor_ponto_wad = 0;
		Point2D melhor_ponto = null; 
		double wad;
		double dist_ponto_cliente;
		double dist_facility_cliente;
		for (Point2D ponto : pontos) {
			wad = 0; 
			for (Point2D cliente : clientes) {
				dist_ponto_cliente = Point2D.distance(ponto.getX(), ponto.getY(), cliente.getX(), cliente.getY());
				for (Point2D facility : faclities) {
					dist_facility_cliente = Point2D.distance(facility.getX(), facility.getY(), cliente.getX(), cliente.getY());
					
					if(dist_ponto_cliente < dist_facility_cliente){
						wad = dist_ponto_cliente; //considerando w(c) = 1 
					}
					else{
						wad = dist_facility_cliente;
					}
				}
			}
		
		if(wad > melhor_ponto_wad){
			melhor_ponto_wad = wad;
			melhor_ponto = ponto;
		}
		}
		return melhor_ponto; 
	}
}
