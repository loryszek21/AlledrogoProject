"use client";

import AuthForm from "../AuthForm/AuthForm";
import { useState } from "react";
import { register } from "../../services/auth";
function Register() {
  const [error, setError] = useState("");
  const [success, setSuccess] = useState(false);
  const handleRegister = async (formData) => {
    try {
      await register(formData.username, formData.email, formData.password);
      console.log("Rejestracja zakończona sukcesem!");
      setSuccess(true);
      // Możesz przekierować na stronę logowania
      window.location.href = "/login";
    } catch (err) {
      console.error("Błąd rejestracji:", err);
      setError("Rejestracja nie powiodła się.");
    }
  };

  return (
    <AuthForm
      title="Sign up"
      buttonText="Sign up"
      onSubmit={handleRegister}
      fields={["username", "email", "password"]} // Nazwa użytkownika, e-mail i hasło
    />
  );
}

export default Register;