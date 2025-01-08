
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

      

<form class="max-w-full mx-auto">
<div class="relative z-0 w-full mb-5 group">
      <input type="text" name="floating_NIP" id="floating_NIP" class="block py-2.5 px-0 w-full text-sm text-gray-900 bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:text-white dark:border-gray-600 dark:focus:border-accentText focus:outline-none focus:ring-0 focus:border-accentText peer" placeholder=" " required />
      <label for="floating_email" class="peer-focus:font-medium absolute text-sm text-black dark:text-white duration-300 transform -translate-y-6 scale-75 top-3 -z-10 origin-[0] peer-focus:start-0 rtl:peer-focus:translate-x-1/4 rtl:peer-focus:left-auto peer-focus:text-accentText peer-focus:dark:text-accentText peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-6">NIP</label>
  </div>
  <div class="relative z-0 w-full mb-5 group">
      <input type="email" name="floating_email" id="floating_email" class="block py-2.5 px-0 w-full text-sm text-gray-900 bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:text-white dark:border-gray-600 dark:focus:border-accentText focus:outline-none focus:ring-0 focus:border-accentText peer" placeholder=" " required />
      <label for="floating_email" class="peer-focus:font-medium absolute text-sm text-black dark:text-white duration-300 transform -translate-y-6 scale-75 top-3 -z-10 origin-[0] peer-focus:start-0 rtl:peer-focus:translate-x-1/4 rtl:peer-focus:left-auto peer-focus:text-accentText peer-focus:dark:text-accentText peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-6">Company name
      </label>
  </div>
  <div class="relative z-0 w-full mb-5 group">
      <input type="email" name="floating_email" id="floating_email" class="block py-2.5 px-0 w-full text-sm text-gray-900 bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:text-white dark:border-gray-600 dark:focus:border-accentText focus:outline-none focus:ring-0 focus:border-accentText peer" placeholder=" " required />
      <label for="floating_email" class="peer-focus:font-medium absolute text-sm text-black dark:text-white duration-300 transform -translate-y-6 scale-75 top-3 -z-10 origin-[0] peer-focus:start-0 rtl:peer-focus:translate-x-1/4 rtl:peer-focus:left-auto peer-focus:text-accentText peer-focus:dark:text-accentText peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-6">Email address</label>
  </div>

  <div class="grid md:grid-cols-2 md:gap-6">
    <div class="relative z-0 w-full mb-5 group">
        <input type="text" name="floating_first_name" id="floating_first_name" class="block py-2.5 px-0 w-full text-sm text-gray-900 bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:text-white dark:border-gray-600 dark:focus:border-accentText focus:outline-none focus:ring-0 focus:border-accentText peer" placeholder=" " required />
        <label for="floating_first_name" class="peer-focus:font-medium absolute text-sm text-black dark:text-white duration-300 transform -translate-y-6 scale-75 top-3 -z-10 origin-[0] peer-focus:start-0 rtl:peer-focus:translate-x-1/4 peer-focus:text-accentText peer-focus:dark:text-accentText peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-6">Street address</label>
    </div>
    <div class="relative z-0 w-full mb-5 group">
        <input type="text" name="floating_last_name" id="floating_last_name" class="block py-2.5 px-0 w-full text-sm text-gray-900 bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:text-white dark:border-gray-600 dark:focus:border-accentText focus:outline-none focus:ring-0 focus:border-accentText peer" placeholder=" " required />
        <label for="floating_last_name" class="peer-focus:font-medium absolute text-sm text-black dark:text-white duration-300 transform -translate-y-6 scale-75 top-3 -z-10 origin-[0] peer-focus:start-0 rtl:peer-focus:translate-x-1/4 peer-focus:text-accentText peer-focus:dark:text-accentText peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-6">Apartament, suite, unit, etc (optional)</label>
    </div>
  </div>
  <div class="grid md:grid-cols-2 md:gap-6">
    <div class="relative z-0 w-full mb-5 group">
        <input type="tel" pattern="[0-9]{3}-[0-9]{3}-[0-9]{4}" name="floating_phone" id="floating_phone" class="block py-2.5 px-0 w-full text-sm text-gray-900 bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:text-white dark:border-gray-600 dark:focus:border-accentText focus:outline-none focus:ring-0 focus:border-accentText peer" placeholder=" " required />
        <label for="floating_phone" class="peer-focus:font-medium absolute text-sm text-black dark:text-white duration-300 transform -translate-y-6 scale-75 top-3 -z-10 origin-[0] peer-focus:start-0 rtl:peer-focus:translate-x-1/4 peer-focus:text-accentText peer-focus:dark:text-accentText peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-6">Postcode</label>
    </div>
    <div class="relative z-0 w-full mb-5 group">
        <input type="text" name="floating_company" id="floating_company" class="block py-2.5 px-0 w-full text-sm text-gray-900 bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:text-white dark:border-gray-600 dark:focus:border-accentText focus:outline-none focus:ring-0 focus:border-accentText peer" placeholder=" " required />
        <label for="floating_company" class="peer-focus:font-medium absolute text-sm text-black dark:text-white duration-300 transform -translate-y-6 scale-75 top-3 -z-10 origin-[0] peer-focus:start-0 rtl:peer-focus:translate-x-1/4 peer-focus:text-accentText peer-focus:dark:text-accentText peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-6">City</label>
    </div>
  </div>
  {/* <button type="submit" class="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm w-full sm:w-auto px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">Submit</button> */}
</form>

      <button 
      onClick={handlePayment}
      className="mt-6 text-base px-5 py-2.5 w-full bg-button text-white hover:bg-buttonHover rounded">Make Payment</button>
    </div>
  );
}

export default CartSummary;