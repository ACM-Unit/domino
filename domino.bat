@echo off
set /p user="enter your database username: "
set /p password="enter your database password: "
@echo user = %user%>src/main/resources/database.properties
@echo password = %password%>>src/main/resources/database.properties
@echo off
:getConfirmation
set /p confirmTest=Continue in test mode [y/n] ?:
if %confirmTest%==y goto :test
if %confirmTest%==n goto :script
:test
set import=importtest
goto :continue
:script
set import=import
goto :continue
:continue
mvn jetty:run -Duser=%user% -Dpassword=%password% -Dimport=%import%