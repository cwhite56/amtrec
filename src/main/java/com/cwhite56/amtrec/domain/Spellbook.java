package com.cwhite56.amtrec.domain;


import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
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
private String id;

@OneToOne
@MapsId
@JoinColumn(name = "user_id")
private User user;

@OneToMany(mappedBy = "spellbook")
private List<SpellList> spellListCollection = new ArrayList<>();



    
}
