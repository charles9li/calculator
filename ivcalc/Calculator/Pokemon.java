package ivcalc.Calculator;

import ivcalc.Data.SelectNatures;
import ivcalc.Data.SelectPokemon;
import ivcalc.Util.StatMap;
import ivcalc.Util.StatType;

import java.util.Map;
import java.util.TreeMap;

public class Pokemon {

    private SelectPokemon sp = new SelectPokemon();
    private SelectNatures sn = new SelectNatures();

    private String name;
    private String nature;
    private Map<StatType, Integer> baseStats;

    public Pokemon(String name, String nature) {
        this.name = name;
        this.nature = nature;
        findBaseStats();
    }

    public Map<StatType, Integer> baseStats() {
        return baseStats;
    }

    private void findBaseStats() {
        baseStats = new TreeMap<>();
        for (StatType stat : StatType.values()) {
            baseStats.put(stat, sp.selectBaseStat(name, stat));
        }
    }

    private int calcStat(StatType statType, int lvl, int iv, int ev) {
        int temp = (int) ((2 * baseStats.get(statType) + iv + ev / 4) * (double) lvl / 100);
        if (statType == StatType.HP) {
            return temp + lvl + 10;
        } else {
            return (int) ((temp + 5) * sn.select(nature, statType));
        }
    }

    private class LevelInfo {
        private int level;
        private Map<StatType, Integer> stats;
        private Map<StatType, Integer> effortValues;

        private LevelInfo(int lvl,
                          int hpStat, int atkStat, int defStat,
                          int spaStat, int spdStat, int speStat) {
            this(lvl,
                hpStat, atkStat, defStat, spaStat, spdStat, speStat,
                0, 0, 0, 0, 0, 0);
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
}
