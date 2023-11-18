//INCLUSÕES

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <time.h>
#include <math.h>

#include "player.h"
#include "libs.h"

//DEFINIÇÕES
#define INT_MAX 2147483647
#define MAX_ATTRIBUTES 8
#define MAX_SIZE_STR 400
#define e 2.718281828

typedef char* String;
typedef const char* literal;

typedef struct S_CQueue{
    Player* array;
    int size;
    int first;
    int last;
    int maxSize;

    void (*insert)(Player player, struct S_CQueue*);
    
    Player (*remove)(struct S_CQueue*);
    
    void (*show)(struct S_CQueue);
    void (*close)(struct S_CQueue*);
} PlayerCQueue;


/**
    Método de remoção de um Player no inicio do array, com custo de theta de (n), devido ao shift dos elementos.
    @param queue - Fila em que o Player sera removido
    @return Caso a fila tenha Player pra ser removido, retornará o Player removido. Caso contrário, será retornado o valor máximo de um inteiro
*/
Player removeQueue(PlayerCQueue* queue)
{
    if(queue->size <= 0){
        printf("Erro! inserindo em fila vazia");
        exit(-1);
    }

    Player removed = queue->array[queue->first];
    
    queue->size--;
    queue->first = (queue->first + 1) % queue->maxSize;
    return removed;
}


/**
    Método de inserção de um player no fim do array, com custo de theta de (1).
    @param player - Player que será inserido na fila.
    @param queue - Fila que será inserido o numero
*/
void insert(Player player, PlayerCQueue* queue)
{
    if((queue->size + 1) == queue->maxSize){
        removeQueue(queue);
    }

    queue->size++;
    queue->array[queue->last] = player;
    queue->last = (queue->last + 1) % queue->maxSize;
}

/**
    Método de mostragem dos playeres dentro da fila, com custo de theta de (N)
*/
void showList(PlayerCQueue queue)
{
    for(int i = queue.first; i != queue.last; i = (i + 1) % queue.maxSize)
    {
        printPlayer(queue.array[i]);
    }
}

void showNoIDList(PlayerCQueue queue)
{
    int j = 0;
    for(int i = queue.first; i != queue.last; i = (i + 1) % queue.maxSize)
    {
        printf("[%d]", j++);
        NoIDPrintPlayer(queue.array[i]);
    }
}

void showPartialList(int k, PlayerCQueue queue)
{
    for(int i = 0; i < k; i++)
    {
        printPlayer(queue.array[i]);
    }
}


/**
    Método para limpar a memória da fila
    @param queue - Fila que será limpa
*/
void closeList(PlayerCQueue* queue)
{
    free(queue->array);
    queue->array = NULL;
    queue->maxSize = 0;
    queue->size = 0;
}


/**
    Método para ler um base da dados e inserir na fila
    @param filePath - Caminho do arquivo.
    @param queue - Fila que será lida.
*/
void readDataBase(String filePath, PlayerCQueue* queue)
{
    FILE* DB;
    if(!(DB = fopen(filePath, "r"))){
        printf("Erro: Falha ao abrir base de dados");
        exit(0);
    }
    //fseek(DB, 60, SEEK_SET);

    while(queue->size < queue->maxSize && !feof(DB))
    {
        String* line = splitFile(8, DB);
        
        Player tmp = newPlayerByStrings(line);
        queue->insert(tmp, queue);
    }
    
}

/**
    Método para criação de uma fila
    @param maxSize - número que dira o tamanho da fila
    @return retorna a Fila
*/
PlayerCQueue newQueue(size_t maxSize)
{

    PlayerCQueue queue;

    if (maxSize == 0) maxSize = 80;

    queue.array = (Player*)(malloc(maxSize * sizeof(Player)));
    queue.size = 0;
    queue.first = 0;
    queue.last = 0;
    queue.maxSize = maxSize;

    queue.insert = insert;
    
    queue.remove = removeQueue;


    queue.show = showList;
    queue.close = closeList;

    return queue;
}


