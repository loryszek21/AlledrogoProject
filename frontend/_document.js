import Document, { Html, Head, Main, NextScript } from "next/document";

class MyDocument extends Document {
    static async getInitialProps(ctx) {
        const initialProps = await Document.getInitialProps(ctx);
        const theme = ctx.reg?.cookies?.theme || "light";
        return { ...initialProps, theme };
    }

    render() {
        return (
            <Html className={this.props.theme === "dark" ? "dark" : ""}>
                <Head />
                <body>
                    <Main />
                    <NextScript />
                </body>
            </Html>
        );
    }
}

export default MyDocument;