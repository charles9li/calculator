package ivcalc.Util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class IVList {

    public static List<Integer> createIVList(int lowerBound, int upperBound) {
        List<Integer> returnList = new ArrayList<>();
        for (int iv = lowerBound; iv <= upperBound; iv++) {
            returnList.add(iv);
        }
        return returnList;
    }

    public static List<Integer> createIVList(Deque<List<Integer>> listDeque) {
        if (listDeque.isEmpty()) {
            throw new IllegalArgumentException("Deque must contain at least one List instance.");
        }
        Set<Integer> IVSet = new HashSet<>(listDeque.pop());
        Set<Integer> temp = new HashSet<>();
        while (listDeque.size() > 0) {
            temp.clear();
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
