package ivcalc.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class TrieMap {
    private Node root;

    private class Node {
        char letter;
        boolean isKey;
        Map<Character, Node> map;
        List<String> fullNames;

        Node(char l, boolean ik, List<String> fn) {
            letter = l;
            isKey = ik;
            map = new HashMap<>();
            fullNames = new LinkedList<>();
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

    public void put(String key, List<String> fullNames) {
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
        curr.fullNames = fullNames;
    }

    public List<String> get(String key) {
        if (contains(key)) {
            Node curr = root;
            for (int i = 0, n = key.length(); i < n; i++) {
                char c = key.charAt(i);
                curr = curr.map.get(c);
            }
            return curr.fullNames;
        }
        return null;
    }

    public List<String> fullNamesWithPrefix(String prefix) {
        List<String> listOfFullNames = new ArrayList<>();
        if (prefix == null || prefix.length() < 1) {
            return listOfFullNames;
        }
        Node curr = root;
        for (int i = 0, n = prefix.length(); i < n; i++) {
            char c = prefix.charAt(i);
            if (!curr.map.containsKey(c)) {
                return listOfFullNames;
            }
            curr = curr.map.get(c);
        }
        return prefixHelper(prefix, listOfFullNames, curr);
    }

    private List<String> prefixHelper(String s, List<String> l, Node n) {
        if (n.isKey) {
            l.addAll(n.fullNames);
        }
        for (char c : n.map.keySet()) {
            l = prefixHelper(s + c, l, n.map.get(c));
        }
        return l;
    }
}
