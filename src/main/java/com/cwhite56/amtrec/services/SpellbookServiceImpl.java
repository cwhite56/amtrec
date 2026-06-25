package com.cwhite56.amtrec.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cwhite56.amtrec.domain.Spellbook;
import com.cwhite56.amtrec.repositories.SpellbookRepository;

@Service
public class SpellbookServiceImpl implements SpellbookService{
    
    private SpellbookRepository spellbookRepository;

    public SpellbookServiceImpl(SpellbookRepository spellbookRepository) {
        this.spellbookRepository = spellbookRepository;
    }

    @Override
    public Spellbook createSpellbook(Spellbook spellbook) {
        return spellbookRepository.save(spellbook);
    }

    @Override
    public Optional<Spellbook> getSpellbook(Spellbook spellbook) {
        return spellbookRepository.findById(spellbook.getUser().getUsername());
    }

    @Override
    public Spellbook updateSpellbook(Spellbook spellbook) {
        return spellbookRepository.save(spellbook);
    }

    @Override
    public void deleteSpellbook(Spellbook spellbook) {
        spellbookRepository.deleteById(spellbook.getUser().getUsername());
    }
}
