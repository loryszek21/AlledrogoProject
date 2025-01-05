'use server';
import Table from "../Table/Table";
import React from "react";
function OrderTable() {
    const orderData = [
      {
        orderDate: "2025-01-03",
        orderStatus: "COMPLETED",
        cartDTO: [
          { productName: "Laptop", quantity: 2, price: 1999.99 },
          { productName: "Tablet", quantity: 2, price: 499.99 },
          { productName: "Dupa", quantity: 1, price: 199.99 },
        ],
        userName: "Lorych",
      },
      {
        orderDate: "2025-01-03",
        orderStatus: "COMPLETED",
        cartDTO: [
          { productName: "Laptop", quantity: 3, price: 1999.99 },
          { productName: "Smartwatch", quantity: 4, price: 199.99 },
        ],
        userName: "Lorych",
      },
    ];
  
    return (
      <Table
        title="Previous Orders"
        headers={["Product", "Quantity", "Price", "Order Date", "Status"]}
        items={orderData}
        renderRow={(order, orderIndex) => (
          <React.Fragment key={`order-${orderIndex}`}>
            {order.cartDTO.map((item, itemIndex) => (
              <tr key={`order-${orderIndex}-item-${itemIndex}`}>
                <td className="p-4">{item.productName}</td>
                <td className="p-4">{item.quantity}</td>
                <td className="p-4">${item.price.toFixed(2)}</td>
                {itemIndex === 0 && (
                  <>
                    <td className="p-4" rowSpan={order.cartDTO.length}>
                      {order.orderDate}
                    </td>
                    <td className="p-4" rowSpan={order.cartDTO.length}>
                      {order.orderStatus}
                    </td>
                  </>
                )}
              </tr>
            ))}
            {/* Spacer row between orders */}
            <tr key={`spacer-${orderIndex}`}>
              <td colSpan={5} className="p-8"></td>
            </tr>
          </React.Fragment>
        )}
      />
    );
  }
  
  export default OrderTable;
  