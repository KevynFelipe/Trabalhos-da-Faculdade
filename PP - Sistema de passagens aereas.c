#include <stdlib.h>
#include <stdbool.h>
#include <stdio.h>
#include <locale.h>
#include <string.h>
#include <ctype.h>

typedef struct agendamento
{	//Informações pessoais do passageiro
	char nome_passageiro[50];
	int idade;
	char tipo_passagem[10];
	char identificacao_passaporte[8];
	int numero_de_telefone;  
	// informações tecnincas da reserva
	char tipo_de_classe[20];
	int numero_do_assento;
	char local_de_destino[50];
	char horario_de_embarque[25];
	char companhia_aerea[30];
	int numero_da_reserva;
}	Agendamentos;
//replace troca os Espaços ' ' por Underlines '_', evitando erros na hora do carregamento do arquivo. 
void replace(char *string) {
    for (int i = 0; string[i] != '\0'; i++) {
        if (string[i] == ' ') {
            string[i] = '_';
        }
    }
}

void flush_in(){ 
    int ch;
    while( (ch = fgetc(stdin)) != EOF && ch != '\n' ){};
};

void criar_passagem(Agendamentos **reserva, int *quantidade_reservas)
{	int tamanho = *quantidade_reservas + 1;
	*reserva = (Agendamentos *)realloc(*reserva, tamanho * sizeof(Agendamentos));
	if (*reserva == NULL)
	{
		printf("Erro ao alocar memória.\n");
		exit(1);
	};
	int contador_reservas = *quantidade_reservas;

	printf("Nome completo: ");
	scanf("%s[^\n]", (*reserva)[contador_reservas].nome_passageiro);

	printf("idade: ");
	scanf("%i", &(*reserva)[contador_reservas].idade);

	if ((*reserva)[contador_reservas].idade <= 12 && (*reserva)[contador_reservas].idade > 2)
	{
		strcpy((*reserva)[contador_reservas].tipo_passagem, "Meia");
		printf((*reserva)[contador_reservas].tipo_passagem);
	}
	else if ((*reserva)[contador_reservas].idade <= 2)
	{
		strcpy((*reserva)[contador_reservas].tipo_passagem, "Não_paga");
		printf((*reserva)[contador_reservas].tipo_passagem);
	}
	else {
		strcpy((*reserva)[contador_reservas].tipo_passagem, "Inteira");
		printf((*reserva)[contador_reservas].tipo_passagem);
	};

	printf("\nIdentificação do passaporte. [Está atrás de seu passaporte, são 2 letras e 6 números]: ");
	scanf("%s[^\n]", (*reserva)[contador_reservas].identificacao_passaporte);
	
	printf("Numero de telefone: ");
	scanf("%i", &(*reserva)[contador_reservas].numero_de_telefone);

	printf("Digite o numero que corresponda a classe desejada: \n 1 - Econômica\n 2 - Econômica premium\n 3 - Executiva\n 4 - Primeira classe\n");
	int escolha;
	do{
		scanf("%i", &escolha);
		if(escolha == 1){
			strcpy((*reserva)[contador_reservas].tipo_de_classe, "Econômica");
			break;
		}
		else if (escolha == 2){
			strcpy((*reserva)[contador_reservas].tipo_de_classe, "Econômica_premium");
			break;
		}
		else if (escolha == 3){
			strcpy((*reserva)[contador_reservas].tipo_de_classe, "Executiva");
			break;
		}
		else if (escolha == 4){
			strcpy((*reserva)[contador_reservas].tipo_de_classe, "Primeira_classe");
			break;
		}
		else{
			printf("\nEscolha inexistente! Por gentileza escolha entre: \n 1 - Econômica\n 2 - Econômica premium\n 3 - Executiva\n 4 - Primeira classe");
		}
	}while (escolha >! 4);

	printf("Escolha um acento [1 à 100]: ");
	scanf("%i", &(*reserva)[contador_reservas].numero_do_assento);

	printf("Qual o seu destino: ");
	scanf("%s[^\n]", (*reserva)[contador_reservas].local_de_destino);
	replace((*reserva)[contador_reservas].local_de_destino);

	printf("horario de embarque: ");
	scanf("%s", (*reserva)[contador_reservas].horario_de_embarque);

	printf("Digite um numero que corresponda a Companhia aérea desejada: \n 1 - LATAM \n 2 - Gol\n 3 - Azul\n 4 - Avianca\n 5 - Passaredo\n");
	do{
		scanf("%i", &escolha);
		if(escolha == 1){
			strcpy((*reserva)[contador_reservas].companhia_aerea, "LATAM");
			break;
		}
		else if (escolha == 2){
			strcpy((*reserva)[contador_reservas].companhia_aerea, "Gol");
			break;
		}
		else if (escolha == 3){
			strcpy((*reserva)[contador_reservas].companhia_aerea, "Azul");
			break;
		}
		else if (escolha == 4){
			strcpy((*reserva)[contador_reservas].companhia_aerea, "Avianca");
			break;
		}
		else if (escolha == 5){
			strcpy((*reserva)[contador_reservas].companhia_aerea, "Passaredo");
			break;
		}
		else {
			printf("\nEscolha inexistente! Por gentileza escolha entre: \n 1 - LATAM \n 2 - Gol\n 3 - Azul\n 4 - Avianca\n 5 - Passaredo");
		}
	}while (escolha >! 5);

	(*reserva)[contador_reservas].numero_da_reserva = contador_reservas;
	printf("O numero de sua passage é %i", contador_reservas);

	(*quantidade_reservas)++;

	printf("\nDeseja fazer mais reservas? [S/N]: ");
	char resposta[1];
	scanf("%s", resposta);
	if (strcmp(resposta, "S") == 0 || strcmp(resposta, "s") == 0)
	{
	tamanho++;
	*reserva = (Agendamentos *)realloc(*reserva, tamanho * sizeof(Agendamentos));
	if (*reserva == NULL)
	{
		printf("Erro ao alocar memória.\n");
		exit(1);
	};
	contador_reservas++;

	criar_passagem(reserva, quantidade_reservas);
	}
	
};
void salvar_dados(Agendamentos *reserva, int quantidade_reservas)
{
FILE *arquivo;
arquivo = fopen("reserva.txt", "a");
if (arquivo == NULL)
{
printf("\nErro ao abrir o arquivo.\n");
exit(1);
}
int i;
for (i = 0; i < quantidade_reservas; i++)
{
	fprintf(arquivo, "%s %i %s %s %i %s %i %s %s %s %i\n", 
	reserva[i].nome_passageiro, 
	reserva[i].idade, 
	reserva[i].tipo_passagem, 
	reserva[i].identificacao_passaporte, 
	reserva[i].numero_de_telefone, 
	reserva[i].tipo_de_classe, 
	reserva[i].numero_do_assento, 
	reserva[i].horario_de_embarque, 
	reserva[i].local_de_destino, 
	reserva[i].companhia_aerea, 
	reserva[i].numero_da_reserva );
};
fclose(arquivo);
printf("\nDados salvos com sucesso!\n");
};

