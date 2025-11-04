import java.util.*;

public class rpg {
	static Scanner input = new Scanner(System.in);
	static Random roll = new Random();

	public static void main(String[] args) throws InterruptedException {

		String nome;
		String classe = "";
		int clas, resultadoB;
		// ATRIBUTOS BANDIDO
		int[] thief = new int[4];
		thief[0] = 2;
		thief[1] = 0;
		thief[2] = 15;
		thief[3] = 2;

		// ATRIBUTO DRAGÃO
		int[] dragon = new int[5];
		dragon[0] = 6;
		dragon[1] = 5;
		dragon[2] = 50;
		dragon[3] = 20;
		dragon[4] = 3;

		int[] character = new int[6];

		// INTRODUÇÃO
		System.out.print(
				"Bem vindo ao RPG: Garras da Nevasca, onde é contada a história de seu aventureiro, e de como ele parte em sua jornada para tentar libertar uma região de se seu algoz gelído.\n");
		System.out.println(
				"Antes de tudo, precisa estabelecer o nome e classe de seu aventureiro! (O Nome não pode ser vazio e tem que ter no mínimo de 3 caracteres e máximo de 20)");
		do {
			System.out.print("Nome: ");
			nome = input.nextLine().trim();
		} while (nome.isBlank() || nome.length() < 3 || nome.length() > 20);

		do {
			System.out.printf("escolha a sua classe pelo número que ela indicar:\n1-Guerreiro\n2-Mago\n");
			clas = input.nextInt();
		} while (clas != 1 && clas != 2);

		switch (clas) { // CRAÇÃO DE PERSONAGEM
			case 1 -> {
				// GUERREIRO
				character[0] = 4;
				character[1] = 3;
				character[2] = 30;
				character[3] = 0;
				character[4] = 0;
				character[5] = 0;
				classe = "guerreiro";
			}
			case 2 -> {
				// MAGO
				character[0] = 1;
				character[1] = 5;
				character[2] = 20;
				character[3] = 25;
				character[4] = 1;
				character[5] = 0;
				classe = "mago";
			}
			default -> System.err.printf("Essa não é uma classe válida, tente denovo\n");
		}
		// CRAÇÃO DE PERSONAGEM

		System.out.printf("você é um %s%n", classe);
		// pausar(1000);

		System.out.print(
				"Em uma taverna afastada, uma missão urgente circulava entre os aventureiros sobre a Floresta encantada,\nque agora era conhecida por ser a moradia de uma temivel fera, conhecida como Dragonfrost.\n");
		// pausar(4000);
		System.out.printf("Sem perder tempo, %s Parte em sua aventura.\n", nome);
		// pausar(3000);
		System.out.println("Porém logo na entrada da floresta um bandido o encontra.");
		// pausar(1500);
		System.out.println("é uma cilada");
		// pausar(1000);
		String ok;

		do {
			System.out.println("digite ok para continuar");
			ok = input.nextLine().trim();
		} while (!ok.equalsIgnoreCase("ok"));
		ok = "";
		resultadoB = combat(character, thief);
		pausar(1000);
		if (resultadoB == 0) {
			lapide();
			System.exit(0);
		}
		character[5] += roll.nextInt(3) + 1;
		System.out.print("\nAgora com o bandido derrotado, o(a) " + classe
				+ " descansa durante a noite,\nse recuperando totalmente antes de partir para enfrentar o dragão.\n");
		if (clas == 0) {
			character[2] = 30;
		} else {
			character[2] = 20;
			character[3] = 25;
		}
		pausar(3000);
		System.out.print("Mas o bandido havia uma certa quantidade de frascos, talvez seja melhor verificar.");
		pausar(2000);
		char invent = ' ';
		while (invent != 's' && invent != 'n') {
			System.out.print("\nDeseja checar o inventário? s/n\n");
			invent = input.next().charAt(0);
		}
		if (invent == 's') {
			System.out.printf("Você tem %d poções!%n", character[5]);
		}
		System.out.print(
				"Caminhando até a caverna você vê a besta dormindo, você acaba pisando em um graveto e a acordando, você se prepara\n");
		while (!ok.equals("ok")) {
			System.out.println("Digite 'Ok' para continuar");
			ok = input.next().toLowerCase();

		}

		resultadoB = combat(character, dragon);
		if (resultadoB == 0) {
			lapide();
			System.exit(0);
		}
		vitoria();
		System.out.print("\n\nVocê consegue derrotar o dragão.\n");
		System.out.print(
				"Libertando a região e ganhando uma recompensa suficiente para viver o restante da vida com luxo! Fim.\n");// PAREI
																															// AQUI
	}

