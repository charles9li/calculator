package ivcalc.Calculator;

import ivcalc.Util.StatMap;
import ivcalc.Util.StatType;

import java.util.Map;

public class LevelInfo {

    private int level;
    private int evoIndex;
    private Map<StatType, Integer> stats;
    private Map<StatType, Integer> effortValues;

    public LevelInfo(int lvl, int evoIndex, int hpStat, int atkStat, int defStat, int spaStat, int spdStat, int speStat) {
        this(lvl, evoIndex, hpStat, atkStat, defStat, spaStat, spdStat, speStat, 0, 0, 0, 0, 0, 0);
    }

    public LevelInfo(int lvl, int evoIndex,
                      int hpStat, int atkStat, int defStat,
                      int spaStat, int spdStat, int speStat,
                      int hpEV, int atkEV, int defEV,
                      int spaEV, int spdEV, int speEV) {
        level = lvl;
        stats = StatMap.createStatMap(hpStat, atkStat, defStat, spaStat, spdStat, speStat);
        effortValues = StatMap.createStatMap(hpEV, atkEV, defEV, spaEV, spdEV, speEV);
    }

    public int getLevel() {
        return level;
    }

    public int getEvoIndex() {
        return evoIndex;
    }

    public int getStat(StatType statType) {
        return stats.get(statType);
    }

    public int getEV(StatType statType) {
        return effortValues.get(statType);
    }
}
