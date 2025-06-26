package com.spellbook.app;
import com.spellbook.core.Spell;
import com.spellbook.spells.ExitSpell;
import com.spellbook.spells.FireballSpell;
import com.spellbook.spells.HealingSpell;
import com.spellbook.spells.TeleportSpell;

import java.util.ArrayList;
import java.util.Objects;

public class SpellBook {
    //Declares the array list of spells
    private ArrayList<Spell> spells = new ArrayList<>();

    //constructor class
    public SpellBook() {
        //Populates the spellbook with at least 4 implementations of different spells
        spells.add(new ExitSpell());
        spells.add(new FireballSpell());
        spells.add(new HealingSpell());
        spells.add(new TeleportSpell());
    }

    public void tryIncantation(String incantation) {
        for (Spell currentSpell : this.spells) {
            if (incantation.equalsIgnoreCase(currentSpell.getIncantation())) {
            //if (Objects.equals(incantation, currentSpell.getIncantation())) {
                currentSpell.cast();
                return;
            }
        }System.out.println("The spell fizzled out! Try again.");
    }
}



