"use client";
import { useRouter } from "next/router";
import axios from "axios";
import { useEffect, useState } from "react";
import Header from "@/app/(site)/components/Header/Header"; // Correct default import
import CommentSection from "../../components/CommentSection/CommentSection";
import { FaStar } from "react-icons/fa";
import { useCart } from "../../components/Cart/CartProvider";

export default function ProductPage({ params }) {
  const { id } = params;
  const [product, setProduct] = useState(null);
  const [image, setImage] = useState(1);
  const [rating, setRating] = useState(0);
  const { addItem } = useCart(); // Uzyskujemy funkcję addItem z CartProvider
  // const { addItem } = useCart(); // Funkcja addItem pochodzi z CartProvider

  useEffect(() => {
    if (id) {
      axios
        .get(`http://localhost:8080/products/${id}`)
        .then((response) => setProduct(response.data))
        .catch((error) =>
          console.error("Błąd podczas ładowania produktu:", error)
        );
    }
  }, [id]);

  useEffect(() => {
    const fetchAvarageReview = async () => {
      try {
        const response = await axios.get(
          `http://localhost:8080/review/avarage/${id}`
        );
        setRating(response.data); // Set the fetched comments to the state
      } catch (error) {
        console.error("Error fetching reviews:", error);
      }
    };

    fetchAvarageReview();
  }, [id]);

  
  if (!product) {
    return <div>Ładowanie...</div>;
  }
  const handleAddToCart = (product) => {
    console.log("Dodawanie produktu do koszyka:", product);
    addItem(product); // Wywołanie addItem z produktem
  };
  
  // console.log(product);
  return (
    <>
      <Header />

      <div className="dark:bg-dark dark:text-white">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 mt-6  ">
          <div className="flex flex-col md:flex-row -mx-4">
            {/* Left Section */}
            <div className="md:flex-1 px-4">
              <div>
                {/* Image Display */}
                <div className="h-64 md:h-80 rounded-lg bg-gray-100 dark:bg-medium dark:text-white  mb-4 flex items-center justify-center">
                  <span className="text-5xl">{image}</span>
                </div>

                {/* Image Selector */}
                <div className="flex -mx-2 mb-4">
                  {[1, 2, 3, 4].map((i) => (
                    <div key={i} className="flex-1 px-2">
                      <button
                        onClick={() => setImage(i)}
                        className={`focus:outline-none w-full rounded-lg h-24 md:h-32 bg-gray-100 dark:bg-medium dark:text-white flex items-center justify-center ${
                          image === i ? "ring-2 ring-indigo-300 ring-inset" : ""
                        }`}
                      >
                        <span className="text-2xl">{i}</span>
                      </button>
                    </div>
                  ))}
                </div>
              </div>
            </div>

            {/* Right Section */}
            <div className="md:flex-1 px-4">
              <h2 className="mb-2 leading-tight tracking-tight font-bold text-gray-800 dark:text-white text-2xl md:text-3xl">
                {product.product_name}
                {/* Lorem ipsum dolor, sit amet consectetur, adipisicing elit. */}
              </h2>
              <p className="text-sm inline-flex items-center">
                {" "}
                Średnia ocen <span>&nbsp;</span>
                {rating} <FaStar className="ml-1 text-yellow-400" />
              </p>
              <p className="text-gray-500 text-sm">
                By{" "}
                <a href="#" className="text-accentText hover:underline">
                  ABC Company
                </a>
              </p>

              <div className="flex items-center space-x-4 my-4">
                <div>
                  <div className="rounded-lg dark:bg-medium bg-gray-100 flex py-2 px-3">
                    <span className="font-bold dark:text-white text-accentText text-3xl">
                      {product.product_price}
                    </span>
                    <span className="text-accentText mr-1 mt-1 dark:text-white">
                      zł
                    </span>
                  </div>
                </div>
              </div>

              <p className="text-medium dark:text-white">
                {product.product_description}
                {/* Lorem ipsum, dolor sit, amet consectetur adipisicing elit. Vitae exercitationem porro saepe ea harum corrupti vero id laudantium enim, libero blanditiis expedita cupiditate a est. */}
              </p>

              <div className="flex py-4 space-x-4">
                <button
                  type="button"
                  // onClick={handleAddToCart}
                  // onClick={ handleAddToCart(product)}
                  onClick={() => handleAddToCart(product)}

                  className="h-14 px-6 py-2 font-semibold rounded-xl bg-button text-white hover:bg-buttonHover "
                >
                  Add to Cart
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>

      <CommentSection productId={id} />
    </>
  );
}
