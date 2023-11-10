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

void showNoIDList(PlayerList list)
{
    for(int i = 0; i < list.size; i++)
    {
        printf("[%d]", i);
        NoIDPrintPlayer(list.array[i]);
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



void commanderReader(PlayerList* list){
    Split split = splitSpace();
    String command = split.array[0];


    if(strcmp("I*", command) == 0)
    {
        int position = atoi(split.array[1]);
        Player tmp = readPlayer(atoi(split.array[2]), "/tmp/players.csv");
        list->insert(position, tmp, list);
    }
    else if(command[0] == 'I')
    {
        Player tmp = readPlayer(atoi(split.array[1]), "/tmp/players.csv");
        
        if(strcmp("IF", command) == 0) list->insertEnd(tmp, list);
        else list->insertStart(tmp, list);
    }
    else if(strcmp("R*", command) == 0)
    {
        int position = atoi(split.array[1]);
        printf("(R) %s\n", list->remove(position, list).name); 
    }
    else if(command[0] == 'R')
    {
        Player removed = (strcmp("RF", command) == 0) ? list->removeEnd(list) : list->removeStart(list);
        printf("(R) %s\n", removed.name);
    }

}

int main (void)
{
    PlayerList list = newList(6000);
    String line = (String) malloc(MAX_SIZE_STR*sizeof(char));

    scanf("%s", line); getchar();

    while(strcmp(line, "FIM"))
    {
        Player tmp = readPlayer(atoi(line), "/tmp/players.csv");
        list.insertEnd(tmp, &list);
        scanf("%s", line);
        getchar();
    }
    int testCases = 0;
    scanf("%d", &testCases);
    getchar();
    for(int i = 0; i < testCases; i++){
        commanderReader(&list);
    }

    showNoIDList(list);
    free(line);
    //Limpando o programa
    closeList(&list);
}
