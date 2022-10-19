package ui; 
import java.util.Scanner;
import model.Game;

public class Main{

	private Scanner reader; 
	private Game gameControl;


	public Main(){
		reader = new Scanner(System.in);
        gameControl = new Game();
		 
    }
    public static void main(String[] args){
    	Main main = new Main(); 
		int option = 0; 

				do{

					option = main.getOptionShowMenu(); 
					main.executeOption(option);

				}while(option != 0);

				main.getReader().close();
	}
	
	
	/**
	 * Method getOptionShowMenu Shows a menu with the options
	 * @return option int :number of the choosen value
	 */
	public int getOptionShowMenu(){
		int option = 0; 
		System.out.println("Welcome to the game");
		System.out.println(
				"1. Crear jugador\n" +
				"2. Modificar puntaje del jugador\n" +
				"3. Incrementar nivel del jugador\n"+
				"4. Registrar enemigo en nivel\n"+
				"5. Registrar tesoro en nivel\n"+
				"6. Mostrar enemigos y tesoros de un nivel\n"+
				"7. Mostrar tesoro mas repetido en un nivel\n"+
				"8. Buscar cantidad de un tesoro en todo el juego\n"+
				"9. Buscar cantidad de un enemigo en todo el juego\n"+
				"10.Buscar enemigo que mas puntos da al jugador en todo el juego\n"+
				"11.Mostrar top 5 de jugadores por puntaje\n"+
				"0. Exit. ");
		option = reader.nextInt(); 

		return option; 
	}

	public Scanner getReader(){
		return reader;
	}

	public void executeOption(int option){
		String name = "";
		String playerNickName = "";
		String msj = "";
		String id = "";
	    int levelId = 0;
		int type = 0;
		double givePoints = 0;
		double removePoints = 0;
		double score = 0;
		int quantity = 0;
		String url = "";
		switch(option){
			case 1: 
			System.out.println("Escribe el nombre del jugador");
		    name = reader.next();
			System.out.println("Escribe el nickname del jugador");
			playerNickName = reader.next();
				
			msj = gameControl.registerPlayer(name, playerNickName);
			System.out.println(msj); 
				break;

			case 2:
			System.out.println("Escribe el nickname del jugador");
			playerNickName = reader.next();
			System.out.println("Escribe el nuevo puntaje del jugador");
			score = reader.nextDouble();
			msj = gameControl.modifyPlayerScore(score, playerNickName);
			System.out.println(msj);

				break; 
			case 3:
			System.out.println("Escribe el nickname del jugador");
			playerNickName = reader.next();
			msj = gameControl.increasePlayerLevel(playerNickName);
			System.out.println(msj);
			
				break;
			case 4:
			System.out.println("Escribe el id del enemigo");
			id = reader.next();
			System.out.println("Escribe la cantidad de puntos que le quitara al jugador");
			givePoints = reader.nextDouble();
			System.out.println("Escribe la cantidad de puntos que le dara al jugador");
			removePoints = reader.nextDouble();
			System.out.println("Escribe el id del nivel al que quieres agrgar el jugador.Recuerda que los id de los nivels son del 1 al 10");
			levelId = reader.nextInt();
			System.out.println("Seleciona uno de los siguientes tipos\n"+
								  "1.Ogro\n"+
								  "2.Abstracto\n"+
								  "3.Magico\n"+
								  "4.Jefe\n");
			type = reader.nextInt();
			msj = gameControl.RegisterEnemyInLevel(id, type, removePoints, givePoints, levelId);
			System.out.println(msj);
				break;
			case 5:
			System.out.println("Escribe el nombre del tesoro");
			name = reader.next();
			System.out.println("Escibe la cantidad de puntos que le da al jugador");
			givePoints = reader.nextDouble();
			System.out.println("Escribe cuantos tesoros de este tipo quieres agregar al nivel");
			quantity = reader.nextInt();
			System.out.println("Escribe el url del tesoro");
			url = reader.next();
			System.out.println("Escribe el id del nivel al que quieres agrgar el jugador.Recuerda que los id de los nivels son del 1 al 10");
			levelId = reader.nextInt();
			msj = gameControl.registerTreasureInlevel(name, url, givePoints, quantity, levelId);
			System.out.println(msj);
				break;
			case 6:
			System.out.println("Escribe el id del nivel");
			levelId = reader.nextInt();
			msj = gameControl.showEnemiesAndTreausresOfLevel(levelId);
			System.out.println(msj);
				break;
			case 7:
			msj = gameControl.MostRepeatedTreasureOfLevels();
			System.out.println(msj);
				break;
			case 8:
			System.out.println("Escribe el nombre del tesoro");
			name = reader.next();
			msj = gameControl.quantityOfTreasureInGame(name);
			System.out.println(msj);
				break;
			case 9:
			System.out.println("Elige el tipo de enemigo\n");
			System.out.println("Seleciona uno de los siguientes tipos\n"+
								  "1.Ogro\n"+
								  "2.Abstracto\n"+
								  "3.Magico\n"+
								  "4.Jefe\n");
			type = reader.nextInt();
			msj = gameControl.quantityOfEnemyInGame(type);
			System.out.println(msj);
				break;
			case 10:
			msj = gameControl.getEnemyWithMostGivenPoints();
			System.out.println(msj);
				break;
			case 11:
			msj = gameControl.getTop5playersByScore();
			System.out.println(msj);
			 	break;
			case 0: 
				System.out.println("Exit program.");
				break; 

			default: 
				System.out.println("Invalid Option");
				break; 
		}
	}

}