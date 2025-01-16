"use client";
import { useState } from "react";
import FloatingInput from "../InputField/FloatingInput";
import { useUser } from "../Login/UserProvider";
import axios from "axios";
function Footer() {
  const [email, setEmail] = useState("");
  const { user } = useUser(); 
  // const [SubscriberDTO, setSubscriberDTO] = useState({



  const handleSubscribe = async (e) => {
    e.preventDefault();

    const payload = {
      userName: user.username,
      email: email,
    };
    console.log('Payload:', payload);
    console.log('http://localhost:8080/subscribe', payload);

    try {
      const response = await axios.post('http://localhost:8080/subscribe', payload);
      console.log('Response:', response.data);
      alert('Subscribed successfully!');
    } catch (error) {
      console.error('Error subscribing:', error);
      alert('Failed to subscribe. Please try again.');
    }
  };
  return (
    <>
      <div className="dark:bg-dark mx-auto max-w-full px-6 py-24 sm:py-32 lg:px-8 dark:text-white">
        <div>
        <form onSubmit={handleSubscribe}>
            <div className="gird-cols-1 grid items-center justify-center gap-4 md:grid-cols-3">
              <div className="md:mb-6 md:ms-auto ">
                <p>
                  <strong>Sign up for our newsletter</strong>
                </p>
              </div>

              <div className="relative md:mb-6" data-twe-input-wrapper-init>
      <FloatingInput
        type="email"
        name="newsletter-email"
        id="newsletter-email"
        label="Email address"
        value={email}
        onChange={(e) => setEmail(e.target.value)}
        required={true}
      />
    </div>

              <div className="mb-6 md:me-auto">
              <button
                type="submit"
                className="inline-block rounded px-6 pb-[6px] pt-2 text-xs font-medium uppercase leading-normal text-surface shadow-dark-3 shadow-black/30 transition duration-150 ease-in-out hover:shadow-dark-1 focus:shadow-dark-1 focus:outline-none focus:ring-0 active:shadow-1  bg-button text-white"
                data-twe-ripple-init
                data-twe-ripple-color="light"
              >
                Subscribe
              </button>
              </div>
            </div>
          </form>
        </div>
        <p className="text-center text-xs leading-5 text-gray-500">
          &copy; Alledrogo, All rights reserved.
        </p>
      </div>
    </>
  );
}

export default Footer;
