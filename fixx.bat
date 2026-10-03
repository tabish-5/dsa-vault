@echo off

echo Opening File1...
start "" /b code "StriverATZ\Arrays\Hard\FourSum.java"
timeout /t 1 /nobreak >nul

echo Closing File1...
powershell -command "$wshell = New-Object -ComObject WScript.Shell; $wshell.AppActivate('Visual Studio Code'); Start-Sleep -Milliseconds 300; $wshell.SendKeys('^w')"
@REM  timeout /t 1 /nobreak >nul

echo Opening File2...
start "" /b code "StriverATZ\Arrays\Easy\MaxConsecutiveOnes.java"
timeout /t 1 /nobreak >nul

echo Closing File2...
powershell -command "$wshell = New-Object -ComObject WScript.Shell; $wshell.AppActivate('Visual Studio Code'); Start-Sleep -Milliseconds 300; $wshell.SendKeys('^w')"
@REM  timeout /t 1 /nobreak >nul


echo Done!
timeout /t 1 /nobreak >nul
cls
