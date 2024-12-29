
import { useCart } from "./CartProvider";
import axios from "axios";
import { useUser } from "../Login/UserProvider";
// const { user } = useUser();

function CartSummary() {
  const { cartItems } = useCart();
  const { user } = useUser();

  const subtotal = cartItems.reduce((sum, item) => sum + item.price * item.quantity, 0);
  const shipping = 4.00;
  const tax = subtotal * 0.23; // np. 23% podatku
  const total = (subtotal + shipping + tax).toFixed(2);

  const handlePayment = async () => {
    console.log('Cart Items:', cartItems);
    try {
      // Create payment order
      const paymentResponse = await axios.post('http://localhost:8080/payment', {
        amount: total,
        currency: 'PLN',
        description: 'Order payment',
        cartDTO: cartItems,
        userName: user.username

        
      });

      console.log('Payment Response:', paymentResponse.data);
      const redirectUrl = paymentResponse.data;
      window.location.href = redirectUrl;

      // Handle successful payment response
    } catch (error) {
      console.error('Payment Error:', error);
      // Handle payment error
    }
  };
  return (
    <div className="dark:bg-dark dark:text-white bg-gray-50 p-6 lg:sticky lg:top-0 lg:h-screen">
      <h2 className="text-2xl font-bold dark:bg-dark dark:text-white border-b pb-4">Order Summary</h2>
      <ul className="dark:bg-dark dark:text-white divide-y mt-6">
        <li className="flex flex-wrap gap-4 text-base py-3">Subtotal <span className="ml-auto font-bold">${subtotal.toFixed(2)}</span></li>
        <li className="flex flex-wrap gap-4 text-base py-3">Shipping <span className="ml-auto font-bold">${shipping.toFixed(2)}</span></li>
        <li className="flex flex-wrap gap-4 text-base py-3">Tax <span className="ml-auto font-bold">${tax.toFixed(2)}</span></li>
        <li className="flex flex-wrap gap-4 text-base py-3 font-bold">Total <span className="ml-auto">${total}</span></li>
      </ul>
      <button 
      onClick={handlePayment}
      className="mt-6 text-base px-5 py-2.5 w-full bg-button text-white hover:bg-buttonHover rounded">Make Payment</button>
    </div>
  );
}

export default CartSummary;