void listar_passagens(Agendamentos *reserva, int quantidade_reservas)
{
	if (reserva == NULL || quantidade_reservas <= 0) {
 	printf("\nLista de reservas vazia.\n");
 	return;
 	}
	for (int i = 0; i < quantidade_reservas; i++)
{
	printf("\nReserva %d:\n", i + 1);
			printf("Nome: %s\n", reserva[i].nome_passageiro);
            printf("Idade: %d\n", reserva[i].idade);
			printf("Tipo de Passagem: %s\n", reserva[i].tipo_passagem);
            printf("Identificação Passaporte: %s\n", reserva[i].identificacao_passaporte);
            printf("Telefone: %d\n", reserva[i].numero_de_telefone);
			printf("Classe: %s\n", reserva[i].tipo_de_classe);
            printf("Número do Assento: %d\n", reserva[i].numero_do_assento);
            printf("Check-in/Check-out: %s\n", reserva[i].horario_de_embarque);
            printf("Embarque/Desembarque: %s\n", reserva[i].local_de_destino);
            printf("Companhia Aérea: %s\n", reserva[i].companhia_aerea);
            printf("Número da Reserva: %d\n", reserva[i].numero_da_reserva);
};
};

void buscar_passagens(Agendamentos *reserva, int quantidade_reservas)
{
char nome_busca[50];
int encontradas = 0;
printf("\nDigite o nome do passageiro que deseja buscar: ");
scanf(" %s[^\n]", nome_busca);

for (int i = 0; i < quantidade_reservas; i++)
{
if (strcmp(reserva[i].nome_passageiro, nome_busca) == 0)
{
			printf("\nReserva %d:\n", i + 1);
			printf("Nome: %s\n", reserva[i].nome_passageiro);
			printf("Idade: %i\n", reserva[i].idade);
			printf("Tipo de Passagem: %s\n", reserva[i].tipo_passagem);
            printf("Identificação Passaporte: %s\n", reserva[i].identificacao_passaporte);
            printf("Telefone: %i\n", reserva[i].numero_de_telefone);
			printf("Classe: %s\n", reserva[i].tipo_de_classe);
            printf("Número do Assento: %i\n", reserva[i].numero_do_assento);
            printf("Check-in/Check-out: %s\n", reserva[i].horario_de_embarque);
            printf("Embarque/Desembarque: %s\n", reserva[i].local_de_destino);
            printf("Companhia Aérea: %s\n", reserva[i].companhia_aerea);
            printf("Número da Reserva: %i\n", reserva[i].numero_da_reserva);
			encontradas++;
		}
	};
	if (encontradas == 0)	
	{
		printf("Nenhuma reserva encontrada para o nome \"%s\".\n", nome_busca);
	};
};
// void editar_passagens(Agendamentos *reserva, int quantidade_reservas)
// {

