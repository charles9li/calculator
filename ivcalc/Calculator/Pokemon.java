package ivcalc.Calculator;

import ivcalc.Data.SelectPokemon;
import ivcalc.Util.StatType;

import java.util.Map;
import java.util.TreeMap;

public class Pokemon {

    private static final SelectPokemon SELECT_POKEMON = new SelectPokemon();

    private String name;
    private Map<StatType, Integer> baseStats;
    private Pokemon evolution;

    public Pokemon(String name) {
        this.name = name;
        findBaseStats();
    }

    public String getName() {
        return name;
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
            baseStats.put(stat, SELECT_POKEMON.selectBaseStat(name, stat));
        }
    }
}
