package rba.mockup;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.json.XML;

import rba.main.Customer;
import rba.mockup.util.UtilSet;

/**
 * Servlet implementation class CustomerServlet
 */
public class InitializeDBServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String xml = "";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InitializeDBServlet() {   
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());

		// Request handling
		String dbname = request.getParameter("dbname");
		String jsonflag = request.getParameter("jsonflag");
		if (jsonflag==null) jsonflag = "false";
		
		// Response handling
		Connection conn = Customer.createConnection(dbname);
		;
		
		xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
		xml += "<mwResponse>";
		xml += "<Tableflag>" + Customer.createCustomerTable(conn) + "</Tableflag>" + 
			       "<DBflag>" + Customer.closeConn(conn) + "</DBflag>" ;

		xml += "</mwResponse>";
		PrintWriter writer = response.getWriter();
		if (jsonflag.equalsIgnoreCase("true")) {
			response.setContentType("application/json;charset=UTF-8");
			JSONObject xmlJSONObj = XML.toJSONObject(xml);
			writer.write(xmlJSONObj.toString(UtilSet.PRETTY_PRINT_INDENT_FACTOR));
		} else {
			response.setContentType("text/xml;charset=UTF-8");
			writer.write(xml);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);		
	}

}
