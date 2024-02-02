package com.example.pdfgenerator.repo;

import com.example.pdfgenerator.entity.Internship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InternshipRepository extends JpaRepository<Internship,Integer> {

}