import java.util.*;

public class Garras_da_nevasca_Final {
	static Scanner input = new Scanner(System.in);
	static Random roll = new Random();

	public static void main(String[] args) {
		String nome;
		String classe = "";
		int clas, resultadoB;
		// ATRIBUTOS BANDIDO
		int[] thief = new int[4];
		thief[0] = 2; // força
		thief[1] = 0; // inteligência
		thief[2] = 15; // HP
		thief[3] = 2; // tipo inimigo

		// ATRIBUTO DRAGÃO
		int[] dragon = new int[5];
		dragon[0] = 6; // força
		dragon[1] = 5; // inteligência
		dragon[2] = 50; // HP
		dragon[3] = 20; // MP
		dragon[4] = 3; // tipo inimigo

		int[] character = new int[6];

		// INTRODUÇÃO
		System.out.println("\nBem vindo ao RPG: Garras da Nevasca, onde é contada a história de seu aventureiro, e de como ele parte em sua jornada para tentar libertar uma região de se seu algoz gelído.\n");
		System.out.println("Antes de tudo, precisa estabelecer o nome e classe de seu aventureiro! (O Nome não pode ser vazio e tem que ter no mínimo de 3 caracteres e máximo de 20)");
		do {
			System.out.print("Nome: ");
			nome = input.nextLine().trim();
		} while (nome.isBlank() || nome.length() < 3 || nome.length() > 20);

		do {
			try {
				System.out.printf("Escolha a sua classe pelo número que ela indicar:\n1-Guerreiro\n2-Mago\n");
				clas = input.nextInt();
			} catch (InputMismatchException excessao) {
				System.err.printf("Além de ser uma letra, essa não é uma classe válida, tente novamente...\n");
				input.nextLine(); // limpar buffer
				clas = 0; // valor inválido para continuar o loop
			}
			
		} while (clas != 1 && clas != 2);
		input.nextLine(); // limpar buffer

		switch (clas) { // CRAÇÃO DE PERSONAGEM
			case 1 -> {
				// GUERREIRO
				character[0] = 4; // força
				character[1] = 3; // inteligência
				character[2] = 30; // HP
				character[3] = 0; // MP
				character[4] = 0; // classe do personagem (0-guerreiro 1-mago)
				character[5] = 0; // itens
				classe = "Guerreiro"; // nome da classe
			}
			case 2 -> {
				// MAGO
				character[0] = 1; // força
				character[1] = 5; // inteligência
				character[2] = 20; // HP
				character[3] = 25; // MP
				character[4] = 1; // classe do personagem (0-guerreiro 1-mago)
				character[5] = 0; // itens
				classe = "Mago"; // nome da classe
			}
			default -> System.err.printf("Essa não é uma classe válida, tente denovo\n");
		}

		System.out.printf("você é um %s%n", classe);
		// pausar(1000);

		System.out.print("Em uma taverna afastada, uma missão urgente circulava entre os aventureiros sobre a Floresta encantada, que agora era conhecida por ser a moradia de uma temivel fera, conhecida como Dragonfrost.\n");
		// pausar(4000);
		System.out.printf("Sem perder tempo, %s parte em sua aventura.\n", nome);
		// pausar(3000);
		System.out.println("Porém logo na entrada da floresta um Bandido o encontra.");
		// pausar(1500);
		System.out.println("É uma cilada!");
		// pausar(1000);
		String ok = "";
		while (!ok.equalsIgnoreCase("ok")) {
			System.out.println("Digite ok para continuar");
			ok = input.nextLine().trim();
		}
		ok = "";
		resultadoB = combat(character, thief);
		pausar(1000);
		if (resultadoB == 0) {
			lapide();
			System.exit(0);
		}
		character[5] += roll.nextInt(3) + 1;
		System.out.printf("\nAgora com o Bandido derrotado, o(a) %s descansa durante a noite para se recuper totalmente antes de partir para enfrentar o dragão. (HP e MP totalmente recuperados!)\n", classe);
		if (clas == 0) { //Recuperação de HP e MP pós combate do guerreiro
			character[2] = 30; // HP
		} else { //Recuperação de HP e MP pós combate do mago
			character[2] = 20; // HP
			character[3] = 25; // MP
		}
		pausar(3000);
		System.out.print("Mas o bandido havia uma certa quantidade de frascos. Talvez seja melhor verificar.");
		pausar(2000);
		char invent;
		input.nextLine(); // limpar buffer
		do {
			try{
				System.out.print("\nDeseja checar o inventário? s/n\n");
				invent = input.nextLine().charAt(0);} 
			catch (StringIndexOutOfBoundsException excessao){
				System.err.print("Impossível usar Espaço como resposta!\n");
				invent = ' ';
				}
		} while (invent != 's' && invent != 'S' && invent != 'n' && invent != 'N');
		if (invent == 's' || invent == 'S') {
			System.out.printf("Você tem %d poções!%n", character[5]);
		}
		System.out.print("Caminhando até a caverna você vê a besta dormindo. Você acaba pisando em um graveto, à acordando, então você se prepara para o que poderia ser sua ultíma aventura.\n");
		while (!ok.equalsIgnoreCase("ok")) {
			System.out.println("Digite ok para continuar");
			ok = input.nextLine().trim();
		}
		resultadoB = combat(character, dragon);
		if (resultadoB == 0) {
			lapide();
			System.exit(0);
		}
		vitoria();
		System.out.print("\n\nVocê consegue derrotar o dragão.\n");
		System.out.print("Libertando a região e ganhando uma recompensa suficiente para viver o restante da vida com luxo! Fim.\n");}

