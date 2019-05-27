package ivcalc.Calculator;

import ivcalc.Data.SelectNatures;
import ivcalc.Util.StatType;

import java.util.ArrayList;
import java.util.List;

public class EvolutionLine {

    private SelectNatures sn = new SelectNatures();

    private Pokemon pokemon;
    private String nature;
    private List<LevelInfo> levelInfoList = new ArrayList<>();

    public EvolutionLine(String name, String nature) {
        this.pokemon = new Pokemon(name);
        this.nature = nature;
    }

    private int calcStat(int evoIndex, StatType statType, int lvl, int iv, int ev) {
        int baseStat = getBaseStat(evoIndex, statType);
        int temp = (int) ((2 * baseStat + iv + ev / 4) * (double) lvl / 100);
        if (statType == StatType.HP) {
            return temp + lvl + 10;
        } else {
            return (int) ((temp + 5) * sn.select(nature, statType));
        }
    }

    private int getBaseStat(int evoIndex, StatType statType) {
        return evolution(evoIndex).getBaseStats().get(statType);
    }

    private Pokemon evolution(int evoIndex) {
        Pokemon current = pokemon;
        while (evoIndex > 0) {
            current = current.getEvolution();
            evoIndex--;
        }
        return current;
    }
}
