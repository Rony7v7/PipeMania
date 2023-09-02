package model;

public class Board {
    
    private PipeLine head;
    private PipeLine tail;

    public Board() {

        initBoard();
        
    }

    public PipeLine getHead() {
        return head;
    }

    public void setHead(PipeLine head) {
        this.head = head;
    }

    public PipeLine getTail() {
        return tail;
    }

    public void setTail(PipeLine tail) {
        this.tail = tail;
    }
    
    public void insertPipeLine(PipeLineType type) {
        insertPipeLine(new PipeLine(type));
    }
    
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


    public void initBoard() {
        boolean sourceFlag = false;
        boolean drainageFlag = false;

        int sourcePosition = (int) (Math.random() * 64);
        int drainagePosition = (int) (Math.random() * 64);

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

    @Override
    public String toString() {
        String board = "";
        PipeLine aux = head;

        for (int i = 0; i < 64; i++) {
            board += aux.toString() + "   ";
            aux = aux.getNext();

            if ((i + 1) % 8 == 0) {
                board += "\n";
            }
        }

        return board;
    }

}
