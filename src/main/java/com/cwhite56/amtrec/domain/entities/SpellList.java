package com.cwhite56.amtrec.domain.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

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
@IdClass(SpellListId.class)
@Table(name = "spelllists")
public class SpellList {

    @Id
    @NotEmpty
    private String title;

    
    @Pattern(regexp = "WIZARD|DRUID|BARD|HEALER")
    private String casterClass;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Id
    private User user;

    private List<Integer> spentPoints;

    private List<Integer> pointsRemainingByLevel;

    private String exp1;
    private String exp2;

    private boolean LTP;

    private List<String> archtypes;
    
}
