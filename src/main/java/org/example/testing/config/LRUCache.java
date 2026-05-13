package org.example.testing.config;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class LRUCache<K, V> {

    class Node {
        K key;
        V value;
        long timestamp;
        Node next;
        Node prev;
        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.timestamp = System.currentTimeMillis();
        }
    }

    private final int capacity;
    private final Map<K, Node> map;
    private final Node head;
    private final Node tail;
    private final long ttlMillis;
    private final ReentrantLock lock = new ReentrantLock();


    public LRUCache(int capacity, long  ttlMillis) {
        this.capacity = capacity;
        this.ttlMillis = ttlMillis;
        this.map = new HashMap<>();

        // Initialize dummy head and tail to avoid null pointer checks
        this.head = new Node(null, null);
        this.tail = new Node(null, null);
        head.next = tail;
        tail.prev = head;
    }

    public V get(K key) {
        lock.lock();
        try {
            if (!map.containsKey(key)) {
                return null; // Return null instead of -1 for Object types
            }

            Node node = map.get(key);
            long now = System.currentTimeMillis();

            // Passive Expiration Check
            if (now - node.timestamp > ttlMillis) {
                remove(node);
                map.remove(key);
                return null;
            }

            // Move to head (Most Recently Used)
            remove(node);
            insertAtHead(node);
            return node.value;

        } finally {
            lock.unlock(); // Always unlock in finally block
        }
    }

    public void put(K key, V value) {
        lock.lock();
        try {
            if (map.containsKey(key)) {
                remove(map.get(key));
            }

            if (map.size() >= capacity) {
                // Remove Least Recently Used (tail.prev) from map and list
                Node lru = tail.prev;
                remove(lru);
                map.remove(lru.key);
            }

            Node newNode = new Node(key, value);
            insertAtHead(newNode);
            map.put(key, newNode);

        } finally {
            lock.unlock();
        }
    }

    public void removeAll(K key) {
        lock.lock();
        try {
            if (map.containsKey(key)) {
                Node node = map.get(key);
                // 1. Unlink pointers in the Doubly Linked List
                node.prev.next = node.next;
                node.next.prev = node.prev;

                // 2. Remove from the HashMap lookup
                map.remove(key);
            }
        } finally {
            lock.unlock();
        }
    }

    // Helper: Remove node from the doubly linked list pointers
    private void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    // Helper: Insert node right after the dummy head
    private void insertAtHead(Node node) {
        node.next = head.next;
        node.next.prev = node;
        head.next = node;
        node.prev = head;
    }
}