// };

void excluir_passagem(Agendamentos **reserva, int *quantidade_reservas)
{
	int indice_excluir;
	printf("Digite o índice da reserva a ser excluída: ");
	scanf("%d", &indice_excluir);
	
	if (indice_excluir >= 1 && indice_excluir <= *quantidade_reservas)
	{
		int indice_array = indice_excluir - 1;
		for (int i = indice_array; i < (*quantidade_reservas) - 1; i++)
		{
		(*reserva)[i] = (*reserva)[i + 1];
		}
		*quantidade_reservas = *quantidade_reservas - 1;
		*reserva = (Agendamentos *)realloc(*reserva, *quantidade_reservas * sizeof(Agendamentos));
		if (*reserva == NULL && *quantidade_reservas > 0)

		{
		printf("Erro ao realocar memória.\n");
		exit(1);
		};

		salvar_dados(*reserva, *quantidade_reservas);

		printf("reserva excluida com sucesso!\n");
	}
	else
	{
	printf("Índice de reserva inválido. Tente novamente.\n");
	}
};

void carregar_passagens(Agendamentos **reserva, int *quantidade_reservas)
{	FILE *arquivo;
 	arquivo = fopen("reserva.txt", "r");
 	if (arquivo == NULL)
 	{
 printf("Arquivo de dados não encontrado. Inicializando com dados vazios.\n");
 return;
 } 
 Agendamentos nova_reserva;
 while (1){
 if(fscanf(arquivo, "%s %i %s %s %i %s %i %s %s %s %i", nova_reserva.nome_passageiro, &nova_reserva.idade, nova_reserva.tipo_passagem, nova_reserva.identificacao_passaporte, &nova_reserva.numero_de_telefone, nova_reserva.tipo_de_classe, &nova_reserva.numero_do_assento, nova_reserva.local_de_destino, nova_reserva.horario_de_embarque, nova_reserva.companhia_aerea, &nova_reserva.numero_da_reserva) == 11)
 	{ 	
	 	int tamanho = *quantidade_reservas + 1;
 		(*reserva) = (Agendamentos *)realloc(*reserva, tamanho * sizeof(Agendamentos));
		
		if (*reserva == NULL)
 		{
 		printf("Erro ao alocar memória.\n");
 		exit(1);
 		}
 	(*reserva)[*quantidade_reservas] = nova_reserva;
 	(*quantidade_reservas)++;
 	}
 	else
 	{
	printf("Carregado com Sucesso!\n");
 	break;
 	}
 	}
 fclose(arquivo);
}
void menu(Agendamentos **reserva, int *quantidade_reservas, int escolha_opcao)
{	do{
	printf("\nMENU:\n\n");
	printf("1. Criar passagem\n");
	printf("2. Salvar dados\n");
	printf("3. Listar todos os voôs marcados\n");
	printf("4. Verificar passagens\n");
	printf("5. Editar passagem\n");
	printf("6. Excluir passagem\n");
	printf("7. Sair\n");
	printf("Escolha uma opção: ");
	scanf("%d", &escolha_opcao);
		switch(escolha_opcao){
			case 1:
				criar_passagem(reserva, quantidade_reservas);
				break;
			case 2:
				salvar_dados(*reserva, *quantidade_reservas);
				break;
			case 3:
				listar_passagens(*reserva, *quantidade_reservas);
				break;
			case 4:
				buscar_passagens(*reserva, *quantidade_reservas);
				break;
		// 	case 5:
		// 		editar_passagens(*reserva, *quantidade_reservas);
		// 		break;
		// 	case 6:
		// 		excluir_passagem(reserva, quantidade_reservas);
		// 		break;
		} 
	flush_in();
	
	} while (escolha_opcao != 7);
};

int main(){
	Agendamentos *reservas = NULL;
	int quantidade_reservas = 0;
	int escolha = 0;
	setlocale(LC_ALL, "Portuguese");
	carregar_passagens(&reservas, &quantidade_reservas);
	printf("Inicializando Sistema de agendamento de passagem aêrea");
	menu(&reservas, &quantidade_reservas, escolha);
	printf("Encerrando Sistema...");
	return 0;
}