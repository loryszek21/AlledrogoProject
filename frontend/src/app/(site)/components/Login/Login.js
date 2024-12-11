"use client";
import Cookies from "js-cookie";

import AuthForm from "../AuthForm/AuthForm";
import { useState } from "react";
import { login } from "../../services/auth";
function Login() {
  const [error, setError] = useState("");

  const handleLogin = async (formData) => {
    try {
      const token = await login(formData.username, formData.password);
      console.log("Zalogowano:", token);

      // Zapisywanie tokenu w cookies
      Cookies.set("token", token, { expires: 7, path: "/" });
      Cookies.set("username", formData.username, { expires: 7, path: "/" });


      // Przekierowanie użytkownika po zalogowaniu
      window.location.href = "/home";
    } catch (err) {
      console.error("Błąd logowania:", err);
      setError("Niepoprawne dane logowania.");
    }
  };

  return (
    <AuthForm
      title="Sign in"
      buttonText="Sign in"
      onSubmit={handleLogin}
      fields={["username", "password"]}
    />
  );
}

export default Login;
