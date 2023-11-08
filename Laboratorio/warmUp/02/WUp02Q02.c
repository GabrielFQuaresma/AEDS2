#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

typedef int** Matrix;
typedef int* Array;

Matrix newMatrix(int col, int rows){

    if(col < 0 || rows < 0){
        printf("Erro! numero de coluna invalido");
        exit(-1);
    } 
    
    Matrix matrix = (Matrix)malloc(col*sizeof(Array));
    for(int i = 0; i < col; i++){
        matrix[i] = (Array)malloc(rows*sizeof(int));
    }
    return matrix;
}

Matrix readMatrix(int col, int rows){
    
    Matrix matrix = newMatrix(col, rows);

    for(int i = 0; i < col; i++){
        for(int j = 0; j < rows; j++){
            int tmp = 0;
            scanf("%d", &tmp);
            matrix[i][j] = tmp;
        }
    }
    return matrix;
}

void printMatrix(int col, int rows,Matrix matrix){
    for(int i = 0; i < col; i++){
        for(int j = 0; j < rows; j++){
            printf("%d ", matrix[i][j]);
        }
        printf("\n");
    }
}

int 

int main ()
{
    int colunas, linhas, u = 0;
    scanf("%d %d %d", &colunas, &linhas, &u);

    Matrix matrix = readMatrix(colunas, linhas);

    printMatrix(colunas, linhas, matrix);
}