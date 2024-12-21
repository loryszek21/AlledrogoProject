'use client';
import React, { createContext, useContext, useState } from 'react';
import Cookies from 'js-cookie';

const UserContext = createContext();

export const useUser = () => useContext(UserContext);

export const UserProvider = ({ children }) => {
  const [user, setUser] = useState(() => {
    const username = Cookies.get('username');
    return username ? { username } : null; // Pobiera nazwę użytkownika z cookies
  });

  const loginUser = (username) => {
    Cookies.set('username', username, { expires: 7, path: '/' });
    setUser({ username });
  };

  const logoutUser = () => {
    Cookies.remove('username');
    setUser(null);
  };

  return (
    <UserContext.Provider value={{ user, loginUser, logoutUser }}>
      {children}
    </UserContext.Provider>
  );
};
