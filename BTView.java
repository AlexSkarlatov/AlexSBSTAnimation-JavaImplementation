package hw4;

/*
 * name: Alexander Skarlatov
 * Date: 3/19/18
 * description:
 * this program is a specialized pane for displaying the Binary search tree data structure in javafx
 * the set shaded nodes data structure and the display tree method work together to display the tree and
 * determine if a node needs to be set to orange or white
 */
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class BTView extends Pane {

	private BST<Integer> tree = new BST<>();
	private double radius = 15; // Tree node radius
	private double vGap = 50; // Gap between two levels in a tree

	//shaded nodes data field
	//should create method called setShadedNodesList(ArrayList path)
	/*
	 * this.setshadedNodes = path
	 */
	private java.util.ArrayList<BST.TreeNode<Integer>> setShadedNodes = new java.util.ArrayList<>();

	public java.util.ArrayList<BST.TreeNode<Integer>> getShadedNodes()
	{
		return this.setShadedNodes;
	}
	public void clearShadedNodes()
	{
		this.setShadedNodes.clear();
	}
	public void setShadedNodesList(java.util.ArrayList<BST.TreeNode<Integer>> path )
	{
		this.setShadedNodes = path;
	}


	BTView(BST<Integer> tree) {
		this.tree = tree;
		setStatus("Tree is empty");
	}

	public void setStatus(String msg) {
		this.getChildren().add(new Text(20, 20, msg));
	}


	public void displayTree() {
		this.getChildren().clear(); // Clear the pane

		if (tree.getRoot() != null) {
			// Display tree recursively
			displayTree(tree.getRoot(), getWidth() / 2, vGap, getWidth() / 4);

		}
	}

	/** Display a subtree rooted at position (x, y) */
	private void displayTree(BST.TreeNode<Integer> root, double x, double y, double hGap) {
		if (root.left != null) {
			// Draw a line to the left node
			getChildren().add(new Line(x - hGap, y + vGap, x, y));
			// Draw the left subtree recursively
			displayTree(root.left, x - hGap, y + vGap, hGap / 2);
		}
		if (root.right != null) {
			// Draw a line to the right node
			getChildren().add(new Line(x + hGap, y + vGap, x, y));
			// Draw the right subtree recursively
			displayTree(root.right, x + hGap, y + vGap, hGap / 2);
		}
		// Display a node
		if(setShadedNodes.contains(root))
		{
			//if this array list has the current root then i can conclude that it must be made orange
			Circle circle = new Circle(x, y, radius);
			circle.setFill(Color.ORANGE);
			circle.setStroke(Color.ORANGE);
			this.getChildren().addAll(circle, new Text(x - 4, y + 4, root.element.toString()));
		}
		else
		{
			//else it must be drawn white
			Circle circle = new Circle(x, y, radius);
			circle.setFill(Color.WHITE);
			circle.setStroke(Color.BLACK);
			this.getChildren().addAll(circle, new Text(x - 4, y + 4, root.element.toString()));
		}
	}
}
