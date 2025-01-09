import { useCart } from "./CartProvider";
import axios from "axios";
import { useUser } from "../Login/UserProvider";
import { useState } from "react";
// const { user } = useUser();
import FloatingInput from "../InputField/FloatingInput";

function CartSummary() {
  const { cartItems } = useCart();
  const { user } = useUser();

  const subtotal = cartItems.reduce(
    (sum, item) => sum + item.price * item.quantity,
    0
  );
  const shipping = 4.0;
  const tax = subtotal * 0.23; // np. 23% podatku
  const total = (subtotal + shipping + tax).toFixed(2);
  // const [invoiceType, setInvoiceType] = useState('receipt');
  const [invoiceDataDTO, setInvoiceData] = useState({
    invoiceType: "receipt",
    nip: "",
    companyName: "",
    firstName: "",
    lastName: "",
    email: "",
    streetAddress: "",
    apartment: "",
    postcode: "",
    city: "",
  });
  const handlePayment = async () => {
    try {
      const paymentRequest = {
        orderPayment: {
          amount: total,
          currency: "PLN",
          description: "Order payment",
          cartDTO: cartItems,
          userName: user.username,
        },
        invoiceDataDTO: invoiceDataDTO,
      };

      const paymentResponse = await axios.post(
        "http://localhost:8080/payment",
        paymentRequest
      );
      const redirectUrl = paymentResponse.data;
      // window.location.href = redirectUrl;

      // Handle successful payment response
    } catch (error) {
      console.error("Payment Error:", error);
      // Handle payment error
    }
  };
  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setInvoiceData((prevData) => {
      const updatedData = { ...prevData, [name]: value };
      console.log("Updated invoiceDataDTO:", updatedData); // Debug log
      return updatedData;
    });
  };
  return (
    <div className="dark:bg-dark dark:text-white bg-gray-50 p-6 lg:sticky lg:top-0 lg:h-screen">
      <h2 className="text-2xl font-bold dark:bg-dark dark:text-white border-b pb-4">
        Order Summary
      </h2>
      <ul className="dark:bg-dark dark:text-white divide-y mt-6">
        <li className="flex flex-wrap gap-4 text-base py-3">
          Subtotal{" "}
          <span className="ml-auto font-bold">${subtotal.toFixed(2)}</span>
        </li>
        <li className="flex flex-wrap gap-4 text-base py-3">
          Shipping{" "}
          <span className="ml-auto font-bold">${shipping.toFixed(2)}</span>
        </li>
        <li className="flex flex-wrap gap-4 text-base py-3">
          Tax <span className="ml-auto font-bold">${tax.toFixed(2)}</span>
        </li>
        <li className="flex flex-wrap gap-4 text-base py-3 font-bold">
          Total <span className="ml-auto">${total}</span>
        </li>
      </ul>

      {/* <form className="max-w-full mx-auto"> */}
      <div className="max-w-full mx-auto">
        <div className="mt-6">
          <h3 className="text-base font-semibold">Select Invoice Type</h3>
          <div className="mt-2">
            <label className="inline-flex items-center">
              <input
                type="radio"
                name="invoiceType"
                value="receipt"
                checked={invoiceDataDTO.invoiceType === "receipt"}
                onChange={handleInputChange}
                className="form-radio text-accentText focus:ring-accentText accent-button text-base"
              />
              <span className="ml-2">Receipt</span>
            </label>
            <label className="inline-flex items-center ml-4">
              <input
                type="radio"
                name="invoiceType"
                value="invoice"
                checked={invoiceDataDTO.invoiceType === "invoice"}
                onChange={handleInputChange}
                className="form-radio text-accentText focus:ring-accentText accent-button text-base"
              />
              <span className="ml-2">Invoice</span>
            </label>
          </div>
        </div>
        <div className="relative z-0 w-full mb-5 group mt-5">
          {invoiceDataDTO.invoiceType === "invoice" && (
            <>
              <FloatingInput
                type="text"
                name="nip"
                id="floating_NIP"
                label="NIP "
                required={true}
                value={invoiceDataDTO.nip}
                onChange={handleInputChange}
              />
              <FloatingInput
                type="text"
                name="companyName"
                id="floating_companyName"
                label="Company name"
                required={true}
                value={invoiceDataDTO.companyName}
                onChange={handleInputChange}
              />
            </>
          )}
          {invoiceDataDTO.invoiceType === "receipt" && (
            <>
              <FloatingInput
                type="text"
                name="firstName"
                id="floating_first_name"
                label="First Name"
                required={true}
                value={invoiceDataDTO.firstName}
                onChange={handleInputChange}
              />
              <FloatingInput
                type="text"
                name="lastName"
                id="floating_last_name"
                label="Last Name"
                required={true}
                value={invoiceDataDTO.lastName}
                onChange={handleInputChange}
              />
            </>
          )}
          <FloatingInput
            type="email"
            name="email"
            id="floating_email"
            label="Email address"
            required={true}
            value={invoiceDataDTO.email}
            onChange={handleInputChange}
          />
          <div className="grid md:grid-cols-2 md:gap-6">
            <FloatingInput
              type="text"
              name="streetAddress"
              id="floating_street_address"
              label="Street address"
              required={true}
              value={invoiceDataDTO.streetAddress}
              onChange={handleInputChange}
            />
            <FloatingInput
              type="text"
              name="apartment"
              id="floating_apartment"
              label="Apartament, unit, etc (optional)"
              required={true}
              value={invoiceDataDTO.apartment}
              onChange={handleInputChange}
            />
          </div>
          <div className="grid md:grid-cols-2 md:gap-6">
            <FloatingInput
              type="tel"
              name="postcode"
              id="floating_postcode"
              pattern="[0-9]{2}-[0-9]{3}"
              label="Postcode"
              required={true}
              value={invoiceDataDTO.postcode}
              onChange={handleInputChange}
            />
            <FloatingInput
              type="text"
              name="city"
              id="floating_city"
              label="City"
              required={true}
              value={invoiceDataDTO.city}
              onChange={handleInputChange}
            />
          </div>
        </div>
      </div>
      {/* </form> */}
      <button
        onClick={handlePayment}
        className="mt-6 text-base px-5 py-2.5 w-full bg-button text-white hover:bg-buttonHover rounded"
      >
        Make Payment
      </button>
    </div>
  );
}

export default CartSummary;
