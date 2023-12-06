package com.serverSide.javaSpringBoot.model.inheritance;

import com.serverSide.javaSpringBoot.model.PaymentModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@Table(name="paypal")
public class PaypalPaymentModel extends PaymentModel {

    @Column(name = "account_email", nullable = false)
    private String account_email;

    public PaypalPaymentModel(long l,
                              BigDecimal i,
                              Date date,
                              String test_details,
                              String s) {
        super(l, i, date, test_details);
        this.account_email = s;
    }
}
