"use client";
import Table from "../Table/Table";
import React, { useState, useEffect } from "react";
import { useUser } from "../Login/UserProvider";
import axios from "axios";
import { FaFileDownload } from "react-icons/fa";

function OrderTable() {
  const { user } = useUser();
  const [orderItems, setOrderItems] = useState([]);
  const [loading, setLoading] = useState(true); // Ładowanie danych
  const [error, setError] = useState(null);

    useEffect(() => {
  /**
   * Fetches order data from the server
   * 
   * Tries to fetch the order data from the server by making a GET request to
   * `http://localhost:8080/orders/${user.username}`.
   * 
   * If the request is successful, sets the `orderItems` state to the received
   * data. If the request fails, sets the `error` state to the error object.
   * 
   * Finally, sets the `loading` state to `false` to indicate that the request
   * has finished.
   */
    const fetchOrderData = async () => {
      try {
        setLoading(true);
        const response = await axios.get(
          `http://localhost:8080/orders/${user.username}`
        );
        setOrderItems(response.data);
        } catch (error) {
        setError(error);
        }
        finally {
        setLoading(false);
        }
    };
    fetchOrderData();
    }, [user.username]);
    if (loading) return <p>Loading orders...</p>;
    if (error) return <p>{error}</p>;


    function info(id) {
      axios.get(`http://localhost:8080/invoice/${id}`, {
          responseType: 'blob' // Odpowiedź jako binarne dane (PDF)
      })
      .then(response => {
          // Tworzenie URL z blob
          const url = window.URL.createObjectURL(new Blob([response.data], { type: 'application/pdf' }));
          const link = document.createElement('a');
          link.href = url;
          link.setAttribute('download', `faktura_${id}.pdf`); // Nazwa pliku
          document.body.appendChild(link);
          link.click();
          link.remove(); // Usunięcie elementu po pobraniu
      })
      .catch(error => {
          console.error("Błąd pobierania faktury:", error);
      });
  }
 

  return (
    <Table
      title="Previous Orders"
      headers={["Product", "Quantity", "Price", "Order Date", "Status", "Invoice"]}
      items={orderItems}
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
                  <td className="p-4" rowSpan={order.cartDTO.length}>
                    <button onClick={() => info(order.orderId)}><FaFileDownload className="text-2xl"  />

                    </button>
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
