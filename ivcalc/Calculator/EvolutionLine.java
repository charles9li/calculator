package ivcalc.Calculator;

import ivcalc.Data.SelectNatures;
import ivcalc.Util.IVList;
import ivcalc.Util.StatType;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class EvolutionLine {

    private static final SelectNatures SELECT_NATURES = new SelectNatures();

    private Pokemon pokemon;
    private String nature;
    private List<LevelInfo> levelInfoList = new ArrayList<>();
    private Map<StatType, List<Integer>> rangeIVMap = new TreeMap<>();

    public EvolutionLine(String name, String nature) {
        this.pokemon = new Pokemon(name);
        this.nature = nature;
    }

    public void addLevelInfo(LevelInfo levelInfo) {
        levelInfoList.add(levelInfo);
    }

    public void calcIVRanges() {
        for (StatType statType : StatType.values()) {
            rangeIVMap.put(statType, calcIVRangeSingleStat(statType));
        }
    }

    private List<Integer> calcIVRangeSingleStat(StatType statType) {
        Deque<List<Integer>> IVRangeDeque = new ArrayDeque<>();
        for (LevelInfo levelInfo : levelInfoList) {
            List<Integer> IVRange = calcIVRangeSingleLevel(statType, levelInfo);
            IVRangeDeque.addLast(IVRange);
        }
        return IVList.createIVList(IVRangeDeque);
    }

    private List<Integer> calcIVRangeSingleLevel(StatType statType, LevelInfo levelInfo) {
        int level = levelInfo.getLevel();
        int evoIndex = levelInfo.getEvoIndex();
        int baseStat = getBaseStat(evoIndex, statType);
        int stat = levelInfo.getStat(statType);
        int ev = levelInfo.getEV(statType);
        List<Integer> returnList = new ArrayList<>();
        int lowerBound = 0;
        int upperBound = 31;
        if (statType == StatType.HP) {
            lowerBound = (stat - level - 10) * 100 / level - 2 * baseStat - ev / 4;
            upperBound = (stat - level - 9) * 100 / level - 2 * baseStat - ev / 4;
        } else {
            int i = 31;
            while (i >= 0) {
                if (calcStat(evoIndex, statType, level, i, ev) == stat) {
                    lowerBound = i;
                }
                i--;
            }
            while (i <= 31) {
                if (calcStat(evoIndex, statType, level, i, ev) == stat) {
                    upperBound = i;
                }
                i++;
            }
        }
        return IVList.createIVList(makeIVProper(lowerBound), makeIVProper(upperBound));
    }

    private int calcStat(int evoIndex, StatType statType, int lvl, int iv, int ev) {
        int baseStat = getBaseStat(evoIndex, statType);
        int temp = (int) ((2 * baseStat + iv + ev / 4) * (double) lvl / 100);
        if (statType == StatType.HP) {
            return temp + lvl + 10;
        } else {
            return (int) ((temp + 5) * getNatureMultiplier(statType));
        }
    }

    private double getNatureMultiplier(StatType statType) {
        return SELECT_NATURES.select(nature, statType);
    }

    private int getBaseStat(int evoIndex, StatType statType) {
        return evolution(evoIndex).getBaseStats().get(statType);
    }

    private Pokemon evolution(int evoIndex) {
        Pokemon current = pokemon;
        while (evoIndex > 0) {
            current = current.getEvolution();
            evoIndex--;
        }
        return current;
    }

    private int makeIVProper(int iv) {
        if (iv > 31) {
            return 31;
        }
        if (iv < 0) {
            return 0;
        }
        return iv;
    }
}
