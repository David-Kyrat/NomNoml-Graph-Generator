$build = "C:\Users\noahm\OneDrive\Documents\tests\java\NomNomGenerator\build\"

#ls .\ch\* -Recurse -Filter "*.java" | % {
	#	javac $_.FullName -d $build
#}
javac .\ch\*.java .\ch\enums\*.java .\ch\GraphContent\*.java -d .\build
java --enable-preview -XX:+ShowCodeDetailsInExceptionMessages -cp .\build 'ch.Main' $args[0] 
