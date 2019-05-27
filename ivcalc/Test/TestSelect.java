package ivcalc.Test;

import ivcalc.Data.SelectNatures;
import ivcalc.Data.SelectPokemon;
import ivcalc.Util.StatType;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestSelect {

    @Test
    public void testSelectNatures() {
        SelectNatures sn = new SelectNatures();
        assertEquals(0.9, sn.select("Bold", StatType.ATK), 0.001);
        assertEquals(1.0, sn.select("Serious", StatType.HP), 0.001);
    }

    @Test
    public void testSelectDexNum() {
        SelectPokemon sp = new SelectPokemon();
        assertEquals(1, sp.selectDexNum("Bulbasaur"));
        assertEquals(251, sp.selectDexNum("Celebi"));
    }

    @Test
    public void testSelectPokemon() {
        SelectPokemon sp = new SelectPokemon();
        assertEquals(45, sp.selectBaseStat("Bulbasaur", StatType.HP));
        assertEquals(108, sp.selectBaseStat("Terrakion", StatType.SPE));
    }

    @Test
    public void testSelectPokemonNames() {
        SelectPokemon sp = new SelectPokemon();
        String[] pokemonNames = sp.selectPokemonNames();
        assertEquals("Bulbasaur", pokemonNames[0]);
        assertEquals("Venusaur", pokemonNames[2]);
    }
}
