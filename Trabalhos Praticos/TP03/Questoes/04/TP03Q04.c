//Inclusões
#include "../../Libs/C/playerCircularQueue.h"
#include "string.h"

int getMediaQueue(PlayerCQueue queue)
{
    float media = 0.0;
    int j = 0;
    for (int i = queue.first; i != queue.last; i = (i + 1) % queue.maxSize){
        j++;
        media += queue.array[i].height;
    }
    if(j > 0) media /= j;
    if((media - (int) (media)) >= 0.5) media++; //Se a parte decimal da média for maior ou igual a 0.5, arredonda pra cima
    return (int)(media);
}

void commanderReader(PlayerCQueue* queue){
    Split split = splitSpace();
    String command = split.array[0];


    if(command[0] == 'I')
    {
        Player tmp = readPlayer(atoi(split.array[1]), "/workspaces/AEDS2/Trabalhos Praticos/TP03/Arquivo/players.csv");
        
        queue->insert(tmp, queue);
        printf("%d\n", getAvarageHeigth(*(queue)));
    }
    else if(command[0] == 'R')
    {
        Player removed = remove(queue);
        printf("(R) %s\n", removed.name);
    }

}

int main (void)
{
    PlayerCQueue queue = newQueue(6);
    String line = (String) malloc(MAX_SIZE_STR*sizeof(char));

    scanf("%s", line); getchar();

    while(strcmp(line, "FIM"))
    {
        Player tmp = readPlayer(atoi(line), "/workspaces/AEDS2/Trabalhos Praticos/TP03/Arquivo/players.csv");
        queue.insert(tmp, &queue);
        printf("%d\n", getAvarageHeigth(queue));
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
    closeList(&queue);
}
