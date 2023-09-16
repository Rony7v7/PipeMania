package model;

/**
 * The PipeLine class represents a pipe in the game Pipe Mania. Each pipe has a specific type that determines
 * its shape and function in the game. Additionally, it contains references to adjacent pipes and methods for obtaining information 
 * about the pipe
 */

public class PipeLine {
    // Type of pipe
    private PipeLineType type;
    // The graphic representation of the pipes
    private String image;

    static String colorRojo= "\u001B[31m";
    static String resetColor = "\u001B[0m";
    static String colorAzul="\u001B[36m";
    static String colorVerde="\u001B[32m";
    static String colorAmarillo="\u001B[33m";
    static String colorRGBgris="\u001B[38;5;8m";

    // Next adjacent pipe
    private PipeLine next;
    // Previous adjacent pipe
    private PipeLine prev;

    /**
     * Creates a nre PipeLine instance with the sepecified pipe type
     * @param type Type of pipe
     */
    public PipeLine(PipeLineType type) {
        this.type = type;
        setImage(type);
    }

    /**
     * Get the type of pipe
     * @return Type of pipe
     */
    public PipeLineType getType() {
        return type;
    }

    /**
     * Sets the type of the pipe and updates its graphical representation
     * @param type The new type of pipe
     */
    public void setType(PipeLineType type) {
        this.type = type;
        setImage(type);
    }

    /**
     * Sets the graphical representation of the pipe based on its type
     * @param type Type of the pipe
     */
    public void setImage(PipeLineType type) {
        switch(type){
            case HORIZONTAL:
                image = colorAmarillo+"="+resetColor;
                break;

            case VERTICAL:
                image = colorRojo+"\u2551"+resetColor;
                break;

            case CIRCULAR:
                image =colorVerde+"o"+resetColor;
                break;
            
            case VOID:
                image = colorRGBgris+"X"+resetColor;
                break;
            
            case SOURCE:
                image = colorAzul+"F"+resetColor;
                break;

            case DRAINAGE:
                image = colorRojo+"D"+resetColor;
                break;
        }
    }

    /**
     * Gets the next adjacent pipe
     * @return The next adjacent pipe
     */
    public PipeLine getNext() {
        return next;
    }

    /**
     * Sets the next adjacent pipe
     * @param next The next adjacent pipe
     */
    public void setNext(PipeLine next) {
        this.next = next;
    }

    /**
     * Get the previous adjacent pipe
     * @return The previous adjacent pipe
     */
    public PipeLine getPrev() {
        return prev;
    }

    /**
     * Sets the previous adjacent pipe
     * @param prev The previous adjacent pipe
     */
    public void setPrev(PipeLine prev) {
        this.prev = prev;
    }

    /**
     * Gets the pipe that is "above" the current pipe following a sequence of connections
     * @return The top pipe
     */
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

    /**
     * Gets the pipe that is "below" the current pipe following a sequence of connections
     * @return The bottom pipe
     */
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

    /**
     * Checks if the pipe is of type SOURCE type
     * @return 'true' if this is a source pipe, 'false' otherwise
     */
    public boolean isSource() {
        return type == PipeLineType.SOURCE;
    }

    /**
     * c
     * @return true' if this is a drain pipe, 'false' otherwise
     */
    public boolean isDrainage() {
        return type == PipeLineType.DRAINAGE;
    }

    /**
     * Checks if the pipe is VOID type
     * @return true' if this is a void pipe, 'false' otherwise
     */
    public boolean isVoid() {
        return type == PipeLineType.VOID;
    }

    /**
     * Checks if the pipe is HORIZONTAL type
     * @return true' if this is a horizontal pipe, 'false' otherwise
     */
    public boolean isHorizontal() {
        return type == PipeLineType.HORIZONTAL;
    }

    /**
     * Checks if the pipe is VERTICAL type
     * @return true' if this is a vertical pipe, 'false' otherwise
     */
    public boolean isVertical() {
        return type == PipeLineType.VERTICAL;
    }

    /**
     * Checks if the pipe is CIRCULAR type
     * @return true' if this is a circular pipe, 'false' otherwise
     */
    public boolean isCircular() {
        return type == PipeLineType.CIRCULAR;
    }

    /**
     * Gets a text string representation of the pipe
     * (Its graphical representation)
     * @return The text string representation of the pipe
     */
    @Override
    public String toString() {
        return image;
    }
}
