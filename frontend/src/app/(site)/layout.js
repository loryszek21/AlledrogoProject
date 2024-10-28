// layout.js
import { cookies } from "next/headers";

export default function Layout({ children }) {
  const cookieStore = cookies();
  const themeStyle = cookieStore.get("theme")?.value || "light";
  if (themeStyle === "dark") {
    if (typeof document !== 'undefined') {
        document.documentElement.classList.add("dark");
    }
  }

  return (
    <div>
        {children} {themeStyle}
    </div>
  );
}
