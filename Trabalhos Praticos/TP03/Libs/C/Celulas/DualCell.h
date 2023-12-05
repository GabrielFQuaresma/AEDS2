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

typedef struct DualCell{
    Player player;
    struct DualCell* next;
    struct DualCell* prior;
}DualCell;

DualCell* newCellEmpty(){
    DualCell* tmp = (DualCell*)malloc(sizeof(DualCell));
    tmp->player = newPlayer();
    tmp->next = NULL;
    tmp->prior = NULL;
    return tmp;
}

DualCell* newCell(Player player){
    DualCell* tmp = (DualCell*)malloc(sizeof(DualCell));
    tmp->player = player;
    tmp->next = NULL;
    tmp->prior = NULL;
    return tmp;
}

DualCell* newCellFull(Player player, DualCell* prior, DualCell* next){
    DualCell* tmp = (DualCell*)malloc(sizeof(DualCell));
    tmp->player = player;
    tmp->next = next;
    tmp->prior = prior;
    return tmp;
}