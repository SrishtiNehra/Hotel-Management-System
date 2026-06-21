package com.hotel.Hotel_Reservation_Management.controller;

import com.hotel.Hotel_Reservation_Management.dto.BillingDTO;
import com.hotel.Hotel_Reservation_Management.entity.Billing;
import com.hotel.Hotel_Reservation_Management.repository.BillingRepository;
import com.hotel.Hotel_Reservation_Management.service.BillingService;
import com.hotel.Hotel_Reservation_Management.validator.BillingValidator;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@RestController
@RequestMapping("/api/billings")
public class BillingController {

    @Autowired
    private BillingService billingService;

    @Autowired
    private BillingValidator billingValidator;
    
    @Autowired
    private BillingRepository billingRepository;

    @PostMapping
    public BillingDTO create(@RequestBody BillingDTO dto) {
        billingValidator.validate(dto);
        return billingService.createBill(dto);
    }

    @GetMapping("/{id}")
    public BillingDTO getById(@PathVariable Long id) {
        return billingService.getBillById(id);
    }

    @GetMapping("/reservation/{reservationId}")
    public BillingDTO getByReservation(@PathVariable Long reservationId) {
        return billingService.getBillByReservation(reservationId);
    }

//    @GetMapping
//    public List<BillingDTO> getAll() {
//        return billingService.getAllBills();
//    }
    
    @GetMapping("/my")
    public List<BillingDTO> getMyBills(Authentication authentication) {

        return billingService.getBillsByLoggedInUser(authentication);
    }

    @PutMapping("/{id}")
    public BillingDTO update(@PathVariable Long id, @RequestBody BillingDTO dto) {
        return billingService.updateBill(id, dto);
    }
    
    @GetMapping("/customer/{customerId}")
    public List<BillingDTO> getByCustomer(@PathVariable Long customerId) {
        return billingService.getBillsByCustomer(customerId);
    }
    
    @GetMapping("/{id}/pdf")
    public void downloadPdf(@PathVariable Long id, HttpServletResponse response) throws Exception {

        Billing bill = billingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bill not found"));

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition",
                "attachment; filename=invoice_" + id + ".pdf");

        Document doc = new Document();
        PdfWriter.getInstance(doc, response.getOutputStream());

        doc.open();

        // ===== TITLE =====
        Paragraph title = new Paragraph("HOTEL INVOICE");
        title.setAlignment(Paragraph.ALIGN_CENTER);
        doc.add(title);

        doc.add(new Paragraph(" ")); // spacing

        // ===== TABLE =====
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);

        table.addCell("Bill ID");
        table.addCell(String.valueOf(bill.getBillingId()));

        table.addCell("Reservation ID");
        table.addCell(String.valueOf(bill.getReservation().getReservationId()));

        table.addCell("Amount");
        table.addCell("₹ " + bill.getAmount());

        table.addCell("Payment Status");
        table.addCell(String.valueOf(bill.getPaymentStatus()));

        table.addCell("Payment Date");
        table.addCell(String.valueOf(bill.getPaymentDate()));

        // ===== ADD CHECK-IN / CHECK-OUT =====
        // (You must fetch reservation data if not in Billing entity)
        table.addCell("Check-In");
        table.addCell(bill.getReservation().getPlannedCheckIn().toString());

        table.addCell("Check-Out");
        table.addCell(bill.getReservation().getPlannedCheckOut().toString());

        doc.add(table);

        doc.add(new Paragraph(" "));

        // ===== FOOTER =====
        Paragraph footer = new Paragraph("Thank you for choosing our hotel!");
        footer.setAlignment(Paragraph.ALIGN_CENTER);
        doc.add(footer);

        doc.close();
    }
    
    @GetMapping("/admin")
    public List<BillingDTO> getAllBillsForAdmin() {
        return billingService.getAllBills();
    }
}