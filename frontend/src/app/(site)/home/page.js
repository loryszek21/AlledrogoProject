"use client";
import Header from "@/app/(site)/components/Header/Header";
import Footer from "@/app/(site)/components/Footer/Footer";
import ThemeToggle from "@/app/(site)/components/ThemeToggle/ThemeToggle";
import ProductGrid from "../components/ProductGrid/ProductGrid";
import { useEffect, useState } from "react";
import { verifyToken } from "../services/auth";
function Home() {


  return (
    <>
      <Header />
      <div className="dark:bg-dark px-10 max-w-full">
        HELLO
      </div>
      <Footer />
    </>
  );
}
export default Home;
