package ivcalc.Util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Map where the keys are strings are stored in a hashmap-based trie and the
 * values are stored at the nodes where keys are located.
 *
 * @param <T> class that implements Comparable
 */
public class TrieMap<T extends Comparable<T>> {

    /**
     * Root of the trie.
     */
    private Node root;

    /**
     * Constructor for the TrieMap class.
     */
    public TrieMap() {
        root = new Node('a', false, null);
    }

    /**
     * Returns true if the TrieMap contains the key.
     *
     * @param key String instance
     * @return true if TrieMap contains the given key
     */
    public boolean containsKey(String key) {
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

    /**
     * Returns true if the TrieMap contains the specified prefix.
     *
     * @param prefix String instance
     * @return true if the TrieMap contains the prefix
     */
    public boolean containsPrefix(String prefix) {
        if (prefix == null || prefix.length() < 1) {
            return false;
        }
        Node curr = root;
        for (int i = 0, n = prefix.length(); i < n; i++) {
            char c = prefix.charAt(i);
            if (!curr.map.containsKey(c)) {
                return false;
            }
            curr = curr.map.get(c);
        }
        return true;
    }

    /**
     * Adds a key-value pair to the TrieMap. If the key already exists, the
     * value is replaced with the new item.
     *
     * @param key String instance
     * @param item object whose class implements Comparable
     */
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

    /**
     * Returns the value bound to the specified key. Returns null if the key is
     * not in the TrieMap.
     *
     * @param key String instance
     * @return value bound to specified key, null if key doesn't exist
     */
    public T get(String key) {
        if (containsKey(key)) {
            Node curr = root;
            for (int i = 0, n = key.length(); i < n; i++) {
                char c = key.charAt(i);
                curr = curr.map.get(c);
            }
            return curr.item;
        }
        return null;
    }

    /**
     * Returns a list of values whose corresponding keys begin with the
     * specified prefix. Returns an empty list if no keys with the prefix
     * exist.
     *
     * @param prefix String instance
     * @return List of values
     */
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
        listOfItems = itemsWithPrefixHelper(prefix, listOfItems, curr);
        Collections.sort(listOfItems);
        return listOfItems;
    }

    /**
     * Private helper method that recursively traverses the trie and gathers
     * all values whose keys have the specified prefix.
     *
     * @param s String prefix
     * @param l List of values
     * @param n current Node
     * @return List of values
     */
    private List<T> itemsWithPrefixHelper(String s, List<T> l, Node n) {
        if (n.isKey) {
            l.add(n.item);
        }
        for (char c : n.map.keySet()) {
            l = itemsWithPrefixHelper(s + c, l, n.map.get(c));
        }
        return l;
    }

    /**
     * Node class that stores a character. isKey is true if the current
     * character is the last character in a string. map stores Node instances
     * of following letters.
     */
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
}
