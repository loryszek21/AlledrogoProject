"use client";
import InputField from "../InputField/InputField";
import { useState } from "react";

function Login() {
  const [username, setUsername] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  return (
    <>
      <div className="flex flex-col justify-center items-center dark:bg-dark dark:text-white h-[90vh] ">
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
          {/* Możesz dodać dodatkowe pola tutaj */}
        </form>
      </div>
    </>
  );
}

export default Login;
