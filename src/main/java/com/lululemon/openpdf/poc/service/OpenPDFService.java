package com.lululemon.openpdf.poc.service;

import com.lululemon.openpdf.poc.entity.Internship;

public interface OpenPDFService {
Internship create(Internship internship);

    Internship getById(Integer id);

    String delete(Integer id);

    Internship update(Internship updatedInternship, Integer id);
}

