// components/ProductGrid.js
import React from "react";
import Image from "next/image";
import Link from "next/link";
// import { IoAddSharp } from "react-icons/io5";
const ProductGrid = ({ products }) => {
  return (
    <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-4 md:gap-6 lg:gap-8">
      {products.map((product) => (
           <Link
           key={product.product_id}
           href={`/products/${product.product_id}`} // Dynamiczny link do strony produktu
           passHref
         >
        <div
          key={product.product_id}
          className="dark:bg-neutral-800 rounded-lg shadow p-4 "
        >
          <div className="relative  w-full h-40 sm:h-52 md:h-60 lg:h-72">
            <Image
              src={product.image}
              alt={product.product_name}
              fill={true}
              className="rounded-md"
            />
          </div>
          <h3 className="mt-4 text-lg font-semibold dark:text-white">
            {product.product_name}
          </h3>
          <p className="dark:text-white">${product.product_price.toFixed(2)}</p>
          {/* <IoAddSharp /> */}
          <button className="mt-2 bg-button text-white py-1 px-3 rounded">
            Add to Cart
          </button>
        </div>
        </Link>
      ))}
    </div>
  );
};

export default ProductGrid;
