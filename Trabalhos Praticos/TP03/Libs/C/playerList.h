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

/*

    Lista Linear de playeres

*/

typedef struct S_List{
    Player* array;
    int size;
    int maxSize;

    void (*insert)(int position, Player player, struct S_List*);
    void (*insertStart)(Player player, struct S_List*);
    void (*insertEnd)(Player player, struct S_List*);
    
    Player (*remove)(int position, struct S_List*);
    Player (*removeStart)(struct S_List*);
    Player (*removeEnd)(struct S_List*);
    
    void (*show)(struct S_List);
    void (*close)(struct S_List*);
} PlayerList;



/**
    Método de inserção de um player no inicio do array, com custo de theta de (n).
    @param player - Player que será inserido na lista.
    @param list - Lista que será inserido o numero
*/
void beginningInsertList(Player player, PlayerList* list)
{
    if(list->maxSize > 0){
        if(list->size < list->maxSize){
            list->size++;

            for(int i = list->size; i > 0; i--) list->array[i] = list->array[i-1];

            list->array[0] = player;
        }
    }
}

/**
    Método de inserção de um player no fim do array, com custo de theta de (1).
    @param player - Player que será inserido na lista.
    @param list - Lista que será inserido o numero
*/
void endInsertList(Player player, PlayerList* list)
{
    if(list->maxSize > 0){
        if(list->size < list->maxSize){
            list->array[list->size++] = player;
        }
    }
}



/**
    Método de inserção de um player em qualquer posicao do array, com custo de theta de (n).
    @param player - Player que será inserido na lista.
    @param list - Lista que será inserido o numero
    @param position - Posição que o player sera inserido
*/
void InsertList(int position, Player player, PlayerList* list)
{
    if(list->maxSize < 0 || list->size == list->maxSize){
        printf("Insercao em qualquer posicao incorreta");
        exit(-1);
    }

    if(position == 0) beginningInsertList(player, list);
    else if(position > list->size) endInsertList(player, list);
    else{
        list->size++;

        for(int i = list->size; i > position; i--) list->array[i] = list->array[i-1];

        list->array[position] = player;
    }
}

/**
    Método de remoção de um Player no inicio do array, com custo de theta de (n), devido ao shift dos elementos.
    @param list - Lista em que o Player sera removido
    @return Caso a lista tenha Player pra ser removido, retornará o Player removido. Caso contrário, será retornado o valor máximo de um inteiro
*/
Player beginningRemoveList(PlayerList* list)
{
    Player removed = newPlayer();
    if(list->size > 0)
    {
        removed = list->array[0];
        
        for(int i = 0; i < list->maxSize - 1; i++)
        {
            list->array[i] = list->array[i+1]; 
        }
        list->size--;
    }
    return removed;
}

Player endRemoveList(PlayerList* list)
{
    Player removed = newPlayer();
    if(list->size > 0){
        removed = list->array[--list->size];
    }
    return removed;
}

Player removeList(int position, PlayerList* list)
{
    if(list->size <= 0 || position < 0 || position > list->size ){
            printf("Erro: Removendo incorretamente");
            exit(-1);
    }

    Player removed = list->array[position];
    list->size--;

    for(int i = position; i < list->size; i++) list->array[i] = list->array[i + 1];
    
    return removed;
}


/**
    Método de mostragem dos playeres dentro da lista, com custo de theta de (N)
*/
void showList(PlayerList list)
{
    for(int i = 0; i < list.size; i++)
    {
        printPlayer(list.array[i]);
    }
}

void showPartialList(int k, PlayerList list)
{
    for(int i = 0; i < k; i++)
    {
        printPlayer(list.array[i]);
    }
}


/**
    Método para limpar a memória da lista
    @param list - Lista que será limpa
*/
void closeList(PlayerList* list)
{
    free(list->array);
    list->array = NULL;
    list->maxSize = 0;
    list->size = 0;
}


/**
    Método para ler um base da dados e inserir na lista
    @param filePath - Caminho do arquivo.
    @param list - Lista que será lida.
*/
void readDataBase(String filePath, PlayerList* list)
{
    FILE* DB;
    if(!(DB = fopen(filePath, "r"))){
        printf("Erro: Falha ao abrir base de dados");
        exit(0);
    }
    //fseek(DB, 60, SEEK_SET);

    while(list->size < list->maxSize && !feof(DB))
    {
        String* line = splitFile(8, DB);
        
        Player tmp = newPlayerByStrings(line);
        list->insertEnd(tmp, list);
    }
    
}

/**
    Método para criação de uma lista
    @param maxSize - número que dira o tamanho da lista
    @return retorna a Lista
*/
PlayerList newList(size_t maxSize)
{

    PlayerList list;

    if (maxSize == 0) maxSize = 80;

    list.array = (Player*)(malloc(maxSize * sizeof(Player)));
    list.size = 0;
    list.maxSize = maxSize;

    list.insert = InsertList;
    list.insertStart = beginningInsertList;
    list.insertEnd = endInsertList;
    
    list.remove = removeList;
    list.removeEnd = endRemoveList;
    list.removeStart = beginningRemoveList;


    list.show = showList;
    list.close = closeList;

    return list;
}



/*


    Algoritmos de pesquisa


*/

Player sequencialSearch(int id, PlayerList list)
{
    Player returned = newPlayer();
    for(int i = 0; i < list.size; i++)
    {
        if(id == list.array[i].id)
        {
            returned = list.array[i];
            i = list.size;
        }
    }
    return returned;
}


bool RecursiveBinarySearch(int left, int right, String name, PlayerList list)
{
    int middle = (left + right)/2;
    Player tmp = list.array[middle];
    if(strstr(tmp.name, name))
        return true;  
    else if(strcmp(tmp.name, name) < 0)
        left = middle + 1; 
    else
        right = middle - 1;
    return left <= right ? RecursiveBinarySearch(left, right, name, list) : false;
}

bool BinarySearch(String name, PlayerList list)
{
    return RecursiveBinarySearch(0, list.size - 1, name, list);
}


void swap(Player* i, Player* j)
{
    Player aux = *i;
    *i = *j;
    *j = aux;
}
