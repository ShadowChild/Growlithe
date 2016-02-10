@ECHO OFF

:setup
set currentDir="%CD%"
set commonDir=%currentDir%\libs\Cybernize
set mainDir=%commonDir%\..\..\

:buildShadowCommon
echo Copying Files...
echo f | xcopy /y %currentDir%\setupSC.bat %commonDir%\setup.bat
pushd %commonDir%
call setup.bat
popd

:mvnInstall
call mvnw clean install

:cleanup
echo Cleaning Up...
del %commonDir%\setup.bat

:end
pause

