package com.cwhite56.amtrec.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cwhite56.amtrec.domain.SpellList;
import com.cwhite56.amtrec.repositories.SpellListRepository;

@Service
public class SpellListServiceImpl implements SpellListService{

    private SpellListRepository spellListRepository;

    public SpellListServiceImpl(SpellListRepository spellListRepository) {
        this.spellListRepository = spellListRepository;
    }

    @Override
    public SpellList createSpellList(SpellList spellList) {
        return spellListRepository.save(spellList);
    }

    @Override
    public Optional<SpellList> getSpellList(SpellList spellList) {
        return spellListRepository.findById(spellList.getTitle());
    }

    @Override
    public SpellList updateSpellList(SpellList spellList) {
        return spellListRepository.save(spellList);
    }

    @Override
    public void deleteSpellList(SpellList spellList) {
        spellListRepository.deleteById(spellList.getTitle());
    }
    
}
