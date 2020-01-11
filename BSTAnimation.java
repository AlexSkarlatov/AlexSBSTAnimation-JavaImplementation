package hw4;
/*
 * name: Alexander Skarlatov
 * Date: 3/19/18
 * description:
 * this is the program that will run the application. the program creates a border pane and in it houses two
 * smaller pane, one for the binary search tree and one for the buttons
 * this program contains 6 buttons along with their corresponding listener codes.
 * this program relies upon the BST class and the BTView class
 */


import hw4.BST.TreeNode;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class BSTAnimation extends Application {
	@Override // Override the start method in the Application class
	public void start(Stage primaryStage) {
		BST<Integer> tree = new BST<>(); // Create a tree

		BorderPane pane = new BorderPane();
		BTView view = new BTView(tree); // Create a View
		pane.setCenter(view);

		TextField tfKey = new TextField();
		tfKey.setPrefColumnCount(3);
		tfKey.setAlignment(Pos.BASELINE_RIGHT);
		Button btInsert = new Button("Insert");
		Button btDelete = new Button("Delete");



		Button btLast = new Button("Last");

		//making all of my buttons now
		Button btSearch = new Button("Search");
		Button btInorder = new Button("Inorder");
		Button btPreorder = new Button("Preorder");
		Button btPostorder = new Button("PostOrder");
		Button btBreadthFirst = new Button("Breadth-first");
		Button btHeight = new Button("Height");
		//btSearch,btInorder,btPreorder,btPostorder,btBreadthfirst,btHeight
		HBox hBox = new HBox(5);
		hBox.getChildren().addAll(new Label("Enter a key: "),
				tfKey, btInsert, btDelete,btSearch,btInorder,btPreorder,btPostorder,
				btBreadthFirst,btHeight,btLast
				);
		hBox.setAlignment(Pos.CENTER);
		pane.setBottom(hBox);
		/////my listeners will go here
		btLast.setOnAction(e -> {
		    System.out.println("the last is " + tree.last());
		    view.displayTree();
			view.setStatus("the last is " + tree.last().toString());
		});


		//here is my search listener
		btSearch.setOnAction(e -> {
			//System.out.println("hello search starting");
			int key = Integer.parseInt(tfKey.getText());
			if(tree.search(key))//IF  key is in tree
			{
				view.setShadedNodesList(tree.path(key));
				//BTView.setShadedNodes = tree.path(key);
				//now paint the thing using the view method from btView
				view.displayTree();
				view.setStatus(key + " the key exists inside the tree and here is the path");
			}
			else
			{
				//BTView.setShadedNodes = tree.path(key);

				view.setShadedNodesList(tree.path(key));

				//now paint the thing using the view method from btView
				view.displayTree();
				view.setStatus(key + " the key does not exist inside the tree but here is the path");
			}

		});


		//here is my next piece of listener code
		//the btInorder listerner
		//BTINORDER LISTENER!!!!
		btInorder.setOnAction(e -> {
			//call the required method
			java.util.ArrayList<Integer> inOrderList = tree.inOrderList();
			view.displayTree();
			view.setStatus( "The Inorder Traversal is: " + inOrderList.toString());
			System.out.println("\nhere is the stuff after inorder traversal" );
			for(int i : inOrderList )
			{
				System.out.print(" " + i);
			}
		});

		//here is my newxt listener code
		//listener  FOR PREORDER

		//btPreorder
		btPreorder.setOnAction(e -> {
			//call the required method that will return an ArrayList to our variable
			java.util.ArrayList<Integer> preOrderList = tree.preOrderList();//write code for here
			//update tree
			view.displayTree();
			view.setStatus( "The Preorder Traversal is: " + preOrderList.toString());

			System.out.println("\nhere is the stuff after Preorder traversal" );
			for(int i : preOrderList )
			{
				System.out.print(" " + i);
			}
		});

		//here is my next listener code
		//listener for Postorder
		btPostorder.setOnAction(e -> {
			//call the required method that will return an ArrayList to our variable
			java.util.ArrayList<Integer> postOrderList = tree.postorderList();
			//update tree
			view.displayTree();
			view.setStatus( "The Postorder Traversal is: " + postOrderList.toString());

			System.out.println("\nhere is the stuff after Postorder traversal" );
			for(int i : postOrderList )
			{
				System.out.print(" " + i);
			}
		});

		//here is my next listener code
		//listener for BreadthFirst
		btBreadthFirst.setOnAction(e -> {

			//instantiate ArrayList here to obtain our elements
			java.util.ArrayList<Integer> breadthFirstList = tree.breadthFirstOrderList();

			//update tree
			view.displayTree();
			view.setStatus( "The Breadth-first order Traversal is: " + breadthFirstList.toString());

			System.out.println("\nhere is the stuff after breadth-first traversal" );
			for(int i : breadthFirstList )
			{
				System.out.print(" " + i);
			}
		});

		//listener for height button
		btHeight.setOnAction(e-> {
			int h = tree.height();
			//update tree
			view.displayTree();
			view.setStatus(  "Height = " + h );
		});
		/////////////////////////////
		btInsert.setOnAction(e -> {
			int key = Integer.parseInt(tfKey.getText());
			if (tree.search(key)) { // key is in the tree already
				view.displayTree();
				view.setStatus(key + " is already in the tree");
			}
			else {
				tree.insert(key); // Insert a new key
				view.displayTree();
				view.setStatus(key + " is inserted in the tree");
			}
		});

		btDelete.setOnAction(e -> {

			//TODO CLEAR ALL ORANGE
			int key = Integer.parseInt(tfKey.getText());
			if (!tree.search(key)) { // key is not in the tree

				view.displayTree();
				view.setStatus(key + " is not in the tree");
			}
			else
			{
				java.util.ArrayList<BST.TreeNode<Integer>> sn = view.getShadedNodes();
				for(BST.TreeNode<Integer> i : sn){
					Integer valueInNode = i.getElement();
					if(i.getElement().equals(key))
					{
						view.clearShadedNodes();
						break;
					}
				}

				//BTView.setShadedNodes.clear();
				tree.delete(key); // Delete a key
				view.displayTree();
				view.setStatus(key + " is deleted from the tree");

			}
		});

		// Create a scene and place the pane in the stage
		Scene scene = new Scene(pane, 750, 400);
		primaryStage.setTitle("BSTAnimation"); // Set the stage title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show(); // Display the stage
	}

	/**
	 * The main method is only needed for the IDE with limited
	 * JavaFX support. Not needed for running from the command line.
	 */
	public static void main(String[] args) {
		launch(args);
	}
}












