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
    }while(!feof(DB) && strcmp(findName, name));

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

typedef struct Node {
    char data[100];
    struct Node* left;
    struct Node* right;
    int height;
}Node;

typedef struct AVLTree {
    struct Node* root;
}AVLTree;

struct Node* newNode(char* data) {
    struct Node* node = (struct Node*)malloc(sizeof(struct Node));
    strcpy(node->data, data);
    node->left = NULL;
    node->right = NULL;
    node->height = 1;
    return node;
}

int getHeight(struct Node* node) {
    if (node == NULL)
        return 0;
    return node->height;
}

int getBalanceFactor(struct Node* node) {
    if (node == NULL)
        return 0;
    return getHeight(node->left) - getHeight(node->right);
}

void updateHeight(struct Node* node) {
    int leftHeight = getHeight(node->left);
    int rightHeight = getHeight(node->right);
    node->height = (leftHeight > rightHeight ? leftHeight : rightHeight) + 1;
}

struct Node* rotateRight(struct Node* node) {
    struct Node* newRoot = node->left;
    struct Node* temp = newRoot->right;
    newRoot->right = node;
    node->left = temp;
    updateHeight(node);
    updateHeight(newRoot);
    return newRoot;
}

struct Node* rotateLeft(struct Node* node) {
    struct Node* newRoot = node->right;
    struct Node* temp = newRoot->left;
    newRoot->left = node;
    node->right = temp;
    updateHeight(node);
    updateHeight(newRoot);
    return newRoot;
}

struct Node* insertNode(struct Node* node, char* data) {
    if (node == NULL)
        return newNode(data);
    int cmp = strcmp(data, node->data);
    if (cmp < 0)
        node->left = insertNode(node->left, data);
    else if (cmp > 0)
        node->right = insertNode(node->right, data);
    else
        return node;

    updateHeight(node);

    int balanceFactor = getBalanceFactor(node);

    if (balanceFactor > 1 && strcmp(data, node->left->data) < 0)
        return rotateRight(node);

    if (balanceFactor < -1 && strcmp(data, node->right->data) > 0)
        return rotateLeft(node);

    if (balanceFactor > 1 && strcmp(data, node->left->data) > 0) {
        node->left = rotateLeft(node->left);
        return rotateRight(node);
    }

    if (balanceFactor < -1 && strcmp(data, node->right->data) < 0) {
        node->right = rotateRight(node->right);
        return rotateLeft(node);
    }

    return node;
}

void inorderTraversal(struct Node* node) {
    if (node != NULL) {
        inorderTraversal(node->left);
        printf("%s ", node->data);
        inorderTraversal(node->right);
    }
}

struct AVLTree* initAVLTree() {
    struct AVLTree* tree = (struct AVLTree*)malloc(sizeof(struct AVLTree));
    tree->root = NULL;
    return tree;
}

void insert(struct AVLTree* tree, char* data) {
    tree->root = insertNode(tree->root, data);
}

void printAVLTree(struct AVLTree* tree) {
    printf("AVL Tree in inorder traversal: ");
    inorderTraversal(tree->root);
    printf("\n");
}


bool searchRecursive(Node* node, literal data) {
    bool result = false;
    if(node == NULL) {
        result = false;
    }else if(strcmp(node->data, data) == 0) {
        result = true;
    } else if(strcmp(node->data, data) > 0) {
        printf("esq ");
        result = searchRecursive(node->left, data);
    } else{
        printf("dir ");
        result = searchRecursive(node->right, data);
    }
    return result;
}



bool search(AVLTree* tree, literal data) {
    printf("raiz ");
    return searchRecursive(tree->root, data);
}

// Main function
int main() {
    AVLTree* tree = initAVLTree();

    String line = (String) malloc(MAX_SIZE_STR*sizeof(char));

    scanf("%s", line); getchar();
    while(strcmp(line, "FIM") != 0)
    {
        Player tmp = readPlayer(atoi(line), "/tmp/players.csv");
        insert(tree, tmp.name);
        scanf("%s", line);
        getchar();
    }

    scanf("%[^\n]", line); getchar();
    while(strcmp(line, "FIM") != 0)
    {
        Player tmp = searchByName(line, "/tmp/players.csv");
        printf("%s ", line);
        printf("%s\n", search(tree, line) ? "SIM" : "NAO");
        scanf("%[^\n]", line); getchar();
    }
    

    free(line);
    return 0;
}
///workspaces/AEDS2/Trabalhos Praticos/TP03/Arquivo/players.csv
