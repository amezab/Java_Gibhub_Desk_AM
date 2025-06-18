package com.spellbook.spells;
import com.spellbook.core.Spell;

public class TeleportSpell implements Spell {
    @Override
    public void cast() {
        System.out.println("The world distorts... you appear somewhere else!");
    }

    @Override
    public String getIncantation() {
        return "Teleport";
    }
}
