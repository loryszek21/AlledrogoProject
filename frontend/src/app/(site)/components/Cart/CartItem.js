import { useCart } from "./CartProvider";

function CartItem({ item }) {
  const { updateQuantity, removeItem } = useCart();

  const handleIncrement = () => updateQuantity(item.productId, item.quantity + 1);
  const handleDecrement = () => {
    if (item.quantity > 1) {
      updateQuantity(item.productId, item.quantity - 1);
    } else if (item.quantity === 1) {
      removeItem(item.productId);
    }
  }
 
  return (
    <tr>
      <td className="p-4">
        <div className="flex items-center gap-4 w-max">
          <div className="h-32 shrink-0">
            <img
              src={item.image}
              className="w-full h-full object-contain rounded-lg"
              alt={item.name}
            />
          </div>
          <div>
            <p className="text-base font-bold text-dark dark:bg-dark dark:text-white">
              {item.productName}
            </p>
            <button
              type="button"
              className="mt-2 font-semibold text-accentText text-sm"
              onClick={() => removeItem(item.productId)}
            >
              Remove
            </button>
          </div>
        </div>
      </td>
      <td className="p-4">
        <div className="flex divide-x border w-max rounded-lg overflow-hidden">
          <button
            onClick={handleDecrement}
            className="flex items-center justify-center  bg-gray-100 dark:bg-dark dark:text-white text-dark w-10 h-10 font-semibold"
          >
            -
          </button>
          <span className="bg-transparent w-10 h-10 font-semibold text-dark dark:text-white text-base flex items-center justify-center">
            {item.quantity}
          </span>
          <button
            onClick={handleIncrement}
            className="flex justify-center items-center bg-medium dark:bg-button text-white w-10 h-10 font-semibold"
          >
            +
          </button>
        </div>
      </td>
      <td className="p-4">
        <h4 className="text-base font-bold text-dark dark:bg-dark dark:text-white">
          ${item.price.toFixed(2)}
        </h4>
      </td>
    </tr>
  );
}

export default CartItem;
