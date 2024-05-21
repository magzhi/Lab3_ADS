# Lab3
 Lab3 assignment for Algorithms and Data Structures course

 The task involved creating 2 basic List classes such as: MyArrayList and MyLinkedList which both implemented the given MyList interface
 The 2nd part of the task involved creating 3 more List classes that used previous to ease up the implementation 

 # Included Classes:
  BST
  MyHashTable
  MyTestingClass

 # Running the project:
 1. Clone and Open repository
 2. Build the project
 3. Run Main.java class to see the provided examples of all functionality


 # BST
 BinaryTreeSearch implementation:
 
 # Class Structure
  The main class in this implementation is BST, which contains the following nested class:

Node: Represents an individual node in the BST.

  Node Class
Each Node object has:

K key: The key of the node.
V value: The value associated with the key.
Node left: Reference to the left child.
Node right: Reference to the right child.

  BST Class
The BST class contains:

Node root: The root node of the BST.
int size: The size of the BST.

# Usage
To insert a key-value pair into the BST, use the put method
To retrieve the value associated with a given key, use the get method
To delete a key-value pair from the BST, use the delete method


# MyHashTable
HashTable implementation:

# Class Structure
The main class in this implementation is MyHashTable, which contains the following nested class:

HashNode: Represents an individual node in a linked list used for chaining.

  HashNode Class
Each HashNode object has:

K key: The key of the node.
V value: The value associated with the key.
HashNode<K, V> next: Reference to the next node in the chain.

  MyHashTable Class
The MyHashTable class contains:

HashNode<K, V>[] chainArray: Array of linked lists for storing key-value pairs.
int M: The number of buckets in the hash table.
int size: The number of key-value pairs in the hash table.

# Usage
To insert a key-value pair into the BST, use the put method
To retrieve the value associated with a given key, use the get method
To delete a key-value pair from the BST, use the remove method
To find if a key-value pair exists in the BST, use the exists method
To retrieve the key associated with a given value, use the getKey method
