package com.cwhite56.amtrec.services;

import java.util.ArrayList;

public class SpellListGen {

    public static void main(String[] args) {
        ArrayList<Integer> spellList = new ArrayList<>();
        for(int i = 0; i < 43; i++) {
            spellList.add(i, 0);
        }
        System.out.println(spellList);
    }
    
}
