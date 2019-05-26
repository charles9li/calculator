package ivcalc.Test;

import ivcalc.Data.SelectNatures;
import ivcalc.Data.SelectPokemon;
import ivcalc.Util.StatType;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestSelect {

    @Test
    public void TestSelectNatures() {
        SelectNatures sn = new SelectNatures();
        assertEquals(0.9, sn.select("Bold", StatType.ATK), 0.001);
        assertEquals(1.0, sn.select("Serious", StatType.HP), 0.001);
    }

    @Test
    public void TestSelectPokemon() {
        SelectPokemon sp = new SelectPokemon();
        assertEquals(45, sp.select("Bulbasaur", StatType.HP));
        assertEquals(108, sp.select("Terrakion", StatType.SPE));
    }
}
