package williamboxhall;

import com.google.common.collect.Lists;

import java.util.List;

public class TraversableBinaryTree {
    private final BinaryTree binaryTree;

    public TraversableBinaryTree(BinaryTree binaryTree) {
        this.binaryTree = binaryTree;
    }

    public List<Integer> inOrder() {
        List<Integer> traversal = Lists.newArrayList();
        visitInOrder(binaryTree.root(), traversal);
        return traversal;
    }

    public List<Integer> preOrder() {
        List<Integer> traversal = Lists.newArrayList();
        visitPreOrder(binaryTree.root(), traversal);
        return traversal;
    }

    public List<Integer> postOrder() {
        List<Integer> traversal = Lists.newArrayList();
        visitPostOrder(binaryTree.root(), traversal);
        return traversal;
    }

    private void visitInOrder(BinaryTree.Node node, List<Integer> traversal) {
        if (node == null) {
            return;
        }
        visitInOrder(node.left(), traversal);
        traversal.add(node.value());
        visitInOrder(node.right(), traversal);
    }

    private void visitPreOrder(BinaryTree.Node node, List<Integer> traversal) {
        if (node == null) {
            return;
        }
        traversal.add(node.value());
        visitPreOrder(node.left(), traversal);
        visitPreOrder(node.right(), traversal);
    }

    private void visitPostOrder(BinaryTree.Node node, List<Integer> traversal) {
        if (node == null) {
            return;
        }
        visitPostOrder(node.left(), traversal);
        visitPostOrder(node.right(), traversal);
        traversal.add(node.value());
    }
}
