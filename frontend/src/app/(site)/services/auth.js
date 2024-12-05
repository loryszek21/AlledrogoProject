import axios from "axios";
import Cookies from "js-cookie";

const API_URL = "http://localhost:8000/";

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
    if(!token) return false;
  
    try {
        await axios.get(API_URL + "verify", { headers: { Authorization: `Bearer ${token}` } });
        return true;
    } catch (error) {
        Cookies.remove("token");
        return false;
}
  };