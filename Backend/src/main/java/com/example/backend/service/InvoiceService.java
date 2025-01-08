package com.example.backend.service;

import com.example.backend.dto.CartDTO;
import com.example.backend.model.Order;
import com.example.backend.model.OrderItem;
import com.example.backend.repository.OrderRepository;
import com.itextpdf.text.pdf.PdfPCell;
import org.springframework.stereotype.Service;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.example.backend.dto.CartDTO;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class InvoiceService {


    private final OrderRepository orderRepository;


    public InvoiceService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public byte[] generateInvoice(Integer orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new IllegalArgumentException("Order not found"));
        if (order == null) {
            throw new IllegalArgumentException("Order not found");
        }

        try(ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            Document document = new Document();
            PdfWriter.getInstance(document, baos);
            document.open();

            document.add(new Paragraph("Invoice " + orderId, FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18 )));
            document.add(new Paragraph("Order data: " + order.getOrderDate()));
            document.add(Chunk.NEWLINE);

            PdfPTable headerTable = new PdfPTable(2); // 2 kolumny
            headerTable.setWidthPercentage(100); // Szerokość tabeli 100% strony
            headerTable.setWidths(new float[]{1, 1}); // Proporcje kolumn: lewa i prawa są równe


            PdfPCell leftCell = new PdfPCell();
            leftCell.setBorder(Rectangle.NO_BORDER); // Bez ramki
            leftCell.addElement(new Paragraph("Your Company Name", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12)));
            leftCell.addElement(new Paragraph("Your Address"));
            leftCell.addElement(new Paragraph("Your City, Postal Code"));
            leftCell.addElement(new Paragraph("Your Contact Info"));

            PdfPCell rightCell = new PdfPCell();
            rightCell.setBorder(Rectangle.NO_BORDER); // Bez ramki
            rightCell.addElement(new Paragraph("Customer Name", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12)));
            rightCell.addElement(new Paragraph("Customer Address"));
            rightCell.addElement(new Paragraph("Customer City, Postal Code"));
            rightCell.addElement(new Paragraph("Customer Contact Info"));


            headerTable.addCell(leftCell);
            headerTable.addCell(rightCell);

            document.add(headerTable);
            document.add(Chunk.NEWLINE);

            PdfPTable table = new PdfPTable(7);
            table.addCell("Product ");
            table.addCell("Quantity");
            table.addCell("Price (Net)");
            table.addCell("VAT Rate");
            table.addCell("VAT Amount");
            table.addCell("Price (Gross)");
            table.addCell("Total");
            double totalNet = 0;
            double totalVat = 0;
            double totalGross = 0;

            for (OrderItem orderItem : order.getOrderItems()) {
                double priceNet = orderItem.getProduct().getProduct_price();
                double vatRate = orderItem.getProduct().getVatRate().getRateValue();
                double vatAmount = priceNet * vatRate * 0.01;
                double priceGross = priceNet + vatAmount;
                double total = priceGross * orderItem.getQuantity();

                table.addCell(orderItem.getProduct().getProductName());
                table.addCell(String.valueOf(orderItem.getQuantity()));
                table.addCell(String.format("%.2f", priceNet));
                table.addCell(String.format("%.2f", vatRate) + "%");
                table.addCell(String.format("%.2f", vatAmount));
                table.addCell(String.format("%.2f", priceGross));
                table.addCell(String.format("%.2f", total));

                totalNet += priceNet * orderItem.getQuantity();
                totalVat += vatAmount * orderItem.getQuantity();
                totalGross += priceGross * orderItem.getQuantity();
            }

            document.add(table);


            document.add(Chunk.NEWLINE);

            PdfPTable totalTable = new PdfPTable(6);
            totalTable.setWidths(new float[]{2, 0, 2, 0, 2, 0}); // Ustawienie proporcji szerokości kolumn

// Pierwszy wiersz - puste komórki
            totalTable.addCell("");  // Kolumna 1
            totalTable.addCell("");  // Kolumna 2
            totalTable.addCell("Total Net:");  // Kolumna 3
            totalTable.addCell("");  // Kolumna 4
            totalTable.addCell(String.format("%.2f", totalNet));  // Kolumna 5
            totalTable.addCell("");  // Kolumna 6

// Drugi wiersz - puste komórki
            totalTable.addCell("");  // Kolumna 1
            totalTable.addCell("");  // Kolumna 2
            totalTable.addCell("Total VAT:");  // Kolumna 3
            totalTable.addCell("");  // Kolumna 4
            totalTable.addCell(String.format("%.2f", totalVat));  // Kolumna 5
            totalTable.addCell("");  // Kolumna 6

// Trzeci wiersz - puste komórki
            totalTable.addCell("");  // Kolumna 1
            totalTable.addCell("");  // Kolumna 2
            totalTable.addCell("Total Gross:");  // Kolumna 3
            totalTable.addCell("");  // Kolumna 4
            totalTable.addCell(String.format("%.2f", totalGross));  // Kolumna 5
            totalTable.addCell("");
            document.add(totalTable);
            document.add(Chunk.NEWLINE);

            PdfPTable signatureTable = new PdfPTable(2);
            signatureTable.setWidthPercentage(100);
            signatureTable.setWidths(new float[]{1, 1}); // Dwie kolumny

// Komórka dla podpisu wystawcy
            PdfPCell leftSignatureCell = new PdfPCell();
            leftSignatureCell.setBorder(Rectangle.NO_BORDER);
            leftSignatureCell.addElement(new Paragraph("Signature of the issuer:", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12)));

// Komórka dla podpisu odbiorcy
            PdfPCell rightSignatureCell = new PdfPCell();
            rightSignatureCell.setBorder(Rectangle.NO_BORDER);
            rightSignatureCell.addElement(new Paragraph("Signature of the recipient:", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12)));

// Dodanie komórek do tabeli
            signatureTable.addCell(leftSignatureCell);
            signatureTable.addCell(rightSignatureCell);

            document.add(signatureTable);

            document.close();
            return baos.toByteArray();

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }



    }

}
