"use client";
import React from "react";

function InputField({ id, type , placeholder, value, onChange }) {
  return (
    <div className="relative md:md-6   " data-twe-input-wrapper-init>
      <input
        id="{id}"
        type={type}
        value={value}
        onChange={onChange}
        className="peer block min-h-[auto] w-full rounded border-0 bg-transparent px-3 py-[0.32rem] leading-[1.6] outline-none transition-all duration-200 ease-linear focus:placeholder:opacity-100 peer-focus:text-primary data-[twe-input-state-active]:placeholder:opacity-100 motion-reduce:transition-none dark:text-white dark:placeholder:text-neutral-300 dark:autofill:shadow-autofill dark:peer-focus:text-primary [&:not([data-twe-input-placeholder-active])]:placeholder:opacity-0"
        placeholder="{placeholder}"
      />
  <label
  htmlFor={id}
  className="absolute text-sm text-black dark:text-white duration-300 transform -translate-y-6 scale-75 top-3 left-3 origin-[0] peer-placeholder-shown:translate-y-0 peer-placeholder-shown:scale-100 peer-placeholder-shown:text-neutral-500 peer-focus:scale-75 peer-focus:-translate-y-6 peer-focus:text-primary"
>
  {placeholder}
</label>

    </div>
  );
}

export default InputField;
