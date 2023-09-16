package model;

/**
 * The Board class represents the game board in the Pipe Mania game, COntains pipelines and
 * logic related to board management
 */
public class Board {
    
    // Initial board pipe
    private PipeLine head;
    // Final board pipe
    private PipeLine tail;
    static String verdeClaro= "\u001B[38;5;82m";
    static String resetColor = "\u001B[0m";

    /**
     * Create a new Board instance and initialize the board by calling (initBoard())
     */
    public Board() {

        initBoard();
        
    }

    /**
     * Gets the starting pipe from the board
     * @return The initial pipe
     */
      public PipeLine getHead() {
        return head;
    }

    /**
     * Sets the initial pipeline of the board
     * @param head The new initial pipe
     */
    public void setHead(PipeLine head) {
        this.head = head;
    }

    /**
     * Gest the final pipe from the board
     * @return The final pipe
     */
    public PipeLine getTail() {
        return tail;
    }

    /**
     * Sets the final pipeline of board
     * @param tail The new final pipe
     */
    public void setTail(PipeLine tail) {
        this.tail = tail;
    }
    
    /**
     * Insert a pipe of specific type into the board
     * @param type The type of pipe to insert
     */
    public void insertPipeLine(PipeLineType type) {
        insertPipeLine(new PipeLine(type));
    }
    
    /**
     * Insert a pipe into the board
     * @param pipeLine The pipe to insert
     */
    public void insertPipeLine(PipeLine pipeLine) {
        if (head == null) {
            head = pipeLine;
            tail = pipeLine;
        } else {
            tail.setNext(pipeLine);
            pipeLine.setPrev(tail);
            tail = pipeLine;
        }
    }

    /**
     * Initialize the game board with pipes, including a random source and drain
     */
    public void initBoard() {
        boolean sourceFlag = false;
        boolean drainageFlag = false;

        int sourcePosition = (int) (Math.random() * 64);
        int drainagePosition = (int) (Math.random() * 64);

        cleanBoard();

        while (sourcePosition == drainagePosition) {
            drainagePosition = (int) (Math.random() * 64);
        }

        for (int i = 0; i < 64; i++) {

            if (i == sourcePosition & !sourceFlag) {
                insertPipeLine(PipeLineType.SOURCE);
                sourceFlag = true;

            } else if (i == drainagePosition && !drainageFlag) {
                insertPipeLine(PipeLineType.DRAINAGE);
                drainageFlag = true;

            } else {
                insertPipeLine(PipeLineType.VOID);
            }
            
        }
    }

    /**
     * Clean the board and removing all the pipes
     */
    public void cleanBoard() {
        head = null;
        tail = null;
    }

    /**
     * Gets a text string representation of the game board
     * @return The text string representation of the game board
     */
    @Override
    public String toString() {
        String board =verdeClaro+ "    0   1   2   3   4   5   6   7\n  0 "+resetColor;
        PipeLine aux = head;

        for (int i = 0; i < 64; i++) {
            board +=aux.toString() + "   ";
            aux = aux.getNext();

            if ((i + 1) % 8 == 0) {
                board +=(i == 63)?"\n":" \n  "+verdeClaro+(i + 1) / 8+" "+resetColor;
            }
        }

        return board;
    }

    /**
     * Simulates the flow of water on the game board
     * @return 'true' if the flow reaches the drain, or 'false' otherwise
     */
    public boolean simulateFlow(){
        return simulateFlow(getSource(), null);
    }
 
    /**
     * Simulates the flow of water on the game board from a given pipe
     * @param pipeLine The pipe from which the flow is started
     * @param prevPipeLine The previous pipe in the flow direction 
     * @return 'true' if the flow reaches the drain, or 'false' otherwise
     */
    private boolean simulateFlow(PipeLine pipeLine, PipeLine prevPipeLine){

        if(pipeLine.isDrainage()){
            return true;
        } else if(pipeLine.isVoid()){
            return false;
        } else {

            PipeLine nextConnectedPipeLine = getFirstConnectedPipeLine(pipeLine, prevPipeLine);

            return simulateFlow(nextConnectedPipeLine, pipeLine);

        }

    }

    /**
     * Gets tge origin Source pipe on the game board
     * @return The source pipe
     */
    public PipeLine getSource(){
        PipeLine aux = head;

        while(aux != null && !aux.isSource()){
            aux = aux.getNext();
        }

        return aux;
    }

    /**
     * Gets the first pipe connected to the current pipe, following the direction of water flow
     * @param pipeLine The current pipe
     * @param prevPipeLine The previous pipe in the flow direction 
     * @return The first connected pipe in the direction of flow
     */
    public PipeLine getFirstConnectedPipeLine(PipeLine pipeLine, PipeLine prevPipeLine){
        
        PipeLine up = pipeLine.getUp();
        PipeLine right = pipeLine.getNext();
        PipeLine down = pipeLine.getDown();
        PipeLine left = pipeLine.getPrev();

        PipeLine nextConnectedPipeLine = new PipeLine(PipeLineType.VOID);

        if(pipeLine.isSource()) {

            if (up != null && up.isVertical()) {
                nextConnectedPipeLine = up;
            } else if (right != null && right.isHorizontal()) {
                nextConnectedPipeLine = right;
            } else if (down != null && down.isVertical()) {
                nextConnectedPipeLine = down;
            } else if (left != null && left.isHorizontal()) {
                nextConnectedPipeLine = left;
            }

        } else if(pipeLine.isCircular()) {

            if (up != null && up.isVertical() && up != prevPipeLine) {
                nextConnectedPipeLine = up;
            } else if (right != null && right.isHorizontal() && right != prevPipeLine) {
                nextConnectedPipeLine = right;
            } else if (down != null && down.isVertical() && down != prevPipeLine) {
                nextConnectedPipeLine = down;
            } else if (left != null && left.isHorizontal() && left != prevPipeLine) {
                nextConnectedPipeLine = left;
            }

        } else if(pipeLine.isHorizontal()) {

            if (right != null && (right.isHorizontal() || right.isCircular() || right.isDrainage()) && right != prevPipeLine) {
                nextConnectedPipeLine = right;
            } else if (left != null && (left.isHorizontal() || left.isCircular() || left.isDrainage()) && left != prevPipeLine) {
                nextConnectedPipeLine = left;
            }

        } else if(pipeLine.isVertical()) {

            if (up != null && (up.isVertical() || up.isCircular() || up.isDrainage()) && up != prevPipeLine) {
                nextConnectedPipeLine = up;
            } else if (down != null && (down.isVertical() || down.isCircular() || down.isDrainage()) && down != prevPipeLine) {
                nextConnectedPipeLine = down;
            }

        }

        return nextConnectedPipeLine;
    
    }

    /**
     * Gets a pipe at position specified by its coordinates (row and column)
     * @param x The pipe row
     * @param y The pipe column
     * @return The pipe at the specified coordinates  
     */
    public PipeLine getPipeLineByXY(int x, int y){
        PipeLine aux = head;
        boolean found = false;

        for(int i = 0; i < 64 && !found; i++){
            if(i == x*8 + y){
                found = true;
            } else {
                aux = aux.getNext();
            }
        }

        if(!found) aux = null;

        return aux;
    }

    /**
     * Gets the number of pipes used in the game board
     * @return The number of pipes used in the game board
     */
    public int countUsedPipes(){
        int count = 0;
        PipeLine aux = head;

        while(aux != null){
            if(!aux.isVoid() && !aux.isSource() && !aux.isDrainage()){
                count++;
            }
            aux = aux.getNext();
        }

        return count;
    }

}
