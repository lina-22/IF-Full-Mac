package com.serverSide.javaSpringBoot.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product_line")
public class ReservationProductForSale {

    @EmbeddedId
    private ReservationProductForSaleId id = new ReservationProductForSaleId();

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("reservationId")
    private ReservationModel reservationModel;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("id")
    private ProductForSale productForSale;

    private int qty;

}
