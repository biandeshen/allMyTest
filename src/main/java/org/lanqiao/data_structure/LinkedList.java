package org.lanqiao.data_structure;

public class LinkedList<T> {
    // 链表起点
    Node head = null;
    // 链表终点
    Node last = null;
    // 链表长度
    private int length = 0;

    /* 链表的结构 NODE：1.存储下个节点的位置 2.存储数据 */
    private class Node {
        private Node next;// 下一节点的位置
        private Object object;// 存储数据

        public Node(Object object) {// 构造方法，即存数据时可用
            this.object = object;
        }

    }

    // 添加数据时，需添加到节点NODE，若当前无节点，则new创建一个节点，且无下一节点位置
    // 若已存在节点，则添加到当前末尾节点后
    private void add(Object object) {
        Node node = new Node(object);
        if (head == null) {
            head = node;
        } else {
            last.next = node;
        }
        last = node;
        length++;

    }

    // 打印链表指定元素
    public void printNode(int index) {
        Node node = head;
        int i = 0;
        while (index != 0 && i < index) {
            node = node.next;
            i++;
        }
        System.out.println(node.object);

    }

    // 打印链表全部元素
    public void printNode() {
        for (int i = 0; i < length; i++) {
            printNode(i);
        }
    }

    // 删除节点，1.头结点 2.中间节点 3.尾节点
    // 根据节点索引值删除节点，故头结点及尾节点是特殊情况
    private void delete(int index) {
        Node node = head;
        int i = 0;
        if (index > 0) {
            while (node != null && i < index - 1) {
                node = node.next;
                i++;
            }
            node.next = node.next.next;
        }
        //处理删除到头结点
        else if (index == 0) {
            head = head.next;
            head = null;
        }else {
            head = null;
        }
        length--;
    }

    // 删除头结点
    private void deleteHead() {
        delete(0);
    }

    // 删除尾节点
    private void deleteLast() {
        delete(length - 1);
    }

    // 获取链表长度
    private int getLength() {
        return length;
    }

    /*
     * 在链表中插入一个新的节点
     * 
     */
    private void insert(int index, Object object) {
        Node node = head;
        Node node2 = new Node(object);
        int i = 0;
        if (index > 0) {
            while (node != null && i < index - 1) {
                node = node.next;
                i++;
            }

            node2.next = node.next;
            node.next = node2;
        }
        if (index == 0) {
            node2.next = head;
            head = node2;
        }
        length++;
    }

    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        linkedList.add("1");
        linkedList.add("2");
        linkedList.add("3");
        linkedList.add("4");
        linkedList.add("5");
        linkedList.add("6");
        // linkedList.delete(0);
        linkedList.insert(1, "7");
//         linkedList.deleteHead();
//        linkedList.deleteLast();
//        linkedList.deleteLast();
//        linkedList.deleteLast();
//        linkedList.deleteLast();
//        linkedList.deleteLast();
//        linkedList.deleteLast();
//        linkedList.deleteLast();
        System.out.println(linkedList.getLength());
        System.out.println();
        // linkedList.printNode(1);
        linkedList.printNode();
    }
}
