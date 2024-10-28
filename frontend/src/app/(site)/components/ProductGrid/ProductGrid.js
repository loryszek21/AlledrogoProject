// components/ProductGrid.js
import React from "react";
import Image from "next/image";

const ProductGrid = ({ products }) => {
  return (
    <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-6">
      {products.map((product) => (
        <div
          key={product.id}
          className="dark:bg-neutral-800 rounded-lg shadow p-4"
        >
          <div className="relative h-40 w-full">
            <Image
              src={product.image}
              alt={product.name}
              fill={true}
              // objectFit="cover"
              className="rounded-md"
            />
          </div>
          <h3 className="mt-4 text-lg font-semibold dark:text-white">
            {product.name}
          </h3>
          <p className="dark:text-white">{product.price}</p>
          <button className="mt-2 bg-button text-white py-1 px-3 rounded">
            Add to Cart
          </button>
        </div>
      ))}
    </div>
  );
};

export default ProductGrid;
