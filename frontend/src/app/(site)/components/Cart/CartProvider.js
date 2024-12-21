'use client';
import React, { createContext, useContext, useState, useEffect } from "react";
import axios from "axios";
import { useUser } from "../Login/UserProvider";

const CartContext = createContext();

export const useCart = () => useContext(CartContext);

export const CartProvider = ({ children }) => {
  const [cartItems, setCartItems] = useState([]);
  const { user } = useUser();

  const addItem = async (item) => {
    console.log("Próba dodawanie produktu do koszyka:", item);
    try {
      const response = await axios.post('http://localhost:8080/cart', {
        id: item.id,
        name: item.productName,
        price: item.product_price,
        quantity: 1, // Domyślna ilość produktu
        userName: user.username
      });
      setCartItems(Array.isArray(response.data) ? response.data : []); 
      alert("Produkt został dodany do koszyka" );
      console.log("Udana próba dodania do koszyka" + response.data); // Aktualizujemy stan koszyka
    } catch (error) {
      console.error('Błąd podczas dodawania do koszyka:', error);
    }
  };


  const removeItem = async (id) => {
  try {
    const response = await axios.delete(`http://localhost:8080/cart/${id}?username=${user.username}`);
    console.log("Odpowiedź serwera po usunięciu:", response.data); // Debug
    setCartItems(Array.isArray(response.data) ? response.data : []);
  } catch (error) {
    console.error('Błąd podczas usuwania produktu z koszyka:', error);
  }
};

  const updateQuantity = async (id, quantity) => {
    if (quantity < 0) return; // Prevent negative quantity
    try {
      const response = await axios.put('http://localhost:8080/cart', {
        productId: id,
        quantity,
        userName: user.username

      });
      setCartItems(Array.isArray(response.data) ? response.data : []); // Aktualizujemy koszyk po zmianie ilości
    } catch (error) {
      console.error('Błąd podczas aktualizacji ilości:', error);
    }
  };

  const fetchCartItems = async () => {
    try {
      const response = await axios.get(`http://localhost:8080/cart/${user.username}`);
      setCartItems(Array.isArray(response.data) ? response.data : []); // Aktualizujemy stan koszyka
    } catch (error) {
      console.error('Błąd podczas pobierania koszyka:', error);
    }
  }

  useEffect(() => {
    if (user) {
      fetchCartItems();
    }
  }, [user]);

  return (
    <CartContext.Provider value={{ cartItems, addItem, removeItem, updateQuantity }}>
      {children}
    </CartContext.Provider>
  );
};
