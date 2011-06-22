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
    public void exposesRootNode() {
        BinaryTree tree = new BinaryTree(50);
        assertThat(tree.root(), is(nodeWithValue(50)));
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

    @Test
    public void canDeleteNodeWithOneChildWithNoGrandchildren() {
        BinaryTree tree = new BinaryTree(50);
        tree.insert(75);
        tree.insert(85);
        assertThat(tree.find(75), is(nodeWithValue(75)));
        assertThat(tree.root().right(), is(nodeWithValue(75)));
        assertThat(tree.find(75).right(), is(nodeWithValue(85)));
        tree.delete(75);
        assertThat(tree.find(75), is(nullValue()));
        assertThat(tree.root().right(), is(nodeWithValue(85)));
    }

    @Test
    public void canDeleteNodeWithOneChildWithGrandChildren() {
        BinaryTree tree = new BinaryTree(50);
        tree.insert(75);
        tree.insert(85);
        tree.insert(90);
        System.out.println(tree);
        assertThat(tree.find(75), is(nodeWithValue(75)));
        assertThat(tree.root().right(), is(nodeWithValue(75)));
        assertThat(tree.find(75).right(), is(nodeWithValue(85)));
        assertThat(tree.find(85).right(), is(nodeWithValue(90)));
        tree.delete(75);
        System.out.println(tree);
        assertThat(tree.find(75), is(nullValue()));
        assertThat(tree.root().right(), is(nodeWithValue(85)));
        assertThat(tree.find(85).right(), is(nodeWithValue(90)));
    }

    @Test
    public void canDeleteNodeWithTwoChildren() {
        BinaryTree tree = new BinaryTree(50);
        tree.insert(75);
        tree.insert(65);
        tree.insert(85);
        System.out.println(tree);
        assertThat(tree.find(75), is(nodeWithValue(75)));
        assertThat(tree.root().right(), is(nodeWithValue(75)));
        assertThat(tree.find(75).left(), is(nodeWithValue(65)));
        assertThat(tree.find(75).right(), is(nodeWithValue(85)));
        tree.delete(75);
        System.out.println(tree);
        assertThat(tree.root().right(), is(nodeWithValue(65)));
        assertThat(tree.find(65).right(), is(nodeWithValue(85)));
    }

    @Test
    public void canDeleteRootNode() {
        BinaryTree tree = new BinaryTree(50);
        tree.insert(75);
        System.out.println(tree);
        assertThat(tree.find(50), is(nodeWithValue(50)));
        assertThat(tree.root(), is(nodeWithValue(50)));
        tree.delete(50);
        System.out.println(tree);
        assertThat(tree.find(50), is(nullValue()));
        assertThat(tree.root(), is(nodeWithValue(75)));
    }

    @Test
    public void hasUsefulToString() {
        BinaryTree tree = new BinaryTree(50);
        tree.insert(25);
        tree.insert(75);
        tree.insert(15);
        tree.insert(35);
        tree.insert(65);
        tree.insert(85);

        StringBuffer expected = new StringBuffer();
        expected.append("                [50]                \n");
        expected.append("        [25]                [75]        \n");
        expected.append("    [15]        [35]    [65]        [85]    \n");

        System.out.println(tree);
        assertThat(tree.toString(), is(expected.toString()));
    }

    private Matcher<BinaryTree.Node> nodeWithValue(final int expected) {
        return new TypeSafeMatcher<BinaryTree.Node>() {
            public BinaryTree.Node actual;

            @Override
            public boolean matchesSafely(BinaryTree.Node actual) {
                this.actual = actual;
                return actual != null && actual.value() == expected;
            }

            public void describeTo(Description description) {
                description.appendText(format("node with value %d but found %d", expected,
                        actual == null ? null : actual.value()));
            }
        };
    }
}
