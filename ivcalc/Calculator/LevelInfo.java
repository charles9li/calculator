package ivcalc.Calculator;

import ivcalc.Util.StatMap;
import ivcalc.Util.StatType;

import java.util.Map;

public class LevelInfo {

    private int level;
    private Map<StatType, Integer> stats;
    private Map<StatType, Integer> effortValues;

    private LevelInfo(int lvl, int hpStat, int atkStat, int defStat, int spaStat, int spdStat, int speStat) {
        this(lvl, hpStat, atkStat, defStat, spaStat, spdStat, speStat, 0, 0, 0, 0, 0, 0);
    }

    private LevelInfo(int lvl,
                      int hpStat, int atkStat, int defStat,
                      int spaStat, int spdStat, int speStat,
                      int hpEV, int atkEV, int defEV,
                      int spaEV, int spdEV, int speEV) {
        level = lvl;
        stats = StatMap.createStatMap(hpStat, atkStat, defStat, spaStat, spdStat, speStat);
        effortValues = StatMap.createStatMap(hpEV, atkEV, defEV, spaEV, spdEV, speEV);
    }
}
