package com.wb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wb.entity.Portfolio;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long>{

}
