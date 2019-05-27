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

/**
 * Contains information for a Pokemon and its evolutions, if they exist. The
 * constructor takes in the species name and nature of the Pokemon. LevelInfo
 * instances are then added to the instance of this class. The calcIVRanges
 * method is called to calculate the IV range for each stat.
 */
public class EvolutionLine {

    /**
     * Used for interfacing with SQL to find stat multipliers for a given
     * nature.
     */
    private static final SelectNatures SELECT_NATURES = new SelectNatures();

    /**
     * Contains base stat information for the Pokemon in this getEvolution line.
     */
    private Pokemon pokemon;

    /**
     * Nature of this Pokemon.
     */
    private String nature;

    /**
     * List of inputs from each line. Inputs are added via the addLevelInfo
     * method.
     */
    private List<LevelInfo> levelInfoList = new ArrayList<>();

    /**
     * Maps the stat type to its corresponding IV range. The ranges are
     * computed using the calcIVRanges method.
     */
    private Map<StatType, List<Integer>> rangeIVMap = new TreeMap<>();

    /**
     * Constructor for the EvolutionLine class. Takes in a species name and
     * nature and gathers information about the Pokemon and its evolutions.
     *
     * @param name Pokemon species name
     * @param nature Pokemon nature
     */
    public EvolutionLine(String name, String nature) {
        this.pokemon = new Pokemon(name);
        this.nature = nature;
    }

    // Add and retrieval methods.

    /**
     * Adds an input line to the levelInfoList variable.
     *
     * @param levelInfo input line
     */
    public void addLevelInfo(LevelInfo levelInfo) {
        levelInfoList.add(levelInfo);
    }

    /**
     * Returns the IV range for a specified stat.
     *
     * @param statType type of stat
     * @return List of IVs
     */
    public List<Integer> getIVRange(StatType statType) {
        return rangeIVMap.get(statType);
    }

    // Methods used to compute the IV ranges from line inputs.
    // Methods from the ivcalc.Util package are used.

    /**
     * Computes the IV range for each stat.
     */
    public void calcIVRanges() {
        for (StatType statType : StatType.values()) {
            rangeIVMap.put(statType, calcIVRangeStat(statType));
        }
    }

    /**
     * Calculates the IV range for a single stat using input from each level.
     *
     * @param statType type of stat
     * @return List instance containing possible IVs for specified stat
     */
    private List<Integer> calcIVRangeStat(StatType statType) {
        Deque<List<Integer>> IVRangeDeque = new ArrayDeque<>();
        for (LevelInfo levelInfo : levelInfoList) {
            List<Integer> IVRange = calcIVRangeLevel(statType, levelInfo);
            IVRangeDeque.addLast(IVRange);
        }
        return IVList.createIVList(IVRangeDeque);
    }

    /**
     * Calculates the IV range for a single levelInfo input and stat. Includes
     * an exception for Shedinja's HP stat where a range of 0 to 31 inclusive
     * is returned by default.
     *
     * @param statType type of stat
     * @param lvlInfo input line
     * @return List containing possible IVs
     */
    private List<Integer> calcIVRangeLevel(StatType statType, LevelInfo lvlInfo) {
        int evoIndex = lvlInfo.getEvoIndex();

        // Shedinja HP exception
        if (statType == StatType.HP && getEvolutionName(evoIndex).equals("Shedinja")) {
            return IVList.createIVList(0, 31);
        }

        // check if stat input is valid
        checkStat(statType, lvlInfo);

        int level = lvlInfo.getLevel();
        int baseStat = getBaseStat(evoIndex, statType);
        int stat = lvlInfo.getStat(statType);
        int ev = lvlInfo.getEV(statType);
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

    // Calculation methods.

    /**
     * Calculates a specified stat for an evolution given level, IV, and EV.
     *
     * @param evoIndex evolution index
     * @param statType type of stat
     * @param lvl level
     * @param iv individual value
     * @param ev effort value
     * @return stat
     */
    private int calcStat(int evoIndex, StatType statType, int lvl, int iv, int ev) {
        int baseStat = getBaseStat(evoIndex, statType);
        int temp = (int) ((2 * baseStat + iv + ev / 4) * (double) lvl / 100);
        if (statType == StatType.HP) {
            return temp + lvl + 10;
        } else {
            return (int) ((temp + 5) * getNatureMultiplier(statType));
        }
    }

    /**
     * Ensures that IVs are between 0 and 31, inclusive.
     *
     * @param iv individual value
     * @return proper individual value
     */
    private int makeIVProper(int iv) {
        if (iv > 31) {
            return 31;
        }
        if (iv < 0) {
            return 0;
        }
        return iv;
    }

    // Methods for checking input values.

    /**
     * Throws IllegalArgumentException if stat is not valid.
     *
     * @param statType type of stat
     * @param levelInfo input line
     */
    private void checkStat(StatType statType, LevelInfo levelInfo) {
        int evoIndex = levelInfo.getEvoIndex();
        int level = levelInfo.getLevel();
        int ev = levelInfo.getEV(statType);
        int stat = levelInfo.getStat(statType);
        int lowerBound = calcStat(evoIndex, statType, level, 0, ev);
        int upperBound = calcStat(evoIndex, statType, level, 31, ev);
        if (stat < lowerBound || stat > upperBound) {
            String message = "A level " + level + " "
                + getEvolutionName(evoIndex) + " with a " + nature
                + " nature and " + ev + " " + statType.toString()
                + " EVs should have a " + statType.toString()
                + " stat between " + lowerBound + " and " + upperBound;
            throw new IllegalArgumentException(message);
        }
    }

    // Methods for retrieving information from SQL databases.

    /**
     * Returns the stat multiplier dictated by the nature.
     *
     * @param statType type of stat
     * @return stat multiplier
     */
    private double getNatureMultiplier(StatType statType) {
        return SELECT_NATURES.select(nature, statType);
    }

    /**
     * Returns the specified base stat for an evolution specified by the
     * evolution index.
     *
     * @param evoIndex evolution index
     * @param statType type of stat
     * @return base stat
     */
    private int getBaseStat(int evoIndex, StatType statType) {
        return getEvolution(evoIndex).getBaseStats().get(statType);
    }

    /**
     * Returns the name of the evolution specified by the evolution index.
     *
     * @param evoIndex evolution index
     * @return evolution name
     */
    private String getEvolutionName(int evoIndex) {
        return getEvolution(evoIndex).getName();
    }

    /**
     * Returns the Pokemon instance for the specified evolution index.
     *
     * @param evoIndex evolution index
     * @return Pokemon instance
     */
    private Pokemon getEvolution(int evoIndex) {
        Pokemon current = pokemon;
        while (evoIndex > 0) {
            current = current.getEvolution();
            evoIndex--;
        }
        return current;
    }
}
