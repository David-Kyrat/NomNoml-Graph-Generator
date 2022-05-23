#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main() {
	char* relDir = "files";
	char* command = "Powershell -noprofile -ExecutionPolicy Bypass \"& './runFxJar.ps1'\" ";

	system("set pt=\%CD\%");
	char* mainArg = malloc(200*sizeof(char));
	sprintf(mainArg, "cd %s && %s", relDir, command);
	system(mainArg);
	system("cd ..");
	//system("cd \%pt\%");
	return 0;
}
