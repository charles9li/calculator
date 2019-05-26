package ivcalc.Test;

import ivcalc.Calculator.Pokemon;
import ivcalc.Util.Stat;
import org.junit.Test;

import java.util.Map;
import java.util.TreeMap;

import static org.junit.Assert.assertEquals;

public class TestPokemon {

    @Test
    public void testBaseStats() {
        Pokemon pokemon = new Pokemon("Bulbasaur");
        Map<Stat, Integer> expected = new TreeMap<>();
        expected.put(Stat.HP, 45);
        expected.put(Stat.ATK, 49);
        expected.put(Stat.DEF, 49);
        expected.put(Stat.SPA, 65);
        expected.put(Stat.SPD, 65);
        expected.put(Stat.SPE, 45);
        assertEquals(expected, pokemon.baseStats());
    }
}
