package com.example.qikserve.repositories;

import com.example.qikserve.model.Promotion;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromotionRepository extends PagingAndSortingRepository<Promotion, Long>, JpaSpecificationExecutor<Promotion> {
}
