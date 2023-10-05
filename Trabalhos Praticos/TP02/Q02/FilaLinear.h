//INCLUSÕES

#include <stdio.h>
#include <stdlib.h>

//DEFINIÇÕES
#define INT_MAX 2147483647;

typedef struct S_Queue{
    int *array;
    int size;
    int maxSize;

    void (*insert)(int number, struct S_Queue*);
    void (*remove)(struct S_Queue*);

    void (*show)(struct S_Queue);
    void (*close)(struct S_Queue*);
} Queue;

/**
    Método de inserção de um número no fim do array, com custo de theta de (1).
    @param number - número para ser inserido na fila
    @param queue - Fila que será inserido o numero
*/
void insertQueue(int number, Queue* queue)
{
    if(queue->maxSize > 0){
        if(queue->size != queue->maxSize){
            queue->array[queue->size++] = number;
        }
    }
}

/**
    Método de remoção de um número no inicio do array, com custo de theta de (n), devido ao shift dos elementos.
    @param queue - Fila em que o número sera removido
    @return Caso a fila tenha número pra ser removido, retornará o número removido. Caso contrário, será retornado o valor máximo de um inteiro
*/
int removeQueue(Queue* queue)
{
    int removed = INT_MAX;
    if(queue->size > 0)
    {
        removed = queue->array[0];
        
        for(int i = 0; i < queue->maxSize - 1; i++)
        {
            queue->array[i] = queue->array[i+1]; 
        }
        queue->size--;
    }
    return removed;
}

/**
    Método de mostragem dos números dentro da fila, com custo de theta de (N)
    @param queue - Fila que será mostrada
*/
void showQueue(Queue queue)
{
    printf("{");
    for(int i = 0; i < queue.size; i++)
    {
        printf("%d", queue.array[i]);
        if(i < queue.size - 1) printf(", ");
    }
    printf("}\n");
}

/**
    Método para limpar a memória da fila
    @param queue - Fila que será limpa
*/
void closeQueue(Queue* queue)
{
    free(queue->array);
    queue->maxSize = 0;
    queue->size = 0;
}

/**
    Método para criação de uma fila
    @param maxSize - número que dira o tamanho da fila
    @return retorna a Fila
*/
Queue newQueue(size_t maxSize)
{

    Queue queue;

    if (maxSize == 0) maxSize = 80;

    queue.array = (int*)(malloc(maxSize * sizeof(int)));
    queue.size = 0;
    queue.maxSize = maxSize;

    queue.insert = insertQueue;
    queue.remove = removeQueue;

    queue.show = showQueue;
    queue.close = closeQueue;

    return queue;
}