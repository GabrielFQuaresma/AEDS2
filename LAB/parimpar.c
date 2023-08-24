#include <stdio.h>

int itspar (int number)
{
	int result = 0;
	if (number % 2 == 0)
	{
		result = 1;
	}
	return(result);
}

int main (void)
{
	int number = 0;
	scanf("%d", &number);
	
	if(itspar(number))
	{
		printf("par\n");
	}
	else
	{
		printf("impar\n");
	}
}