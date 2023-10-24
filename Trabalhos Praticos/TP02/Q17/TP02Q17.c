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


String* splitFile(int attributes, FILE* file);

/*

    Struct do Timer

*/


typedef struct Timer {
	clock_t startTime;
	clock_t endTime;
	double totalTime;
	void (*Start)(struct Timer*);
	void (*Stop)(struct Timer*);
	double (*Time)(struct Timer*);
} Timer;

void TimerStart(Timer* timer) {
	timer->startTime = clock();
}

void TimerStop(Timer* timer) {
	timer->endTime = clock();
}

double TimerTime(Timer* timer) {
	timer->totalTime = ((double)(timer->endTime - timer->startTime)) / CLOCKS_PER_SEC;
	return timer->totalTime;
}

Timer newTimerStart() {
	Timer timer;
	timer.Start = TimerStart;
	timer.Stop = TimerStop;
	timer.Time = TimerTime;
	timer.Start(&timer);
	return timer;
}

Timer newTimer() {
	Timer timer;
	timer.Start = TimerStart;
	timer.Stop = TimerStop;
	timer.Time = TimerTime;
	return timer;
}


/*

    Struct de Resultado

*/


typedef struct Resultado {
	int comparacoes, movimentacoes;
} Resultado;


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

    return tmp;
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

/**
    Função para criação de um novo jogador com uma matriz de caracteres
    @param strings - Vetor de string que será lido para inserir no jogador.
*/
Jogador newJogadorByStrings(String* strings)
{
    int id = atoi(strings[0]);
    String nome = strings[1];
    int altura = atoi(strings[2]);
    int peso = atoi(strings[3]);
    String universidade = strings[4];
    int anoNascimento = atoi(strings[5]);
    String cidadeNascimento = strings[6];
    String estadoNascimento = strings[7];

    return (newFullJogador(id, nome, altura, peso, universidade, anoNascimento, cidadeNascimento, estadoNascimento));
}


void printJogador(Jogador jogador)
{
    printf("[%d ## %s ## %d ## %d ## %d ", jogador.id, jogador.nome, jogador.altura, jogador.peso, jogador.anoNascimento);
    printf("## %s ## %s ## %s]\n", jogador.universidade, jogador.cidadeNascimento, jogador.estadoNascimento);
}

