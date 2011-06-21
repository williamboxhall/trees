package williamboxhall;

import sun.reflect.generics.tree.Tree;

public class BinaryTree implements Tree {
    private Node root;

    public BinaryTree(int rootValue) {
        this.root = new Node(rootValue);
    }

    public void insert(int value) {
        root.addChild(value);
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

        public Node addChild(int value) {
            return this.value > value ? addLeft(value) : addRight(value);
        }

        private Node addLeft(int value) {
            return left == null ? createLeft(value) : left.addChild(value);
        }

        private Node addRight(int value) {
            return right == null ? createRight(value) : right.addChild(value);
        }

        private Node createRight(int value) {
            this.right = new Node(value);
            return right;
        }

        private Node createLeft(int value) {
            this.left = new Node(value);
            return left;
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
