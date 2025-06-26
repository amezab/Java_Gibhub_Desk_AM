package com.spellbook.spells;

import com.spellbook.core.Spell;

public class FireballSpell implements Spell {
    @Override
    public void cast() {
        System.out.println("An orb of fire crackles in your hand and shoots forward!");
        System.out.println("CRACKLE! ZAP! A burst of flames!");
    }

    @Override
    public String getIncantation() {
        return "Fireball";
    }
}
