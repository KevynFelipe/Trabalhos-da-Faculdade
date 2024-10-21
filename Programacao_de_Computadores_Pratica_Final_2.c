#include <stdlib.h>
#include <stdbool.h>
#include <stdio.h>
#include <string.h>

int main(){
    char temperatura_recebida[10];
    double soma = 0;
    int contador = 0;
    float media;
    char char_media[20];
    FILE *temperaturas;
    FILE *media_temperaturas;

// temperaturas.txt é aberto e lido, extraindo cada valor por meio do fgets() + while. Logo é convertido em double e somado à variável soma
    temperaturas=fopen ("temperaturas.txt", "r");
        printf("Abrindo arquivo temperaturas.txt...\n");
        if (temperaturas == NULL) { printf("Não foi possível abrir o arquivo"); }
        while(fgets(temperatura_recebida, sizeof(temperatura_recebida), temperaturas) != NULL)
        {
        soma = soma + atof(temperatura_recebida);
        contador++;
        printf("\n%i°Temperatura de: %s Graus Celsius\n", contador ,temperatura_recebida);
        };
        printf("\nFechando arquivo temperaturas.txt...\n");
    fclose(temperaturas);

    media = soma / contador;
    ltoa(media, char_media , 10);

// media_temperaturas é aberto e usado para gravar a media das temperaturas extraidas do temperaturas.txt
    media_temperaturas = fopen("media_temperaturas.txt", "w");
        printf("\nAbrindo arquivo media_temperaturas.txt...\n");
        if (media_temperaturas == NULL) { printf("Não foi possível abrir o arquivo"); }

        fprintf(media_temperaturas, char_media);
        printf("\nFechando arquivo media_temperaturas...\n");
        fclose(media_temperaturas);
printf("\nForam lidos %i valores de temperatura\n", contador);
printf("Soma das temperaturas: %.2f\n", soma);
printf("Media das temperaturas: %.2f\n", media);
printf("Media das temperaturas: %s\n", char_media);
return 1;
}