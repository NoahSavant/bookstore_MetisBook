package com.metis.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.metis.book.model.Aim;

@Repository
public interface AimRepository extends JpaRepository<Aim, Long> {
    public Aim findByYear(int year);
}