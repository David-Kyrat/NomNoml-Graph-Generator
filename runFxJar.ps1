$oldPath = pwd
$arg = @($args)[0]

if ($arg -ne $null) {cd $arg}
java '-Dprism.order=sw' -Xmx8192m -Xms8192m --enable-preview '-XX:+ShowCodeDetailsInExceptionMessages' --module-path $env:JAVAFX_HOME --add-modules javafx.controls,javafx.fxml,javafx.graphics -jar "NomNomGenerator.jar"

if ($arg -ne $null) {cd $oldPath}