package ui;

import java.sql.Time;
import java.util.Scanner;
import model.PipeManiaSystem;

public class Main {

    Scanner input;
    PipeManiaSystem controller;

    public Main() {
        input = new Scanner(System.in);
        controller = new PipeManiaSystem();
    }

    public static void main(String[] args) {
        Main view = new Main();
        System.out.println("--------------------------------");
        System.out.println("         -PIPE-MANIA-            ");
        System.out.println("--------------------------------");
        boolean isActive = true;

        do {
            isActive = view.execOptionMainMenu(view.showMainMenu());
        } while (isActive);

    }

    public int showMainMenu() {
        System.out.println("1. Nueva partida");
        System.out.println("2. Ver puntaje");
        System.out.println("3. Salir");
        System.out.println("--------------------------------");
        int option = input.nextInt();
        return option;
    }

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

    // Main menu option 1 - New Game

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

    public String loginUser() {
        System.out.println("\n--------------------------------" +
                "\n     -Ingrese su nickname-      " +
                "\n--------------------------------");

        System.out.print(">> ");
        input.nextLine();
        String nickname = input.nextLine();

        if (controller.searchPlayer(nickname) != null) {
            System.out.println("\n--------------------------------" +
                    "\n ¡Bienvenido de nuevo " + nickname + "!" +
                    "\n--------------------------------");
        } else {
            System.out.println("\n--------------------------------" +
                    "\n ¡Bienvenido " + nickname + "!" +
                    "\n--------------------------------");
        }

        System.out.println("\n--------------------------------" +
                "\n ¿Que desea hacer " + nickname + "? " +
                "\n--------------------------------");

        return nickname;
    }

    public int showGameMenu() {
        System.out.println( "\n1. Poner tuberia" +
                            "\n2. Simular flujo" +
                            "\n3. Salir");

        int option = input.nextInt();
        input.nextLine();

        return option;
    }

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

    public void showBoard() {
        System.out.println(controller.boardToString());
    }

    // Game menu Option 1 - Insert Pipe
    public void insertPipe() {

        int option = 0;
        int row, column;

        while (option != 5) {
            System.out.println( "\n--------------------------------" +
                                "\n          (INSERTAR)            " +
                                "\n1. Tuberia Horizontal (=)       " +
                                "\n2. Tuberia Vertical (||)        " +
                                "\n3. Tuberia Circular (O)         " +
                                "\n3. Vacío (X)                    " +
                                "\n--------------------------------" +
                                "\n4. Salir                        " +
                                "\n--------------------------------");

            option = input.nextInt();

            if (option < 0 || option > 3){
                System.out.println("Opción inválida");
            } else {
                System.out.print("Ingrese la posicion de la fila (1-8): ");
                row = input.nextInt();
                input.nextLine();

                System.out.print("Ingrese la posicion de la columna (1-8): ");
                column = input.nextInt();
                input.nextLine();

                controller.insertPipeLine(option, row, column);
                option = 5;
            }
        }

    }

    // Game meun Option 2 - Simulate Flow
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

    // Main menu Option 2 -
    public void showScore() {
        System.out.println("-Tabla de puntajes-");
        System.out.println(controller.scoreTableToString());
    }

}