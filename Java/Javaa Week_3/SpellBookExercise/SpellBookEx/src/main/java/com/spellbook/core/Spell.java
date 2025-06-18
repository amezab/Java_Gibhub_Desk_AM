package com.spellbook.core;
//The interface defines the required "whats." The classes that implement it provide the actual "hows."
public interface Spell {

    //Method to execute the spell
    //Thinking about a house  with a room, this room has to have a door and a window-Interface
    void cast();
    //Method to obtain the magic words for the spell
    String getIncantation();


}
