$build = "C:\Users\noahm\OneDrive\Documents\tests\java\NomNomGenerator\build\"

#ls .\ch\* -Recurse -Filter "*.java" | % {
	#	javac $_.FullName -d $build
#}
javac .\ch\*.java .\ch\enums\*.java .\ch\GraphContent\*.java -d .\build
java --enable-preview -XX:+ShowCodeDetailsInExceptionMessages -cp .\build 'ch.Main' $args[0] 

#java '-Dprism.order=sw' -Xmx8192m -Xms8192m --enable-preview '-XX:+ShowCodeDetailsInExceptionMessages' --module-path $env:JAVAFX_HOME --add-modules javafx.controls,javafx.fxml,javafx.graphics  '@C:\Users\noahm\AppData\Local\Temp\cp_bk0kgri6ijybhcodh9e0g76g5.argfile' 'ch.Main'