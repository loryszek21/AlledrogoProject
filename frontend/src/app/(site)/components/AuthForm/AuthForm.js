"use client";

import { useState } from "react";
import InputField from "../InputField/InputField";

function AuthForm({ title, buttonText, onSubmit, fields }) {
  const [formData, setFormData] = useState({
    username: "",
    email: "",
    password: "",
  });

  const handleChange = (field) => (e) => {
    setFormData((prev) => ({ ...prev, [field]: e.target.value }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    onSubmit(formData);
  };

  return (
    <div className="flex flex-col justify-center items-center dark:bg-dark dark:text-white h-[90vh]">
      <div className="shadow-2xl max-w-7xl min-w-6xl min-h-max rounded-xl p-10">
        <form className="flex flex-col gap-10" onSubmit={handleSubmit}>
          <div className="flex justify-center dark:text-white text-lg">{title}</div>
          {fields.includes("username") && (
            <InputField
              id="username"
              type="text"
              placeholder="Nazwa użytkownika"
              value={formData.username}
              onChange={handleChange("username")}
            />
          )}
          {fields.includes("email") && (
            <InputField
              id="email"
              type="email"
              placeholder="Email"
              value={formData.email}
              onChange={handleChange("email")}
            />
          )}
          {fields.includes("password") && (
            <InputField
              id="password"
              type="password"
              placeholder="Hasło"
              value={formData.password}
              onChange={handleChange("password")}
            />
          )}
          <button
            type="submit"
            className="inline-block rounded px-6 pb-[6px] pt-2 font-medium uppercase leading-normal text-surface shadow-dark-3 shadow-black/30 transition duration-150 ease-in-out hover:shadow-dark-1 focus:shadow-dark-1 focus:outline-none focus:ring-0 active:shadow-1 bg-button text-white"
          >
            {buttonText}
          </button>
        </form>
      </div>
    </div>
  );
}

export default AuthForm;
