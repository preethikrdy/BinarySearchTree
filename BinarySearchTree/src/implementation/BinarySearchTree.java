package implementation;

import java.util.Comparator;
import java.util.TreeSet;

public class BinarySearchTree<K, V> {
	
	private class Node {
		private K key;
		private V value;
		private Node left, right;

		private Node(K key, V value) {
			this.key = key;
			this.value = value;
		}
	}

	private Node root;
	private int treeSize, maxEntries;
	private Comparator<K> comparator;

	public BinarySearchTree(Comparator<K> comparator, int maxEntries) {
		this.comparator = comparator;
		this.maxEntries = maxEntries;
		treeSize = 0;
	}

	public BinarySearchTree<K, V> add(K key, V value) throws TreeIsFullException {
		//Adds only if it has not reached the max entries
		if(treeSize == maxEntries) {
			throw new TreeIsFullException("Tree is full");
		}
		root = addRecursive(root, key, value);
		return this;
	}
	
	private Node addRecursive(Node curr, K key, V val) {
		//Adding if the tree is empty
		if(curr == null) {
			treeSize++;
			return new Node(key, val);
		}
		//Adding based on the comparator order
		int compare = comparator.compare(key, curr.key);
		if(compare > 0) {
			curr.right = addRecursive(curr.right, key, val);
		} else if(compare < 0) {
			curr.left = addRecursive(curr.left, key, val);
		} else {
			curr.value = val;
		}
		return curr;
		
	}

	public String toString() {
		if(isEmpty()) {
			return "EMPTY TREE";
		}
		StringBuffer sb = new StringBuffer();
		toStringRecursive(root, sb);
		return sb.toString();
	}
	private void toStringRecursive(Node curr, StringBuffer sb) {
		if(curr == null) {
			return;
		}
		//Appending the values
		toStringRecursive(curr.left, sb);
		sb.append("{" + curr.key + ":" + curr.value + "}");
		toStringRecursive(curr.right, sb);
	}

	public boolean isEmpty() {
	
		return root == null;
	}

	public int size() {
		
		return treeSize;
	}

	
	public boolean isFull() {
		
		return treeSize == maxEntries;
	}

	public KeyValuePair<K, V> getMinimumKeyValue() throws TreeIsEmptyException {
		if(isEmpty()) {
			throw new TreeIsEmptyException("Empty tree");
		}
		//Recursively moving towards the left if there is a left node available
		Node min = getMinimum(root);
        return new KeyValuePair<>(min.key, min.value);

		}
	
	private Node getMinimum(Node node){
		if (node.left == null) {
            return node;
        }
        return getMinimum(node.left);
	}

	public KeyValuePair<K, V> getMaximumKeyValue() throws TreeIsEmptyException {
		if(isEmpty()) {
			throw new TreeIsEmptyException("Empty tree");
		}
		//Recursively moving towards the right if there is a right node available
        Node maximumNode = getMaximumNode(root);
        return new KeyValuePair<>(maximumNode.key, maximumNode.value);

	}
	private Node getMaximumNode(Node currentNode) {
        if (currentNode.right == null) {
            return currentNode;
        }
        return getMaximumNode(currentNode.right);
    }


	public KeyValuePair<K, V> find(K key) {
		if (root == null) {
	        return null;
	    }
	    if (key == null) {
	        return null;
	    }
	    int compare = ((String) key).compareTo((String) root.key);
	    //If the two values are the same, return that node
	    if (compare == 0) {
	        return new KeyValuePair<>(root.key, root.value);
	    //If it is less, keep moving left
	    } else if (compare < 0) {
	        BinarySearchTree<K, V> leftTree = new BinarySearchTree<>(this.comparator, this.maxEntries);
	        leftTree.root = root.left;
	        return leftTree.find(key);
	    } else {
	    //If it is greater, keep moving right
	        BinarySearchTree<K, V> rightTree = new BinarySearchTree<>(this.comparator, this.maxEntries);
	        rightTree.root = root.right;
	        return rightTree.find(key);
	    }

	}

