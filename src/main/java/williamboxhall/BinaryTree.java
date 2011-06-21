package williamboxhall;

import sun.reflect.generics.tree.Tree;

public class BinaryTree implements Tree {
    private Node root;

    public BinaryTree(int rootValue) {
        this.root = new Node(rootValue);
    }

    public Node find(int value) {
        return findFrom(root, value);
    }

    public Node insert(int value) {
        return addChildTo(root, value);
    }

    public Node delete(int value) {
        return delete(find(value));
    }

    private Node findFrom(Node node, int value) {
        return (node == null || node.value == value) ? node : findFrom(traverse(node, value), value);
    }

    private Node traverse(Node node, int value) {
        return (node.value > value) ? node.left : node.right;
    }

    public Node addChildTo(Node node, int value) {
        return (node.value > value) ? addLeft(node, value) : addRight(node, value);
    }

    private Node addLeft(Node node, int value) {
        return (node.left == null) ? createLeft(node, value) : addChildTo(node.left, value);
    }

    private Node addRight(Node node, int value) {
        return (node.right == null) ? createRight(node, value) : addChildTo(node.right, value);
    }

    private Node createRight(Node parent, int value) {
        parent.right = new Node(parent, value);
        return parent.right;
    }

    private Node createLeft(Node parent, int value) {
        parent.left = new Node(parent, value);
        return parent.left;
    }

    private Node delete(Node node) {
        return (node == node.parent.left) ? deleteLeft(node.parent) : deleteRight(node.parent);
    }

    private Node deleteLeft(Node parent) {
        Node node = parent.left;
        parent.left = null;
        return node;
    }

    private Node deleteRight(Node parent) {
        Node node = parent.right;
        parent.right = null;
        return node;
    }

    public class Node {
        private final int value;
        private Node left;
        private Node right;
        private Node parent;

        private Node(int value) {
            this(null, value);
        }

        private Node(Node parent, int value) {
            this.parent = parent;
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
