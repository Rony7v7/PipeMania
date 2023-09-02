package ui;

import java.util.Scanner;
import model.PipeManiaSystem;

public class Main{

    Scanner input;
    PipeManiaSystem controller;

    public Main() {
        input = new Scanner(System.in);
        controller = new PipeManiaSystem();
    }

    public static void main(String[] args) {
        Main view = new Main();

        boolean isActive = true;

        do{
            isActive = view.execOptionMainMenu(view.showMainMenu());
        } while(isActive);

    }

    public int showMainMenu(){
        // 1. Nueva partida
        // 2. Ver puntaje
        // 3. Salir

        return 0;
    }

    public boolean execOptionMainMenu(int option) {
        boolean isActive = true;

        switch(option){
            case 1: initGame();
                break;
            
            case 2: showScore();
                break;
            
            case 3: isActive = false;
                break;
            
            default: System.out.println("Opción inválida.");
                break;
        }

        return isActive;
    }

    //Main menu option 1 - New Game

    public void initGame(){
        boolean isActive = true;
        //controller.createBoard();


        loginUser();

        do{
            showBoard();

            isActive = execOptionGameMenu(showGameMenu());

        } while(isActive);

    }

    public void loginUser(){

    }

    public int showGameMenu() {
        // 1. Poner tubería
        // 2. Simular
        // 3. Salir

        return 0;
    }

    public boolean execOptionGameMenu(int option) {
        boolean isActive = true;

        switch(option){
            case 1: insertPipe();;
                break;
            
            case 2: simulateFlow();;
                break;
            
            case 3: isActive = false;
                break;
            
            default: System.out.println("Opción inválida.");
                break;
        }

        return isActive;
    }

    public void showBoard(){

    }

        //Game menu Option 1 - Insert Pipe
    public void insertPipe(){
        //Aqui se permite insertar, editar o eliminar tuberías (Reemplazar un pos x,y por una pipe [X, o, ||, =]  )
    }

        //Game meun Option 2 - Simulate Flow
    public void simulateFlow(){
        //controller.simulateFlow()  ---> boolean
    }

    //Main menu Option 2 - 
    public void showScore() {
        //controller.getScore(); ----> String
    }



}