package williamboxhall;

import sun.reflect.generics.tree.Tree;

public class BinaryTree implements Tree {
    private Node root;

    public BinaryTree(int rootValue) {
        this.root = new Node(rootValue);
    }
    

    public void insert(int value) {
        root.addChild(value);
        System.out.println(this);
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

        public void addChild(int value) {
            if (this.value > value) {
                addLeft(value);
            } else {
                addRight(value);
            }
        }

        private void addLeft(int value) {
            if (left == null) {
                left = new Node(value);
            } else {
                left.addChild(value);
            }
        }

        private void addRight(int value) {
            if (right == null) {
                right = new Node(value);
            } else {
                right.addChild(value);
            }
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