	// DADOS
	public static int d20() {
		return roll.nextInt(20) + 1;
	}

	public static int d12() {
		return roll.nextInt(12) + 1;
	}

	public static int d10() {
		return roll.nextInt(10) + 1;
	}

	public static int d8() {
		return roll.nextInt(8) + 1;
	}

	public static int d6() {
		return roll.nextInt(6) + 1;
	}

	public static int d4() {
		return roll.nextInt(4) + 1;
	}

	public static void pausar(int milissegundos) {
		try {
			Thread.sleep(milissegundos);
		} catch (InterruptedException e) {

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
				turnP = input.nextInt();
				if (turnP > 2 || turnP < 1) {
					System.err.print("Ação inválida! Tente denovo\n");
				}
			} while (turnP != 1 && turnP != 2);
			if (persona[4] == 1) { // COMBATE MAGO
				if (turnP == 1) {
					do { // ESCOLHA DE ATAQUE
						System.out.printf("Escolha o tipo de ataque\n1-Ataque Físico\n2-Bolda de Fogo\n");
						escolhaA = input.nextInt();
					} while (escolhaA != 1 && escolhaA != 2);
					pausar(800);
					switch (escolhaA) {
						case 1 -> {
							// ATAQUE FÍSICO
							int dado = d20();
							pausar(500);
							System.out.printf("Você tirou %d!%n", dado);
							pausar(500);
							if (dado >= 10) {
								int dano0 = d4() + persona[0];
								enemy[2] -= dano0;
								System.out.printf("Você causou %d de dano!%n", dano0);
								pausar(1000);
								if (enemy[2] <= 0) {
									System.out.print("você derrotou o!\n");
									break;
								} else {
									System.out.printf("O inimigo está agora com %d de vida\n", enemy[2]);
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
								System.out.printf("%nvocê tirou %d!%n", dado);
								pausar(500);
								if (dado >= 5) {
									int dano01 = d12() + persona[1];
									enemy[2] -= dano01;
									System.out.printf("Você causou %d de dano!\n\n", dano01);
									pausar(1000);
									if (enemy[2] <= 0) {
										System.out.print("você derrotou o inimigo!\n");
									} else {
										System.out.printf("O inimigo está agora com %d de vida%n%n", enemy[2]);
									}
								} else {
									System.out.print("Você errou!\n\n");
									persona[1] -= 10;
								}
							} else {
								System.out.print("Você não tem MP suficiente\n");
							}
						}

					}// PAREI AQUI
				} else {// ITEM MAGO
					pausar(1000);
					if (persona[5] > 0) {
						persona[3] += 15;
						persona[5] -= 1;
						System.out.print("Você usou uma poção de mana! Recuperando 8 de MP\n");
					} else {
						System.out.print("Você não tem nenhum item\n");
					}
				}
			} else { // COMBATE GUERREIRO
				if (turnP == 1) {
					int dado = d20();
					pausar(500);
					System.out.printf("%nvocê tirou %d no dado%n", dado);
					pausar(500);
					if (dado >= 10) {
						int dano02 = d8() + persona[0];
						enemy[2] -= dano02;
						System.out.printf("Você causou %d de dano!\n", dano02);
						pausar(1000);
						if (enemy[2] <= 0) {
							System.out.print("o inimigo foi derrotado!\n");
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
					int escolhendo = roll.nextInt(1, 2);
					if (escolhendo == 1) {
						int dado = d20();
						if (dado > 10) {
							int dano04 = d10() + enemy[0];
							persona[2] -= dano04;
							System.out.printf("Você recebeu %d de dano!\n", dano04);
						} else {
						}
						System.out.print("o inimigo errou!\n");
					} else {
						int dado = d20();
						if (dado >= 10) {
							int dano05 = d12() + enemy[1];
							persona[2] -= dano05;
							System.out.printf("O inimigo deu %d de dano", dano05);
						}
						System.out.print("o inimigo errou!\n");
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

	public static void lapide() throws InterruptedException {
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

	public static void vitoria() throws InterruptedException {
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
