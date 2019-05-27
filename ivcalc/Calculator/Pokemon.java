package ivcalc.Calculator;

import ivcalc.Data.SelectNatures;
import ivcalc.Data.SelectPokemon;
import ivcalc.Util.StatMap;
import ivcalc.Util.StatType;

import java.util.Map;
import java.util.TreeMap;

public class Pokemon {

    private SelectPokemon sp = new SelectPokemon();

    private String name;
    private Map<StatType, Integer> baseStats;
    private Pokemon evolution;

    public Pokemon(String name) {
        this.name = name;
        findBaseStats();
    }

    public Map<StatType, Integer> getBaseStats() {
        return baseStats;
    }

    public Pokemon getEvolution() {
        return evolution;
    }

    private void findBaseStats() {
        baseStats = new TreeMap<>();
        for (StatType stat : StatType.values()) {
            baseStats.put(stat, sp.selectBaseStat(name, stat));
        }
    }
}
