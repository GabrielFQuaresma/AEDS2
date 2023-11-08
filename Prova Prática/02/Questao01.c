//INCLUSÕES

#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

//DEFINIÇÕES
#define INT_MAX 2147483647;


typedef struct Dragao{
    int diasTreino;
    int multa;
}Dragao;

typedef struct S_Queue{
    Dragao* array;
    int size;
    int maxSize;
    bool livre;

    void (*insert)(Dragao dragao, struct S_Queue*);
    int (*remove)(int posicao, int dia, int multa, struct S_Queue*);

    void (*show)(struct S_Queue);
    void (*close)(struct S_Queue*);
} Queue;

void insertQueue(Dragao dragao, Queue* queue)
{
    if(queue->maxSize > 0){
        if(queue->size != queue->maxSize){
            queue->array[queue->size++] = dragao;
        }
    }
}

int removeQueue(int posicao, int dia, int multa, Queue* queue)
{
    if(queue->livre)
    if(queue->size > 0)
    {
        for(int i = 0; i < queue->maxSize - 1; i++)
        {
            queue->array[i] = queue->array[i+1]; 
        }
        queue->size--;
    }
}

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

int main()
{
    int diasTreino, multaDormindo = 0;
    Queue queue = newQueue(10000);
    int dia = 1;
    int maiorMulta = 0;
    int posicaoMaiorMulta = 0;

    while(scanf("%d %d", diasTreino, multaDormindo) == 1){
        Dragao tmp;
        tmp.diasTreino = diasTreino;
        tmp.multa = multaDormindo;
        queue.insert(tmp, &queue);
        dia++;
        if(maiorMulta < multaDormindo){
            maiorMulta = multaDormindo;
            posicaoMaiorMulta = dia;
        }
        queue.remove(posicaoMaiorMulta, dia, maiorMulta, &queue);
    }

}