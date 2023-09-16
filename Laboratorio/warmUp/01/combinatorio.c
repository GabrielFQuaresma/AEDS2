#include <string.h>
#include <stdio.h>

int main (void)
{
	//Declaracao de variaveis
	char string1[250]; 
	char string2[250];
	
	int virtualCursor1 = 0;
	int virtualCursor2 = 0;
	
	int tamanho1 = 0;
	int tamanho2 = 0;
	int tamanhoTotal = 0;
	
	
	while (scanf("%s%s", string1, string2) != EOF)
	{	
		virtualCursor1 = 0;
		virtualCursor2 = 0;
		
		tamanho1 = strlen(string1);
		tamanho2 = strlen(string2);
		tamanhoTotal = tamanho1 + tamanho2;
		
		char tmp[tamanhoTotal];
		
		for(int i = 0; i < tamanhoTotal;)
		{
			if(virtualCursor1 < tamanho1)
			{
				tmp[i++] = string1[virtualCursor1++];
			}
			if(virtualCursor2 < tamanho2)
			{
				tmp[i++] = string2[virtualCursor2++];
			}
		}
		printf("%s\n", tmp);
	}
}