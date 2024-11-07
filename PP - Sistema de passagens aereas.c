#include <stdlib.h>
#include <stdbool.h>
#include <stdio.h>
#include <locale.h>
#include <string.h>

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
	char checkin_checkout[25];
	char local_de_embarque_desembarque[50];
	char companhia_aerea[30];
	int numero_da_reserva;
}	Agendamentos;

void criar_passagem(Agendamentos **reserva, int *quantidade_reservas)
{	int tamanho = *quantidade_reservas + 1;
	*reserva = (Agendamentos *)realloc(*reserva, tamanho * sizeof(Agendamentos));
	if (*reserva == NULL)
	{
		printf("Erro ao alocar memória.\n");
		exit(1);
	};
	int contador_reservas = *quantidade_reservas;

	printf('Nome completo: ');
	scanf("%s[^\n]", (*reserva)[contador_reservas].nome_passageiro);

	printf('Idade: ');
	scanf("%i", (*reserva)[contador_reservas].idade);

	if ((*reserva)[contador_reservas].idade <= 12 && (*reserva)[contador_reservas].idade > 2)
	{
		strcpy((*reserva)[contador_reservas].tipo_de_classe, "Meia");
	}
	else if ((*reserva)[contador_reservas].idade <= 2)
	{
		strcpy((*reserva)[contador_reservas].tipo_de_classe, "Não paga");
	};

	printf('Identificação do passaporte: [Está atrás de seu passaporte, são 2 letras e 6 números]');
	scanf("%s[^\n]", (*reserva)[contador_reservas].identificacao_passaporte);
	
	printf("Deseja fazer mais reservas?: [S/N]");
	char resposta[1];
	scanf("%s", resposta);
	if (strcmp(resposta, "S") == 1 || strcmp(resposta, "s") == 1)
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
for (int i = 0; i < quantidade_reservas; i++)
{
fprintf(arquivo, " %s, %i, %s, %s, %d, %s, %d, %s, %s, %s, %d\n",
reserva[i].nome_passageiro, 
reserva[i].idade, 
reserva[i].tipo_passagem,
reserva[i].identificacao_passaporte, 
reserva[i].numero_de_telefone,
reserva[i].tipo_de_classe, 
reserva[i].numero_do_assento, 
reserva[i].checkin_checkout,
reserva[i].local_de_embarque_desembarque,
reserva[i].companhia_aerea,
reserva[i].numero_da_reserva );
}
fclose(arquivo);
printf("\nDados salvos com sucesso!\n");
};
void listar_passagens(Agendamentos *reserva, int quantidade_reservas)
{

};
void buscar_passagens(Agendamentos *reserva, int quantidade_reservas)
{
void buscar_reserva(Agendamentos *reserva, int quantidade_reservas)
{
	char nome_busca[50];
	int encontradas = 0;
	printf("\nDigite o nome do passageiro que deseja buscar: ");
	scanf(" %[^\n]", nome_busca);

	for (int i = 0; i < quantidade_reservas; i++)
	{
		if (strcmp(reserva[i].nome_passageiro, nome_busca) == 0)
		{
			printf("\nReserva %d:\n", i + 1);
			printf("Nome: %s\n", reserva[i].nome_passageiro);
            printf("Idade: %d\n", reserva[i].idade);
			printf("Tipo de Passagem: %s\n", reserva[i].tipo_passagem);
            printf("Identificação Passaporte: %s\n", reserva[i].identificacao_passaporte);
            printf("Telefone: %d\n", reserva[i].numero_de_telefone);
			printf("Classe: %s\n", reserva[i].tipo_de_classe);
            printf("Número do Assento: %d\n", reserva[i].numero_do_assento);
            printf("Check-in/Check-out: %s\n", reserva[i].checkin_checkout);
            printf("Embarque/Desembarque: %s\n", reserva[i].local_de_embarque_desembarque);
            printf("Companhia Aérea: %s\n", reserva[i].companhia_aerea);
            printf("Número da Reserva: %d\n", reserva[i].numero_da_reserva);
			encontradas++;
		}
	}
	if (encontradas == 0)	
	{
		printf("Nenhuma reserva encontrada para o nome \"%s\".\n", nome_busca);
	}

};
void editar_passagens(Agendamentos *reserva, int quantidade_reservas)
{

};
void excluir_passagem(Agendamentos *reserva, int quantidade_reservas)
{
	int indice_excluir;
	printf("Digite o índice da reserva a ser excluída: ");
	scanf("%d", &indice_excluir);
	
	if (indice_excluir >= 1 && indice_excluir <= *quantidade_reservas);
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
		if (*reserva== NULL && *quantidade_reservas > 0)

		{
		printf("Erro ao realocar memória.\n");
		exit(1);
		}

		salvar_dados(*reserva, *quantidade_reservas);

		printf("reserva excluida com sucesso!\n");
	}
	else
	{
	printf("Índice de reserva inválido. Tente novamente.\n");
	}
};

void carregar_passagens(Agendamentos **reserva, int *quantidade_reservas)
{

};
void menu(Agendamentos **reserva, int *quantidade_reservas, int escolha)
{int escolha_opcao;
	do{
	printf("\nMENU:\n\n");
	printf("1. Criar passagem\n");
	printf("2. Salvar dados\n");
	printf("3. Listar todos os voôs marcados\n");
	printf("4. Verificar passagens\n");
	printf("5. Editar passagem\n");
	printf("6. Excluir passagem\n"); 
	printf("7.Sair\n");
	printf("Escolha uma opção: ");
	scanf("%i", &escolha_opcao);
	
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
		case 5:
			editar_passagens(*reserva, *quantidade_reservas);
			break;
		case 6:
			excluir_passagem(reserva, quantidade_reservas);		
	} 
		
	} while (escolha_opcao != 7);
};

int main(){
	Agendamentos *reservas = NULL;
	int quantidade_reservas = 0;
	int escolha = 0;

	carregar_passagens(&reservas, &quantidade_reservas);
	setlocale(LC_ALL, "Portuguese");
	printf("Inicializando Sistema de agendamento de passagem aêrea");
	menu(&reservas, &quantidade_reservas, escolha);
	printf("Encerrando Sistema...");
	return 0;
}
