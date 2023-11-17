//Inclusões
#include "../../Libs/C/playerFlexStack.h"
#include "string.h"
#include "../../Libs/C/libs.h"

void commanderReader(PlayerFStack* stack){
    Split split = splitSpace();
    String command = split.array[0];


    if(command[0] == 'I')
    {
        Player tmp = readPlayer(atoi(split.array[1]), "/tmp/players.csv");
        stack->insert(tmp, stack);
    }
    else if(command[0] == 'R')
    {
        Player removed = stack->remove(stack);
        printf("(R) %s\n", removed.name);
    }

}

int main (void)
{
    PlayerFStack stack = newList(6000);
    String line = (String) malloc(MAX_SIZE_STR*sizeof(char));

    scanf("%s", line); getchar();

    while(strcmp(line, "FIM"))
    {
        Player tmp = readPlayer(atoi(line), "/tmp/players.csv");
        stack.insert(tmp, &stack);
        scanf("%s", line);
        getchar();
    }
    int testCases = 0;
    scanf("%d", &testCases);
    getchar();
    for(int i = 0; i < testCases; i++){
        commanderReader(&stack);
    }

    showNoIDList(stack);
    free(line);
    //Limpando o programa
}