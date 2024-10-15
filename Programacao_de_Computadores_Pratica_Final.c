#include <ctype.h>
#include <stdlib.h>
#include <stdbool.h>
#include <stdio.h>
#include <string.h>


int verificar_telefone(char numero_de_celular[12]){
    size_t i = 0;
    for (i = 0; i < strlen(numero_de_celular); i++){
    if (isdigit(numero_de_celular[i])){
        printf("%c e um numero!\n", numero_de_celular[i]);}
    else {
    printf("%c nao e um numero!\n", numero_de_celular[i]);
    return 0;
    };

    };
    printf("\nO numero de telefone: %s possui %zu digitos\n", numero_de_celular,strlen(numero_de_celular));
    return 1;
};

int main(){
    char numero_de_telefone[12] = "11977889911";
    printf("%i", verificar_telefone(numero_de_telefone));
    return 1;
}