Jogador readJogador(int id, String filePath)
{
    FILE* DB;
    if(!(DB = fopen(filePath, "r")))
    {
        printf("Erro: Arquivo não abriu");
        exit(0);
    }

    Jogador tmp = newJogador();
    String* line;
    int findId = 0;
    do
    {
        line = splitFile(8, DB);
        findId = atoi(line[0]);
    }while(findId != id && !feof(DB));

    if(findId == id)
    {
        tmp = newJogadorByStrings(line);
    }
    return tmp;
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
        if(queue->size < queue->maxSize){
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

void showPartialQueue(int k, Queue queue)
{
    for(int i = 0; i < k; i++)
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
        
        Jogador tmp = newJogadorByStrings(line);
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



/*


    Algoritmos de pesquisa


*/

Jogador sequencialSearch(int id, Queue queue)
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


bool RecursiveBinarySearch(int left, int right, String name, Queue queue)
{
    int middle = (left + right)/2;
    Jogador tmp = queue.array[middle];
    if(strstr(tmp.nome, name))
        return true;  
    else if(strcmp(tmp.nome, name) < 0)
        left = middle + 1; 
    else
        right = middle - 1;
    return left <= right ? RecursiveBinarySearch(left, right, name, queue) : false;
}

bool BinarySearch(String name, Queue queue)
{
    return RecursiveBinarySearch(0, queue.size - 1, name, queue);
}


void swap(Jogador* i, Jogador* j)
{
    Jogador aux = *i;
    *i = *j;
    *j = aux;
}

/*


    Algoritmos de ordenação


*/


void RecursiveSelectionSort(int i, int j, int menor, Queue* queue, Resultado* resultado)
{
    if(j < queue->size)
    {
        if(strcmp(queue->array[menor].nome, queue->array[j].nome) > 0)
        {
            menor = j;
        }
        resultado->comparacoes++;
        RecursiveSelectionSort(i, j + 1, menor, queue, resultado);
    }
    else{
        swap(&queue->array[i], &queue->array[menor]);
        resultado->movimentacoes++;
    }

    //printf("%d\n", j);
    if(++i < queue->size - 1 && j == i)
    {
        RecursiveSelectionSort(i, i + 1, i, queue, resultado);
    }
}

void SelectionSort(Queue* queue, Resultado* resultado)
{
    RecursiveSelectionSort(0, 0, 0, queue, resultado);
}



void ColorInsertionWeight(int cor, int h, Queue* queue, Resultado* resultado)
{
    for(int i = (cor + h); i < queue->size; i+=h)
    {
        Jogador tmp = queue->array[i];
        int j = i - h;
        while((j >= 0) && ((queue->array[j].peso > tmp.peso) || ((queue->array[j].peso == tmp.peso) && (strcmp(queue->array[j].nome, tmp.nome) > 0))))
        {
            queue->array[j + h] = queue->array[j];
            j -= h;

            //Log do numero de movimentações e comparações
            resultado->movimentacoes++;
            resultado->comparacoes++;
        }
        resultado->comparacoes++; //Numero de Comparações
        queue->array[j + h] = tmp; 
        resultado->movimentacoes++; //Numero de movimentações
    }
}

//Sequencia do Pigeon - menos comparações quando o array é maior
void PigeonShellSortWeigth(Queue* queue, Resultado* resultado)
{
    int n = 1;
    int h = 1;
    while(h < queue->size) {h = round(1 + pow(e, n++ - 2));}
    n--;
    while(h != 1)
    {
        n--;
        h = round(1 + pow(e, n - 2));
        for(int cor = 0; cor < h; cor++)
        {
            ColorInsertionWeight(cor, h, queue, resultado);
        }
    }
}

//Sequencia do Knuth - menos comparações quando o array é menor
void KnuthShellSortWeigth(Queue* queue, Resultado* resultado)
{
    int h = 1;
    while(h < queue->size) {h = (3 *h) + 1;}
    while(h != 1)
    {
        h /= 3;
        for(int cor = 0; cor < h; cor++)
        {
            ColorInsertionWeight(cor, h, queue, resultado);
        }
    }
}


void RecursiveQuickSort(int esq, int dir, Queue* queue, Resultado* resultado)
{
    Jogador* array = queue->array;
    Jogador pivo = array[(esq + dir)/2];
    String nascimentoJogador = pivo.estadoNascimento;
    int i = esq;
    int j = dir;
     while ((strcmp(array[i].estadoNascimento , nascimentoJogador)) < 0 ||
               (strcmp(array[i].estadoNascimento , nascimentoJogador)) == 0 && (strcmp(array[i].nome , pivo.nome)) < 0 ) 
    {
        i++;
        resultado->comparacoes++;
    }
    while ((strcmp(array[j].estadoNascimento , nascimentoJogador)) > 0 ||
               (strcmp(array[j].estadoNascimento , nascimentoJogador)) == 0 && (strcmp(array[j].nome , pivo.nome)) > 0) //Desempate por nome 
    {
        j--;
        resultado->comparacoes++;
    }
    if (i <= j)
    {
        swap(&queue->array[i++], &queue->array[j--]);
        resultado->movimentacoes++;
    }
    if(esq < j)
        RecursiveQuickSort(esq, j, queue, resultado);
    if(dir > i)
        RecursiveQuickSort(i, dir, queue, resultado);
}


void QuickSort(Queue* queue, Resultado* resultado)
{
    RecursiveQuickSort(0, queue->size - 1, queue, resultado);
}



void bubbleSort(Queue* queue, Resultado* resultado)
{
    for(int i = 0; i < queue->size; i++){
        for(int b = 0; b < queue->size - (i + 1); b++){
            
            Jogador bubble = queue->array[b];
            Jogador tmp = queue->array[b + 1];

            if(bubble.anoNascimento > tmp.anoNascimento || (bubble.anoNascimento == tmp.anoNascimento && strcmp(bubble.nome, tmp.nome) > 0)){
                swap(&queue->array[b], &queue->array[b + 1]);
                resultado->movimentacoes++;
            }
            resultado->comparacoes++;
        }
    }
}


void PartialInsertion(int k, Queue* queue, Resultado* resultado)
{
    Jogador* array = queue->array;


    for(int i = 1; i < queue->size; i++)
    {
        Jogador tmp = array[i];
        int j = (i < k) ? i - 1 : k - 1;
        while(j >= 0 && (array[j].anoNascimento > tmp.anoNascimento))
        {
            array[j-- + 1] = array[j];
        }
        array[j + 1] = tmp;
    }
}



void maxHeapify(Queue* queue, int n, int i) {
    Jogador* array = queue->array;

    int largest = i;
    int left = 2 * i + 1;
    int right = 2 * i + 2;

    if (left < n && array[left].altura > array[largest].altura)
        largest = left;
    if (right < n && array[right].altura > array[largest].altura)
        largest = right;

    if (largest != i) {
        swap(&queue->array[largest], &queue->array[i]);

        maxHeapify(queue, n, largest);
    }
}

void buildMaxHeap(Queue* queue, int n) {
    for (int i = n / 2 - 1; i >= 0; i--)
        maxHeapify(queue, n, i);
}

void heapSort(Queue* queue, int n) {
    buildMaxHeap(queue, n);

    for (int i = n - 1; i >= 0; i--) {
        swap(&queue->array[0], &queue->array[i]);
        maxHeapify(queue, i, 0);
    }
}

void partialHeapSort(Queue* queue, int n, int k) {
    buildMaxHeap(queue, k);
    Jogador* array = queue->array;

    for (int i = k; i < n; i++) {
        if (queue->array[i].altura < queue->array[0].altura) {
            swap(&queue->array[i], &queue->array[0]);
            maxHeapify(queue, k, 0);
        }
    }

    heapSort(queue, k);
}



void insertionSort(Queue* queue) {
    int i, j;
    Jogador* array = queue->array;

    for (i = 1; i < queue->size; i++) {
        Jogador tmp = array[i];
        j = i - 1;
        
        while (j >= 0 && array[j].altura == tmp.altura && strcmp(array[j].nome, tmp.nome) > 0) {
                queue->array[j + 1] = queue->array[j];
                j--;
            }
        
        queue->array[j + 1] = tmp;
    }
}


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
            fgetc(file); 
        }
    }
    return str;
    
    
}

