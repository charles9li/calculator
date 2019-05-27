package ivcalc.Util;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class IVList {

    public static List<Integer> createIVList(int lowerBound, int upperBound) {
        List<Integer> returnList = new ArrayList<>();
        for (int i = lowerBound; i <= upperBound; i++) {
            returnList.add(i);
        }
        return returnList;
    }

    public static List<Integer> createIVList(Deque<List<Integer>> listDeque) {
        return null;
    }
}
