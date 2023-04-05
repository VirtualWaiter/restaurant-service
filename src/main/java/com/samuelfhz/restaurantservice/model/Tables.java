package com.samuelfhz.restaurantservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.samuelfhz.restaurantservice.model.enums.RestaurantState;
import com.samuelfhz.restaurantservice.model.enums.TableState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


import javax.persistence.*;

@Entity
@Table(name = "TABLES")
@Data
@AllArgsConstructor
@Builder
public class Tables {

    private static final long serialVersionUID = 1L;

    public Tables(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "QRCODE")
    private String qrcode;

    @Column(name = "NUMBER")
    private int number;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "RESTAURANT_ID", referencedColumnName = "ID")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "items"})
    @JsonIgnore
    private Restaurant restaurant;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATE")
    private TableState tableState;
}
