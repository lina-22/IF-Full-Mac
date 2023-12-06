package com.serverSide.javaSpringBoot.dto.inheritance;

import com.serverSide.javaSpringBoot.model.PaymentModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VisaPaymentDto extends PaymentModel {
    private String card_number;
    private Date expiration_date;
}
