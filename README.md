# invoicedemo
Simple Invoice to add multiple items and show summary

Its a small application giving an option of adding multiple lineitems and show summary. 

Steps to setup is quick - 

1. Download the repo. 
2. Build it using mvn clean install. 
3. Run the application using mvn jetty:run
4. Open browser and hit http://localhost:8080/spring-mvc-form/invoices
5. click on +Add items
6. Fill all the elements. Name and email are mandatory and if not given will throw error message and you can fill it again.  
7. Click SEND to save to database and redirect back to summary page with amount total
8. Currently embedded db is used and could be changed to mysql changing the datasource config in SpringDBConfig class. 
