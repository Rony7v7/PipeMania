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
        System.out.println("--------------------------------");
        System.out.println("         -PIPE-MANIA-            ");
        System.out.println("--------------------------------");
        boolean isActive = true;

        do{
            isActive = view.execOptionMainMenu(view.showMainMenu());
        } while(isActive);
        System.out.println("-Hasta luego :)");

    }

    public int showMainMenu(){
        System.out.println("1. Nueva partida"); 
        System.out.println("2. Ver puntaje"); 
        System.out.println("3. Salir"); 
        System.out.println("--------------------------------");
        int option=input.nextInt();
        return option;
    }

    public boolean execOptionMainMenu(int option) {
        boolean isActive = true;

        switch(option){
            case 1: 
                initGame();
                break;
            
            case 2: 
                showScore();
                break;
            
            case 3: 
                isActive = false;
                break;
            
            default:  
                System.out.println("-Opción inválida.");
                break;
        }

        return isActive;
    }

    //Main menu option 1 - New Game

    public void initGame(){
        boolean isActive = true;

        loginUser();

        do{
            showBoard();

            isActive = execOptionGameMenu(showGameMenu());

        } while(isActive);

        //controller.endGame(username, startTime, endTime);

    }

    public void loginUser(){
        System.out.println("\n--------------------------------");
        System.out.println("-Ingrese su nickname");
        System.out.println("--------------------------------");
        input.nextLine();
        String nickname=input.nextLine();
        System.out.println("\n--------------------------------");
        System.out.println("-¿Que deseas hacer "+nickname+"?");
        System.out.println("--------------------------------");
    }

    public int showGameMenu() {
        System.out.println("\n1. Poner tuberia"); 
        System.out.println("2. Simular"); 
        System.out.println("3. Salir"); 
        int option2=input.nextInt();
        return option2;
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
        boolean flag=true;
            int fila=-1;
            int columna=-1;
            while(flag==true){
                System.out.println("\n--------------------------------");
                System.out.println("          (INSERTAR)           ");
                System.out.println("\n1. (=) Tuberia Horizontal");
                System.out.println("2. (||) Tuberia Vertical");
                System.out.println("3. (O) Tuberia Circular");
                System.out.println("--------------------------------");
                System.out.println("4. Editar o eliminar tuberia");
                System.out.println("5. Salir");
                System.out.println("--------------------------------");    
                int opcionInsert=input.nextInt();
                if(opcionInsert==5){
                    return;
                }else{
                    System.out.println("Ingrese la posicion de la fila");
                    fila=input.nextInt();
                    System.out.println("Ingrese la posicion de la columna");
                    columna=input.nextInt();
                    if(opcionInsert>0||opcionInsert<3){
                        //controller.insertarTuberia
                    }else if(opcionInsert==4){
                        editEliminate(fila, columna);
                    }else{
                        System.out.println("Opcion invalida");
                    }
                }
            }
    }
            
            
    public void editEliminate(int fila, int columna){

    }
              
        //Aqui se permite insertar, editar o eliminar tuberías (Reemplazar un pos x,y por una pipe [X, o, ||, =]  )


        //Game meun Option 2 - Simulate Flow
    public void simulateFlow(){
        System.out.println("-Simulacion de Tuberias-");
        //controller.simulateFlow()  ---> boolean
    }

    //Main menu Option 2 - 
    public void showScore() {
        System.out.println("Este es su puntaje");
        //controller.getScore(); ----> String
    }



}