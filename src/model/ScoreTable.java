package model;

/**
 * The ScoreTable class represents a score table for players. This class implements a binary seaarch
 * tree structure to store and manage players according to their scores
 */
public class ScoreTable {
    //Score tree root node
    private NodeST root;

    /**
     * Creates a new ScoreTsble instance without any initial players
     */
    public ScoreTable() {
    }

   /**
     * Gets the root node of the scores tree
     * @return The root node of the tree
     */
     public NodeST getRoot() {
        
        return root;
    }

    /**
     * Sets the root node of the scores tree
     * @param newRoot The new root node
     */
    public void setRoot(NodeST newRoot) {
        root = newRoot;
    }

    /**
     * Insert a player into the scoreboard
     * @param player The player to be inserted
     */
    public void insertPlayer(Player player) {
        if (root == null) {
            root = new NodeST(player);
        } else {
            insertPlayer(root, player);
        }
    }

   /**
     * Private method to insert a player recursively into the tree
     * @param pointer The current node in the insertion process
     * @param player The player to be inserted
     */
     private void insertPlayer(NodeST pointer, Player player) {
        if (pointer.getPlayer().getScore() <= player.getScore()) {
            if (pointer.getRight() == null) {
                pointer.setRight(new NodeST(player));
            } else {
                insertPlayer(pointer.getRight(), player);
            }
        } else {
            if (pointer.getLeft() == null) {
                pointer.setLeft(new NodeST(player));
            } else {
                insertPlayer(pointer.getLeft(), player);
            }
        }
    }

    /**
     * Remove a player from the scoreboard
     * @param player The player to be eliminated
     */
    public void deletePlayer(Player player){
        deletePlayer(root, player);
    }

    /**
     * Private method to recursively delete a player in the tree
     * @param pointer The current node in the deletion process
     * @param player The player to be eliminated
     * @return The modified node after deletion
     */
    private NodeST deletePlayer(NodeST pointer, Player player){
        if(pointer == null){
            return null;
        } else if(pointer.compareTo(player) > 0){
            pointer.setLeft(deletePlayer(pointer.getLeft(), player));
        } else if(pointer.compareTo(player) < 0){
            pointer.setRight(deletePlayer(pointer.getRight(), player));
        } else {
            if(pointer.getLeft() == null && pointer.getRight() == null){
                pointer = null;
            } else if(pointer.getLeft() == null){
                pointer = pointer.getRight();
            } else if(pointer.getRight() == null){
                pointer = pointer.getLeft();
            } else {
                NodeST successor = getSuccessor(pointer.getRight());
                pointer.setPlayer(successor.getPlayer());
            }
        }

        return pointer;
    }

    /**
     * Convert the scoreboard to a text string
     * @return The representation of the scores table in the form of a text string
     */
    public String toString() {
        String view = getPostorder(root);
        return view;
    }

    /**
     * Gets a postorder representation of the score tree
     * @param pointer The current node in the process of obtaining the traversal in subsequent order
     * @return A text string with the subsequent order traversal of the tree
     */
    public String getPostorder(NodeST pointer){
        String msg = "";
        if(pointer != null){
            msg += getPostorder(pointer.getRight());
            msg += pointer.toString()+"\n";
            msg += getPostorder(pointer.getLeft());
        }

        return msg;
    }

    /**
     * Updates the score of a given player
     * @param prevPlayer The player whose score is to be updated
     * @param newScore The players new score
     */
    public void updateScorePlayer(Player prevPlayer, int newScore){
        Player temp = new Player(prevPlayer.getNickname(), prevPlayer.getScore());
        temp.addScore(newScore);

        deletePlayer(prevPlayer);
        insertPlayer(temp);
        
    }

    /**
     * Gets the successor of a given node in the tree
     * @param pointer The node for which the successor is to be found 
     * @return The successor of the given node
     */
    private NodeST getSuccessor(NodeST pointer){
        NodeST successor = null;
        NodeST successorParent = null;
        NodeST current = pointer;

        while(current != null){
            successorParent = successor;
            successor = current;
            current = current.getLeft();
        }

        if(successor != pointer.getRight()){
            successorParent.setLeft(successor.getRight());
            successor.setRight(pointer.getRight());
        }

        return successor;
    }

}
