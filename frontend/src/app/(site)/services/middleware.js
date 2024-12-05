import { NextResponse } from "next/server";
import jwt from "jsonwebtoken";
export async function middleware(req) {
    const token = req.cookies.get("token");
    if (!token) {
        return NextResponse.redirect(new URL("/login", req.url));
    }
    try {
        const decoded = jwt.verify(token, process.env.JWT_SECRET);
        return NextResponse.next();
    } catch (err) {
        return NextResponse.redirect(new URL("/login", req.url));
    }
}

export const config = {
    matcher: ["/home/:path*"],
}