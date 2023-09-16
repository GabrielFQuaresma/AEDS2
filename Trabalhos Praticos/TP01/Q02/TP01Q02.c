#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int palindromo (int tamanho, char* string)
{
	int verdade = 0; //Variavel boolean
	if (string != NULL)
	{
		verdade = 1; //Caso a string exista, define a variavel como verdadeira
		int i = 0;
		int j = tamanho - 1; 
		while (verdade && i < tamanho/2)
		{
			if (string[i] != string[j]) // Se a string for diferente
			{
				verdade = 0; //Define o boolean como falso
			}
			i++;
			j--;
		}
	}
	return(verdade);
}

int main (void)
{
	char* string = (char*)(malloc(80 * sizeof(char))); //Palavra do usuário
	scanf("%[^\n]%*c", string); //Leitura da palavra
	while(strcmp(string, "FIM")){
		int tamanho = 0; //Tamanho da palavra
		
		tamanho = strlen(string); //Captação do tamanho da palavra
	
		printf("%s\n", palindromo(tamanho, string) ? "SIM" : "NAO"); //Mostrando Resultado
		scanf("%[^\n]%*c", string);
	}
}