package model;

/**
 * The NodeST class represents a node used in the binary search tree data structure which is used in the game's
 * score table. Each node contains a reference to a player and links to the left and rigth child nodes.
 */

public class NodeST{

    // Left child node
    private NodeST left;
    // Right child node
    private NodeST right;
    // Player associated with the node
    private Player player;

    /**
     * Create a new node with an associated player
     * @param player The player associated with the node
     */
    public NodeST(Player player) {
        this.player = player;
    }

    /**
     * Gets the left child node
     * @return The left child node
     */
    public NodeST getLeft() {
        return left;
    }

    /**
     * Sets the left child node
     * @param left The left child node
     */
    public void setLeft(NodeST left) {
        this.left = left;
    }

    /**
     * Gets the right child node
     * @return The right child node
     */
     public NodeST getRight() {
        return right;
    }

    /**
     * Sets the right child node
     * @param right The new right child node
     */
    public void setRight(NodeST right) {
        this.right = right;
    }

    /**
     * Gets the player associated with the node
     * @return The player associated with the node
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Sets the player associated with the node
     * @param newPlayer The new player associated with the node
     */
    public void setPlayer(Player newPlayer) {
        player = newPlayer;
    }

    /**
     * Returns a text string representation of the player associated with the node
     * @return The text string representation of the player
     */
    @Override
    public String toString() {
        return player.toString();
    }

    /**
     * Compare the current node with another node based on players' score
     * @param node The node to compare with
     * @return A negative value if the current node has a lower score, or a positive value if it 
     * has a higher score, or zero if they have the same score
     */
    public int compareTo(NodeST node){
        return player.getScore() - node.getPlayer().getScore();        
    }

    /**
     * Compares the current node to a player based on scores and usernames
     * @param player The player to compare against
     * @return A negative value if the current node score is lower,a positive value if higher, or 
     * zero if the scores are the same and the usernames are the same 
     */
    public int compareTo(Player player){
        int result = this.player.getScore() - player.getScore();
        if(result == 0){
            result = this.player.getNickname().compareTo(player.getNickname());
        }

        return result;        
    }

}
