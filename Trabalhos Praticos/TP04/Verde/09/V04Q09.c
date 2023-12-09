
//INCLUSÕES

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <time.h>
#include <math.h>

//DEFINIÇÕES
#define INT_MAX 2147483647
#define MAX_ATTRIBUTES 8
#define MAX_SIZE_STR 400
#define e 2.718281828

typedef char* String;
typedef const char* literal;


/*

    Struct de Player

*/

String* splitFile(int attributes, FILE* file)
{
    
    if(attributes < 0)
    {
        printf("Erro: Numero de atributos deve ser maior que 0");
        exit(0);
    }

    String* str = (String*) malloc(attributes*sizeof(String));

    if(str != NULL)
    {
        char valor[MAX_SIZE_STR];
        for(int i = 0; i < attributes; i++)
        {
            str[i] = (String) malloc(MAX_SIZE_STR*sizeof(char));
            if (fscanf(file, "%[^,\n]", valor) == 0) {
                strcpy(str[i], "nao informado");
            } else {
                strcpy(str[i], valor);
            }
            fgetc(file); 
        }
    }
    return str;
}

typedef struct S_Player{

    int id;
    String name;
    int height;
    int weight;
    String university;
    int birthYear;
    String birthCity;
    String birthState;

    void (*setId)(int id, struct S_Player*);
    void (*setName)(String name, struct S_Player*);
    void (*setHeight)(int height, struct S_Player*);
    void (*setWeight)(int weight, struct S_Player*);
    void (*setUniversity)(String university, struct S_Player*);
    void (*setBirthYear)(int birthYear, struct S_Player*);
    void (*setBirthCity)(String birthCity, struct S_Player*);
    void (*setBirthState)(String birthState, struct S_Player*);


    struct S_Player (*clone)(struct S_Player); 
}Player;


void setId(int id, Player* player)
{
    player->id = id;
}

void setName(String name, Player* player)
{
    player->name = strdup(name);
}

void setHeight(int height, Player* player)
{
    player->height = height;
} 

void setWeight(int weight, Player* player)
{
    player->weight = weight;
}

void setUniversity(String university, Player* player)
{
    player->university = strdup(university);
}

void setBirthYear(int birthYear, Player* player)
{
    player->birthYear = birthYear;
}

void setBirthCity(String birthCity, Player* player)
{
    player->birthCity = strdup(birthCity);
}

void setBirthState(String birthState, Player* player)
{
    player->birthState = strdup(birthState);
}

Player clone(Player player)
{
    Player clone = player;
    
    clone.name = strdup(player.name);
    clone.university = strdup(player.university);
    clone.birthCity = strdup(player.birthCity);
    clone.birthState = strdup(player.birthState);

    return clone;
}


Player newPlayer()
{
    
    Player tmp;

    //Definindo atributos
    tmp.id = -1;
    tmp.name = (String) malloc(MAX_SIZE_STR*sizeof(char));
    tmp.height = -1;
    tmp.university = (String) malloc(MAX_SIZE_STR*sizeof(char));
    tmp.birthYear = -1;
    tmp.birthCity = (String) malloc(MAX_SIZE_STR*sizeof(char));
    tmp.birthState = (String) malloc(MAX_SIZE_STR*sizeof(char));

    //Definindo ponteiro pra funções
    tmp.setId = setId;
    tmp.setName = setName;
    tmp.setHeight = setHeight;
    tmp.setWeight = setWeight;
    tmp.setUniversity = setUniversity;
    tmp.setBirthYear = setBirthYear;
    tmp.setBirthCity = setBirthCity;
    tmp.setBirthState = setBirthState;

    return tmp;
}

Player newFullPlayer(int id, String name, int height, int weight, String university, int birthYear, String birthCity, String birthState)
{
    
    Player tmp;

    //Definindo atributos
    tmp.id = id;
    tmp.name = strdup(name);
    tmp.height = height;
    tmp.weight = weight;
    tmp.university = strdup(university);
    tmp.birthYear = birthYear;
    tmp.birthCity = strdup(birthCity);
    tmp.birthState = strdup(birthState);

    //Definindo ponteiro pra funções
    tmp.setId = setId;
    tmp.setName = setName;
    tmp.setHeight = setHeight;
    tmp.setWeight = setWeight;
    tmp.setUniversity = setUniversity;
    tmp.setBirthYear = setBirthYear;
    tmp.setBirthCity = setBirthCity;
    tmp.setBirthState = setBirthState;

    return tmp;
}

/**
    Função para criação de um novo player com uma matriz de caracteres
    @param strings - Vetor de string que será lido para inserir no player.
*/
Player newPlayerByStrings(String* strings)
{
    int id = atoi(strings[0]);
    String name = strings[1];
    int height = atoi(strings[2]);
    int weight = atoi(strings[3]);
    String university = strings[4];
    int birthYear = atoi(strings[5]);
    String birthCity = strings[6];
    String birthState = strings[7];

    return (newFullPlayer(id, name, height, weight, university, birthYear, birthCity, birthState));
}


