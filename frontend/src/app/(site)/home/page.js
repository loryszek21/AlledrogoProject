"use client";
import Header from "@/app/(site)/components/Header/Header";
import Footer from "@/app/(site)/components/Footer/Footer";
import ThemeToggle from "@/app/(site)/components/ThemeToggle/ThemeToggle";
import ProductGrid from "../components/ProductGrid/ProductGrid";
import { useEffect, useState } from "react";
import { verifyToken } from "../services/auth";
function Home(){
  const [products, setProducts] = useState([]);
  const [isAuthenticated, setIsAuthenticated] = useState(false);
  useEffect(() => {
    const checkAuth = async () => {
      const valid = await verifyToken();
      if (!valid) {
        window.location.href = "/login";
      } else {
        setIsAuthenticated(true);
      }
    };

    checkAuth();
  }, []);

  if (!isAuthenticated) {
    return <div>Loading...</div>;
  }

  useEffect(() => {
    const fetchProducts = async () => {
      try {
        const response = await fetch("http://localhost:8080/products");
        const data = await response.json();
        setProducts(data);
      } catch (error) {
        console.error("Error fetching products:", error);
      }
    };
    fetchProducts();
  }, []);
    
  return (
    <>
      <Header />
      <div className="dark:bg-dark px-10 max-w-full">
        <div className="dark:bg-dark dark:text-white container  py-5 max-w-full">
          <h1 className="text-3xl font-bold mb-8">Products</h1>
          <ProductGrid products={products} />
        </div>
      </div>
      {/* <div> */}
      {/* <h1>Witaj na stronie</h1> */}
      {/* </div> */}
      <Footer />
    </>
  );
}
export default Home;
