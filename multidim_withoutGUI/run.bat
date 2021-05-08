@ECHO OFF
set PATH_TO_FX="C:\Program Files\Java\javafx-sdk-11.0.2\lib"
javac --module-path %PATH_TO_FX% --add-modules javafx.controls *.java >> error_comp.log
java --module-path %PATH_TO_FX% --add-modules javafx.controls Main 3 4 %1 %2 %3 %4 %5 2> error.log
pause