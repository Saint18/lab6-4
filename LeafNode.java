import java.util.List;
import java.util.function.Function;
import java.util.function.BiFunction;

public class LeafNode<T> implements TreeNode<T> {
	private T data;

	// constructor initializes with data
	public LeafNode(T data) {
		this.data = data;
	}

	// since leaf nodes cannot have children throw exception
	@Override
	public TreeNode<T> addChild(TreeNode<T> child) {
		throw new UnsupportedOperationException("Leaf node can't add children.");
	}

	// leaf nodes cannot have children return false
	@Override
	public boolean removeChild(TreeNode<T> child) {
		return false;
	}

	// Leaf nodes do not have children return empty list
	@Override
	public List<TreeNode<T>> getChildren() {
		return List.of();
	}

	// prints data in leaf node
	@Override
	public void print() {
		System.out.println(this.data.toString());
	}

	// returns a list of data of leaf node
	@Override
	public List<T> toList() {
		return List.of(data);
	}

	// transforms data from T to R creating new leaf node instance of transformed
	// data
	@Override
	public <R> TreeNode<R> map(Function<T, R> transform) {
		return new LeafNode<>(transform.apply(data));
	}

	// combines initialValue with node's data returning the result
	@Override
	public T reduce(T initialValue, BiFunction<T, T, T> combiner) {
		return combiner.apply(initialValue, data);
	}
}
