#include <stdio.h>

//Função para achar um número dentro de um arranjo
int ArrayFindN (int length, int number, int * array)
{
	int result = 0; //Variável começando com o valor "TRUE"
	int i = 0; //Variável de controle de índice
	
	if (array != NULL)
	{
		while(result == 0 && i < length)
		{
			//Teste para ver se o numero está no arranjo
			if (array[i] == number)
			{
				result = 1; 
			}
			i++;
		}
	}
	return (result);
}

int main (void)
{
	int length = 0;
	int number = 0;
	
	//Perguntar o tamanho do arranjo
	printf("Qual o tamanho do arranjo? ");
	scanf("%d", &length);

	//Criação do arranjo
	int array[length];
	
	//Atribuição de valores para o arranjo
	for(int i = 0; i < length; i++)
	{
		printf("Qual o %d valor do arranjo? ", i+1);
		scanf("%d", &number);
		array[i] = number;
	}
	
	//Atribuição do valor que deseja ser encontrado
	printf("Qual o valor que você deseja encontrar no arranjo? ");
	scanf("%d", &number);
	
	//Chamada da função para procurar o valor"
	printf("%s\n", ArrayFindN(length, number, array) ? "Existe no Array" : "Não existe no Array");
}