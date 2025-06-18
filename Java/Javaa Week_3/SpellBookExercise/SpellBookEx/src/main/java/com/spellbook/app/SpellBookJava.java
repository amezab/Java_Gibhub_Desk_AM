package com.spellbook.app;
import java.util.Scanner;
public class SpellBookJava {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // The actual SpellBook object that holds all our spells and logic
        SpellBook spellBook = new SpellBook();

        while(true){
            System.out.println("Recite a spell: ");
            String spell = scanner.nextLine();
            spellBook.tryIncantation(spell);
        }
    }

}
