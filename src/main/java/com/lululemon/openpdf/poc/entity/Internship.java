package com.lululemon.openpdf.poc.entity;



import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
@Entity
@Data
@Table(name = "Employees_Table")
public class Internship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    private String name;
    private String offerLetterReleasingDate;
    private String town;
    private String district;
    private String internPosition;
    private String commencmentDate;
    private String submissionDate;
    private String email;
   private String phone;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOfferLetterReleasingDate() {
        return offerLetterReleasingDate;
    }

    public void setOfferLetterReleasingDate(String offerLetterReleasingDate) {
        this.offerLetterReleasingDate = offerLetterReleasingDate;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }







    public String getInternPosition() {
        return internPosition;
    }

    public void setInternPosition(String internPosition) {
        this.internPosition = internPosition;
    }

    public String getCommencmentDate() {
        return commencmentDate;
    }

    public void setCommencmentDate(String commencmentDate) {
        this.commencmentDate = commencmentDate;
    }

    public String getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(String submissionDate) {
        this.submissionDate = submissionDate;
    }

    // Other methods if needed
}
