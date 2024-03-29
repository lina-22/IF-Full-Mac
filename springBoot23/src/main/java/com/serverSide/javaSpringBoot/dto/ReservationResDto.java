package com.serverSide.javaSpringBoot.dto;

import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationResDto {
    long id;
    String status;
    String reference;
    Date expireDate;
    long userId;
    String email;
    String firstName;
    String lastName;
    List<ReservedProductResDto> reservedProductResDtos;
}
