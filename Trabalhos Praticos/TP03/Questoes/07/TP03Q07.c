//Inclusões
#include "../../Libs/C/playerCircularFlexQueue.h"
#include "string.h"

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