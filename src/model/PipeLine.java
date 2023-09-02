package model;

public class PipeLine {
    private PipeLineType type;
    private String image;

    private PipeLine next;
    private PipeLine prev;

    public PipeLine(PipeLineType type) {
        this.type = type;

        switch(type){
            case HORIZONTAL:
                image = "=";
                break;

            case VERTICAL:
                image = "||";
                break;

            case CIRCULAR:
                image = "o";
                break;
            
            case VOID:
                image = "X";
                break;
            
            case SOURCE:
                image = "F";
                break;

            case DRAINAGE:
                image = "D";
                break;
        }
    }

    public PipeLineType getType() {
        return type;
    }

    public void setType(PipeLineType type) {
        this.type = type;
    }

    public PipeLine getNext() {
        return next;
    }

    public void setNext(PipeLine next) {
        this.next = next;
    }

    public PipeLine getPrev() {
        return prev;
    }

    public void setPrev(PipeLine prev) {
        this.prev = prev;
    }

    public boolean isSource() {
        return type == PipeLineType.SOURCE;
    }

    public boolean isDrainage() {
        return type == PipeLineType.DRAINAGE;
    }

    public boolean isVoid() {
        return type == PipeLineType.VOID;
    }

    public boolean isHorizontal() {
        return type == PipeLineType.HORIZONTAL;
    }

    public boolean isVertical() {
        return type == PipeLineType.VERTICAL;
    }

    public boolean isCircular() {
        return type == PipeLineType.CIRCULAR;
    }

    @Override
    public String toString() {
        return image;
    }
}
