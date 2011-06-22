package williamboxhall;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TraversableBinaryTreeTest {
    private TraversableBinaryTree tree;

    @Before
    public void createTree() {
        // TODO preferable this would be a balanced tree - manual balance for now
        BinaryTree binaryTree = new BinaryTree(50);
        binaryTree.insert(25);
        binaryTree.insert(75);
        binaryTree.insert(15);
        binaryTree.insert(35);
        binaryTree.insert(65);
        binaryTree.insert(85);

        tree = new TraversableBinaryTree(binaryTree);
    }

    @Test
    public void canTraversePreOrder() {
        assertThat(tree.preOrder().toString(), is("[50, 25, 15, 35, 75, 65, 85]"));
    }

    @Test
    public void canTraverseInOrder() {
        assertThat(tree.inOrder().toString(), is("[15, 25, 35, 50, 65, 75, 85]"));
    }

    @Test
    public void canTraversePostOrder() {
        assertThat(tree.postOrder().toString(), is("[15, 35, 25, 65, 85, 75, 50]"));
    }
}
