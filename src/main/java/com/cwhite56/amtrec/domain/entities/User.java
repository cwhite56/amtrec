package com.cwhite56.amtrec.domain.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderColumn;
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
@Table(name = "users")
public class User {


@Id
private String username;

private String password;

@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
@ToString.Exclude
@EqualsAndHashCode.Exclude
private List<SpellList> spellbook;

public void deleteSpellList(SpellList spellList) {
    spellbook.remove(spellList);
    spellList.setUser(null);
}



    
}
