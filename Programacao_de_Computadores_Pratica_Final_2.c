#include <stdlib.h>
#include <stdbool.h>
#include <stdio.h>

int main(){
    int temperaturas[100];
    char temperatura_recebida[100];
    // FILE *fp;
    FILE *fptr;
    // fp=fopen ("temperaturas.txt", "a");
    // fprintf(fp,"%i\n", temperatura);
    // fclose(fp);
fptr=fopen ("temperaturas.txt", "r");
if(fptr){
        while(fgets(temperatura_recebida, sizeof(temperatura_recebida), fptr) != NULL)
        {
        printf("Teste: %s\n", temperatura_recebida);
        fscanf(fptr, "%d", &temperaturas);
        printf("%i", temperaturas);
        };
    fclose(fptr);
}
return 1;
}