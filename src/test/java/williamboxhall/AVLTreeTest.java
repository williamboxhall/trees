package williamboxhall;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Test;
import org.junit.internal.matchers.TypeSafeMatcher;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static williamboxhall.BinaryTreeTest.nodeWithValue;

public class AVLTreeTest {
    @Test
    public void isABinaryTree() {
        assertThat(BinaryTree.class.isAssignableFrom(AVLTree.class), is(true));
    }

    @Test
    public void nodeWithNoChildrenHasBalanceFactorOfZero() {
        AVLTree tree = new AVLTree(50);
        assertThat(tree.find(50), hasBalanceFactor(0));
    }

    @Test
    public void nodeWithOneLeftChildHasBalanceFactorOfOne() {
        AVLTree tree = new AVLTree(50);
        tree.insert(25);
        assertThat(tree.find(50), hasBalanceFactor(1));
    }

    @Test
    public void nodeWithOneRightChildHasBalanceFactorOfNegativeOne() {
        AVLTree tree = new AVLTree(50);
        tree.insert(75);
        assertThat(tree.find(50), hasBalanceFactor(-1));
    }

    @Test
    public void nodeWithRightChildAndGrandchildHasBalanceFactorOfNegativeTwo() {
        AVLTree tree = new AVLTree(50);
        tree.insert(75);
        tree.insert(85);
        assertThat(tree.find(50), hasBalanceFactor(-2));
        assertThat(tree.find(75), hasBalanceFactor(-1));
        assertThat(tree.find(85), hasBalanceFactor(0));
    }

    @Test
    public void nodeWithTwoChildrenAndNoGrandChildrenHasBalanceFactorOfZero() {
        AVLTree tree = new AVLTree(50);
        tree.insert(25);
        tree.insert(75);
        assertThat(tree.find(50), hasBalanceFactor(0));
    }

    @Test
    public void rightRightUnbalanceFromRootWillChangeRoot() {
        AVLTree tree = new AVLTree(50);
        tree.insert(75);
        assertThat(tree.root(), is(nodeWithValue(50)));
        tree.insert(85);
        assertThat(tree.root(), is(nodeWithValue(75)));
        assertThat(tree.root().left(), is(nodeWithValue(50)));
        assertThat(tree.root().right(), is(nodeWithValue(85)));
    }

    @Test
    public void rightLeftUnbalanceFromRootWillChangeRoot() {
        AVLTree tree = new AVLTree(50);
        tree.insert(75);
        assertThat(tree.root(), is(nodeWithValue(50)));
        tree.insert(65);
        assertThat(tree.root(), is(nodeWithValue(65)));
        assertThat(tree.root().left(), is(nodeWithValue(50)));
        assertThat(tree.root().right(), is(nodeWithValue(75)));
    }

    @Test
    public void insertionThatUnbalancesDescendantOfRootWithChangeDescendant() {
    }

    private Matcher<BinaryTree.Node> hasBalanceFactor(final int expected) {
        return new TypeSafeMatcher<BinaryTree.Node>() {
            private int actual;

            @Override
            public boolean matchesSafely(BinaryTree.Node node) {
                actual = ((AVLTree.BalancedNode) node).balanceFactor();
                return actual == expected;
            }

            public void describeTo(Description description) {
                description.appendText(String.format("node with balance factor %d but got %d", expected, actual));
            }
        };
    }
}
