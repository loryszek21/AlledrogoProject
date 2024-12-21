'use client';
import { useState } from 'react';
import { useUser } from './UserProvider';
import { login } from '../../services/auth';
import AuthForm from '../AuthForm/AuthForm';
function Login() {
  const [error, setError] = useState('');
  const { loginUser } = useUser();

  const handleLogin = async (formData) => {
    try {
      const token = await login(formData.username, formData.password);
      console.log('Zalogowano:', token);
      Cookies.set("token", token, { expires: 7, path: "/" });
      // Cookies.set("username", formData.username, { expires: 7, path: "/" });
      loginUser(formData.username); // Zapisujemy nazwę użytkownika w kontekście
      window.location.href = '/home';
    } catch (err) {
      console.error('Błąd logowania:', err);
      setError('Niepoprawne dane logowania.');
    }
  };

  return (
    <AuthForm
      title="Sign in"
      buttonText="Sign in"
      onSubmit={handleLogin}
      fields={['username', 'password']}
    />
  );
}

export default Login;