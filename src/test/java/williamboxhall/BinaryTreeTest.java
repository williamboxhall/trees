package williamboxhall;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Test;
import org.junit.internal.matchers.TypeSafeMatcher;

import static java.lang.String.format;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class BinaryTreeTest {
    @Test
    public void canFindRootNode() {
        BinaryTree tree = new BinaryTree(50);
        assertThat(tree.find(50), is(nodeWithValue(50)));
        assertThat(tree.find(25), is(nullValue()));
    }

    @Test
    public void canInsertNodesBeneathRoot() {
        BinaryTree tree = new BinaryTree(50);
        tree.insert(25);
        assertThat(tree.find(25), is(nodeWithValue(25)));
    }

    @Test
    public void leftChildOfRootIsSmaller() {
        BinaryTree tree = new BinaryTree(50);
        tree.insert(25);
        assertThat(tree.find(50).left(), is(nodeWithValue(25)));
        assertThat(tree.find(50).right(), is(nullValue()));
    }

    @Test
    public void leftChildOfAnyNodeIsSmaller() {
        BinaryTree tree = new BinaryTree(50);
        tree.insert(25);
        tree.insert(15);
        assertThat(tree.find(25).left(), is(nodeWithValue(15)));
        assertThat(tree.find(25).right(), is(nullValue()));
    }

    @Test
    public void rightChildOfRootIsLarger() {
        BinaryTree tree = new BinaryTree(50);
        tree.insert(75);
        assertThat(tree.find(50).left(), is(nullValue()));
        assertThat(tree.find(50).right(), is(nodeWithValue(75)));
    }

    @Test
    public void rightChildOfAnyRootIsLarger() {
        BinaryTree tree = new BinaryTree(50);
        tree.insert(75);
        tree.insert(85);
        assertThat(tree.find(75).left(), is(nullValue()));
        assertThat(tree.find(75).right(), is(nodeWithValue(85)));
    }
    
    @Test
    public void canDeleteLeafNodes() {
        BinaryTree tree = new BinaryTree(50);
        tree.insert(75);
        assertThat(tree.find(75), is(nodeWithValue(75)));
        tree.delete(75);
        assertThat(tree.find(75), is(nullValue()));
    }

    private Matcher<BinaryTree.Node> nodeWithValue(final int value) {
        return new TypeSafeMatcher<BinaryTree.Node>() {
            @Override
            public boolean matchesSafely(BinaryTree.Node node) {
                return node != null && node.value() == value;
            }

            public void describeTo(Description description) {
                description.appendText(format("expected node with value %d", value));
            }
        };
    }
}
