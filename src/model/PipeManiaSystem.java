package model;

/**
 * The PipeManiaSystem class represents the main system of the Pipe Mania Game. Manage the game
 * board, scoring and game-related operations
 */
public class PipeManiaSystem {
    // Game board
    Board board;
    // Score table
    ScoreTable scoreTable;

    /**
     * Create a new instance of PipeManiaSystem by initializing the game board and scoreboard
     */
    public PipeManiaSystem() {
        board = new Board();
        scoreTable = new ScoreTable();
    }

   /**
     * Create a new gamer board
     */
     public void createBoard() {
        board.initBoard();
    }

    /**
     * Gets a text string representation of the game board
     * @return The text string representation of the game board
     */
    public String boardToString() {
        return board.toString();
    }

    /**
     * Finish the game, calculate the score and update the scoreboard
     * @param username The player is username
     * @param gameTime Game time
     */
    public void endGame(String username, Long gameTime) {
        int score = calculateScore(gameTime);
        Player player = searchPlayer(username);

        if(player != null){
            scoreTable.updateScorePlayer(player, player.getScore()+score);
        } else {
            player = new Player(username, score);
            scoreTable.insertPlayer(player);
        }

        board.initBoard();

    }

    /**
     * Calculates the score of the current game board based on the game time
     * @param gameTime Game time
     * @return The calculated score
     */
    public int calculateScore(Long gameTime) {
        int score = 0;

        if (simulateFlow()) score = (int)(((100 - countUsedPipes()) * 10) - gameTime/1000);

        return score;
    }

    /**
     * Gets the number of pipes used in the game board
     * @return The number of pipes used in the game board
     */
    public int countUsedPipes(){
        return board.countUsedPipes();
    }

    /**
     * Search for a player by username in the scoreboard
     * @param username The username of the player to search
     * @return The player found or null if not found
     */
    public Player searchPlayer(String username) { 
        /*
         * Este metodo sirve para identificar si un usuario ya existe y asignarle los nuevos puntos a el o avisarle al
         * usuario que el nombre ya existe. 
         */
        return null;
    }

    /**
     * Inserts a type of pipe into a position on the game board
     * @param intType The type of pipe to be inserted (1: Horizontal, 2: Vertical, 3: Circular, 4: Empty)
     * @param row The row in which the pipe will be inserted
     * @param col The column in which the pipe will be inserted
     */
    public void insertPipeLine(int intType, int row, int col) { 
        PipeLineType type = null;

        switch(intType){
            case 1:
                type = PipeLineType.HORIZONTAL;
                break;
            case 2:
                type = PipeLineType.VERTICAL;
                break;
            case 3:
                type = PipeLineType.CIRCULAR;
                break;
            case 4:
                type = PipeLineType.VOID;
                break;
        }
        
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

    /**
     * Simulates the flow of water on the game board
     * @return 'true' if the flow was successful, 'false' otherwise
     */
    public boolean simulateFlow() {
        return board.simulateFlow();
    }

    /**
     * Gets a text string representation of the scores table
     * @return The text string representation of the socres table
     */
    public String scoreTableToString() {
        return scoreTable.toString();
    }

}
