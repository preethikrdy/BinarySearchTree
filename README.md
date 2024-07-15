# Binary Search Tree Implementation

This Java project provides an implementation of a Binary Search Tree (BST) capable of storing key-value pairs.

## Overview

The project includes several classes and interfaces that facilitate the management and manipulation of a binary search tree.

### Classes and Interfaces

1. **BinarySearchTree**
   - Manages a binary search tree structure with nodes containing key-value pairs.
   - Implements methods for adding, deleting, finding, and processing nodes.
   - Key Java Concepts: Generics, Recursion, Comparator, Exception Handling.

2. **Callback (Interface)**
   - Defines a callback interface for processing key-value pairs within the tree.
   - Implementing classes can define custom processing logic.
   - Key Java Concepts: Interface, Generic Types.

3. **GetDataIntoArrays**
   - Implements the Callback interface to store key and value pairs into ArrayLists.
   - Provides methods to retrieve stored keys and values.
   - Key Java Concepts: Interface Implementation, Collections (ArrayList).

4. **KeyValuePair**
   - Simple class to hold a key-value pair retrieved from the tree.
   - Provides getters for key and value.
   - Key Java Concepts: Encapsulation.

5. **TreeIsEmptyException**
   - Exception thrown when an operation is attempted on an empty tree.
   - Ensures safe handling of operations on non-existent data.
   - Key Java Concepts: Exception Handling.

6. **TreeIsFullException**
   - Exception thrown when attempting to add to a tree that has reached its maximum capacity.
   - Prevents exceeding the predefined limit of entries in the tree.
   - Key Java Concepts: Exception Handling.

### Usage

- **Adding Nodes:** Use `BinarySearchTree.add(K key, V value)` to add key-value pairs to the tree.
- **Deleting Nodes:** Use `BinarySearchTree.delete(K key)` to remove nodes by their key.
- **Finding Nodes:** Use `BinarySearchTree.find(K key)` to search for a node by its key.
- **Processing Nodes:** Use `BinarySearchTree.processInorder(Callback<K, V> callback)` to process nodes in an inorder traversal.
