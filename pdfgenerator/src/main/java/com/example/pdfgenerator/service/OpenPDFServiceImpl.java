package com.example.pdfgenerator.service;


import com.example.pdfgenerator.entity.Internship;
import com.example.pdfgenerator.repo.InternshipRepository;
import com.itextpdf.text.log.Logger;
import com.itextpdf.text.log.LoggerFactory;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import java.io.ByteArrayOutputStream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;

import static java.awt.Color.BLACK;

@Service
public class OpenPDFServiceImpl implements OpenPDFService {
//    @Value("${output.directory}")
//    private String outputDirectory;
    private static final Logger logger = LoggerFactory.getLogger(OpenPDFServiceImpl.class);
    private static final Properties properties = loadProperties();
    private static final String OUTPUT_DIRECTORY  = properties.getProperty("output.directory");
    @Autowired
    private InternshipRepository internshipRepository;

    public byte[] generatePdf(Integer id) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            Document document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, baos);

            document.open();
            addContent(document, id);

            if (writer.getPageNumber() > 0) {
                document.close();
                writer.flush();
                return baos.toByteArray();
            } else {
                System.err.println("The document has no pages. No PDF will be generated.");
                return null;
            }
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Properties loadProperties() {
        Properties props = new Properties();
        try (InputStream input = OpenPDFServiceImpl.class.getClassLoader().getResourceAsStream("application.properties")) {
            if (input != null) {
                props.load(input);
            }
        } catch (IOException e) {
            logger.error("Error loading configuration properties", e);
        }
        return props;
    }



    public byte[] generatesPdf(Integer id) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            Document document = new Document();
            String absoluteFilePath = OUTPUT_DIRECTORY + "generated_pdf_" + id + ".pdf";

            PdfWriter writer = PdfWriter.getInstance(document, baos);
            document.open();
            addContent(document, id);
            document.close();
            writeToFile(baos.toByteArray(), absoluteFilePath);
            return baos.toByteArray();
        } catch (DocumentException | IOException e) {
            logger.error("Error during PDF generation", e);
            return null;
        }
    }

    private void writeToFile(byte[] data, String filePath) {
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            fos.write(data);
            logger.info("PDF generated successfully at: " + filePath);
        } catch (IOException e) {
            logger.error("Error writing PDF to file", e);
        }
    }



    private void addContent(Document document,Integer id) {
        try {
            Font boldFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16, BLACK);
            Font boldFonts = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10, BLACK);

            document.add(new Paragraph("PLOT NO 45,46\nCENTRAL PARK, PHASE-1, KONDAPUR\nRANGAREDDY, TELANGANA - 500084"));

            final var logoPath = new ClassPathResource("images/logo9.jpg").getInputStream();
            float scalePercentage = 50; // You can adjust this percentage
            float targetWidth = 200f; // Set the desired width
            float targetHeight = 100f; // Set the desired height

            final var logo = Image.getInstance(IOUtils.toByteArray(logoPath));
            logo.scalePercent(scalePercentage);
            logo.scaleAbsolute(targetWidth, targetHeight);

            document.add(logo);
            addEmptyLine(document, 2);
            Paragraph title = new Paragraph("Internship Offer Letter", boldFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            addEmptyLine(document, 2);



            document.add(new Paragraph("PLOT NO 45,46\nCENTRAL PARK, PHASE-1, KONDAPUR\nRANGAREDDY, TELANGANA - 500084"));
            document.add(new Paragraph("Contact: +91 81437 54111"));
            document.add(new Paragraph("E-Mail: hr@sscreativelabs.com"));
            Internship internship = internshipRepository.findById(id).orElse(null);

            addEmptyLine(document, 2);

//            document.add(new Paragraph(internship.getOfferLetterReleasingDate()));

            addEmptyLine(document, 1);

            document.add(new Paragraph("Mr."+internship.getName() ));

            document.add(new Paragraph(internship.getTown()));
            document.add(new Paragraph(internship.getDistrict()));
            document.add(new Paragraph("E-Mail:" +" "+internship.getEmail()));
            document.add(new Paragraph("Contact: +91"+" "+internship.getPhone()));




            addEmptyLine(document, 1);

            document.add(new Paragraph("Dear" + " " + internship.getName(),boldFonts));

            // Add space
            addEmptyLine(document, 1);

            document.add(new Paragraph("We are delighted to offer you an internship position as a"+" " + getById(id).getInternPosition() + " " + "at Sunshine"));
            document.add(new Paragraph("Creative Labs, commencing on" + " " + getById(id).getOfferLetterReleasingDate() +" ."+ "We believe your skills and enthusiasm will"));
            document.add(new Paragraph("be valuable additions to our team."));
            addEmptyLine(document, 1);

            document.add(new Paragraph("Details of the Internship:",boldFonts));
            addEmptyLine(document, 1);
            document.add(new Paragraph("Position:" + " " + internship.getInternPosition()));
            document.add(new Paragraph("Location: Hyderabad (Remote)"));
            document.add(new Paragraph("Compensation: No Stipend,"));
            document.add(new Paragraph("Reporting to: [Supervisor/Manager Name]"));
            addEmptyLine(document, 1);
            addEmptyLine(document, 1);
            addEmptyLine(document, 1);
            addEmptyLine(document, 1);
            addEmptyLine(document, 1);

            document.add(new Paragraph("Responsibilities:",boldFonts));
            addEmptyLine(document, 1);
            if ("Frontend Developer".equals(internship.getInternPosition())) {
                document.add(new Paragraph("As a Frontend Development Intern, you'll collaborate on creating visually "));
                document.add(new Paragraph("appealing and responsive web interfaces. Responsibilities include implementing "));
                document.add(new Paragraph("UI designs, structuring HTML markup, styling with CSS, and utilizing JavaScript and "));
                document.add(new Paragraph("frameworks for client-side interactivity. Gain hands-on experience in cross-browser "));
                document.add(new Paragraph("compatibility, responsive design, API integration, and contributing to a dynamic "));
                document.add(new Paragraph("development environment."));

            } else if ("AWS DevOps Engineer".equals(internship.getInternPosition())) {
                document.add(new Paragraph("Streamline processes and improve system reliability by Assisting in designing,implementing,"));
                document.add(new Paragraph("and maintaining automation tools.Support the team in deploying,managing,and monitoring"));
                document.add(new Paragraph("cloud-based applications.Participate in troubleshooting and resolving infrastructure issues."));

            } else if ("Software Tester".equals(internship.getInternPosition())) {

                document.add(new Paragraph("Tester plays a crucial role in the software development life cycle by ensuring that"));
                document.add(new Paragraph("software applications are of high quality, reliable, and free of defects, meeting client"));
                document.add(new Paragraph("expectations. Tester responsibilities may vary based on the development methodology and"));
                document.add(new Paragraph("the specific requirements of projects. Collaborating closely with the software development"));
                document.add(new Paragraph("team and the client."));

            } else if ("Backend Java Developer".equals(internship.getInternPosition())) {
                document.add(new Paragraph("As a Backend Java Developer, you'll contribute to building robust and efficient "));
                document.add(new Paragraph("server-side applications. Responsibilities include designing and implementing "));
                document.add(new Paragraph("scalable backend solutions, optimizing database performance, and ensuring "));
                document.add(new Paragraph("secure data management. Work with Java, frameworks, and database technologies to "));
                document.add(new Paragraph("support the development of server-side logic. Gain hands-on experience in handling "));
                document.add(new Paragraph("data storage, API development, and collaborating in a dynamic development "));
                document.add(new Paragraph("environment."));
            }
            else {
                document.add(new Paragraph("As a Backend  Developer, you'll contribute to building robust and efficient "));
                document.add(new Paragraph("server-side applications. Responsibilities include designing and implementing "));
                document.add(new Paragraph("scalable backend solutions, optimizing database performance, and ensuring "));
                document.add(new Paragraph("secure data management. Work with Java, frameworks, and database technologies to "));
                document.add(new Paragraph("support the development of server-side logic. Gain hands-on experience in handling "));
                document.add(new Paragraph("data storage, API development, and collaborating in a dynamic development "));
                document.add(new Paragraph("environment."));
            }

            // Add space
            addEmptyLine(document, 1);

            document.add(new Paragraph("Please indicate your acceptance of this internship offer by signing and returning a copy of this "));
            document.add(new Paragraph("letter by"+" "+ internship.getSubmissionDate()+"."+"We look forward to welcoming you to Sunshine Creative Labs"));
            document.add(new Paragraph("and working together towards shared success."));

            addEmptyLine(document, 1);

            document.add(new Paragraph("Sincerely,",boldFonts));
            addEmptyLine(document, 1);

            document.add(new Paragraph("Sunshine Creative Labs",boldFonts));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addEmptyLine(Document document, int lines) throws Exception {
        for (int i = 0; i < lines; i++) {
            document.add(new Paragraph(" "));
        }
    }



    public Internship create(Internship internship) {

        return  internshipRepository.save(internship);
    }

    public Internship getById(Integer id) {
        return internshipRepository.findById(id).orElse(null);
    }

    @Override
    public String delete(Integer id) {
        internshipRepository.deleteById(id);
        return "deleted";
    }



    public Internship update(Internship updatedInternship,Integer id) {
        // Check if the internship with the given ID exists in the database
        Optional<Internship> existingInternshipOptional = internshipRepository.findById(updatedInternship.getId());

        if (existingInternshipOptional.isPresent()) {
            // If the internship exists, update its fields with the new values
            Internship existingInternship = existingInternshipOptional.get();

            // Update specific fields
            existingInternship.setName(updatedInternship.getName());
            existingInternship.setOfferLetterReleasingDate(updatedInternship.getOfferLetterReleasingDate());
            existingInternship.setTown(updatedInternship.getTown());
            existingInternship.setDistrict(updatedInternship.getDistrict());
            existingInternship.setInternPosition(updatedInternship.getInternPosition());
            existingInternship.setCommencmentDate(updatedInternship.getCommencmentDate());
            existingInternship.setSubmissionDate(updatedInternship.getSubmissionDate());
            existingInternship.setEmail(updatedInternship.getEmail());
            existingInternship.setPhone(updatedInternship.getPhone());


            return internshipRepository.save(existingInternship);
        } else {
            return null;
        }
    }

}
