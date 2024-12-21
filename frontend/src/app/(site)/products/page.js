"use client";
import Header from "@/app/(site)/components/Header/Header";
import Footer from "@/app/(site)/components/Footer/Footer";
import ThemeToggle from "@/app/(site)/components/ThemeToggle/ThemeToggle";
import ProductGrid from "../components/ProductGrid/ProductGrid";
import { useEffect, useState } from "react";
import { verifyToken } from "../services/auth";

function Products() {
  const [products, setProducts] = useState([]);
  const [isAuthenticated, setIsAuthenticated] = useState(false);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const checkAuth = async () => {
      console.log("Checking authentication...");
      const valid = await verifyToken();
      if (!valid) {
        console.error("Not authenticated, redirecting to login page...");
        window.location.href = "/login";
      } else {
        console.log("Authenticated!");
        setIsAuthenticated(true);
      }
      setLoading(false);
    };

    checkAuth();
  }, []);

  useEffect(() => {
    if (isAuthenticated) {
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
    }
  }, [isAuthenticated]);

  if (loading) {
    return <div>Loading...</div>;
  }
  return (
    <>
      <Header />
      <div className="dark:bg-dark px-10 max-w-full">
        <div className="dark:bg-dark dark:text-white container  py-5 max-w-full">
          <h1 className="text-3xl font-bold mb-8">Products</h1>
          <ProductGrid products={products} />
        </div>
      </div>
      <Footer />
    </>
  );
}
export default Products;
