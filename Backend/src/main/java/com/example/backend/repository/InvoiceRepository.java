package com.example.backend.repository;

import com.example.backend.model.CartItem;
//import com.example.backend.model.InvoiceData;
import com.example.backend.model.InvoicedataModel;
import com.example.backend.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<InvoicedataModel, Long> {

}
