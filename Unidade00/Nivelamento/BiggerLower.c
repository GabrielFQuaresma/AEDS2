#include <stdio.h>
#include <stdlib.h>

int biggest (int length, int* array)
{
	//Numero de comparação
	int bigger = array[0];
	
	for(int i = 1; i < length; i++)
	{
		if(bigger < array[i])
			bigger = array[i];
	}
	return(bigger);
}

int lowest (int length, int* array)
{
	//Numero de comparação
	int lower = array[0];
	
	for(int i = 1; i < length; i++)
	{
		if(lower > array[i])
			lower = array[i];
	}
	return(lower);
}

int main (void)
{
	int length = 0;
	int number = 0;
	
	//Perguntar o tamanho do arranjo
	printf("Qual o tamanho do arranjo? ");
	scanf("%d", &length);

	//Criação do arranjo
	int* array = (int*)(malloc(length*sizeof(int)));
	
	//Atribuição de valores para o arranjo
	for(int i = 0; i < length; i++)
	{
		printf("Qual o %d valor do arranjo? ", i+1);
		scanf("%d", &number);
		array[i] = number;
	}
	
	printf("O maior numero: %d\nO menor numero: %d\n", biggest(length, array), lowest(length, array));
}