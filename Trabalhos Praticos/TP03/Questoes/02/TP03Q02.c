//Inclusões
#include "../../Libs/C/playerList.h"
#include "string.h"


void commanderReader(PlayerList* list){
    Split split = splitSpace();
    String command = split.array[0];


    if(strcmp("I*", command) == 0)
    {
        int position = atoi(split.array[1]);
        Player tmp = readPlayer(atoi(split.array[2]), "/workspaces/AEDS2/Trabalhos Praticos/TP03/Arquivo/players.csv");
        list->insert(position, tmp, list);
    }
    else if(command[0] == 'I')
    {
        Player tmp = readPlayer(atoi(split.array[1]), "/workspaces/AEDS2/Trabalhos Praticos/TP03/Arquivo/players.csv");
        
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
        Player tmp = readPlayer(atoi(line), "/workspaces/AEDS2/Trabalhos Praticos/TP03/Arquivo/players.csv");
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
