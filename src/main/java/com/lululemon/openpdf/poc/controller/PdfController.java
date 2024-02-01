package com.lululemon.openpdf.poc.controller;

import com.lululemon.openpdf.poc.entity.Internship;
import com.lululemon.openpdf.poc.service.OpenPDFService;
import com.lululemon.openpdf.poc.service.OpenPDFServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@RequestMapping("/api/v1/pdf")
public class PdfController {

    @Autowired
    private OpenPDFService openPDFService;
    @Autowired
    OpenPDFServiceImpl openPDFServiceImpl;

    @Autowired
    HttpServletResponse response;
    @GetMapping("/generates/{id}")
    public ResponseEntity<byte[]> generatePdff(@PathVariable int id) throws Exception {
        byte[] pdfContent = openPDFServiceImpl.generatesPdf(id);

        if (pdfContent != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "generated_pdf_" + id + ".pdf");

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(pdfContent.length)
                    .body(pdfContent);
        } else {
            return ResponseEntity.badRequest().body("Error generating PDF".getBytes());
        }
    }

    @GetMapping("/generate/{id}")
    public ResponseEntity<byte[]> generatePdf(@PathVariable int id) throws IOException {
        byte[] pdfContent = openPDFServiceImpl.generatePdf(id);
        if (pdfContent != null) {

            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=generated.pdf");
            response.getOutputStream().write(pdfContent);
            response.getOutputStream().flush();
        } else {
            return ResponseEntity.badRequest().body("Error generating PDF".getBytes());
        }
        return null;
    }

        @PostMapping("/send")
        public Internship sendDetails (@RequestBody Internship internship){
            return openPDFService.create(internship);
        }

        @GetMapping("/{id}")
        public Internship getDetails (@PathVariable Integer id){
            return openPDFService.getById(id);

        }
        @DeleteMapping("/{id}")
        public String deleteIntern (@PathVariable Integer id){
            openPDFService.delete(id);
            return "deleted Successfully";
        }
        @PutMapping("/{id}")
        public ResponseEntity<Internship> updateInternship (@PathVariable Integer id, @RequestBody Internship
        updatedInternship){
            updatedInternship.setId(id);

            Internship result = openPDFService.update(updatedInternship, id);

            if (result != null) {
                return ResponseEntity.ok(result);
            } else {
                return ResponseEntity.notFound().build();
            }
        }
    }

