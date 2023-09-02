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

    // Sin comprobar
    public void insertPlayer(Player player) {
        NodeST newNode = new NodeST(player);

        if (root == null) {
            root = newNode;
        } else {
            NodeST aux = root;
            NodeST father;
            while (true) {
                father = aux;
                if (player.getScore() < aux.getPlayer().getScore()) {
                    aux = aux.getLeft();
                    if (aux == null) {
                        father.setLeft(newNode);
                        return;
                    }
                } else {
                    aux = aux.getRight();
                    if (aux == null) {
                        father.setRight(newNode);
                        return;
                    }
                }
            }
        }
    }


}
