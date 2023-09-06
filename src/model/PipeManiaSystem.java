package model;

public class PipeManiaSystem {
    Board board;
    ScoreTable scoreTable;

    public PipeManiaSystem() {
        board = new Board();
        scoreTable = new ScoreTable();
    }

    public void createBoard() {
        board.initBoard();
    }

    public String boardToString() {
        return board.toString();
    }

    public void endGame(){
        /*
         1. Calcular puntaje
         2. buscar user por username en scoreTable
         3. si existe el user, actualizar su puntaje y acutalizar el bst
         4. si no existe el user, crearlo y agregarlo al bst
         */
    }

    public int calculateScore() { // calcula el score del board actual segun la formula, recibe un tiempo inicial y un tiempo final
        return 0;
    }

    public Player searchPlayer(String username) { // busca un player en el bst por username
        return null;
    }

    public void insertPipeLine(PipeLineType type, String position) { 
        /*
         recibe la posición en formato "x,y"
         separa las coordenadas con split
         revisa si las cordenadas no son pipelines Source ni Drainage
         reemplaza la pipeLine en la posición indicada por el tipo de pipeLine recibido
         *Este metodo es el mismo para eliminar y editar*
         */
    }

    public boolean simulateFlow() {
        return board.simulateFlow();
    }

    public String scoreTableToString() {
        return scoreTable.toString();
    }

}
