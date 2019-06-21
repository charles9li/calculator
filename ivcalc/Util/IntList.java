package ivcalc.Util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Contains methods used to create ordered Lists of integers.
 */
public class IntList {

    /**
     * Creates a List instance containing integers between a lower and upper
     * bound, inclusive.
     *
     * @param lowerBound lower bound
     * @param upperBound upper bound
     * @return List instance
     */
    public static List<Integer> createIntList(int lowerBound, int upperBound) {
        List<Integer> returnList = new ArrayList<>();
        for (int iv = lowerBound; iv <= upperBound; iv++) {
            returnList.add(iv);
        }
        return returnList;
    }

    /**
     * Takes a Deque containing Lists of integers and returns the intersection
     * between them. If there is no intersection, then an empty List is
     * returned.
     *
     * @param listDeque Deque containing Lists of integers.
     * @return intersection of input integers Lists
     */
    public static List<Integer> intListIntersect(Deque<List<Integer>> listDeque) {
        if (listDeque.isEmpty()) {
            throw new IllegalArgumentException("Deque must contain at least one List instance.");
        }
        Set<Integer> IVSet = new HashSet<>(listDeque.pop());
        Set<Integer> temp;
        while (listDeque.size() > 0) {
            temp = new HashSet<>();
            List<Integer> IVList = listDeque.pop();
            for (int iv : IVList) {
                if (IVSet.contains(iv)) {
                    temp.add(iv);
                }
            }
            IVSet = temp;
        }
        List<Integer> returnList = new ArrayList<>(IVSet);
        Collections.sort(returnList);
        return returnList;
    }
}
