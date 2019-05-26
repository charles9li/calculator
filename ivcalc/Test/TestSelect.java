package ivcalc.Test;

import ivcalc.Data.SelectNatures;
import ivcalc.Data.SelectPokemon;
import ivcalc.Util.Stat;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestSelect {

    @Test
    public void TestSelectNatures() {
        SelectNatures sn = new SelectNatures();
        assertEquals(0.9, sn.select("Bold", Stat.ATK), 0.001);
        assertEquals(1.0, sn.select("Serious", Stat.HP), 0.001);
    }

    @Test
    public void TestSelectPokemon() {
        SelectPokemon sp = new SelectPokemon();
        assertEquals(45, sp.select("Bulbasaur", Stat.HP));
        assertEquals(108, sp.select("Terrakion", Stat.SPE));
    }
}
