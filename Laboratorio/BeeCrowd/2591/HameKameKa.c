#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int readKa(char str[])
{
    int N = 0;
    int i = 0;
    int tamanho = strlen(str);
    while(i < tamanho && str[i] != 'k'){
        i++;
    }
    i++;
    while(i < tamanho && str[i] == 'a'){
        N++;
        i++;
    }
    return N;
}


int main(void)
{
    int C = 0;
    char* str = (char*) malloc(200*sizeof(char));
    char* saida = (char*) malloc(200*sizeof(char));
    scanf("%d", &C);

    for(int i = 0; i < C; i++)
    {
        scanf("%s", str);
        int N = readKa(str);
        int AQNT = N * (i + 1);
        *(saida + 0) = 'k';
        for(int j = 0; j < AQNT; j++)
        {
            *(saida + j + 1) = 'a';
        }
        printf("%s\n", saida);
    }
}