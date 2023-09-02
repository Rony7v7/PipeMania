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

    public boolean simulateFlow(){
        return simulateFlow(getSource(), null);
    }
 
    private boolean simulateFlow(PipeLine pipeLine, PipeLine prevPipeLine){

        if(pipeLine.isDrainage()){
            return true;
        } else if(pipeLine.isVoid()){
            return false;
        } else {

            PipeLine nextConnectedPipeLine = getFirstConnectedPipeLine(pipeLine, prevPipeLine);

            if(nextConnectedPipeLine == null){
                return false;
            }
            
            return simulateFlow(nextConnectedPipeLine, pipeLine);

        }

    }

    public PipeLine getSource(){
        PipeLine aux = head;

        while(aux != null && !aux.isSource()){
            aux = aux.getNext();
        }

        return aux;
    }

    public PipeLine getFirstConnectedPipeLine(PipeLine pipeLine, PipeLine prevPipeLine){
        PipeLine up = pipeLine.getUp();
        PipeLine right = pipeLine.getNext();
        PipeLine down = pipeLine.getDown();
        PipeLine left = pipeLine.getPrev();

        PipeLine nextConnectedPipeLine = null;

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

            if (right != null && (right.isHorizontal() || right.isCircular()) && right != prevPipeLine) {
                nextConnectedPipeLine = right;
            } else if (left != null && (left.isHorizontal() || left.isCircular()) && left != prevPipeLine) {
                nextConnectedPipeLine = left;
            }

        } else if(pipeLine.isVertical()) {

            if (up != null && (up.isVertical() || up.isCircular()) && up != prevPipeLine) {
                nextConnectedPipeLine = up;
            } else if (down != null && (down.isVertical() || down.isCircular()) && down != prevPipeLine) {
                nextConnectedPipeLine = down;
            }

        }

        return nextConnectedPipeLine;
    
    }



}
