package williamboxhall;

import sun.reflect.generics.tree.Tree;

public class BinaryTree implements Tree {
    private Node root;

    public BinaryTree(int rootValue) {
        this.root = new Node(rootValue);
    }

    public void insert(int value) {
        addChildTo(root, value);
    }

    public Node addChildTo(Node node, int value) {
        return node.value > value ? addLeft(node, value) : addRight(node, value);
    }

    private Node addLeft(Node node, int value) {
        return node.left == null ? createLeft(node, value) : addChildTo(node.left, value);
    }

    private Node addRight(Node node, int value) {
        return node.right == null ? createRight(node, value) : addChildTo(node.right, value);
    }

    private Node createRight(Node node, int value) {
        node.right = new Node(value);
        return node.right;
    }

    private Node createLeft(Node node, int value) {
        node.left = new Node(value);
        return node.left;
    }

    public void delete(int value) {
    }

    public Node find(int value) {
        return findFrom(root, value);
    }

    private Node findFrom(Node node, int value) {
        if (node == null) {
            return null;
        }
        if (node.value == value) {
            return node;
        }
        return findFrom(traverse(node, value), value);
    }

    private Node traverse(Node node, int value) {
        return (node.value > value) ? node.left : node.right;
    }

    public class Node {
        private final int value;
        private Node left;
        private Node right;

        private Node(int value) {
            this.value = value;
        }

        public int value() {
            return value;
        }

        public Node left() {
            return left;
        }

        public Node right() {
            return right;
        }
    }
}
