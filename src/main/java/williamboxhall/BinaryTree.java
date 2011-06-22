package williamboxhall;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import sun.reflect.generics.tree.Tree;

import java.util.List;

public class BinaryTree implements Tree {
    Node root;

    public BinaryTree(int rootValue) {
        this.root = createNode(rootValue);
    }

    public Node root() {
        return root;
    }

    public Node find(int value) {
        return findFrom(root, value);
    }

    public Node insert(int value) {
        return addChildTo(root, value);
    }

    public Node delete(int value) {
        Node deleted = find(value);
        delete(deleted);
        return deleted;
    }

    private Node findFrom(Node node, int value) {
        return (node == null || node.value == value) ? node : findFrom(traverse(node, value), value);
    }

    private Node traverse(Node node, int value) {
        return (node.value > value) ? node.left : node.right;
    }

    private Node addChildTo(Node node, int value) {
        return (node.value > value) ? addLeft(node, value) : addRight(node, value);
    }

    Node addLeft(Node node, int value) {
        return (node.left == null) ? createLeft(node, value) : addChildTo(node.left, value);
    }

    Node addRight(Node node, int value) {
        return (node.right == null) ? createRight(node, value) : addChildTo(node.right, value);
    }

    Node createRight(Node parent, int value) {
        parent.right = createNode(parent, value);
        return parent.right;
    }

    Node createLeft(Node parent, int value) {
        parent.left = createNode(parent, value);
        return parent.left;
    }

    private void delete(Node node) {
        if (node == root) {
            deleteRoot();
        } else if (node == node.parent.left) {
            deleteLeft(node.parent);
        } else {
            deleteRight(node.parent);
        }
    }

    private void deleteRoot() {
        root = replaceWithChildOf(root);
    }

    private void deleteLeft(Node parent) {
        parent.left = replaceWithChildOf(parent.left);
    }

    private void deleteRight(Node parent) {
        parent.right = replaceWithChildOf(parent.right);
    }

    private Node replaceWithChildOf(Node deleted) {
        Node replacement = replacementFor(deleted);
        return hasTwoChildren(deleted) ? swapValuesThenDeleteSecond(deleted, replacement) : replacement;
    }

    private Node replacementFor(Node deleted) {
        return (deleted.left != null) ? deleted.left : deleted.right;
    }

    private boolean hasTwoChildren(Node node) {
        return node.left != null && node.right != null;
    }

    private Node swapValuesThenDeleteSecond(Node first, Node second) {
        swapValues(first, second);
        delete(second);
        return first;
    }

    private void swapValues(Node first, Node second) {
        int firstValue = first.value;
        first.value = second.value;
        second.value = firstValue;
    }

    Node createNode(int rootValue) {
        return new Node(rootValue);
    }

    Node createNode(Node parent, int value) {
        return new Node(parent, value);
    }

    static class Node {
        private int value;
        private Node left;
        private Node right;
        Node parent;

        Node(int value) {
            this(null, value);
        }

        Node(Node parent, int value) {
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

    @Override
    public String toString() {
        List<Node> top = Lists.newArrayList(root);
        List<Node> second = Lists.newArrayList(root.left, root.right);
        List<Node> thirdLeft = root.left == null ? Lists.<Node>newArrayList(null,
                null) : Lists.newArrayList(root.left.left, root.left.right);
        List<Node> thirdRight = root.right == null ? Lists.<Node>newArrayList(null,
                null) : Lists.newArrayList(root.right.left, root.right.right);

        return print(top, 16) + "\n" + print(second, 8) + "\n" + print(thirdLeft, 4) + print(thirdRight,
                4) + "\n" + "-----";
    }

    private String print(List<Node> nodes, int padLength) {
        String padding = paddingOfLength(padLength);
        StringBuffer result = new StringBuffer();
        for (Node node : nodes) {
            result.append(padding).append(node == null ? "" : node.value).append(padding);
        }
        return result.toString();
    }

    private String paddingOfLength(int padLength) {
        return Strings.repeat(" ", padLength);
    }
}
