package com.smart.hotel.domain;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Builder
@Table(name = "numbers")
@NoArgsConstructor
@AllArgsConstructor
public class NumberEntity extends AbstractAuditingEntity {

    @Id
    private String number;

    @Column(name = "level", nullable = false)
    private String level;

    @Column(name = "price", nullable = false, scale = 2, precision = 10)
    private BigDecimal price;

    @Column(name = "users_amount", nullable = false)
    private int usersAmount;

    @Column(name = "door_locked", nullable = false)
    private boolean doorLocked;

    @Column(name = "alarm_enabled", nullable = false)
    private boolean alarmEnabled;

    @Column(name = "registered", nullable = false)
    private boolean registered;

    @Column(name = "locked", nullable = false)
    private boolean locked;

    @ManyToOne
    public UserEntity user;


    public boolean isAssinged() {
        return user != null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NumberEntity numberEntity1 = (NumberEntity) o;
        return number.equals(numberEntity1.number);
    }

    @Override
    public int hashCode() {
        return number.hashCode();
    }
}
