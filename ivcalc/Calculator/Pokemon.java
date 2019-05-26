package ivcalc.Calculator;

import ivcalc.Data.SelectNatures;
import ivcalc.Data.SelectPokemon;
import ivcalc.Util.Stat;

import java.util.Map;
import java.util.TreeMap;

public class Pokemon {

    private SelectPokemon sp = new SelectPokemon();
    private SelectNatures sn = new SelectNatures();

    private String name;
    private String nature;
    private Map<Stat, Integer> baseStats;

    public Pokemon(String name, String nature) {
        this.name = name;
        this.nature = nature;
        findBaseStats();
    }

    public Map<Stat, Integer> baseStats() {
        return baseStats;
    }

    private void findBaseStats() {
        baseStats = new TreeMap<>();
        for (Stat stat : Stat.values()) {
            baseStats.put(stat, sp.select(name, stat));
        }
    }

    private int calcStat(Stat stat, int lvl, int iv, int ev) {
        int temp = (int) ((2 * baseStats.get(stat) + iv + ev / 4) * (double) lvl / 100);
        if (stat == Stat.HP) {
            return temp + lvl + 10;
        } else {
            return (int) ((temp + 5) * sn.select(nature, stat));
        }
    }
}
