***thormegseleniumdemo Shop Demo based on automationpractice.com***

*Note* 
This was created in 2018 in Eclipse and did not run locally in my IntelliJ before pushing. Versions can be modified and this sorted. The code worked as expect (I think!)

*How to run*
Zip file attached; no git steps necessary.

Framework was created within Eclipse using:
*rest-assured(.io) for REST API calls
*Selenium webdriver for browser automation, using only Chrome browser at the moment
*TestNG for asserts and reporting
*Gradle as build software.

TestNG supports some default reporting options, including a HTML output report which shows runs, 
passes and failures.

TestNG requires a free Eclipse plugin from the Eclipse Marketplace ('TestNG for Eclipse') in order to 
right click > 'Run as TestNG'.

Can otherwise be run from Runner with the supplied and generic testng file in resources.

*Technical Debt*
-Page object models should be expanded to be more specific to each page for future testing efforts rather than 
combined and created as 'just enough' for current testing.

-Some locators and wait utils are implemented hastily; it would be best to expand these utils using
 better ID hooks, to improve robustness. Some of this could require changes to the target website, though.

-Config files should be supported; I would encourage that the project be moved to a model whereby we
 can specify a config file (in YAML, or otherwise) which can be read in at runtime and set things like
 target server, base URIs and user/passwords. Ideally, these environmental variables could be overloaded
 at runtime to support continuous integration tools like Jenkins.
 
-Reporting and logging should be moved to a proper framework - I suggest a database backend where test
 runs can be reported on with a frontend or exported as necessary. 
 
-When using rest-assured, it is best to create some wrapper utilities to handle REST calls, to manage error logging
and the DTOs should be heavily utilised where possible to avoid brittle JSON code.