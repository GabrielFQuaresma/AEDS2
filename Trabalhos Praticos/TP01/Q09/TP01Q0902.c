#include <stdio.h>

#define ref(x) *(x)

void setCursor (int* cursorPosition, FILE* file)
{
	fseek(file, --ref(cursorPosition), SEEK_SET);
	char symbol = fgetc(file);
	while(symbol != '\n' && ref(cursorPosition) >= 0)
	{
		fseek(file, --ref(cursorPosition), SEEK_SET);
		symbol = fgetc(file);
	}
	fseek(file, ref(cursorPosition) + 1, SEEK_SET);
}



//Função para escrever no arquivo
void fileWrite (int length, char* fileName)
{
	FILE* arquivo = fopen(fileName, "w");
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
void fileRead(int length, char* fileName)
{
    FILE* file = fopen(fileName, "r");
    if (file != NULL)
    {
		float number = 0;
		fseek(file, 0, SEEK_END);
		int cursorPosition = ftell(file);
		for (int i = 0; i < length; i++)
		{
			setCursor(&cursorPosition, file);
			fscanf(file, "%g", &number);
			printf("%g", number);
			if(i + 1 < length) printf("\n");
		}
	}
    fclose(file);
}


int main ()
{
	int quantidade = 0;
	float numero = 0.0;
	
	scanf("%d", &quantidade);
	getchar();
	
	fileWrite(quantidade, "OUTPUT.txt");
	
	fileRead(quantidade, "OUTPUT.txt");
}