package model;

public class ScoreTable {
    private NodeST root;

    public ScoreTable() {
    }

    public NodeST getRoot() {
        return root;
    }

    public void setRoot(NodeST newRoot) {
        root = newRoot;
    }

    public void insertPlayer(Player player) {
        if (root == null) {
            root = new NodeST(player);
        } else {
            insertPlayer(root, player);
        }
    }

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

    public void deletePlayer(Player player){
        deletePlayer(root, player);
    }

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

    public String toString() {
        String view = "SCORE TABLE\n"+
                      getPostorder(root);
        return view;
    }

    public String getPostorder(NodeST pointer){
        String msg = "";
        if(pointer != null){
            msg += getPostorder(pointer.getRight());
            msg += pointer.toString()+"\n";
            msg += getPostorder(pointer.getLeft());
        }

        return msg;
    }

    public void updateScorePlayer(Player prevPlayer, int newScore){
        Player temp = new Player(prevPlayer.getNickname(), prevPlayer.getScore());
        temp.addScore(newScore);

        deletePlayer(prevPlayer);
        insertPlayer(temp);
        
    }

    //getSuccessor
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
