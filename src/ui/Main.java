package ui;

import java.sql.Time;
import java.util.Scanner;
import model.PipeManiaSystem;


/**
 * The 'main' class represents the main interface of the Pipe Mania game. Allows
 * the Users interact with the game, start new games, view scores and perform in-game actions
 * 
 * 
 * @author Rony Ordoñez, Alexis Delgado, Damy Villegas
 *
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
    static String colorMorado= "\u001B[35m";
    static String resetColor = "\u001B[0m";
    static String colorVerde="\u001B[32m";
    static String colorAzul="\u001B[36m";
    static String colorAmarillo="\u001B[33m";
    static String colorRojo= "\u001B[31m";
    static String colorRGB="\u001B[38;5;12m";

    


    /**
     * Create a new instance of the 'Main' class by initializing the scanner and the controller
     */
    public Main() {
        input = new Scanner(System.in);
        controller = new PipeManiaSystem();
    }

   /**
    * Main methods of the application. Create as instance of the 'Main' class to handle the game loop
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
        System.out.println(colorRGB+"\n╔══════════════════════╗\n"
                                     +"║"+resetColor+"      Pipe"+colorMorado+"-"+resetColor+"Mania      "+colorMorado+"║\n"
                                    + "╚══════════════════════╝"+resetColor);
        System.out.println(colorVerde+"1."+resetColor+" Nueva partida");
        System.out.println(colorVerde+"2."+resetColor+" Ver puntaje");
        System.out.println(colorAzul+"3."+resetColor+" Salir");

        System.out.print(">> ");
        int option = input.nextInt();
        input.nextLine();

        return option;
    }

    /**
     * Execute the option selected in the main menu
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
        System.out.println(colorMorado+"╔══════════════════════╗");
        System.out.println(            "║"+resetColor+" Ingrese su Nickname  "+colorMorado+"║");
        System.out.println(            "╚══════════════════════╝"+resetColor);

        System.out.print(">> ");
        String nickname = input.nextLine();

        if (controller.searchPlayer(nickname) != null) {
            System.out.println( colorAmarillo+"---------------------------------------" +resetColor+
                                "\n ¡Bienvenido de nuevo " + nickname + "!" +colorAmarillo+
                                "\n---------------------------------------"+resetColor);
        } else {
            System.out.println( colorAmarillo+"----------------------------------" +resetColor+
                                "\n         "+colorRojo+"¡"+resetColor+"Bienvenido " + nickname + colorRojo+"!" +resetColor+
                                colorAmarillo+"\n----------------------------------"+resetColor);
        }
        System.out.println(colorMorado+"    ╔═════════════════════════╗"+resetColor);
        System.out.println("      ¿Que desea hacer " + nickname + "?");
        System.out.println(colorMorado+"    ╚═════════════════════════╝"+resetColor);
        return nickname;
    }

    /**
     * Displays the game menu and allows the user to select an option
     * @return the option selected by the user
     */
    public int showGameMenu() {
        System.out.println(colorMorado+"══════════════════════════════════"+resetColor);
        System.out.println( colorVerde+"1"+resetColor+". Poner tuberia" +
                            colorVerde+"\n2"+resetColor+". Simular flujo" +
                            colorRojo+"\n3"+resetColor+". Salir :(");

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
            System.out.println(colorMorado+"═════════════════════════"+resetColor+
                                "\n        (INSERTAR)            " +
                                "\n\n1. Tuberia Horizontal ("+colorAmarillo+"="+resetColor+")       " +
                                "\n2. Tuberia Vertical ("+colorRojo+"\u2551"+resetColor+")        " +
                                "\n3. Tuberia Circular ("+colorVerde+"O"+resetColor+")         " +
                                "\n4. Vacío (X)                    " +colorMorado+
                                colorMorado+"\n═════════════════════════" +resetColor+
                                "\n5. Salir                        " +
                                colorMorado+"\n═════════════════════════"+resetColor);

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
                System.out.println(colorMorado+"\n══════════════════════════════════"+resetColor);
                System.out.println(colorRGB+"            PIPE"+resetColor+"-"+colorRGB+"MANIA"+resetColor);
                System.out.println(colorMorado+"══════════════════════════════════"+resetColor);
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
            System.out.println("\nLo sentimos, la simulacion es incorrecta"+
                               "\n        Vuelve a intentarlo");
            System.out.println(colorMorado+"\n══════════════════════════════════"+resetColor);
        }

        return isCorrect;
    }

   
    /**
     * Shows the game score table
     */
    public void showScore() {
        System.out.println(colorMorado+"\n══════════════════════════════════"+resetColor);
        System.out.println("-Tabla de puntajes-");
        System.out.println(controller.scoreTableToString());
        System.out.println(colorMorado+"\n══════════════════════════════════"+resetColor);
    }

}