	// DADOS
	public static int d20() {
		return roll.nextInt(0, 21);
	}

	public static int d12() {
		return roll.nextInt(0,13);
	}

	public static int d10() {
		return roll.nextInt(0, 11);
	}

	public static int d8() {
		return roll.nextInt(0, 9);
	}

	public static int d6() {
		return roll.nextInt(0, 7);
	}

	public static int d4() {
		return roll.nextInt(0, 5);
	}

	public static void pausar(int milissegundos) {
		try {
			Thread.sleep(milissegundos);
		} catch (InterruptedException excessao) {

			Thread.currentThread().interrupt();
		}
	}

	// COMBATE
	public static int combat(int persona[], int enemy[]) {

		int turnP = 0;
		int escolhaA = 0;
		System.out.println("O combate começa!\n");
		while (persona[2] > 0 && enemy[2] > 0) { // LOOP COMBATE
			pausar(1000);
			System.out.printf("HP: %d\nMP: %d%n", persona[2], persona[3]);
			pausar(500);
			System.out.print("Escolha sua ação conforme o número\n");
			do {
				System.out.printf("1-ataque 2-item\n");
				try {
					turnP = input.nextInt();
				} catch (InputMismatchException excessao) {
					System.err.print("Ação inválida! Tente denovo\n");
					input.nextLine(); // limpar buffer
					turnP = 0; // valor inválido para continuar o loop
					continue;
				}
				if (turnP > 2 || turnP < 1) {
					System.err.print("Ação inválida! Tente denovo\n");
				}
			} while (turnP != 1 && turnP != 2);
			if (persona[4] == 1) { // COMBATE MAGO
				if (turnP == 1) {
					do { // ESCOLHA DE ATAQUE
						try {
							System.out.printf("Escolha o tipo de ataque\n1-Ataque Físico\n2-Bola de Fogo\n");
							escolhaA = input.nextInt();
						} catch (InputMismatchException excessaoe) {
							System.err.print("Ação inválida! Tente denovo\n");
							input.nextLine(); // limpar buffer
						}
					} while (escolhaA != 1 && escolhaA != 2);
					pausar(800);
					switch (escolhaA) {
						case 1 -> {
							// ATAQUE FÍSICO
							int dado = d20();
							pausar(500);
							System.out.printf("%nVocê tirou %d no D20!%n", dado);
							pausar(500);
							if (dado >= 10) {
								int dano0 = d4() + persona[0];
								enemy[2] -= dano0; // Calculo de dano no inimigo ao usar ataque físico
								System.out.printf("Você tirou %d no D4! Logo causou %d de dano!\n\n",dano0 - persona[0], dano0);
								pausar(1000);
								if (enemy[2] <= 0) { // inimigo derrotado
									System.out.print("Você derrotou o inimigo!\n");
									break;
								} else {
									System.out.printf("O inimigo está agora com %d de vida\n", enemy[2]);	// vida restante do inimigo
								}
							} else {
								System.out.print("Você errou!\n");
							}
						}
						case 2 -> {
							int dado;
							// BOLA DE FOGO
							if (persona[3] >= 10) {
								dado = d20();
								pausar(500);
								System.out.printf("%nVocê tirou %d no D20!%n", dado);
								pausar(500);
								if (dado >= 5) { // Acerto de bola de fogo. Essa magia possui uma chance alta de acerto, diferente dos outros ataques com dado 20
									int dano01 = d12() + persona[1];
									enemy[2] -= dano01; // Calculo de dano no inimigo ao usar bola de fogo
									System.out.printf("Você tirou %d no D12! Logo causou %d de dano!\n\n",dano01 - persona[1], dano01);
									pausar(1000);
									if (enemy[2] <= 0) {
										System.out.print("você derrotou o inimigo!\n");
									} else {
										System.out.printf("O inimigo está agora com %d de vida%n%n", enemy[2]);
									}
								} else {
									System.out.print("Você errou!\n\n");
									
								}
								persona[3] -= 10; // Custo de MP ao usar bola de fogo
							} else {
								System.out.print("Você não tem MP suficiente\n");
							}
						}

					}
				} else {// ITEM MAGO
					pausar(1000);
					if (persona[5] > 0) { // Validação de posse de item
						persona[3] += 15; // Recuperação de MP
						persona[5] -= 1; // Consumo de item (poção de mana)
						System.out.print("Você usou uma poção de mana! Recuperando 15 de MP\n");
					} else {
						System.out.print("Você não tem nenhum item\n");
					}
				}
			} else { // COMBATE GUERREIRO
				if (turnP == 1) {
					int dado = d20();
					pausar(500);
					System.out.printf("%nVocê tirou %d no D20!%n", dado);
					pausar(500);
					if (dado >= 10) { // Chance de acerto do ataque
						int dano02 = d8() + persona[0]; // Cálculo de dano do ataque
						enemy[2] -= dano02; // Aplicação do dano calculado no inimigo
						System.out.printf("Você tirou %d no D8! Logo causou %d de dano!\n\n",dano02 - persona[0], dano02);
						pausar(1000);
						if (enemy[2] <= 0) {
							System.out.print("O inimigo foi derrotado!\n");
							break;
						} else {
							System.out.printf("O inimigo está agora com %d de vida\n\n", enemy[2]);
						}
					} else {
						System.out.print("Você errou!\n\n");
					}
				} else { // ITEM GUERREIRO
					if (persona[5] > 0) {
						persona[0] += 8;
						persona[5] -= 1;
						System.out.print("Você usou o item e se sente mais poderoso! (+8 de dano)\n");
					} else {
						System.out.print("Você não tem nenhum item\n");
					}
				}
			}
			if (enemy[2] <= 0) {
				break;
			} else {// TURNO DO INIMIGO
				pausar(1000);
				System.out.print("Turno do inimigo\n");
				pausar(1000);
				if (enemy[3] == 2) { // COMBATE LADRÃO
					int dado = d20();
					if (dado > 10) {
						int dano03 = d6() + enemy[0];
						persona[2] -= dano03;
						System.out.printf("Você recebeu %d de dano!\n", dano03);

					} else {
						System.out.print("O inimigo errou!\n\n");
					}
				} else {// COMBATE DRAGÃO
					int escolhendo = roll.nextInt(1, 3); // escolha aleatória de ataque do dragão. 1-Garras 2-Baforada
					if (escolhendo == 1) {
						int dado = d20();
						System.out.println("O Dragonfrost te ataco com suas garras afiadas!");
						if (dado >= 10) {
							int dano04 = d10() + enemy[0]; // ataque físico do dragão
							persona[2] -= dano04; // aplicação do dano calculado no personagem
							System.out.printf("Você recebeu %d de dano!\n", dano04);
						} else {System.out.print("O dragão erra!\n");}
						
					} else {
						int dado = d20();
						System.out.println("O Dragonfrost te ataca com sua poderosa baforada gélida! Como algo tão frio pode queimar tanto?");
						if (dado >= 10) {
							int dano05 = d12() + enemy[1]; // ataque mágico do dragão
							persona[2] -= dano05; // aplicação do dano mágico calculado no personagem
							System.out.printf("Você recebeu %d de dano!", dano05);
						} else {System.out.print("PORÉM ELE ERRA!\n");}
					}
				}
			}

		}
		if (persona[2] > 0) {
			return 1;
		} else {
			return 0;
		}

	}

