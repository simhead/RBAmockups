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

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import rba.main.Customer;
import rba.mockup.util.UtilSet;

/**
 * Servlet implementation class CardServlet
 */
public class AuthCardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String xml = "";
	SAXParserFactory factory;

	public void init() throws ServletException {

		factory = SAXParserFactory.newInstance();
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AuthCardServlet() {
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

		/*
		 * <?xml version="1.0" encoding="UTF-8"?> 
		 * <mwResponse>
		 * <responseCode>0</responseCode> 
		 * <responseMessage>Transaction approved</responseMessage>
		 * <transactionID>1336-37edc560-b601-11e6-b9c3-005056b209e0</transactionID>
		 * <transactionReferenceID>12345</transactionReferenceID>
		 * <authCode>731357433</authCode> 
		 * <receiptNo>731357433</receiptNo>
		 * <authMessage>Honour with identification</authMessage>
		 * <authResponseCode>08</authResponseCode>
		 * <authSettledDate>2016-11-29</authSettledDate>
		 * <paymentCardNumber>512345XXXXXX2346</paymentCardNumber>
		 * <transactionAmount>1.00</transactionAmount> 
		 * <custom1/> 
		 * <custom2/> 
		 * <custom3/>
		 * <customHash>65b172551b7d3a0706c0ce5330c98470</customHash> 
		 * </mwResponse>
		 */
		xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
		xml += "<mwResponse>";

		Customer customer = new Customer();
		customer.createConnection();
		String[] custDetails = customer.getCustomers();
		String custId = custDetails[0];
		String custName = custDetails[1];
		String custAddr = custDetails[2];
		String responseCode = "0";
		String responseMessage = "Transaction approved";
		String transactionID = "1336-20be3569-b600-11e6-b9c3-005056b209e0";
		String authCode = "731357421";
		String receiptNo = "731357421";
		String authMessage = "Honour with identification";
		String authResponseCode = "08";
		String authSettledDate = "2016-11-29";
		String paymentCardNumber = "512345XXXXXX2346";
		String transactionAmount = "1.00";
		String custom1 = "";
		String custom2 = "";
		String custom3 = "";
		String customHash = "65b172551b7d3a0706c0ce5330c98470";

		xml += "<responseCode>" + responseCode + "</responseCode>" + "<responseMessage>" + responseMessage
				+ "</responseMessage>" + "<transactionID>" + transactionID + "</transactionID>" + "<authCode>"
				+ authCode + "</authCode>" + "<receiptNo>" + receiptNo + "</receiptNo>" + "<authMessage>" + authMessage
				+ "</authMessage>" + "<authResponseCode>" + authResponseCode + "</authResponseCode>"
				+ "<authSettledDate>" + authSettledDate + "</authSettledDate>" + "<paymentCardNumber>"
				+ paymentCardNumber + "</paymentCardNumber>" + "<transactionAmount>" + transactionAmount
				+ "</transactionAmount>" + "<custom1>" + custom1 + "</custom1>" + "<custom2>" + custom2 + "</custom2>"
				+ "<custom3>" + custom3 + "</custom3>" + "<customHash>" + customHash + "</customHash>";

		xml += "</mwResponse>";
		PrintWriter writer = response.getWriter();
		/*
		 * try { SAXParser parser = factory.newSAXParser(); InputSource input = new
		 * InputSource(new StringReader(xml)); parser.parse(input, new
		 * DefaultHandler()); } catch (ParserConfigurationException e) { throw new
		 * ServletException(e); } catch (SAXException e) { throw new
		 * ServletException(e); }
		 */
		customer.shutdown();
		response.setContentType("text/xml;charset=UTF-8");
		writer.write(xml);
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
		String transactionAmount = request.getParameter("transactionAmount");
		String transactionCurrency = request.getParameter("transactionCurrency");
		String transactionProduct = request.getParameter("transactionProduct");
		String customerName = request.getParameter("customerName");
		String customerCountry = request.getParameter("customerCountry");
		String customerState = request.getParameter("customerState");
		String customerCity = request.getParameter("customerCity");
		String customerAddress = request.getParameter("customerAddress");
		String customerPostCode = request.getParameter("customerPostCode");
		String customerPhone = request.getParameter("customerPhone");
		String customerEmail = request.getParameter("customerEmail");
		String customerIP = request.getParameter("customerIP");
		String paymentCardName = request.getParameter("paymentCardName");
		String paymentCardNumber = request.getParameter("paymentCardNumber");
		String paymentCardExpiry = request.getParameter("paymentCardExpiry");
		String paymentCardCSC = request.getParameter("paymentCardCSC");
		String hash = request.getParameter("hash");

		// Response handling
		String responseCode = "0";
		String responseMessage = "Transaction approved";
		String transactionID = "1336-20be3569-b600-11e6-b9c3-005056b209e0";
		String authCode = "731357421";
		String receiptNo = "731357421";
		String authMessage = "Honour with identification";
		String authResponseCode = "08";
		String authSettledDate = UtilSet.getSomeDate();
		paymentCardNumber = UtilSet.maskCardNumber(paymentCardNumber, "######xxxxxx####");
		transactionAmount = request.getParameter("transactionAmount");
		String custom1 = "";
		String custom2 = "";
		String custom3 = "";
		String customHash = UtilSet.getCustomHash(apiKey, merchantUUID, transactionAmount, transactionCurrency);
		
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
			       "<paymentCardNumber>" + paymentCardNumber + "</paymentCardNumber>" + 
			       "<transactionAmount>" + transactionAmount + "</transactionAmount>" + 
			       "<custom1>" + custom1 + "</custom1>" + 
			       "<custom2>" + custom2 + "</custom2>" + 
			       "<custom3>" + custom3 + "</custom3>" + 
			       "<customHash>" + customHash + "</customHash>";

		xml += "</mwResponse>";
		PrintWriter writer = response.getWriter();
		response.setContentType("text/xml;charset=UTF-8");
		writer.write(xml);
	}

}
