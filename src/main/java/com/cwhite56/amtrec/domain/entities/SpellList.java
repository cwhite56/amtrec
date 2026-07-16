package com.cwhite56.amtrec.domain.entities;

import java.util.List;

import com.cwhite56.amtrec.domain.CasterClass;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
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
@Table(name = "spelllists")
public class SpellList {

    @Id
    @NotEmpty
    private String title;

    @NotEmpty
    @Enumerated(EnumType.STRING)
    private CasterClass casterClass;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private User user;

    @Size(max = 52, min = 43)
    private List<Integer> spentPoints;
    
}
