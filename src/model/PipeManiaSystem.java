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

    public void endGame(String username, Long gameTime) {
        int score = calculateScore(gameTime);
        Player player = searchPlayer(username);

        if(player != null){
            scoreTable.updateScorePlayer(player, player.getScore()+score);
        } else {
            player = new Player(username, score);
            scoreTable.insertPlayer(player);
        }
    }

    public int calculateScore(Long gameTime) { // calcula el score del board actual segun la formula, recibe un tiempo
        return 0;
    }

    public Player searchPlayer(String username) { // busca un player en el bst por username
        /*
         * Este metodo sirve para identificar si un usuario ya existe y asignarle los nuevos puntos a el o avisarle al
         * usuario que el nombre ya existe. 
         */
        return null;
    }

    public void insertPipeLine(PipeLineType type, int row, int col) { 
        
        PipeLine pipeLine = board.getPipeLineByXY(row, col);

        if(pipeLine != null && !pipeLine.isDrainage() && !pipeLine.isSource()){
            pipeLine.setType(type);
        }

        /*
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
