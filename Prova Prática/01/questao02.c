#include <stdio.h>
#include <stdlib.h>

/*
int hasPair(int length, int* array)
{
	int noPair = 0;
	int numAnterior = -10000;
	int qntNumAnt = 0;
	int quantidadePar = 0;
	for(int i = 0; i < length; i++)
	{
		//printf("qntPar antes = %d\n", quantidadePar);
		int tmpNum = array[i];
		//printf("%d\n", tmpNum);
		if(numAnterior != tmpNum)
		{
			quantidadePar = 1;
			numAnterior = tmpNum;
			for(int j = i+1; j < length; j++)
			{
				if(tmpNum == array[j])
				{
					//printf("array = %d\n", array[j]);
					//printf("qntPar = %d\n", quantidadePar);
					quantidadePar ++;
					//printf("qntPar = %d\n", quantidadePar);
				}
			}
			
		}
		if(quantidadePar % 2 != 0)
		{
			noPair = tmpNum;
			i = length;
		}
	}
	return(noPair);
}
*/

int hasPair(int length, int* array)
{
	int noPair = 0;
	int alreadyGone[length];
	int qntGone = 0;
	int qntNum = 0;
	
	for(int i = 0; i < length; i++)
	{
		qntNum = 1;
		int gone = 0;
		for(int j = 0; j < qntGone; j++)
		{
			if(array[i] == alreadyGone[j])
			{
				gone = 1;
			}
		}
		int tmpNum = array[i];
		if(!gone)
		{
			for(int j = i+1; j < length; j++)
			{
				if(tmpNum == array[j])
				{
					qntNum++;
				}
			}
			alreadyGone[qntGone] = tmpNum;
			qntGone++;
			if(qntNum % 2 != 0)
			{
				noPair = tmpNum;
				i = length;
			}
		}
	}
	return(noPair);
}


int main ()
{
	int quantidade = 0;
	int num = 0;
	
	scanf("%d", &quantidade);
	
	while(quantidade != 0)
	{
		int array[quantidade];
		
		for(int i = 0; i < quantidade; i++)
		{
			scanf("%d", &num);
			array[i] = num;
		}
		printf("%d\n", hasPair(quantidade, array));
		scanf("%d", &quantidade);
	}
	
}