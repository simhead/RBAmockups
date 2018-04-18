package rba.mockup;

import java.io.IOException;
import java.io.PrintWriter;

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
public class GetCustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String xml = "";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetCustomerServlet() {   
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
		String custId = request.getParameter("custId");
		String jsonflag = request.getParameter("jsonflag");
		if (jsonflag==null) jsonflag = "false";
		
		// Response handling
		Customer customer = new Customer();
		customer.createConnection();
		String[] custRec = customer.getCustomer(custId);
		//customer.shutdown();
		
		xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
		xml += "<mwResponse>";
		xml += "<CUSTID>" + custRec[0] + "</CUSTID>" + 
			       "<CUSTOMERNAME>" + custRec[1] + "</CUSTOMERNAME>" + 
			       "<CUSTOMERCOUNTRY>" + custRec[2] + "</CUSTOMERCOUNTRY>" + 
			       "<CUSTOMERSTATE>" + custRec[3] + "</CUSTOMERSTATE>" + 
			       "<CUSTOMERCITY>" + custRec[4] + "</CUSTOMERCITY>" + 
			       "<CUSTOMERADDRESS>" + custRec[5] + "</CUSTOMERADDRESS>" + 
			       "<CUSTOMERPOSTCODE>" + custRec[6] + "</CUSTOMERPOSTCODE>" + 
			       "<CUSTOMERPHONE>" + custRec[7] + "</CUSTOMERPHONE>" + 
			       "<CUSTOMEREMAIL>" + custRec[8] + "</CUSTOMEREMAIL>" + 
			       "<CUSTOMERIP>" + custRec[9] + "</CUSTOMERIP>" + 
			       "<STOREID>" + custRec[10] + "</STOREID>";

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
