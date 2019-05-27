package ivcalc.Test;

import edu.princeton.cs.algs4.In;
import ivcalc.Calculator.EvolutionLine;
import ivcalc.Calculator.LevelInfo;
import ivcalc.Calculator.Pokemon;
import ivcalc.Util.IVList;
import ivcalc.Util.StatType;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestEvolutionLine {

    @Test
    public void TestAegislash() {
        EvolutionLine evolutionLine = new EvolutionLine("Aegislash", "Quiet");
        LevelInfo lvl50 = new LevelInfo(50, 0, 167, 70, 170, 112, 171, 58, 252, 0, 0, 252, 4, 0);
        evolutionLine.addLevelInfo(lvl50);
        evolutionLine.calcIVRanges();

        List<Integer> hpExpected = IVList.createIVList(31, 31);
        assertEquals(hpExpected, evolutionLine.getIVRange(StatType.HP));

        List<Integer> atkExpected = IVList.createIVList(30, 31);
        assertEquals(atkExpected, evolutionLine.getIVRange(StatType.ATK));

        List<Integer> defExpected = IVList.createIVList(30, 31);
        assertEquals(atkExpected, evolutionLine.getIVRange(StatType.DEF));

        List<Integer> spaExpected = IVList.createIVList(31, 31);
        assertEquals(spaExpected, evolutionLine.getIVRange(StatType.SPA));

        List<Integer> spdExpected = IVList.createIVList(31, 31);
        assertEquals(spdExpected, evolutionLine.getIVRange(StatType.SPD));

        List<Integer> speExpected = IVList.createIVList(0, 1);
        assertEquals(speExpected, evolutionLine.getIVRange(StatType.SPE));
    }

    @Test
    public void testShedinja() {
        EvolutionLine evolutionLine = new EvolutionLine("Shedinja", "Adamant");
        LevelInfo lvl100 = new LevelInfo(100, 0, 1, 225, 113, 80, 75, 100);
        evolutionLine.addLevelInfo(lvl100);
        evolutionLine.calcIVRanges();

        List<Integer> hpExpected = IVList.createIVList(0, 31);
        assertEquals(hpExpected, evolutionLine.getIVRange(StatType.HP));

        List<Integer> atkExpected = IVList.createIVList(20, 20);
        assertEquals(atkExpected, evolutionLine.getIVRange(StatType.ATK));

        List<Integer> spaExpected = IVList.createIVList(24, 24);
        assertEquals(spaExpected, evolutionLine.getIVRange(StatType.SPA));
    }

    @Test
    public void testStaryu() {
        EvolutionLine evolutionLine = new EvolutionLine("Staryu", "Gentle");
        LevelInfo lvl22 = new LevelInfo(22, 0, 52, 30, 31, 42, 39, 46);
        evolutionLine.addLevelInfo(lvl22);
        evolutionLine.calcIVRanges();

        List<Integer> atkExpected = IVList.createIVList(24, 28);
        assertEquals(atkExpected, evolutionLine.getIVRange(StatType.ATK));

        LevelInfo lvl23 = new LevelInfo(23, 0, 53, 31, 32, 44, 40, 48);
        evolutionLine.addLevelInfo(lvl23);
        evolutionLine.calcIVRanges();

        atkExpected = IVList.createIVList(24, 27);
        assertEquals(atkExpected, evolutionLine.getIVRange(StatType.ATK));

        LevelInfo lvl24 = new LevelInfo(24, 0, 55, 33, 33, 46, 41, 50);
        evolutionLine.addLevelInfo(lvl24);
        evolutionLine.calcIVRanges();

        atkExpected = IVList.createIVList(27, 27);
        assertEquals(atkExpected, evolutionLine.getIVRange(StatType.ATK));
    }
}
