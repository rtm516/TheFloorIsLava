@echo off
start "" /b /wait cmd /c D:\Projects\eclipse\apache-maven-3.6.1\bin\mvn package

echo Locating and copying latest build file...
for /f %%i in ('dir .\target\ /b/a-d/od/t:c') do set LAST=%%i
copy .\target\%LAST% .\dev_server\plugins\

echo Starting/Restarting Paper server...
taskkill /F /FI "WindowTitle eq Paper Server" /T

ping 127.0.0.1 -n 2 > nul

cd .\dev_server\
start start.bat
echo Done.