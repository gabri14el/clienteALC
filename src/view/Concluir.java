package view;

import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.WebALC;

/**
 * Servlet implementation class VisualizarPontos
 */
@WebServlet("/Concluir")
public class Concluir extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String comeco;
	String meio; 
	String fim; 
	
	
	
	
	
    /**
     * Default constructor. 
     */
    public Concluir() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	private void carregarHtml(String path){
		 try {
			 BufferedReader comeco = new BufferedReader(new FileReader(path+"/visualizar_pontos.html"));
			 String str;
			 StringBuffer buf = new StringBuffer();
			 while (comeco.ready()) {
			  str = comeco.readLine();
			  buf.append(str);
			 }
			 comeco.close();
			 this.comeco = buf.toString();
			 
			 buf = new StringBuffer();
			 BufferedReader fim = new BufferedReader(new FileReader(path+"/visualizar_pontos_final.html"));
			 
			 buf = new StringBuffer();
			 while (fim.ready()) {
			  str = fim.readLine();
			  buf.append(str);
			 }
			 comeco.close();
			 this.fim = buf.toString();
			 
			 
			 BufferedReader meio = new BufferedReader(new FileReader(path+"/visualizar_pontos_meio.html"));
			 buf = new StringBuffer();
			 while (meio.ready()) {
			  str = meio.readLine();
			  buf.append(str);
			 }
			 comeco.close();
			 this.meio = buf.toString();
			  } catch (IOException e) {
			 e.printStackTrace();
			 
			  }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = getServletContext().getRealPath("WEB-INF/arquivos/");
		String db = getServletContext().getRealPath("WEB-INF/arquivos/database.db");
		String osm = getServletContext().getRealPath("WEB-INF/arquivos/osm.txt");
		String clientes = getServletContext().getRealPath("WEB-INF/arquivos/clientes.txt");
		
		
		String amenity = request.getParameter("facility");
		carregarHtml(path);
		WebALC alc = new WebALC(clientes, osm);
		alc.init(amenity, -38.27777, -38.5359, -12.8272000, -13.0236000);
		
		
		String tmp1 = request.getParameter("lat1");
		String tmp2 = request.getParameter("lng1");
		alc.addCandidato(Double.parseDouble(tmp1), Double.parseDouble(tmp2));
		
		tmp1 = request.getParameter("lat2");
		tmp2 = request.getParameter("lng2");
		alc.addCandidato(Double.parseDouble(tmp1), Double.parseDouble(tmp2));
		
		tmp1 = request.getParameter("lat3");
		tmp2 = request.getParameter("lng3");
		alc.addCandidato(Double.parseDouble(tmp1), Double.parseDouble(tmp2));
		
		alc.run();
		
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.println(comeco);
		out.println(meio);
		String marker1 = "var marker = new google.maps.Marker({position: "
				+ "{lat:"+alc.getAlc().getX()+", lng:"+alc.getAlc().getY()+"},map: map, label: \" \"});";
		out.println(marker1);
		out.println(fim);
		out.close();
	}

}
