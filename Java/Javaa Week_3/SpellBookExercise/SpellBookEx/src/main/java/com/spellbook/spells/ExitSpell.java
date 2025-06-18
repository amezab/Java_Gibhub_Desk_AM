package com.spellbook.spells;
import com.spellbook.core.Spell;

public class ExitSpell implements Spell {
    @Override
    public void cast() {
        System.out.println("A portal is opening under you, Good bye Wizard!");
        System.exit(0);
    }

    @Override
    public String getIncantation() {
        return "Exit";
    }
}
