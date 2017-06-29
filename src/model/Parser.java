package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;



/**
 * Classe que lê e converte em nodes e tweets (objetos) os arquivos xml e txt, respectivamente. 
 * @author gabri14el
 *
 */
public class Parser {

	List<Node> facilities;
        List<Node> not_facilities;
	List<Node> pontos;
	List<Tweet> clientes;
	
	public List<Tweet> getClientes() {
		return clientes;
	}

        public List<Node> getNot_facilities() {
            return not_facilities;
        }

        
	public Parser() {
	pontos = new ArrayList<>();
	facilities = new ArrayList<>();
	clientes = new ArrayList<>();
        not_facilities = new ArrayList<>();
        
	}
	
    
	public List<Node> getFacilities() {
		return facilities;
	}


	public List<Node> getNodes() {
		return pontos;
	}

	
	public List<Node> getFacilities(String amenity){
		 
		return facilities;
	}

	public void leClientes(String txt){
		try {
			BufferedReader reader = new BufferedReader(new FileReader(txt));
			reader.readLine(); //Lê linha de início #id lat .. 
			while(reader.ready()){
				String linha = reader.readLine();
				StringTokenizer token = new StringTokenizer(linha); //tokens utilizando " "
				Tweet tmp = new Tweet();
				tmp.id = Integer.parseInt(token.nextToken()); //id
				tmp.lat = Double.parseDouble(token.nextToken());
				tmp.lon = Double.parseDouble(token.nextToken());
				tmp.data = new Date(Long.parseLong(token.nextToken()));
				tmp.texto = token.nextToken(""); //so resta o texto do tweet então pega todos os tokens 
				clientes.add(tmp);
			}
			reader.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void init(String xml, String type){
		//String a = "/Users/gabrielantonio/Downloads/map";
                String a = xml;
		Node nodeAtual = null;
		XMLInputFactory factory = XMLInputFactory.newInstance();
		try {
			XMLStreamReader reader =
			        factory.createXMLStreamReader(new FileReader(a));
			        //ClassLoader.getSystemResourceAsStream(a));
			
			while(reader.hasNext()){
				int event = reader.next();
				
				switch (event) {
				case XMLStreamConstants.START_ELEMENT:
					if("node".equals(reader.getLocalName())){ //pega o inicio do elemento node, ou seja: <Node ....>
						
						nodeAtual = new Node();
							pontos.add(nodeAtual);
						//nodeAtual.id = Integer.parseInt(reader.getAttributeValue(0)); //erro nesse parse não to afim de corrigir pois não é necessário por enquanto
						nodeAtual.lat = Double.parseDouble(reader.getAttributeValue(1)); //pega o valor do atributo 2, que é sempre a latitude ou "lat" 
						nodeAtual.lon = Double.parseDouble(reader.getAttributeValue(2)); // '' longitude ou "lon"
					}
					else if("way".equals(reader.getLocalName())){ //consome todas as tags way, para evitar bugs na leitura. Ainda não sei o que são ways
						boolean tmp = true; 
						int tmp_event;
						while(tmp){
							tmp_event = reader.next();
							if(tmp_event == XMLStreamConstants.END_ELEMENT){
								if(reader.getLocalName().equals("way")){
									tmp = false;
								}
							}
							
						} 
						
					}
					else if("tag".equals(reader.getLocalName())){ //captura inicio de elemtnos <tag .. /> para obter o atributo amenity
						if(reader.getAttributeValue(0).equals("amenity")){
							nodeAtual.amenity = reader.getAttributeValue(1);
							
							if(type != null) 
								if(nodeAtual.amenity.equals(type)) //caso a amenity seja igual a que queremos, salva numa lista separada que representa as facilities
									facilities.add(nodeAtual);
                                                                
						}
					}
					break;
				
				default:
						break;
					}
			}
		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

                
                pontos.removeAll(facilities); //remove todas as facilities que queremos da lista de pontos
		
	}
}
