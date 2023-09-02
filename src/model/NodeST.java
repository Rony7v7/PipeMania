package model;

public class NodeST {

    private NodeST left;
    private NodeST right;

    private Player player;

    public NodeST(Player player) {
        this.player = player;
    }

    public NodeST getLeft() {
        return left;
    }

    public void setLeft(NodeST left) {
        this.left = left;
    }

    public NodeST getRight() {
        return right;
    }

    public void setRight(NodeST right) {
        this.right = right;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player newPlayer) {
        player = newPlayer;
    }

    @Override
    public String toString() {
        return "NodeST{" + "left=" + left + ", right=" + right + ", player=" + player + '}';
    }

}
