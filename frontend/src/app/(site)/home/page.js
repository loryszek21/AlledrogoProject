import Header from "@/app/(site)/components/Header/Header";
import Footer from "@/app/(site)/components/Footer/Footer";
import ThemeToggle from "@/app/(site)/components/ThemeToggle/ThemeToggle";
function Home() {
    return (
        <>
        <Header/>
        {/* <ThemeToggle/> */}
            <div>
                <h1>Witaj na stronie</h1>
            </div>
            <Footer/>
        </>
        );
}
export default Home;
