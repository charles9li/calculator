package ivcalc.Calculator;

import ivcalc.Util.StatMap;
import ivcalc.Util.StatType;

import java.util.Map;

/**
 * Contains information from an input line.
 */
public class LevelInfo {

    /**
     * Pokemon level.
     */
    private int level;

    /**
     * Indicates which evolution the input line corresponds to.
     */
    private int evoIndex;

    /**
     * Inputted stats.
     */
    private Map<StatType, Integer> stats;

    /**
     * Inputted effort values.
     */
    private Map<StatType, Integer> effortValues;

    /**
     * Constructor for no effort values.
     */
    public LevelInfo(int lvl, int evoIndex,
                     int hpStat, int atkStat, int defStat,
                     int spaStat, int spdStat, int speStat) {
        this(lvl, evoIndex, hpStat, atkStat, defStat, spaStat, spdStat, speStat,
            0, 0, 0, 0, 0, 0);
    }

    /**
     * Constructor for the LevelInfo class.
     */
    public LevelInfo(int lvl, int evoInd,
                      int hpStat, int atkStat, int defStat,
                      int spaStat, int spdStat, int speStat,
                      int hpEV, int atkEV, int defEV,
                      int spaEV, int spdEV, int speEV) {
        level = lvl;
        evoIndex = evoInd;
        stats = StatMap.createStatMap(hpStat, atkStat, defStat,
            spaStat, spdStat, speStat);
        effortValues = StatMap.createStatMap(hpEV, atkEV, defEV,
            spaEV, spdEV, speEV);
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