	public static void lapide(){
		System.out.println("é o fim");
		pausar(3000);
		System.out.print("                                 _____  _____\n");
		pausar(75);
		System.out.print("                                <     `/     |\n");
		System.out.print("                                 >          (\n");
		pausar(75);
		System.out.print("                                |   _     _  |\n");
		pausar(75);
		System.out.print("                                |  |_) | |_| |\n");
		pausar(75);
		System.out.print("                                |  | \\ | |   |\n");
		pausar(75);
		System.out.print("                                |            |\n");
		pausar(75);
		System.out.print("                 ______.______%_|            |__________  _____\n");
		pausar(75);
		System.out.print("               _/                                       \\\\|     |\n");
		pausar(75);
		System.out.print("              |             V O C Ê    M O R R E U             <\n");
		pausar(75);
		System.out.print("              |_____.-._________              ____/|___________|\n");
		pausar(75);
		System.out.print("                                |            |\n");
		pausar(75);
		System.out.print("                                |            |\n");
		pausar(75);
		System.out.print("                                |            |\n");
		pausar(75);
		System.out.print("                                |            |\n");
		pausar(75);
		System.out.print("                                |   _        <\n");
		pausar(75);
		System.out.print("                                |__/         |\n");
		pausar(75);
		System.out.print("                                 / `--.      |\n");
		pausar(75);
		System.out.print("                               %|            |%\n");
		pausar(75);
		System.out.print("                           |/.%%|          -< @%%%\n");
		pausar(75);
		System.out.print("                           `\\%`@|     v      |@@%@%%    - mfj\n");
		pausar(75);
		System.out.print("                         .%%%@@@|%    |    % @@@%%@%%%%\n");
		pausar(75);
		System.out.print("                    _.%%%%%%@@@@@@%%_/%\\_%@@%%@@@@@@@%%%%%%\n");
	}

