$fxPath = "javafx-sdk-16/lib"
#java --enable-preview  --module-path $env:JAVAFX_HOME --add-modules "javafx.controls,javafx.fxml,javafx.graphics" -jar ./NomNomGenerator.jar  

java --enable-preview  --module-path $fxPath --add-modules "javafx.controls,javafx.fxml,javafx.graphics" -jar ./NomNomGenerator.jar


if (Test-Path *.nomnoml) {
	mv *.nomnoml ../
}

