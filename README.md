# EasyJetTestFramework

<b><h1>Steps to run:</h1></b></br>

1)Clone the project.</br>
2)Navigate to the project through command line.</br>
3)Execute the command - <b>mvn clean install -P Chrome,Medium\ View\ Port\ Landscape,Production\ Environment</b> </br>
4)Check reports - EasyJetTestFramework/target/cucumber-html-report/cucumber-html-reports/overview-features.html</br></br></br>


<b><h2>Features of the framework </h2></b></br>

<b>1)Can be run on different browsers using multiple view ports. </b></br>
 
 Example to run on Firefox in Medium View Port Landscape mode - </br>
 
 <b>mvn clean install -P Firefox,Medium\ View\ Port\ Landscape,Production\ Environment</b></br>
 
<b>2)Tests run parallely (count can be increased) through multiple runners and the json files</b></br> 
<b>from different runners are merged at the end to produce a consolidated HTML report.</b></br>

<b>3)Can be run on different environments.</b></br>

  Example to run on QA Environment - </br>
  
  <b>mvn clean install -P Chrome,Extra\ Large\ View\ Port,QA\ Environment</b></br>
  
<b>4)Can run on different Operating Systems (Currently Windows and Mac - more can be added).</b></br>

<b>5)Takes screenshot of failing scenarios and embeds it in the test report.</b></br></br></br>



<b><h2>Things to note</h2></b></br>

1)All the timeouts are dynamic in nature with regular polling. </br>

2)Additional feature file (Flight Booking Demo For Parallel Run) has been created with the same scenarios </br>
just to demonstrate parallel run. </br>

3)First scenario in the Flight Booking Demo For Parallel Run.feature has been provided with invalid data </br>
for the price to make the test fail purposely to demonstrate that the failing scenarios embed screenshots in the </br>
test report </br>

4)Only the first scenario in both the feature files have been implemented. </br>

5)Chrome and Firefox need to be updated to the latest version. </br>

6)Operating System should have maven and java installed. </br>

7)Parallel run for multiple browsers for different view ports and environments at once using the same code </br>
base can be easily achieved through Jenkins </br>

For example - Multiple instances of firefox can run parallely with multiple instances of chrome </br>
through jenkins at the same time using the same code base. </br>

8)Binaries for browsers are downloaded automatically during runtime. </br>