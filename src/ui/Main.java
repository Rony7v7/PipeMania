package ui;

import java.sql.Time;
import java.util.Scanner;
import model.PipeManiaSystem;


/**
 * The 'main' class represents the main interface of the Pipe Mania game. Allows
 * the Users interacat with the game, start new games, view scores and perform in-game actions
*/
public class Main {

    /**
     * Scanner used for user input
     */
    Scanner input;

    /**
     * Pipe Mania System Controller
     */
    PipeManiaSystem controller;

    public Main() {
        input = new Scanner(System.in);
        controller = new PipeManiaSystem();
    }

   /**
    * Main methos of the applicatio. Create as instance of the 'Main' class to handle the game loop
     * @param args Coummand line arguments not used
     */
     public static void main(String[] args) {
        Main view = new Main();
        boolean isActive = true;

        do {
            isActive = view.execOptionMainMenu(view.showMainMenu());
        } while (isActive);

    }

    /**
     * Dysplays the main menu of the game and allows the user to select an option
     * @return The opction selected for the user
     */
    public int showMainMenu() {
        System.out.println("--------------------------------");
        System.out.println("         -PIPE-MANIA-           ");
        System.out.println("--------------------------------");
        System.out.println("1. Nueva partida");
        System.out.println("2. Ver puntaje");
        System.out.println("3. Salir");

        System.out.print(">> ");
        int option = input.nextInt();
        input.nextLine();

        return option;
    }

    /**
     * @param option The option selected for the user
     * @return 'true' if the game should continue running, 'false' if the user chooses to exit
     */
    public boolean execOptionMainMenu(int option) {
        boolean isActive = true;

        switch (option) {
            case 1:
                initGame();
                break;

            case 2:
                showScore();
                break;

            case 3:
                isActive = false;
                System.out.println("-Hasta luego :)");
                break;

            default:
                System.out.println("-Opción inválida.");
                break;
        }

        return isActive;
    }

    /**
     * Start a new game of the game
     */
    public void initGame() {
        boolean isActive = true;

        String nickname = loginUser();

        Time startTime = new Time(System.currentTimeMillis());

        do {
            showBoard();

            isActive = execOptionGameMenu(showGameMenu(), nickname);

        } while (isActive);

        Time endTime = new Time(System.currentTimeMillis());

        controller.endGame(nickname, endTime.getTime() - startTime.getTime());

    }

    /**
     * Allows the user to enter their username
     * @return The username entered
     */
    public String loginUser() {
        System.out.println( "\n--------------------------------" +
                            "\n     -Ingrese su nickname-      " +
                            "\n--------------------------------");

        System.out.print(">> ");
        String nickname = input.nextLine();

        if (controller.searchPlayer(nickname) != null) {
            System.out.println("--------------------------------" +
                               "\n ¡Bienvenido de nuevo " + nickname + "!" +
                               "\n--------------------------------");
        } else {
            System.out.println( "--------------------------------" +
                                "\n ¡Bienvenido " + nickname + "!" +
                                "\n--------------------------------");
        }

        System.out.println("¿Que desea hacer " + nickname + "? \n");

        return nickname;
    }

    /**
     * Displays the game menu and allows the user to select an option
     * @return the option selected by the user
     */
    public int showGameMenu() {
        System.out.println( "1. Poner tuberia" +
                            "\n2. Simular flujo" +
                            "\n3. Salir");

        System.out.print(">> ");
        int option = input.nextInt();
        input.nextLine();

        return option;
    }

    /**
     * Execute the option selected in the game menu
     * @param option The option selected by the user
     * @param nickname The player is username
     * @return 'true' if the gmae should continue running, 'false' if the user chooses to exit
     */
    public boolean execOptionGameMenu(int option, String nickname) {
        boolean isActive = true;

        switch (option) {
            case 1:
                insertPipe();
                break;

            case 2:
                isActive = !simulateFlow();
                break;

            case 3:
                isActive = false;
                break;

            default:
                System.out.println("Opción inválida.");
                break;
        }

        return isActive;
    }

    /**
     * Shows the game board
     */
    public void showBoard() {
        System.out.println(controller.boardToString());
    }

   
    /**
     * Allows the user to insert a Pipe into the board
     */
    public void insertPipe() {

        int option = 0;
        int row, column;

        while (option != 5) {
            System.out.println( "\n--------------------------------" +
                                "\n          (INSERTAR)            " +
                                "\n1. Tuberia Horizontal (=)       " +
                                "\n2. Tuberia Vertical (||)        " +
                                "\n3. Tuberia Circular (O)         " +
                                "\n4. Vacío (X)                    " +
                                "\n--------------------------------" +
                                "\n5. Salir                        " +
                                "\n--------------------------------");

            System.out.print(">> ");
            option = input.nextInt();
            input.nextLine();

            if (option < 1 || option > 5){
                System.out.println("\nOpción inválida");
            } else {
                System.out.print("\nIngrese la posicion de la fila (0-7): ");
                row = input.nextInt();
                input.nextLine();

                System.out.print("Ingrese la posicion de la columna (0-7): ");
                column = input.nextInt();
                input.nextLine();

                controller.insertPipeLine(option, row, column);
                option = 5;
            }
        }

    }

    /**
     * Simulates the flow of pipes on the board and pisplays the result
     * @return 'true' if the simulation is correct, 'false' if it is incorrect
     */
    public boolean simulateFlow() {
        boolean isCorrect = controller.simulateFlow();

        System.out.println("-Simulacion de Tuberias-");
        if (isCorrect) {
            System.out.println("¡Felicidades! Ha ganado el juego");
        } else {
            System.out.println("Lo sentimos, la simulacion es incorrecta, vuelva a intentarlo");
        }

        return isCorrect;
    }

   
    /**
     * Shows the game score table
     */
    public void showScore() {
        System.out.println("-Tabla de puntajes-");
        System.out.println(controller.scoreTableToString());
    }

}