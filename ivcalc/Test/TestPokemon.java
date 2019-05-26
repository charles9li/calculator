package ivcalc.Test;

import ivcalc.Calculator.Pokemon;
import ivcalc.Util.StatType;
import org.junit.Test;

import java.util.Map;
import java.util.TreeMap;

import static org.junit.Assert.assertEquals;

public class TestPokemon {

    @Test
    public void testBaseStats() {
        Pokemon pokemon = new Pokemon("Bulbasaur", "Bold");
        Map<StatType, Integer> expected = new TreeMap<>();
        expected.put(StatType.HP, 45);
        expected.put(StatType.ATK, 49);
        expected.put(StatType.DEF, 49);
        expected.put(StatType.SPA, 65);
        expected.put(StatType.SPD, 65);
        expected.put(StatType.SPE, 45);
        assertEquals(expected, pokemon.baseStats());
    }
}