	public BinarySearchTree<K, V> delete(K key) throws TreeIsEmptyException {
		if (isEmpty()) {
	        throw new TreeIsEmptyException("Empty tree");
	    }
	    root = deleteRecursive(root, key);
	   
	    return this;
	}
	private Node deleteRecursive(Node curr, K key) {
	    if (curr == null) {
	        return null;
	    }
	    
	    int compare = comparator.compare(key, curr.key);
	    //keep moving left if it is less, then delete
	    if (compare < 0) {
	        curr.left = deleteRecursive(curr.left, key);
	    } else if (compare > 0) {
	    	//keep moving right if it is more, then delete
	        curr.right = deleteRecursive(curr.right, key);
	    } else {
	        if (curr.left == null) {
	        	//Decreasing the treeSize
	        	treeSize--;
	            return curr.right;
	        } else if (curr.right == null) {
	        	treeSize--;
	            return curr.left;
	        }
	        //Resetting the variables
	        Node minNode = getMinimum(curr.right);
	        curr.key = minNode.key;
	        curr.value = minNode.value;
	        curr.right = deleteRecursive(curr.right, minNode.key);
	    }
	    return curr;
	}

	public void processInorder(Callback<K, V> callback) {
	    if (callback == null) {
	        throw new IllegalArgumentException("parameter cannot be null.");
	    }
	    if (root != null) {
	        processInorderHelper(root, callback);
	    }

	}
	private void processInorderHelper(Node node, Callback<K,V> callback) {
		//Calling recursive method if there is nothing less that that node
	    if (node.left != null) {
	        processInorderHelper(node.left, callback);
	    }
	    //Calling recursive method if there is nothing greater than that node
	    callback.process(node.key, node.value);
	    if (node.right != null) {
	        processInorderHelper(node.right, callback);
	    }
	}


	public BinarySearchTree<K, V> subTree(K lowerLimit, K upperLimit) {
		BinarySearchTree<K, V> subTree = new BinarySearchTree<>(this.comparator, this.maxEntries);
	    subTree.root = subTreeRecursive(root, lowerLimit, upperLimit);
	    return subTree;
	}
	private Node subTreeRecursive(Node curr, K lowerLimit, K upperLimit) {
		if (curr == null) {
	        return null;
	    }
	    
	    int cmpLower = comparator.compare(lowerLimit, curr.key);
	    int cmpUpper = comparator.compare(upperLimit, curr.key);
	    
	    if (cmpLower <= 0 && cmpUpper >= 0) {
	        Node node = new Node(curr.key, curr.value);
	        //checking if we need to traverse the left subtree
	        if (cmpLower < 0) {
	            node.left = subTreeRecursive(curr.left, lowerLimit, upperLimit);
	        }
	        //checking if we need to traverse the right subtree
	        if (cmpUpper > 0) {
	            node.right = subTreeRecursive(curr.right, lowerLimit, upperLimit);
	        }
	        return node;  
	    } else if (cmpLower > 0) {
	        //the node and its left subtree are outside the range, so we won't need to traverse them
	        return subTreeRecursive(curr.right, lowerLimit, upperLimit);  
	    } else {
	        //the node and its right subtree are outside the range, so we won't need to traverse them
	        return subTreeRecursive(curr.left, lowerLimit, upperLimit);
	    }	
	}
	
	public TreeSet<V> getLeavesValues() {
		TreeSet<V> result = new TreeSet<>();
	    if (root != null) {
	        getLeavesValuesRecursive(root, result);
	    }
	    return result;
	}
	private void getLeavesValuesRecursive(Node node, TreeSet<V> result) {
		//If this is root, add it 
	    if (node.left == null && node.right == null) {
	        result.add(node.value);
	    } else {
	    	//if there is nothing else to traverse, add it to the tree set
	        if (node.left != null) {
	            getLeavesValuesRecursive(node.left, result);
	        }
	        if (node.right != null) {
	        	//if there is nothing greater to traverse, add it to the tree set
	            getLeavesValuesRecursive(node.right, result);
	        }
	    }
	}
}
