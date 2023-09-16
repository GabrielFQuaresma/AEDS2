#include <stdio.h>

//Função para escrever no arquivo
void fileWrite (int length, char* fileName)
{
	FILE* arquivo = fopen(fileName, "wb");
	float number = 0.0;
	
	
	for(int i = 0; i < length; i++)
	{
		scanf("%f", &number);
		fprintf(arquivo, "%g", number);
		
		if(i < length - 1)
			fprintf(arquivo, "\n");
	}
	fclose(arquivo);
}

//Função para concatenar no arquivo
void fileAppend(char* fileName, float number)
{
	FILE* arquivo = fopen(fileName, "ab");
	
	if(arquivo != NULL)
	{
		fprintf(arquivo, "%f\n", number);
	}
	fclose(arquivo);
}

//Função para ler o arquivo de trás para frente
void fileRead(int n, char* fileName)
{
    FILE* arquivo = fopen(fileName, "rb");
	float number = 0;

    if (arquivo != NULL)
    {
		fseek(arquivo, 1, SEEK_END);
		for (int i = 0; i < n; i++)
		{
			fscanf(arquivo, "%f", &number);
			printf("%g\n", number);
			
			fseek(arquivo, -sizeof(float), SEEK_CUR);
		}
	}
    fclose(arquivo);
}


int main ()
{
	int quantidade = 0;
	float numero = 0.0;
	
	scanf("%d", &quantidade);
	getchar();
	
	fileWrite(quantidade, "OUTPUT");
	
	fileRead(quantidade, "OUTPUT");
}