package com.team4.domain.division;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DivisionRepository extends CrudRepository<Division, Long> {
    List<Division> findAll();
}
