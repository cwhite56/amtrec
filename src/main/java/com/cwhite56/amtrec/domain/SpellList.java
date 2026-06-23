package com.cwhite56.amtrec.domain;

import java.util.List;

import jakarta.persistence.Entity;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "spelllists")
public class SpellList {

    @Id
    private String title;

    @ManyToOne
    @JoinColumn(name = "spellbook_id")
    private Spellbook spellbook;

    private List<Integer> spentPoints;
    
}
