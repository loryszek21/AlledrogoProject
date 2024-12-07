import axios from "axios";
import Cookies from "js-cookie";

const API_URL = "http://localhost:8080/";

export const login = async (username, password) => {
  const response = await axios.post(API_URL + "login", { username, password });
  const token = response.data.token;
  Cookies.set("token", token);
  return token;
};

export const regster = async (username, email, password) => {
  return await axios.post(API_URL + "register", { username, email, password });
};
export const verifyToken = async () => {
  const token = Cookies.get("token");
  if (!token) {
    return false; // Brak tokenu
  }

  try {
    const response = await fetch("http://localhost:8080/verify", {
      method: "GET",
      headers: {
        Authorization: `Bearer ${token}`, // Dodanie tokenu do nagłówka
      },
    });

    if (response.ok) {
      return true; // Token jest prawidłowy
    }
  } catch (error) {
    console.error("Błąd weryfikacji tokenu:", error);
  }

  return false; // Token nieprawidłowy lub brak odpowiedzi
};
