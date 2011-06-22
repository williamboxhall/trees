package williamboxhall;

public class AVLTree extends BinaryTree {
    public AVLTree(int rootValue) {
        super(rootValue);
    }

    @Override
    Node createNode(int value) {
        return new BalancedNode(value);
    }

    @Override
    Node createNode(Node parent, int value) {
        return new BalancedNode(parent, value);
    }

    @Override
    Node addLeft(Node node, int value) {
        balanceLeft(node);
        return super.addLeft(node, value);
    }

    @Override
    Node addRight(Node node, int value) {
        balanceRight(node);
        return super.addRight(node, value);
    }

    private void balanceLeft(Node node) {
        ((BalancedNode) node).balanceFactor++;
    }

    private void balanceRight(Node node) {
        ((BalancedNode) node).balanceFactor--;
    }

    static class BalancedNode extends Node {
        int balanceFactor = 0;

        public BalancedNode(int value) {
            super(value);
        }

        public BalancedNode(Node parent, int value) {
            super(parent, value);
        }

        public int balanceFactor() {
            return balanceFactor;
        }
    }
}
