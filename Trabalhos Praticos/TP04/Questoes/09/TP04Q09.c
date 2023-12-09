//Inclusões
#include "../../Libs/C/FlexListHash.h"
#include "../../Libs/C/player.h"



int main (void)
{
    StringFHash hash = newHash(25);
    String line = (String) malloc(MAX_SIZE_STR*sizeof(char));

    scanf("%s", line); getchar();

    while(strcmp(line, "FIM"))
    {
        Player tmp = readPlayer(atoi(line), "/workspaces/AEDS2/Trabalhos Praticos/TP03/Arquivo/players.csv");
        hash.insert(tmp.name, tmp.height, &hash);
        scanf("%s", line);
        getchar();
    }

    scanf("%", line);
    while(strcmp(line, "FIM"))
    {
        Player tmp = searchByName(line, "/workspaces/AEDS2/Trabalhos Praticos/TP03/Arquivo/players.csv");
        printf("%s %s", line, hash.search(line, tmp.height, hash) ? "SIM" : "NAO");
    }
    getchar();
    

    free(line);
    //Limpando o programa
}