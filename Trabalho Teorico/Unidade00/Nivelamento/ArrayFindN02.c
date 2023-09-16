#include <stdio.h>
#include <stdlib.h>

//Função para achar um número em um array ordenado

int ArrayFindN (int length, int number, int* array){
	
	//Passando pelo arranjo
	for (int i = length/2; i < length && i >= 0;)
	{
		if(*(array + i) == number)
		{
			return(1);
		}
		else if(number > *(array + i) )
		{
			i++;
		}
		else
		{
			i--;
		}
	}
	return(0);
}

int isSort(int length, int* array)
{
	int sort = 1;
	//Para testar se está ordenado
	for(int i = length - 1; i > 0 && sort; i--)
	{
		if(*(array + i) < *(array + i - 1))
		{
			sort = 0;
		}
	}
	return(sort);
}

void Sort (int length, int index, int sort, int* array){
	
	//Váriavel de apoio
	int number = 0;
	
	//Teste para saber se não está ordenado, se o arranjo existe e o index é maior que 0
	if(index > 0 && !sort && array != NULL)
	{
		if(*(array + index) < *(array + index - 1))
		{
			//inverta as posições
			number = *(array + index - 1);
			*(array + index - 1) = *(array + index);
			*(array + index) = number;
		}
		
		//Variável para saber se está ordenado
		sort = isSort(length, array);
		
		//Recursividade
		Sort(length, index - 1, sort, array);
	}
	
	//Se ainda não estiver arrumado, chama denovo do inicio
	if(!sort)
		Sort(length, length - 1, sort, array);
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
	
	//Atribuição do valor que deseja ser encontrado
	printf("Qual o valor que você deseja encontrar no arranjo? ");
	scanf("%d", &number);
	
	//Chamada de método para ordenar o arranjo, caso necessário
	Sort(length, length - 1, isSort(length, array), array);
	
	//Chamada da função para procurar o valor"
	printf("%s\n", ArrayFindN(length, number, array) ? "Existe no Array" : "Não existe no Array");
}