	public static void vitoria(){
		System.out.print("     ⣠⠤⠤⣄⣠⡤⠤⠤⠤⠤⠤⠤⠤⠤⢤⣄⣠⠤⠤⣄⠀⠀⠀⠀\n");
		pausar(150);
		System.out.print("⠀⠀⠀⡜⢁⡶⠶⢤⡇⠀⠈⠉⠉⠉⠉⠉⠉⠁   ⠀⠸⡦⠾⠶⡄⢳⠀⠀⠀\n");
		pausar(150);
		System.out.print("⠀⠀⠀⡇⢸⠀⠀⠀⡃⠀⠀ V O C Ê⠀⠀ ⠀⡇⠀  ⡇⢸ ⠀⠀\n");
		pausar(150);
		System.out.print("⠀⠀⠀⢧⠘⣆⠀⠀⡇⠀⠀V E N C E U ⢰⠇⠀⠇⣸⠀⠀⠀\n");
		pausar(150);
		System.out.print("⠀⠀⠀⠈⢦⡘⠦⣀⠹⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡞⣀⡴⠋ ⡰⠃⠀⠀⠀\n");
		pausar(150);
		System.out.print("⠀⠀⠀⠀⠀⠙⠦⣌⡙⠻⣄⠀⠀⠀⠀⠀⠀⠀⠀⣠⠞⠋⣁⡴⠚⠁⠀⠀⠀⠀\n");
		pausar(150);
		System.out.print("⠀⠀⠀⠀⠀⠀⠀  ⠉⠚⠳⣄⠀⠀⠀⠀⣠⠞⠋⠉⠉⠀⠀⠀⠀⠀⠀⠀\n");
		pausar(150);
		System.out.print("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢳⡀⠀⡼⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n");
		pausar(150);
		System.out.print("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡇⠸⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n");
		pausar(150);
		System.out.print("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡜⠀⠀⢳⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n");
		pausar(150);
		System.out.print("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣞⣀⣀⣀⣀⣳⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n");
		pausar(150);
		System.out.print("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣾⠉⠉⠉⠉⠉⠉⢹⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n");
		pausar(150);
		System.out.print("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡷⠤⠤⠤⠤⠤⠤⠼⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n");
		pausar(150);
		System.out.print("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠓⠒⠒⠒⠒⠒⠒⠒⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n");
		pausar(150);
		System.out.print("⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀\n");
		pausar(150);
		System.out.print("⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n");
	}
}
