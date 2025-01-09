package com.example.backend.mapper;

import com.example.backend.dto.InvoiceDataDTO;
import com.example.backend.model.InvoicedataModel;
import com.example.backend.model.Order;
import com.example.backend.model.User;

public class InvoiceMapper {
    public static InvoicedataModel toEntity(InvoiceDataDTO dto) {
        InvoicedataModel entity = new InvoicedataModel();
        entity.setNip(dto.getNip());
        entity.setCompanyName(dto.getCompanyName());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setEmail(dto.getEmail());
        entity.setPostCode(dto.getPostCode());
        entity.setStreetAddress(dto.getStreetAddress());
        entity.setApartmentNumber(dto.getApartmentNumber());
        entity.setCity(dto.getCity());

        if (dto.getUserId() != null) {
            User user = new User();
            user.setId(dto.getUserId());
            entity.setUser(user);
        }

        if (dto.getOrderId() != null) {
            Order order = new Order();
            order.setId(dto.getOrderId());
            entity.setOrder(order);
        }

        return entity;
    }
}
