#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int gravity(int N, int teto, int** matriz)
{
    int altura = 0;
    int trocas = 0;
    for(int i = 0; i < N; i++)
    {
        altura = matriz[teto][i];
        if(altura < matriz[teto][i + 1])
        {
            trocas++;
            teto = (teto == 1) ? 0 : 1;
        }
        else if(matriz[teto][i] == 0)
        {
            trocas++;
            teto = (teto == 1) ? 0 : 1;
        }
    }
    return trocas;
}


int main ()
{
    int N = 0;

    int** matriz = (int**) malloc(2*sizeof(int*));
    for(int i = 0; i < 2; i++)
    {
        matriz[i] = (int*) malloc(10000*sizeof(int));
    }

    while(scanf("%d", &N) == 1)
    {
        for(int i = 0; i < 2; i++){
            for(int j = 0; j < N; j++){
                int numero = 0;
                scanf("%d", &numero);
                matriz[i][j] = numero;
            }
        }
        int menorTroca = 0;
        menorTroca = (gravity(N, 0, matriz) > gravity(N, 1, matriz)) ? gravity(N, 1, matriz) : gravity(N, 0, matriz);
        printf("%d\n", menorTroca);
    }
            

    
}