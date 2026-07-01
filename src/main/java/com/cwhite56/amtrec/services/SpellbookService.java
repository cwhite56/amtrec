package com.cwhite56.amtrec.services;

import java.util.Optional;

import com.cwhite56.amtrec.domain.entities.Spellbook;

public interface SpellbookService {
    //POST
    public Spellbook createSpellbook(Spellbook spellbook);

    //GET
    public Optional<Spellbook> getSpellbook(Spellbook spellbopk);

    //PUT
    public Spellbook updateSpellbook(Spellbook spellbook);
}
