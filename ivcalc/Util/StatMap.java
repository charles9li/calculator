package ivcalc.Util;

import java.util.Map;
import java.util.TreeMap;

public class StatMap {

    public static Map<StatType, Integer> createStatMap(int hp, int atk, int def,
                                                       int spa, int spd, int spe) {
        int[] statArray = new int[] {hp, atk, def, spa, spd, spd};
        return createStatMap(statArray);
    }

    public static Map<StatType, Integer> createStatMap(int[] statArray) {
        if (statArray.length != 6) {
            throw new IllegalArgumentException("Argument must be int array of length 6.");
        }
        Map<StatType, Integer> returnMap = new TreeMap<>();
        int i = 0;
        for (StatType statType : StatType.values()) {
            returnMap.put(statType, statArray[i]);
        }
        return returnMap;
    }
}
