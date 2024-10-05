#include <stdlib.h>
#include <stdbool.h>
#include <stdio.h>
#include <locale.h>

typedef struct agendamento
{	//Informações pessoais do passageiro
	char nome_completo_do_passageiro[50];
	int data_de_nascimento;
	int passaporte;
	int cep;
	char genero[10];
	int numero_de_telefone;   
	// informações tecnincas da reserva
	int quantidade_passageiros;
	char tipo_de_classe[20];
	int numero_do_assento;
	char checkin[10];
	char checkout[10];
	char local_de_embarque[30];
	char local_de_desembarque[30];
	char companhia_aerea[30];
	int numero_da_reserva;
}	Agendamentos;

void criar_passagem(Agendamentos **reserva, int quantidade_reservas)
{

};
void salvar_dados(Agendamentos *reserva, int quantidade_reservas)
{

};
void listar_passagens(Agendamentos *reserva, int quantidade_reservas)
{

};
void verificar_passagens(Agendamentos *reserva, int quantidade_reservas)
{

};
void editar_passagens(Agendamentos *reserva, int quantidade_reservas)
{

};
void excluir_passagem(Agendamentos *reserva, int quantidade_reservas)
{

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
	printf("3. Listar todos os vôos marcados\n");
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
			verificar_passagens(*reserva, *quantidade_reservas);
			break;
		case 5:
			editar_passagens(*reserva, *quantidade_reservas);
			break;
		case 6:
			excluir_passagem(reserva, quantidade_reservas);		
	} 
		
	} while (escolha_opcao != 7);
};

int main()
	Agendamentos *reservas = NULL;
	int quantidade_reservas = 0;
	int escolha = 0;

	carregar_passagens(&reservas, &quantidade_reservas);
{	setlocale(LC_ALL, "Portuguese");
	printf("Inicializando Sistema de agendamento de passagem aérea");
	menu(&reservas, &quantidade_reservas, escolha);
	printf("Encerrando Sistema...");
	return 0;
}
