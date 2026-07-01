package com.cwhite56.amtrec.domain.entities;


import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
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
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
@ToString.Exclude
@EqualsAndHashCode.Exclude
private User user;

@OneToMany(mappedBy = "spellbook", cascade = CascadeType.ALL)
private List<SpellList> spellListCollection;



    
}
