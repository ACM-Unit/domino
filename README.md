# web service domino
This service calculates all possible and the longest chains of domino from your chosen domino set. Your have 28 dominoes to pick. From 0:0 to 6:6. You can choose a set with a predefined amount of dominoes or use random set. Also you can choose what the system should calculate: all chains or only the longest one. After processing is finished, you can save your chains or load previously saved chains on the page with results.

Assemble, deploy and launch instructions.
To run this application your should have maven installed on your PC with its environment variable (mvn) set, and you'll need MySQL database installed as well. There's batch script domino.bat in the root directory. Run it. You will be prompted to enter your login and password for your database, also you have to choose mode - usual or test. In the test mode, the database is filled with test data. Logs a placed in /logs directory.
After deploymen web service will be available at http://localhost:8081/domino
