package rba.mockup;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.json.JSONObject;
import org.json.XML;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import rba.main.Customer;
import rba.mockup.util.UtilSet;

/**
 * Servlet implementation class AuthServlet
 */
public class AuthServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String xml = "";
	SAXParserFactory factory;

	public void init() throws ServletException {

		factory = SAXParserFactory.newInstance();
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AuthServlet() {
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
		String method = request.getParameter("method");
		String merchantUUID = request.getParameter("merchantUUID");
		String apiKey = request.getParameter("apiKey");
		String transactionID = request.getParameter("transactionID");
		String hash = request.getParameter("hash");
		String jsonflag = request.getParameter("jsonflag");
		if (jsonflag==null) jsonflag = "false";

		// Response handling
		String responseCode = "0";
		String responseMessage = "Transaction approved";
		//transactionID = "1336-20be3569-b600-11e6-b9c3-005056b209e0";
		String authCode = "731357421";
		String receiptNo = "731357421";
		String authMessage = "Honour with identification";
		String authResponseCode = "08";
		String authSettledDate = UtilSet.getSomeDate();
		String refundTotal = "1.00";
		String custom1 = "";
		String custom2 = "";
		String custom3 = "";
		String customHash = UtilSet.getCustomHash(apiKey, merchantUUID, transactionID, null);
		
		xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
		xml += "<mwResponse>";
		xml += "<responseCode>" + responseCode + "</responseCode>" + 
			       "<responseMessage>" + responseMessage + "</responseMessage>" + 
			       "<transactionID>" + transactionID + "</transactionID>" + 
			       "<authCode>" + authCode + "</authCode>" + 
			       "<receiptNo>" + receiptNo + "</receiptNo>" + 
			       "<authMessage>" + authMessage + "</authMessage>" + 
			       "<authResponseCode>" + authResponseCode + "</authResponseCode>" + 
			       "<authSettledDate>" + authSettledDate + "</authSettledDate>" + 
			       "<refundTotal>" + refundTotal + "</refundTotal>" + 
			       "<custom1>" + custom1 + "</custom1>" + 
			       "<custom2>" + custom2 + "</custom2>" + 
			       "<custom3>" + custom3 + "</custom3>" + 
			       "<customHash>" + customHash + "</customHash>";

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

}
