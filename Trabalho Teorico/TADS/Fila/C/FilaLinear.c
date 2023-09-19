
#include "FilaLinear.h"
#include <stdio.h>


int main ()
{
    puts("---------Fila Linear---------");

    Queue fila = newQueue(10);

    fila.show(fila);
    fila.insert(5, &fila);
    fila.insert(10,&fila);
    fila.insert(15,&fila);
    
    fila.show(fila);
    fila.remove(&fila);
    fila.show(fila);
}
