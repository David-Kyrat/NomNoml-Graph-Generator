$jarPath = NomNomGenerator.jar
if ($args.Length > 0) { $jarPath = $args[0] }

java '-Dprism.order=sw' -Xmx8192m -Xms8192m --enable-preview '-XX:+ShowCodeDetailsInExceptionMessages' --module-path $env:JAVAFX_HOME --add-modules javafx.controls,javafx.fxml,javafx.graphics -jar  $jarPath
