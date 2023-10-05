//INCLUSÕES

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

//DEFINIÇÕES
#define INT_MAX 2147483647
#define MAX_ATTRIBUTES 8
#define MAX_SIZE_STR 400

typedef char* String;

/*
    Função Geral
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
            fgetc(file); // Despresando a virgula
        }
    }
    return str;
    
}

/*

    Struct de Jogador

*/

typedef struct S_Jogador{

    int id;
    String nome;
    int altura;
    int peso;
    String universidade;
    int anoNascimento;
    String cidadeNascimento;
    String estadoNascimento;

    void (*setId)(int id, struct S_Jogador*);
    void (*setNome)(String nome, struct S_Jogador*);
    void (*setAltura)(int altura, struct S_Jogador*);
    void (*setPeso)(int peso, struct S_Jogador*);
    void (*setUniversidade)(String universidade, struct S_Jogador*);
    void (*setAnoNascimento)(int anoNascimento, struct S_Jogador*);
    void (*setCidadeNascimento)(String cidadeNascimento, struct S_Jogador*);
    void (*setEstadoNascimento)(String estadoNascimento, struct S_Jogador*);


    struct S_Jogador (*clone)(struct S_Jogador); 
}Jogador;


void setId(int id, Jogador* jogador)
{
    jogador->id = id;
}

void setNome(String nome, Jogador* jogador)
{
    jogador->nome = strdup(nome);
}

void setAltura(int altura, Jogador* jogador)
{
    jogador->altura = altura;
} 

void setPeso(int peso, Jogador* jogador)
{
    jogador->peso = peso;
}

void setUniversidade(String universidade, Jogador* jogador)
{
    jogador->universidade = strdup(universidade);
}

void setAnoNascimento(int anoNascimento, Jogador* jogador)
{
    jogador->anoNascimento = anoNascimento;
}

void setCidadeNascimento(String cidadeNascimento, Jogador* jogador)
{
    jogador->cidadeNascimento = strdup(cidadeNascimento);
}

void setEstadoNascimento(String estadoNascimento, Jogador* jogador)
{
    jogador->estadoNascimento = strdup(estadoNascimento);
}

Jogador clone(Jogador jogador)
{
    Jogador clone = jogador;
    
    clone.nome = strdup(jogador.nome);
    clone.universidade = strdup(jogador.universidade);
    clone.cidadeNascimento = strdup(jogador.cidadeNascimento);
    clone.estadoNascimento = strdup(jogador.estadoNascimento);

    return clone;
}


Jogador newJogador()
{
    
    Jogador tmp;

    //Definindo atributos
    tmp.id = -1;
    tmp.nome = (String) malloc(MAX_SIZE_STR*sizeof(char));
    tmp.altura = -1;
    tmp.universidade = (String) malloc(MAX_SIZE_STR*sizeof(char));
    tmp.anoNascimento = -1;
    tmp.cidadeNascimento = (String) malloc(MAX_SIZE_STR*sizeof(char));
    tmp.estadoNascimento = (String) malloc(MAX_SIZE_STR*sizeof(char));

    //Definindo ponteiro pra funções
    tmp.setId = setId;
    tmp.setNome = setNome;
    tmp.setAltura = setAltura;
    tmp.setPeso = setPeso;
    tmp.setUniversidade = setUniversidade;
    tmp.setAnoNascimento = setAnoNascimento;
    tmp.setCidadeNascimento = setCidadeNascimento;
    tmp.setEstadoNascimento = setEstadoNascimento;

}

Jogador newFullJogador(int id, String nome, int altura, int peso, String universidade, int anoNascimento, String cidadeNascimento, String estadoNascimento)
{
    
    Jogador tmp;

    //Definindo atributos
    tmp.id = id;
    tmp.nome = strdup(nome);
    tmp.altura = altura;
    tmp.peso = peso;
    tmp.universidade = strdup(universidade);
    tmp.anoNascimento = anoNascimento;
    tmp.cidadeNascimento = strdup(cidadeNascimento);
    tmp.estadoNascimento = strdup(estadoNascimento);

    //Definindo ponteiro pra funções
    tmp.setId = setId;
    tmp.setNome = setNome;
    tmp.setAltura = setAltura;
    tmp.setPeso = setPeso;
    tmp.setUniversidade = setUniversidade;
    tmp.setAnoNascimento = setAnoNascimento;
    tmp.setCidadeNascimento = setCidadeNascimento;
    tmp.setEstadoNascimento = setEstadoNascimento;

    return tmp;
}

