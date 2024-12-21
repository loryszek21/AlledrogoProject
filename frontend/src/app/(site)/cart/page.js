"use client";
import Header from "@/app/(site)/components/Header/Header";
import Footer from "@/app/(site)/components/Footer/Footer";
import CartSummary from "../components/Cart/CartSummary";
import CartTable from "../components/Cart/CartTable";
import { CartProvider } from "../components/Cart/CartProvider";
// import { Children } from "react";

function Cart() {
  return (
    <>
      <Header />
      <CartProvider>
        <div className="grid lg:grid-cols-3 dark:bg-dark">
          <CartTable />
          <CartSummary />
        </div>
      </CartProvider>
      <Footer />
    </>
  );
}

export default Cart;