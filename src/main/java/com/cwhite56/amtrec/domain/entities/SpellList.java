package com.cwhite56.amtrec.domain.entities;

import java.util.ArrayList;
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
    @JoinColumn(name = "user_id")
    private User user;

    private List<Integer> spentPoints;
    
}
