#java -jar '-Dprism.order=sw' -Xmx8192m -Xms8192m --enable-preview '-XX:+ShowCodeDetailsInExceptionMessages' --module-path $env:JAVAFX_HOME --add-modules javafx.controls,javafx.fxml,javafx.graphics .\NomNomGenerator.jar

java --enable-preview -jar --module-path $env:JAVAFX_HOME --add-modules javafx.controls,javafx.fxml,javafx.graphics NomNomGenerator.jar 