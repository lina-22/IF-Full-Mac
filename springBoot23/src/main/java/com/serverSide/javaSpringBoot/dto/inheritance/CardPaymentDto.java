package com.serverSide.javaSpringBoot.dto.inheritance;

import com.serverSide.javaSpringBoot.model.PaymentModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardPaymentDto extends PaymentModel {
    private String account_number;
}
