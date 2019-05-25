package ivcalc.Test;

import ivcalc.Data.SelectPokemon;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import ivcalc.Data.SelectNatures;

public class TestSelect {

    @Test
    public void TestSelectNatures() {
        SelectNatures sn = new SelectNatures();
        assertEquals(0.9, sn.select("Bold", "atk"), 0.001);
        assertEquals(1.0, sn.select("Serious", "hp"), 0.001);
    }

    @Test
    public void TestSelectPokemon() {
        SelectPokemon sp = new SelectPokemon();
        assertEquals(45, sp.select("Bulbasaur", "hp"));
        assertEquals(108, sp.select("Terrakion", "spe"));
    }
}
