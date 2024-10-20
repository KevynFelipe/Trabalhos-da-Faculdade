#include <stdlib.h>
#include <stdbool.h>
#include <stdio.h>
#include <string.h>

int main(){
    char temperatura_recebida[10];
    float soma = 0;
    int contador = 0;
    float media;
    char char_media[20];
    FILE *temperaturas;
    FILE *temperatura_media;

    temperaturas=fopen ("temperaturas.txt", "r");
    if (temperaturas == NULL) { printf("Não foi possível abrir o arquivo"); }
        while(fgets(temperatura_recebida, sizeof(temperatura_recebida), temperaturas) != NULL)
        {
        soma = soma + atof(temperatura_recebida);
        contador++;
        printf("Temperatura de: %s Graus Celsius\n", temperatura_recebida);
        };
    fclose(temperaturas);
    
    media = soma / contador;
    ltoa(media, char_media , 10);
    temperatura_media = fopen("temperatura_media", "w");
    if (temperatura_media == NULL) { printf("Não foi possível abrir o arquivo"); }
    fprintf(temperatura_media, char_media);

printf("Soma das temperaturas: %.2f\n", soma);
printf("Media das temperaturas: %.2f\n", media);
printf("Media das temperaturas: %s\n", char_media);
return 1;
}