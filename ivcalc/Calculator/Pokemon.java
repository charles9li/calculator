package ivcalc.Calculator;

import ivcalc.Data.SelectPokemon;
import ivcalc.Util.Stat;

import java.util.Map;
import java.util.TreeMap;

public class Pokemon {

    private SelectPokemon sp = new SelectPokemon();

    private String name;
    private Map<Stat, Integer> baseStats;

    public Pokemon(String name) {
        this.name = name;
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
}
