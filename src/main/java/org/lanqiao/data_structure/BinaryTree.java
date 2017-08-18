package org.lanqiao.data_structure;

public class BinaryTree {
    // 树具有一个根节点
    private Node root;

    /*
     * 树由节点组成 一个节点又有左节点，右节点 节点本身又存储数据
     */
    private class Node {
        // left
        private Node left;
        // right
        private Node right;
        // data
        int object;

        public Node(int object) {
            left = null;
            right = null;
            this.object = object;
        }
    }

    // 二叉树初始化
    public BinaryTree() {
        root = null;
    }

    // 尝试输入一个数组，即可得到一个BST
    public static BinaryTree getTree(int[] object) {
        BinaryTree bst = new BinaryTree();
        for (int i = 0; i < object.length; i++) {
            bst.insert(object[i]);
        }
        return bst;

    }

    // 插入数据，根节点数据
    private void insert(int object) {
        root = insert(root, object);
    }

    // 指定节点位置，插入数据
    private Node insert(Node node, int object) {
        if (node == null) {
            node = new Node(object);
        } else {
            // 根据数据选择插入节点位置
            if (object < node.object) {
                node.left = insert(node.left, object);
            } else if (object > node.object) {
                node.right = insert(node.right, object);
            } else {
                return node;
            }

        }
        return node;

    }

    /**
     * ;
     */
    public void printInorder() {
        printInorder(root);
        System.out.println();
    }

    // 根据结点打印树，打印整个树
    private void printInorder(Node root) {
        if (root == null) {
            return;
        }
        // 左，中，右 结点打印
        printInorder(root.left);
        System.out.println(root.object);
        printInorder(root.right);
    }

    /**
     * 打印当前树,后序遍历
     */
    public void printPostorder() {
        printPostorder(root);
        System.out.println();
    }

    // 根据结点打印树，打印整个树
    private void printPostorder(Node root) {
        if (root == null) {
            return;
        }
        // 左，右，中 结点打印
        printPostorder(root.left);
        printPostorder(root.right);
        System.out.println(root.object);
    }

    /**
     * 打印当前树,先序遍历
     */
    public void printPreorder() {
        printPreorder(root);
        System.out.println();
    }

    // 根据结点打印树，打印整个树
    private void printPreorder(Node root) {
        if (root == null) {
            return;
        }
        // 中，左，右 结点打印
        System.out.println(root.object);
        printPreorder(root.left);
        printPreorder(root.right);
    }

    /**
     * 层次遍历（未完成）
     * 
     */
    public int printLevel(int level) {
        System.out.println();
        return printLevel(root, level);
    }

    // 层次遍历(未完成)
    private int printLevel(Node root, int level) {
        // if (level < 0 || root == null) {
        // return 0;
        // }
        // if (0 == level) {
        // System.out.print(root.object);
        // System.out.print("\t");
        // return 1;
        // }
        // return printLevel(root.left, level - 1) + printLevel(root.right,
        // level - 1);
        Node node = root;
        if (level < 0 || root == null) {
            return 0;
        }
        if (0 == level) {
            System.out.print(root.object);
            System.out.print("\t");
            return 1;
        }
        System.out.println(root.object);
        return printLevel(root.left, level - 1) + printLevel(root.right, level - 1);
    }

    public void build123a() {
        root = new Node(3);
        Node lChlid = new Node(1);
        Node rChild = new Node(3);
        root.left = lChlid;
        root.right = rChild;

    }
    // 搜索数据

    /**
     * Build 123 using only one pointer variable.
     */
    public void build123b() {
        root = new Node(2);
        root.left = new Node(1);
        root.right = new Node(3);
    }

    /**
     * Build 123 by calling insert() three times. Note that the '2' must be
     * inserted first.
     */
    public void build123c() {
        root = null;
        root = insert(root, 2);
        root = insert(root, 1);
        root = insert(root, 3);
        root = insert(root, 5);
        root = insert(root, 11);
        root = insert(root, 4);
        root = insert(root, 23);
        root = insert(root, 11);
        root = insert(root, 22);

    }

    public static void main(String[] args) {
        int[] arr = { 4, 1, 3, 5, 7, 2, 6, 8, 10, 14, 15 };
        BinaryTree binaryTree = BinaryTree.getTree(arr);
        // binaryTree.build123a();
        // binaryTree.build123b();
        // binaryTree.build123c();
        // binaryTree.insert(5);
        // binaryTree.printInorder();
        // binaryTree.printLevel(0);
        // binaryTree.printLevel(1);
        // binaryTree.printLevel(2);
        // binaryTree.printLevel(3);
        // binaryTree.printLevel(4);
        // binaryTree.printLevel(5);
        // binaryTree.printLevel(6);
        // binaryTree.printPostorder();
        binaryTree.printPreorder();
        // binaryTree.printInorder(binaryTree.root);
        // binaryTree.printPostorder(binaryTree.root);
        // binaryTree.printPreorder(binaryTree.root);

    }
}