import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.BiFunction;

public class GroupNode<T> implements TreeNode<T> {
	private T data;
	private List<TreeNode<T>> children = new ArrayList<>();

	// constructor initializing with given data
	public GroupNode(T data) {
		this.data = data;
	}

	// adds child node to children list returning this allowing chaining
	@Override
	public TreeNode<T> addChild(TreeNode<T> child) {
		children.add(child);
		return this;
	}

	// remove a child from children list returning true if successful and false
	// otherwise
	@Override
	public boolean removeChild(TreeNode<T> child) {
		return children.remove(child);
	}

	// returns list of child nodes
	@Override
	public List<TreeNode<T>> getChildren() {
		return children;
	}

	// prints node's data and recursive call print on all children
	@Override
	public void print() {
		System.out.println(data.toString());
		for (TreeNode<T> child : children) {
			child.print();
		}
	}

	// constructs a list containing the data of this node and all descendants
	// starting with parent
	@Override
	public List<T> toList() {
		List<T> list = new ArrayList<>();
		list.add(data);
		for (TreeNode<T> child : children) {
			list.addAll(child.toList());
		}
		return list;
	}

	// implements map method. transforms this node's and all descendants data
	// from type T to R returning new tree of transformed nodes.
	@Override
	public <R> TreeNode<R> map(Function<T, R> transform) {
		GroupNode<R> newNode = new GroupNode<>(transform.apply(data));
		for (TreeNode<T> child : children) {
			newNode.addChild(child.map(transform));
		}
		return newNode;
	}

	// combines the data of this node and all descendants into a single value
	// starting with initial value
	@Override
	public T reduce(T initialValue, BiFunction<T, T, T> combiner) {
		T result = data;
		for (TreeNode<T> child : children) {
			result = child.reduce(result, combiner);
		}
		return combiner.apply(initialValue, result);
	}

	// returns node's data
	public T getData() {
		return data;
	}
}