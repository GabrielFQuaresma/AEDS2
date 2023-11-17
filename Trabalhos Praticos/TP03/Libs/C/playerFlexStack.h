//INCLUSÕES

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <time.h>
#include <math.h>

#include "Celulas/Cell.h"
#include "libs.h"

//DEFINIÇÕES
#define INT_MAX 2147483647
#define MAX_ATTRIBUTES 8
#define MAX_SIZE_STR 400
#define e 2.718281828

typedef char* String;
typedef const char* literal;

typedef struct S_FStack{
    Cell* top;
    int size;

    void (*insert)(Player player, struct S_FStack*);
    
    Player (*remove)(struct S_FStack*);
    
    void (*show)(struct S_FStack);
} PlayerFStack;

/**
    Método de inserção de um player no inicio do array, com custo de theta de (1).
    @param player - Player que será inserido na Pilha.
    @param stack - Pilha que será inserido o numero
*/
void Insert(Player player, PlayerFStack* stack){
    
    Cell* tmp = newCell(player);
    tmp->next = stack->top->next;
    stack->top->next = tmp;

    stack->size++;
}

Player Remove(PlayerFStack* stack){
    if(stack->size < 0){
        printf("Erro! Removendo em pilha vazia");
        exit(-1);
    }

    Player removed = stack->top->next->player;
    stack->top->next = stack->top->next->next;

    stack->size--;
    return removed;
}

/**
    Método de mostragem dos playeres dentro da lista, com custo de theta de (N)
*/
void showList(PlayerFStack stack)
{
    for(Cell* tmp = stack.top->next; tmp != NULL; tmp = tmp->next){
        printPlayer(tmp->player);
    }
}

void showNoIDList(PlayerFStack stack)
{
    int i = 0;
    for(Cell* tmp = stack.top->next; tmp != NULL; tmp = tmp->next){
        printf("[%d]", i++);
        NoIDPrintPlayer(tmp->player);
    }
}


/**
    Método para criação de uma lista
    @param maxSize - número que dira o tamanho da lista
    @return retorna a Lista
*/
PlayerFStack newList(size_t maxSize)
{

    PlayerFStack stack;

    stack.top = newCellEmpty();

    stack.size = 0;

    stack.insert = Insert;
    
    stack.remove = Remove;


    stack.show = showList;

    return stack;
}