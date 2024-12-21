import Cartitem from "./CartItem";
import { useCart } from "./CartProvider";
function CartTable() {
  const { cartItems } = useCart();

  return (
    <>
      <div className="lg:col-span-2 p-6 bg-white dark:bg-dark dark:text-white overflow-x-auto">
        <div className="flex gap-2 border-b pb-4">
          <h2 className="text-2xl font-bold text-dark dark:bg-dark dark:text-white flex-1">
            Shopping Cart
          </h2>
          <h3 className="text-base text-dark dark:bg-dark dark:text-white ">
          {cartItems.length} Items
          </h3>
        </div>

        <table className="mt-6 w-full border-collapse divide-y">
          <thead className="whitespace-nowrap text-left">
            <tr>
              <th className="text-base text-dark dark:bg-dark dark:text-white  p-4">
                Description
              </th>
              <th className="text-base text-dark dark:bg-dark dark:text-white  p-4">
                Quantity
              </th>
              <th className="text-base text-dark dark:bg-dark dark:text-white  p-4">
                Price
              </th>
            </tr>
          </thead>

          <tbody className="whitespace-nowrap divide-y">
            {cartItems.map((item, index) => (
              <Cartitem key={item.id || index} item={item} />
            ))}
          </tbody>
        </table>
      </div>
    </>
  );
}
export default CartTable;
