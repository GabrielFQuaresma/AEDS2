
//INCLUSÕES

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <time.h>
#include <math.h>

#include "stringFlexList.h"

//DEFINIÇÕES
#define INT_MAX 2147483647
#define MAX_ATTRIBUTES 8
#define MAX_SIZE_STR 400
#define e 2.718281828

typedef char* String;
typedef const char* literal;

typedef struct S_FLHash{
    StringFList* array;
    int maxSize;

    void (*insert)(String name, int height, struct S_FLHash*);
    
    bool (*search)(String name, int height, struct S_FLHash);
    
} StringFHash;



void Insert(String name, int height, StringFHash* hash)
{
    int hashPosition = height % hash->maxSize;
    InsertList(name, &hash->array[hashPosition]);
}

bool Search(String name, int height, StringFHash hash)
{
    bool result = false;
    int hashPosition = height % hash.maxSize;
    Cell* tmp = hash.array[hashPosition].top->next;
    while(tmp != nullptr){
        if(strcmp(tmp->string, name) == 0) result = true;
    }
    return result;
}

/**
    Método para criação de uma Hash
    @param maxSize - número que dira o tamanho da hash
    @return retorna a Hash
*/
StringFHash newHash(size_t maxSize)
{

    StringFHash hash;

    hash.array = (StringFList*)(malloc(maxSize * sizeof(StringFList)));
    
    for(int i = 0; i < maxSize; i++){
        hash.array[i] = newList();
    }

    hash.maxSize = maxSize;

    hash.insert = Insert;
    
    hash.search = Search;

    return hash;
}