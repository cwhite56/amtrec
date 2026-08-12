package com.cwhite56.amtrec.domain.entities;

import java.io.Serializable;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SpellListId implements Serializable{

    private User user;
    private String title;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpellListId that = (SpellListId) o;
        return Objects.equals(user, that.user) && Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, title);
    }

    
}