void printJogador(Jogador jogador)
{
    printf("[%d ## %s ## %d ## %d ## %d ", jogador.id, jogador.nome, jogador.altura, jogador.peso, jogador.anoNascimento);
    printf("## %s ## %s ## %s]\n", jogador.universidade, jogador.cidadeNascimento, jogador.estadoNascimento);
}



/*

    Fila Linear de jogadores

*/

typedef struct S_Queue{
    Jogador* array;
    int size;
    int maxSize;

    void (*insert)(Jogador jogador, struct S_Queue*);
    Jogador (*remove)(struct S_Queue*);

    void (*show)(struct S_Queue);
    void (*close)(struct S_Queue*);
} Queue;

/**
    Método de inserção de um jogador no fim do array, com custo de theta de (1).
    @param jogador - Jogador que será inserido na fila.
    @param queue - Fila que será inserido o numero
*/
void insertQueue(Jogador jogador, Queue* queue)
{
    if(queue->maxSize > 0){
        if(queue->size != queue->maxSize){
            queue->array[queue->size++] = jogador;
        }
    }
}

/**
    Método de remoção de um Jogador no inicio do array, com custo de theta de (n), devido ao shift dos elementos.
    @param queue - Fila em que o Jogador sera removido
    @return Caso a fila tenha Jogador pra ser removido, retornará o Jogador removido. Caso contrário, será retornado o valor máximo de um inteiro
*/
Jogador removeQueue(Queue* queue)
{
    Jogador removed = newJogador();
    if(queue->size > 0)
    {
        removed = queue->array[0];
        
        for(int i = 0; i < queue->maxSize - 1; i++)
        {
            queue->array[i] = queue->array[i+1]; 
        }
        queue->size--;
    }
    return removed;
}

/**
    Método de mostragem dos jogadores dentro da fila, com custo de theta de (N)
*/
void showQueue(Queue queue)
{
    for(int i = 0; i < queue.size; i++)
    {
        printJogador(queue.array[i]);
    }
}

/**
    Método para limpar a memória da fila
    @param queue - Fila que será limpa
*/
void closeQueue(Queue* queue)
{
    free(queue->array);
    queue->array = NULL;
    queue->maxSize = 0;
    queue->size = 0;
}


/**
    Método para ler um base da dados e inserir na fila
    @param filePath - Caminho do arquivo.
    @param queue - Fila que será lida.
*/
void readDataBase(String filePath, Queue* queue)
{
    FILE* DB;
    if(!(DB = fopen(filePath, "r"))){
        printf("Erro: Falha ao abrir base de dados");
        exit(0);
    }
    
    //fseek(DB, 60, SEEK_SET);

    while(queue->size < queue->maxSize && !feof(DB))
    {
        String* line = splitFile(8, DB);
        
        int id = atoi(line[0]);
        String nome = line[1];
        int altura = atoi(line[2]);
        int peso = atoi(line[3]);
        String universidade = line[4];
        int anoNascimento = atoi(line[5]);
        String cidadeNascimento = line[6];
        String estadoNascimento = line[7];

        Jogador tmp = newFullJogador(id, nome, altura, peso, universidade, anoNascimento, cidadeNascimento, estadoNascimento);
        queue->insert(tmp, queue);
    }
    
}

/**
    Método para criação de uma fila
    @param maxSize - número que dira o tamanho da fila
    @return retorna a Fila
*/
Queue newQueue(size_t maxSize)
{

    Queue queue;

    if (maxSize == 0) maxSize = 80;

    queue.array = (Jogador*)(malloc(maxSize * sizeof(Jogador)));
    queue.size = 0;
    queue.maxSize = maxSize;

    queue.insert = insertQueue;
    queue.remove = removeQueue;

    queue.show = showQueue;
    queue.close = closeQueue;

    return queue;
}

Jogador search(int id, Queue queue)
{
    Jogador returned = newJogador();
    for(int i = 0; i < queue.size; i++)
    {
        if(id == queue.array[i].id)
        {
            returned = queue.array[i];
            i = queue.size;
        }
    }
    return returned;
}

int main ()
{
    Queue queue = newQueue(3921);
    readDataBase("/tmp/players.csv", &queue);

    String line = (String) malloc(MAX_SIZE_STR * sizeof(char));
    scanf("%s", line); getchar();

    while(strcmp(line, "FIM"))
    {
        Jogador tmp = search(atoi(line), queue);
        printJogador(tmp);
        scanf("%s", line);
        
    }
    
    
}