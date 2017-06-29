package model;
import java.awt.geom.Point2D;
import java.util.Collection;

public class ALC {

	public static Point2D getLC(Collection<Node> pontos, Collection<Node> faclities, Collection<Tweet> clientes){
		double score = 0; 
		double melhor_score = 0;
		Point2D melhor_ponto = null;
		boolean menor;
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
					}
					
					
				}
				
			}
			if(score > melhor_score){
				melhor_score = score;
				melhor_ponto = ponto;
			}
		}
		return melhor_ponto;
	}
	
	public static Point2D getMinSum(Collection<Node> pontos, Collection<Node> faclities, Collection<Tweet> clientes){
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
	
	public static Point2D getMinMax(Collection<Node> pontos, Collection<Node> faclities, Collection<Tweet> clientes){
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

