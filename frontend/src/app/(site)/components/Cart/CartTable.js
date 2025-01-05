import { useCart } from "./CartProvider";
import Table from "../Table/Table";
import Cartitem from "./CartItem";

function CartTable() {
  const { cartItems } = useCart();

  return (
    <Table
      title="Shopping Cart"
      items={cartItems}
      headers={["Description", "Quantity", "Price"]}
      renderRow={(item, index) => <Cartitem key={item.id || index} item={item} />}
    />
  );
}

export default CartTable;
