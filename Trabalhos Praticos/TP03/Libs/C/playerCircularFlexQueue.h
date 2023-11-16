//INCLUSÕES

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <time.h>
#include <math.h>

#include "Celulas/Cell.h"++++++
#include "libs.h"

//DEFINIÇÕES
#define INT_MAX 2147483647
#define MAX_ATTRIBUTES 8
#define MAX_SIZE_STR 400
#define e 2.718281828

typedef char* String;
typedef const char* literal;

typedef struct S_CFQueue{
    Player* array;
    Cell* first;
    Cell* last;
    int size;

    void (*insert)(Player player, struct S_CFQueue*);
    
    Player (*remove)(struct S_CFQueue*);
    
    void (*show)(struct S_CFQueue);
} PlayerCFQueue;


/**
    Método de inserção de um player no fim do array, com custo de theta de (1).
    @param player - Player que será inserido na Fila.
    @param queue - Fila que será inserido o numero
*/
void Insert(Player player, PlayerCFQueue* queue){
    queue->last->next = newCell(player);
    queue->last = queue->last->next;
    queue->last->next = queue->first;
    
    queue->size++;
}

Player Remove(PlayerCFQueue* queue){
    if(queue->size < 0){
        printf("Erro! Removendo em fila vazia");
        exit(-1);
    }

    Player removed = queue->first->next->player;
    queue->first->next = queue->first->next->next;

    queue->size--;
    return removed;
}

/**
    Método de mostragem dos playeres dentro da lista, com custo de theta de (N)
*/
void showList(PlayerCFQueue queue)
{
    for(Cell* tmp = queue.first->next; tmp != queue.first; tmp = tmp->next){
        printPlayer(tmp->player);
    }
}

void showNoIDList(PlayerCFQueue queue)
{
    int i = 0;
    for(Cell* tmp = queue.first->next; tmp != queue.first; tmp = tmp->next){
        printf("[%d]", i++);
        NoIDPrintPlayer(tmp->player);
    }
}


/**
    Método para criação de uma lista
    @param maxSize - número que dira o tamanho da lista
    @return retorna a Lista
*/
PlayerCFQueue newList(size_t maxSize)
{

    PlayerCFQueue queue;

    queue.first = newCellEmpty();
    queue.last = queue.first;

    if (maxSize == 0) maxSize = 80;

    queue.array = (Player*)(malloc(maxSize * sizeof(Player)));
    queue.size = 0;

    queue.insert = Insert;
    
    queue.remove = Remove;


    queue.show = showList;

    return queue;
}
