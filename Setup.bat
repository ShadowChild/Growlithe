@ECHO OFF

:setup
set currentDir="%CD%"
set commonDir=%currentDir%\ShadowCommon
set mainDir=%silenceDir%\..\

:buildShadowCommon
echo Copying Files...
echo f | xcopy /y %currentDir%\setupSC.bat %commonDir%\setup.bat
pushd %commonDir%
call setup.bat
popd

:copyLibs
echo Copying Libraries...
robocopy %commonDir%\build\ %mainDir%\libs\ShadowCommon\

:mvnInstall
call mvnw clean install

:cleanup
echo Cleaning Up...
del %commonDir%\setup.bat

:end
pause

