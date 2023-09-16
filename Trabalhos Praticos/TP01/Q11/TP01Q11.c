#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <locale.h>

//Função recursiva que testa se a string passada como parametro é palindromo ou não
int isPalindromoRecursivo(int index, int end, int result, char* string)
{
	if (string != NULL)
	{
		if(result && index < end)
		{
			//Recursividade acrescentando um no index e diminuindo um na variavel de index oposta
			result = isPalindromoRecursivo(index+1, end-1, string[index] == string[end], string);
		}
	}
	
	return result;
}

int isPalindromo(int length, char* string)
{
	return(isPalindromoRecursivo(0, length - 1, 1, string));
}

int main ()
{
	setlocale(LC_ALL, "pt_BR.utf8");
	//Declaração de váriaveis
	char* string = (char*)(malloc(80 * sizeof(char))); //Palavra do usuário
	
	scanf("%[^\n]%*c", string);
	while(strcmp(string, "FIM"))
	{
		printf(isPalindromo(strlen(string), string) ? "SIM\n" : "NAO\n");
		scanf("%[^\n]%*c", string);
	}
}