void registroLog(Timer timer, Resultado resultado) {

	literal fileName = "814832_partialInsertion.txt";
	FILE* file = fopen(fileName, "w");

	fprintf(file, "Matrícula: 814832\t");
	fprintf(file, "Tempo de execução: %fs\t", timer.Time(&timer));
	fprintf(file, "Número de comparações: %d\t", resultado.comparacoes);
    fprintf(file, "Número de movimentações: %d", resultado.movimentacoes);

	fclose(file);

}



/*

    Método principal 

*/

int main ()
{
    Queue queue = newQueue(6000);
    
    Resultado resultado = { 0, 0}; //Resultado das comparações e movimentações

    Timer timer = newTimer(); //Tempo percorrido durante a execução do código

    String line = (String) malloc(MAX_SIZE_STR * sizeof(char));
    scanf("%s", line); getchar();

    while(strcmp(line, "FIM"))
    {
        Jogador tmp = readJogador(atoi(line), "/tmp/players.csv");
        queue.insert(tmp, &queue);
        scanf("%s", line);
        getchar();
    }
    timer.Start(&timer);

    partialHeapSort(&queue, queue.size, 100);
    insertionSort(&queue);

    timer.Stop(&timer);    
    showPartialQueue(10, queue);
    
    //Limpando o programa
    free(line);
    

}