****************
* Double-Linked List
* CS 221-3
* 11/14/2024
* Gabriel Tinsley
****************

OVERVIEW:
The IUDoubleLinkedList is a node implementation of a doubly linked list with a fully functional ListIterator. This project supports basic list operations, bidirectional traversal, and list modifications through iterator methods like remove(), add(), and set().

INCLUDED FILES:
* IndexedUnsortedList.java - source file
* IUDoubleLinkedList.java - source file
* ListTester.java - source file
* Node.java - source file
* README - this file

COMPILING AND RUNNING:
From the directory containing all source files, compile the
driver class (and all dependencies) with the command:
$ javac ListTester.java

Run the compiled class file with the command:
$ java ListTester

Console output will give the results of ListTester for IUDoubleLinkedList.

PROGRAM DESIGN AND IMPORTANT CONCEPTS:
The IUDoubleLinkedList is designed with efficiency in mind for operations such as insertion, deletion and traversal. The class supports bidirectional traversal and modifications through its nodes, each of which maintains references to both previous and next nodes.

The purpose of the Node class is to encapsulate a single element in the list and hold references to previous and next nodes. For IUDoubleLinkedList addToFront(), addToRear(), add(), removeFirst(), and removeLast() are all O(1) which makes the class more efficient than IUSingleLinkedList and IUArrayList.

Algorithms to understand are adding elements, removing elements and getting elements. Efficient adding is done by directly linking the new node to its surrounding neighbors or updating head and tail as needed. In cases such as empty lists, that is handled separately for consistency. Efficient removal is by adjusting neighbor node references and updating head or tail as needed. Finally getting is done by iterating through nodes. Index-based get starts at head and moves down the list until reaching the given index that is returned.

The DLLIterator inner class implements ListIterator<T>, allowing for modifications of the list during iteration. Allows methods for moving next() and previous() through the list. Allows modifications like remove(), add(), and set() while keeping an IUDoubleLinkedList. Finally, keep track of modification count and iterator modification count to prevent concurrent modifications.

TESTING:
I tested IUDoubleLinkedList using the ListTester class, which performs extensive tests covering all possible change scenarios and ListIterator concurrency scenarios. This testing suite executes more than 11000 test cases, including tests for edge cases, boundary conditions, and stress scenarios to ensure completeness. When bad inputs are encountered, the class throws exceptions as expected. During testing, I identified and fixed several bugs, such as my get(int index) method returning null because I was moving to the wrong node. I resolved this issue by fixing my for-loop to go to index instead of index-1 because index-1 gets the wrong element, resulting in a stable implementation with no known remaining issues.

DISCUSSION:
A recurring issue I encountered during programming was maintaining references to previous and next nodes. Drawing out every method and finding the special cases such as empty lists played a huge role in completing this project. The most challenging part of the project was remembering to update the previous node after adding or removing an element. Finally, the process of creating a node, setting the node's next reference then previous reference clicked.

----------------------------------------------------------------------------