package com.lululemon.openpdf.poc.repo;

import com.lululemon.openpdf.poc.entity.Internship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InternshipRepository extends JpaRepository<Internship,Integer> {

}