void printPlayer(Player player)
{
    printf("[%d ## %s ## %d ## %d ## %d ", player.id, player.name, player.height, player.weight, player.birthYear);
    printf("## %s ## %s ## %s]\n", player.university, player.birthCity, player.birthState);
}

void NoIDPrintPlayer(Player player)
{
    printf(" ## %s ## %d ## %d ## %d ", player.name, player.height, player.weight, player.birthYear);
    printf("## %s ## %s ## %s ##\n", player.university, player.birthCity, player.birthState);
}

Player readPlayer(int id, String filePath)
{
    FILE* DB;
    if(!(DB = fopen(filePath, "r")))
    {
        printf("Erro: Arquivo não abriu");
        exit(0);
    }

    Player tmp = newPlayer();
    String* line;
    int findId = 0;
    do
    {
        line = splitFile(8, DB);
        findId = atoi(line[0]);
    }while(findId != id && !feof(DB));

    if(findId == id)
    {
        tmp = newPlayerByStrings(line);
    }
    return tmp;
}

Player searchByName(literal name, String filePath)
{
    FILE* DB;
    if(!(DB = fopen(filePath, "r")))
    {
        printf("Erro: Arquivo não abriu");
        exit(0);
    }

    Player tmp = newPlayer();
    String* line;
    int findId = 0;
    String findName;
    do
    {
        line = splitFile(8, DB);
        findId = atoi(line[0]);
        findName = line[1];
        if(findId == 2315){
            printf("%s\n", findName);
            printf("%s\n", name);
            printf("resultado: %d %s/%s\n", strcmp(findName, name),findName, name);
        }
    }while(!feof(DB) && strcmp(findName, name));
    printf("%s / %s\n", findName, name);

    if(strcmp(findName, name) == 0){
        tmp = newPlayerByStrings(line);
    }
    return tmp;
}





String nextSpace(String line)
{
    int sizeOfLine = strlen(line);
    String tmp = (String) malloc(sizeOfLine*sizeof(String));

    int i = 0;
    for(; i < sizeOfLine && line[i] != ' '; i++)
    {
        tmp[i] = line[i];
        line[i] = line[i + 1]; //Tirando tudo que já foi lido
    }
    line[i] = line[i + 1];
    return tmp; 
}

typedef struct Cell{
    String string;
    struct Cell* next;
}Cell;

Cell* newCellEmpty(){
    Cell* tmp = (Cell*)malloc(sizeof(Cell));
    tmp->string = "";
    tmp->next = NULL;
    return tmp;
}

Cell* newCell(String string){
    Cell* tmp = (Cell*)malloc(sizeof(Cell));
    tmp->string = string;
    tmp->next = NULL;
    return tmp;
}

Cell* newCellFull(String string, Cell* next){
    Cell* tmp = (Cell*)malloc(sizeof(Cell));
    tmp->string = string;
    tmp->next = next;
    return tmp;
}





typedef struct S_FList{
    Cell* top;
    int size;

    void (*insert)(String string, struct S_FList*);
    
    String (*remove)(struct S_FList*);

    void (*show)(struct S_FList);
    
    
} StringFList;

void showList(StringFList stack)
{
    for(Cell* tmp = stack.top->next; tmp != NULL; tmp = tmp->next){
        printf("%s", tmp->string);
    }
}

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
    while(tmp != NULL){
        if(strcmp(tmp->string, name) == 0) result = true;
        tmp = tmp->next;
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


int main (void)
{
    StringFHash hash = newHash(25);
    String line = (String) malloc(MAX_SIZE_STR*sizeof(char));
    String line2 = (String) malloc(MAX_SIZE_STR*sizeof(char));

    scanf("%s", line); getchar();
    while(strcmp(line, "FIM") != 0)
    {
        Player tmp = readPlayer(atoi(line), "/workspaces/AEDS2/Trabalhos Praticos/TP03/Arquivo/players.csv");
        hash.insert(tmp.name, tmp.height, &hash);
        scanf("%s", line);
        getchar();
    }
    getchar();
    scanf("%[^\n]", line2); getchar();
    printf("line: %s\n", line2);
    while(strcmp(line2, "FIM") != 0)
    {
        Player tmp = searchByName(line2, "/workspaces/AEDS2/Trabalhos Praticos/TP03/Arquivo/players.csv");
        printPlayer(tmp);
        printf("%s %s\n", line2, Search(line2, tmp.height, hash) ? "SIM" : "NAO");
        scanf("%[^\n]", line2); getchar();
    }
    

    free(line);
    //Limpando o programa
}
///workspaces/AEDS2/Trabalhos Praticos/TP03/Arquivo/players.csv