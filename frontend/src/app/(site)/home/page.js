import Header from "@/app/(site)/components/Header/Header";
import Footer from "@/app/(site)/components/Footer/Footer";
import ThemeToggle from "@/app/(site)/components/ThemeToggle/ThemeToggle";
import ProductGrid from "../components/ProductGrid/ProductGrid";
function Home() {
  const products = [
    {
      id: 1,
      name: "Product 1",
      price: "$10.00",
      image: "/product1.jpg",
    },
    {
      id: 2,
      name: "Product 2",
      price: "$15.00",
      image: "/product2.jpg",
    },
    {
      id: 3,
      name: "Product 1",
      price: "$10.00",
      image: "/product1.jpg",
    },
    {
      id: 4,
      name: "Product 2",
      price: "$15.00",
      image: "/product2.jpg",
    },
    {
      id: 5,
      name: "Product 1",
      price: "$10.00",
      image: "/product1.jpg",
    },
    {
      id: 6,
      name: "Product 2",
      price: "$15.00",
      image: "/product2.jpg",
    },
    {
      id: 7,
      name: "Product 1",
      price: "$10.00",
      image: "/product1.jpg",
    },
    {
      id: 8,
      name: "Product 2",
      price: "$15.00",
      image: "/product2.jpg",
    },
    // More products here
  ];
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
