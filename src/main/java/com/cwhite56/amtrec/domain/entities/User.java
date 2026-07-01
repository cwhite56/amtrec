package com.cwhite56.amtrec.domain.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
@Table(name = "users")
public class User {


@Id
private String username;

private String password;

@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
@ToString.Exclude
@EqualsAndHashCode.Exclude
private Spellbook spellbook;



    
}
