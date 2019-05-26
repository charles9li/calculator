package ivcalc.Calculator;

import ivcalc.Data.SelectNatures;
import ivcalc.Data.SelectPokemon;
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
            baseStats.put(stat, sp.select(name, stat));
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

//    private class
}
