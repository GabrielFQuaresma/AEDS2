//INCLUSÕES

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <time.h>
#include <math.h>

#include "player.h"

//DEFINIÇÕES
#define INT_MAX 2147483647
#define MAX_ATTRIBUTES 8
#define MAX_SIZE_STR 400
#define e 2.718281828

typedef char* String;
typedef const char* literal;

typedef struct Cell{
    Player player;
    struct Cell* next;
}Cell;

Cell* newCellEmpty(){
    Cell* tmp = (Cell*)malloc(sizeof(Cell));
    tmp->player = newPlayer();
    tmp->next = NULL;
    return tmp;
}

Cell* newCell(Player player){
    Cell* tmp = (Cell*)malloc(sizeof(Cell));
    tmp->player = player;
    tmp->next = NULL;
    return tmp;
}

Cell* newCellFull(Player player, Cell* next){
    Cell* tmp = (Cell*)malloc(sizeof(Cell));
    tmp->player = player;
    tmp->next = next;
    return tmp;
}


