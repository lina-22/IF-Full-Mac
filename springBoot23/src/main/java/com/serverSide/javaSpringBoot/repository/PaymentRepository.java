package com.serverSide.javaSpringBoot.repository;

import com.serverSide.javaSpringBoot.model.PaymentModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface PaymentRepository extends CrudRepository<PaymentModel, Long> {
}
