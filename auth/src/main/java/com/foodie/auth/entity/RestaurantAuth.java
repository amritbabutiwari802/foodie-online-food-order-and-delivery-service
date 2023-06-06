package com.foodie.auth.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class RestaurantAuth {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private UUID restaurantId;

    @Column(unique = true)
    private UUID branchId;

    @Column(unique = true)
    private String email;
    private String password;
}
