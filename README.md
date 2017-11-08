# web service domino
This service calculates all possible and the longest chains of domino from your chosen domino set.
Your have 28 domino in start. From 0:0 to 6:6. You can choose a set with a predefined amount of dominoes or select random
Also you can choose what the system should calculate: all chains or only the longest ones.
After calculate, on page which displays the results you can save your chains or load saved

Assembly, deploy and start-up instructions
For successful work during assembling your must have installed maven on your PC and write environment variable (mvn) 
and also installed database MySQL.
In root directory stored batch script domino.bat. Run it.
You will be prompted to enter your login and password from your database also you have to choose mode - usual or test.
In the test mode, the database is filled with test data.
Logs a placed in /logs
