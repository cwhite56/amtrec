package com.cwhite56.amtrec.services;

import java.util.Optional;

import com.cwhite56.amtrec.domain.SpellList;

public interface SpellListService {
    //POST
    public SpellList createSpellList(SpellList spellList);

    //GET
    public Optional<SpellList> getSpellList(SpellList spellList);

    //PUT
    public SpellList updateSpellList(SpellList spellList);

    //DELETE
    public void deleteSpellList(SpellList spellList);
}
