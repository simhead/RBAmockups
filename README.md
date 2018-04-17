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
Request:
curl -X GET <tomcat_serverIP>/PaymentServices/GetCustomerServlet?custId=<custId>
Response:
<?xml version="1.0" encoding="UTF-8"?>
<mwResponse>
    <CUSTID>02291751888</CUSTID>
    <CUSTOMERNAME>customerName12345</CUSTOMERNAME>
    <CUSTOMERCOUNTRY>customerCountry12345</CUSTOMERCOUNTRY>
    <CUSTOMERSTATE>customerState12345</CUSTOMERSTATE>
    <CUSTOMERCITY>customerCity12345</CUSTOMERCITY>
    <CUSTOMERADDRESS>customerAddress12345</CUSTOMERADDRESS>
    <CUSTOMERPOSTCODE>customerPostCode12345</CUSTOMERPOSTCODE>
    <CUSTOMERPHONE>customerPhone12345</CUSTOMERPHONE>
    <CUSTOMEREMAIL>customerEmail12345</CUSTOMEREMAIL>
    <CUSTOMERIP>null</CUSTOMERIP>
    <STOREID>customerIP12345</STOREID>
</mwResponse>

2.	Create Customer (POST) - add new customer.
Request:
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
Response:
<?xml version="1.0" encoding="UTF-8"?>
<mwResponse>
    <responseCode>02291751888</responseCode>
    <responseMessage>Created Successfully for Customer: customerName12345</responseMessage>
</mwResponse>

3.	Process Card (POST) - to perform a purchase request.
Request:
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
Response:
<?xml version="1.0" encoding="UTF-8"?>
<mwResponse>
    <responseCode>0</responseCode>
    <responseMessage>Transaction approved</responseMessage>
    <transactionID>1336-20be3569-b600-11e6-b9c3-005056b209e0</transactionID>
    <authCode>731357421</authCode>
    <receiptNo>731357421</receiptNo>
    <authMessage>Honour with identification</authMessage>
    <authResponseCode>08</authResponseCode>
    <authSettledDate>2018-04-24</authSettledDate>
    <paymentCardNumber>512345xxxxxx2346</paymentCardNumber>
    <transactionAmount>1.00</transactionAmount>
    <custom1></custom1>
    <custom2></custom2>
    <custom3></custom3>
    <customHash>4ffc9271716dc4c016c97df67a4e8072</customHash>
</mwResponse>

4.	Query Card (POST) - to perform a query of an existing transaction.
Request:
curl -X POST \
  -d method="queryCard" \
  -d merchantUUID="5265f8eed6a19" \
  -d apiKey="ksmnwxab" \
  -d transactionID="1336-20be3569-b600-11e6-b9c3-005056e109e0" \
  -d hash="11b6a860e5bdf0bd91a503b42346ee1d" <tomcat_serverIP>/PaymentServices/QueryCardServlet
Response:
<?xml version="1.0" encoding="UTF-8"?>
<mwResponse>
    <responseCode>0</responseCode>
    <responseMessage>Transaction approved</responseMessage>
    <transactionID>1336-20be3569-b600-11e6-b9c3-005056e109e0</transactionID>
    <authCode>731357421</authCode>
    <receiptNo>731357421</receiptNo>
    <authMessage>Honour with identification</authMessage>
    <authResponseCode>08</authResponseCode>
    <authSettledDate>2018-04-24</authSettledDate>
    <refundTotal>1.00</refundTotal>
    <custom1></custom1>
    <custom2></custom2>
    <custom3></custom3>
    <customHash>65295af1a7b667756469a32add723b51</customHash>
</mwResponse>

## TODO (Future expansion):
TBD