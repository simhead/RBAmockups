# RBAmockups

## SOLUTION REQUIREMENT:
- Design Mockup services to provide the following APIs:
	1.	Get Customer (GET)
	2.	Create Customer (POST)
	3.	Process Card (POST) 
	4.	Query Card (POST) 

NOTE: 3 & 4 APIs are based on Merchant Warrior API doc and specs are available from the following links:
- https://dox.merchantwarrior.com/#processcard
- https://dox.merchantwarrior.com/#querycard

## CONFIGURATION REQUIREMENT:
- Tomcat 8.5.x + Derby embed DB
- maven for building the war package
- setup initial DB - execute rba.main.Customer.class

### USECASE:
1.	Get Customer (GET) - extract customer details by customer ID.
#### Request:
curl -X GET <tomcat_serverIP>/PaymentServices/GetCustomerServlet?custId=<custId>
#### Response:
&lt;?xml version=&quot;1.0&quot; encoding=&quot;UTF-8&quot;?&gt;
&lt;mwResponse&gt;
    &lt;CUSTID&gt;02291751888&lt;/CUSTID&gt;
    &lt;CUSTOMERNAME&gt;customerName12345&lt;/CUSTOMERNAME&gt;
    &lt;CUSTOMERCOUNTRY&gt;customerCountry12345&lt;/CUSTOMERCOUNTRY&gt;
    &lt;CUSTOMERSTATE&gt;customerState12345&lt;/CUSTOMERSTATE&gt;
    &lt;CUSTOMERCITY&gt;customerCity12345&lt;/CUSTOMERCITY&gt;
    &lt;CUSTOMERADDRESS&gt;customerAddress12345&lt;/CUSTOMERADDRESS&gt;
    &lt;CUSTOMERPOSTCODE&gt;customerPostCode12345&lt;/CUSTOMERPOSTCODE&gt;
    &lt;CUSTOMERPHONE&gt;customerPhone12345&lt;/CUSTOMERPHONE&gt;
    &lt;CUSTOMEREMAIL&gt;customerEmail12345&lt;/CUSTOMEREMAIL&gt;
    &lt;CUSTOMERIP&gt;null&lt;/CUSTOMERIP&gt;
    &lt;STOREID&gt;customerIP12345&lt;/STOREID&gt;
&lt;/mwResponse&gt;

2.	Create Customer (POST) - add new customer.
#### Request:
curl -X POST \
  -d customerName="customerName" \
  -d customerCountry="customerCountry" \
  -d customerState="customerState" \
  -d customerCity="customerCity" \
  -d customerAddress="customerAddress" \
  -d customerPostCode="customerPostCode" \
  -d customerCountry="customerCountry" \
  -d customerPhone="customerPhone" \
  -d customerEmail="customerEmail" \
  -d customerIP="customerIP" \
  -d storeID="storeID" <tomcat_serverIP>/PaymentServices/AddCustomerServlet
#### Response:
&lt;?xml version=&quot;1.0&quot; encoding=&quot;UTF-8&quot;?&gt;
&lt;mwResponse&gt;
    &lt;responseCode&gt;02291751888&lt;/responseCode&gt;
    &lt;responseMessage&gt;Created Successfully for Customer: customerName12345&lt;/responseMessage&gt;
&lt;/mwResponse&gt;

3.	Process Card (POST) - to perform a purchase request.
#### Request:
curl -X POST \ 
    -d method="processCard" \
    -d merchantUUID="5265f8eed6a19" \
    -d apiKey="ksmnwxab" \
    -d transactionAmount="1.00" \
    -d transactionCurrency="AUD" \
    -d transactionProduct="Test Product" \
    -d customerName="Test Customer" \
    -d customerCountry="AU" \
    -d customerState="QLD" \
    -d customerCity="Brisbane" \
    -d customerAddress="123 Test Street" \
    -d customerPostCode="4000" \
    -d customerPhone="61731665489" \
    -d customerEmail="mw@emailaddress.com" \
    -d customerIP="1.1.1.1" \
    -d paymentCardName="Test Customer" \
    -d paymentCardNumber="5123456789012346" \
    -d paymentCardExpiry="0521" \
    -d paymentCardCSC="123" \
    -d hash="b55552ff426d7e3d4885465d27ea0062" <tomcat_serverIP>/PaymentServices/ProcessCardServlet
#### Response:
&lt;?xml version=&quot;1.0&quot; encoding=&quot;UTF-8&quot;?&gt;
&lt;mwResponse&gt;
    &lt;responseCode&gt;0&lt;/responseCode&gt;
    &lt;responseMessage&gt;Transaction approved&lt;/responseMessage&gt;
    &lt;transactionID&gt;1336-20be3569-b600-11e6-b9c3-005056b209e0&lt;/transactionID&gt;
    &lt;authCode&gt;731357421&lt;/authCode&gt;
    &lt;receiptNo&gt;731357421&lt;/receiptNo&gt;
    &lt;authMessage&gt;Honour with identification&lt;/authMessage&gt;
    &lt;authResponseCode&gt;08&lt;/authResponseCode&gt;
    &lt;authSettledDate&gt;2018-04-24&lt;/authSettledDate&gt;
    &lt;paymentCardNumber&gt;512345xxxxxx2346&lt;/paymentCardNumber&gt;
    &lt;transactionAmount&gt;1.00&lt;/transactionAmount&gt;
    &lt;custom1&gt;&lt;/custom1&gt;
    &lt;custom2&gt;&lt;/custom2&gt;
    &lt;custom3&gt;&lt;/custom3&gt;
    &lt;customHash&gt;4ffc9271716dc4c016c97df67a4e8072&lt;/customHash&gt;
&lt;/mwResponse&gt;

4.	Query Card (POST) - to perform a query of an existing transaction.
#### Request:
curl -X POST \
  -d method="queryCard" \
  -d merchantUUID="5265f8eed6a19" \
  -d apiKey="ksmnwxab" \
  -d transactionID="1336-20be3569-b600-11e6-b9c3-005056e109e0" \
  -d hash="11b6a860e5bdf0bd91a503b42346ee1d" <tomcat_serverIP>/PaymentServices/QueryCardServlet
#### Response:
&lt;?xml version=&quot;1.0&quot; encoding=&quot;UTF-8&quot;?&gt;
&lt;mwResponse&gt;
    &lt;responseCode&gt;0&lt;/responseCode&gt;
    &lt;responseMessage&gt;Transaction approved&lt;/responseMessage&gt;
    &lt;transactionID&gt;1336-20be3569-b600-11e6-b9c3-005056e109e0&lt;/transactionID&gt;
    &lt;authCode&gt;731357421&lt;/authCode&gt;
    &lt;receiptNo&gt;731357421&lt;/receiptNo&gt;
    &lt;authMessage&gt;Honour with identification&lt;/authMessage&gt;
    &lt;authResponseCode&gt;08&lt;/authResponseCode&gt;
    &lt;authSettledDate&gt;2018-04-24&lt;/authSettledDate&gt;
    &lt;refundTotal&gt;1.00&lt;/refundTotal&gt;
    &lt;custom1&gt;&lt;/custom1&gt;
    &lt;custom2&gt;&lt;/custom2&gt;
    &lt;custom3&gt;&lt;/custom3&gt;
    &lt;customHash&gt;65295af1a7b667756469a32add723b51&lt;/customHash&gt;
&lt;/mwResponse&gt;

## TODO (Future expansion):
TBD