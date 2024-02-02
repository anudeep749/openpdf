package com.example.pdfgenerator.service;

import com.example.pdfgenerator.entity.Internship;

public interface OpenPDFService {
    Internship create(Internship internship);

    Internship getById(Integer id);

    String delete(Integer id);

    Internship update(Internship updatedInternship, Integer id);
}
