package com.cwhite56.amtrec.domain;


import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
@Table(name = "spellbooks")
public class Spellbook {

@Id
@OneToOne
@JoinColumn(name = "user_id")
private User user;

@OneToMany(mappedBy = "spellbook")
private List<SpellList> spellListCollection;



    
}
