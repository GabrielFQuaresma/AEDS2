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

typedef struct S_FList{
    Cell* top;
    int size;

    void (*insert)(String string, struct S_FList*);
    
    String (*remove)(struct S_FList*);
    
} StringFList;

/**
    Método de inserção de um string no inicio do array, com custo de theta de (1).
    @param string - String que será inserido na Lista.
    @param list - Lista que será inserido o numero
*/
void InsertList(String string, StringFList* list){
    
    Cell* tmp = newCell(string);
    tmp->next = list->top->next;
    list->top->next = tmp;

    list->size++;
}

String Remove(StringFList* list){
    if(list->size < 0){
        printf("Erro! Removendo em lista vazia");
        exit(-1);
    }

    String removed = list->top->next->string;
    list->top->next = list->top->next->next;

    list->size--;
    return removed;
}





/**
    Método para criação de uma lista
    @return retorna a Lista
*/
StringFList newList()
{

    StringFList list;

    list.top = newCellEmpty();

    list.size = 0;

    list.insert = InsertList;
    
    list.remove = Remove;


    return list;
}