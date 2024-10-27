/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./src/pages/**/*.{js,ts,jsx,tsx,mdx}",
    "./src/components/**/*.{js,ts,jsx,tsx,mdx}",
    "./src/app/**/*.{js,ts,jsx,tsx,mdx}",
  ],
  darkMode: "class",
  theme: {
    extend: {
      colors: {
        dark: "#171717",
        medium: "#232A3C",
        button: "#eb5e28",
        background: "var(--background)",
        secondary: "var(--secondary)",
        foreground: "var(--foreground)",
      },
    },
  },
  plugins: [],
};
