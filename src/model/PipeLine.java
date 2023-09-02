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

    public PipeLine getUp() {
        PipeLine aux = this;
        boolean hasPrev = true;

        for (int i = 0; i < 8 && hasPrev; i++) {
            if (aux.getPrev() != null) {
                aux = aux.getPrev();
            } else {
                hasPrev = false;
                aux = null;
            }
        }

        return aux;
    }

    public PipeLine getDown() {
        PipeLine aux = this;
        boolean hasNext = true;

        for (int i = 0; i < 8 && hasNext; i++) {
            if (aux.getNext() != null) {
                aux = aux.getNext();
            } else {
                hasNext = false;
                aux = null;
            }
        }

        return aux;
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
