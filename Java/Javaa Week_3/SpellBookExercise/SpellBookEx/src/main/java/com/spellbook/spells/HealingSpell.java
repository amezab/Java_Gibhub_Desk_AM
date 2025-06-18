package com.spellbook.spells;
import com.spellbook.core.Spell;

public class HealingSpell implements Spell {
    @Override
    public void cast() {
        System.out.println("A warm light envelops you, restoring your vitality.");
    }

    @Override
    public String getIncantation() {
        return "Healing...";
    }
}
