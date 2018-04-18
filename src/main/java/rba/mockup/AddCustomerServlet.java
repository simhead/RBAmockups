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
public class AddCustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String xml = "";
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddCustomerServlet() {
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
		response.getWriter().append("Served at: ").append(request.getContextPath());

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Request handling
		String customerName = request.getParameter("customerName");
		String customerCountry = request.getParameter("customerCountry");
		String customerState = request.getParameter("customerState");
		String customerCity = request.getParameter("customerCity");
		String customerAddress = request.getParameter("customerAddress");
		String customerPostCode = request.getParameter("customerPostCode");
		String customerPhone = request.getParameter("customerPhone");
		String customerEmail = request.getParameter("customerEmail");
		String customerIP = request.getParameter("customerIP");
		String storeID = request.getParameter("storeID");
		String jsonflag = request.getParameter("jsonflag");
		if (jsonflag==null) jsonflag = "false";

		// Response handling
		Customer customer = new Customer();
		customer.createConnection();
		String custId = customer.insertCustomer(customerName, customerCountry, 
				customerState, customerCity, customerAddress, 
				customerPostCode, customerPhone, customerEmail, customerIP, storeID);
		//customer.shutdown();
		
		// Response handling
		String responseCode = custId;
		String responseMessage = "Created Successfully for Customer: "+customerName;
				
		xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
		xml += "<mwResponse>";
		xml += "<responseCode>" + responseCode + "</responseCode>" + 
			       "<responseMessage>" + responseMessage + "</responseMessage>";

		xml += "</mwResponse>";
		if (custId==null) response.setStatus(500);
		else {
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
	}

}
