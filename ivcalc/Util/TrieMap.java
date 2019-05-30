package ivcalc.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrieMap<T> {
    private Node root;

    private class Node {
        char letter;
        boolean isKey;
        Map<Character, Node> map;
        T item;

        Node(char l, boolean ik, T i) {
            letter = l;
            isKey = ik;
            map = new HashMap<>();
            item = i;
        }
    }

    public TrieMap() {
        root = new Node('a', false, null);
    }

    public boolean contains(String key) {
        if (key == null || key.length() < 1) {
            return false;
        }
        Node curr = root;
        for (int i = 0, n = key.length(); i < n; i++) {
            char c = key.charAt(i);
            if (!curr.map.containsKey(c)) {
                return false;
            }
            curr = curr.map.get(c);
        }
        return curr.isKey;
    }

    public void put(String key, T item) {
        if (key == null || key.length() < 1) {
            return;
        }
        Node curr = root;
        for (int i = 0, n = key.length(); i < n; i++) {
            char c = key.charAt(i);
            if (!curr.map.containsKey(c)) {
                curr.map.put(c, new Node(c, false, null));
            }
            curr = curr.map.get(c);
        }
        curr.isKey = true;
        curr.item = item;
    }

    public T get(String key) {
        if (contains(key)) {
            Node curr = root;
            for (int i = 0, n = key.length(); i < n; i++) {
                char c = key.charAt(i);
                curr = curr.map.get(c);
            }
            return curr.item;
        }
        return null;
    }

    public List<T> itemsWithPrefix(String prefix) {
        List<T> listOfItems = new ArrayList<>();
        if (prefix == null || prefix.length() < 1) {
            return listOfItems;
        }
        Node curr = root;
        for (int i = 0, n = prefix.length(); i < n; i++) {
            char c = prefix.charAt(i);
            if (!curr.map.containsKey(c)) {
                return listOfItems;
            }
            curr = curr.map.get(c);
        }
        return prefixHelper(prefix, listOfItems, curr);
    }

    private List<T> prefixHelper(String s, List<T> l, Node n) {
        if (n.isKey) {
            l.add(n.item);
        }
        for (char c : n.map.keySet()) {
            l = prefixHelper(s + c, l, n.map.get(c));
        }
        return l;
    }
}
