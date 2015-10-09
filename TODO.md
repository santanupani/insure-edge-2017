Story : Get Product List
------------------------

Product description should be populated with correct values for all 4 products

JasperReports/JavaMail Issue
-----------------------------
# Problem statement
1. JasperReports needs Lowagie dependency version 2.1.7 to generate JasperReports (Using JasperSoft)
2. When this dependency is used Emails are not sent from the application, DH Key pair (a security issue exception is thrown).
3. When v2.1.7 is used, JasperReport PDFs are produced tested from the Swagger, but the application doesn't send emails
4. If any other version is used, application sends emails but doesn't generate PDFs because these other versions don't have DocumentException or unable to handle PDFs generations.
5. When v2.1.7 is used together with other version, it generates the PDFs but doesn't send the email
6. When NO lowagie version is used PDFs are generated from swagger.
7. Application needs any Lowagie version EXCEPT 2.1.7 to send the emails.

# Errors reproductions
1. Include Lowagie v2.1.7 in pom and run application.
2. Go to the swagger to generate the Policy Schedule(Existing policies No. to use: 2015-1202/ 2015-1203)  using URI /api/policy-schedule/{policyReference}
2.1 Go into the application and check the Policy Schedule PDF generated according to PolicyReference.
2.2 Check application logs
2.3 Request a quote
Now
3. Include lowagie v2.1.7 and v4.2.1 (it generates PDFs from swagger but doesn't send emails)
3. Go into the application and check the Policy Schedule PDF generated according to PolicyReference.
4. After create a new Quote for a client and check email or IDE console logs
Then
5. Change from v2.1.7 (to any other version - 4.2.1) and run the application again
6. Repeat the steps above about the checking PDFs and App logs.

I'm still refining the JasperReport, thus the output pdf is not the final one and directories to store the generated PDFs schedules.
