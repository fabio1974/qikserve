package com.example.qikserve.repositories;

import com.example.qikserve.model.Basket;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasketRepository extends PagingAndSortingRepository<Basket, Long>, JpaSpecificationExecutor<Basket> {

}
