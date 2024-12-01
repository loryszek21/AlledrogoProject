"use client";
import { Button } from "@headlessui/react";
import InputField from "../InputField/InputField";
import { useState } from "react";

function Login() {
  const [username, setUsername] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  return (
    <>
      <div className="flex flex-col justify-center items-center dark:bg-dark dark:text-white h-[90vh] ">
        <div className=" shadow-2xl max-w-7xl min-w-6xl min-h-max  rounded-xl p-10 ">
          <form className="flex flex-col gap-10">
            <div className="flex justify-center dark:text-white text-lg">
              Sign in{" "}
            </div>
            <InputField
              id="username"
              type="text"
              placeholder="Nazwa użytkownika"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
              // className="dark:bg-dark dark:text-white"
            />
            <InputField
              id="email"
              type="email"
              placeholder="Email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
            />
            <InputField
              id="password"
              type="password"
              placeholder="Hasło"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
            />
            {/* <Button>Sign in</Button> */}
            <button
              type="submit"
              className="inline-block rounded px-6 pb-[6px] pt-2 font-medium uppercase leading-normal text-surface shadow-dark-3 shadow-black/30 transition duration-150 ease-in-out hover:shadow-dark-1 focus:shadow-dark-1 focus:outline-none focus:ring-0 active:shadow-1  bg-button text-white"
              data-twe-ripple-init
              data-twe-ripple-color="light"
            >
              Sign in
            </button>
            {/* Możesz dodać dodatkowe pola tutaj */}
          </form>
        </div>
      </div>
    </>
  );
}

export default Login;
