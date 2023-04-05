package com.samuelfhz.restaurantservice.model;

import com.samuelfhz.restaurantservice.model.enums.RestaurantState;
import lombok.*;

import javax.persistence.*;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "RESTAURANT")
@Data
@AllArgsConstructor
@Builder
public class Restaurant {

    private static final long serialVersionUID = 1L;

    public Restaurant(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "ADDRESS")
    private String address;

    @OneToMany(mappedBy = "restaurant")
    private List<Tables> tables;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATE")
    private RestaurantState restaurantState;

}
