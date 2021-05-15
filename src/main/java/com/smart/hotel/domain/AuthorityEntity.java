package com.smart.hotel.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

/**
 * An authority (a security role) used by Spring Security.
 */
@Entity
@Getter
@Setter
@Table(name = "authorities")
public class AuthorityEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 50)
    @Id
    @Column(length = 50)
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AuthorityEntity)) {
            return false;
        }
        return Objects.equals(name, ((AuthorityEntity) o).name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Authority{" +
            "name='" + name + '\'' +
            "}";
    }
}
