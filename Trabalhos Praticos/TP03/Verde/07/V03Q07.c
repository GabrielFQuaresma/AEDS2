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

typedef struct Split
{
    char array[MAX_ATTRIBUTES][MAX_SIZE_STR];
}Split;


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

Split splitSpace() {

	Split split;

	for (int i = 0; i < 3; i++) {
		scanf("%[^ \n]", split.array[i]);
		if (getchar() == '\n') i = 3;
	}

	return split;
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

/*

    Struct de Player

*/

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


int getMediaQueue(PlayerCFQueue queue)
{
    float media = 0.0;
    int j = 0;
    for (Cell* i = queue.first->next; i != queue.first; i = i->next){
        j++;
        media += i->player.height;
    }
    if(j > 0) media /= j;
    if((media - (int) (media)) >= 0.5) media++; //Se a parte decimal da média for maior ou igual a 0.5, arredonda pra cima
    return (int)(media);
}

void commanderReader(PlayerCFQueue* queue){
    Split split = splitSpace();
    String command = split.array[0];


    if(command[0] == 'I')
    {
        Player tmp = readPlayer(atoi(split.array[1]), "/tmp/players.csv");
        
        if(queue->size == 5) queue->remove(queue);
        queue->insert(tmp, queue);
        printf("%d\n", getMediaQueue(*queue));
    }
    else if(command[0] == 'R')
    {
        Player removed = queue->remove(queue);
        printf("(R) %s\n", removed.name);
    }

}

int main (void)
{
    PlayerCFQueue queue = newList(6000);
    String line = (String) malloc(MAX_SIZE_STR*sizeof(char));

    scanf("%s", line); getchar();

    while(strcmp(line, "FIM"))
    {
        Player tmp = readPlayer(atoi(line), "/tmp/players.csv");
        if(queue.size == 5) queue.remove(&queue);
        queue.insert(tmp, &queue);
        printf("%d\n", getMediaQueue(queue));
        scanf("%s", line);
        getchar();
    }
    int testCases = 0;
    scanf("%d", &testCases);
    getchar();
    for(int i = 0; i < testCases; i++){
        commanderReader(&queue);
    }

    showNoIDList(queue);
    free(line);
    //Limpando o programa
}
