import React from 'react';

const FloatingInput = ({ type, name, id, label, value, onChange, required, pattern }) => {
  return (
    <div className="relative z-0 w-full mb-5 group">
      <input
        type={type}
        name={name}
        id={id}
        pattern={pattern}
        value={value}
        onChange={onChange}
        className="block py-2.5 px-0 w-full text-base text-gray-900 bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:text-white dark:border-gray-600 dark:focus:border-accentText focus:outline-none focus:ring-0 focus:border-accentText peer"
        placeholder=" "
        required={required}
      />
      <label
        htmlFor={id}
        className="peer-focus:font-medium absolute text-base text-black dark:text-white duration-300 transform -translate-y-6 scale-75 top-3 -z-10 origin-[0] peer-focus:start-0 rtl:peer-focus:translate-x-1/4 rtl:peer-focus:left-auto peer-focus:text-accentText peer-focus:dark:text-accentText peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-6"
      >
        {label}
      </label>
    </div>
  );
};

export default FloatingInput;