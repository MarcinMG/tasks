call runcrud.bat
if "%ERRORLEVEL%" == "0" goto runchrome
echo.
echo RUNCRUD.BAT has errors - breaking work
goto fail

:fail
echo.
echo There were errors

:runchrome
start chrome http://localhost:8080/crud/v1/task/getTasks