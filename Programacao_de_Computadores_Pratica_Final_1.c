#include <ctype.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>

int verificar_telefone(char numero_de_celular[12]){
    size_t i = 0;
    printf("\nIniciando verificação do número: %s\n", numero_de_celular);

    for (i = 0; i < strlen(numero_de_celular); i++){
    if (isdigit(numero_de_celular[i])){
        printf("%c é um número!\n", numero_de_celular[i]);}
    else {
    printf("%c não é um número!\n", numero_de_celular[i]);
    return 0;
    };

    };
    printf("\nO número de telefone: %s possui %zu digitos\n", numero_de_celular,strlen(numero_de_celular));
    return 1;
};

int main(){
    char numero_de_telefone[12] = "11977889911";
    printf("%i", verificar_telefone(numero_de_telefone));
    return 1;
}