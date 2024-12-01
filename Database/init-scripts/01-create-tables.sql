CREATE TABLE
    Users (
        user_id SERIAL PRIMARY KEY,
        username VARCHAR(50) UNIQUE,
        password VARCHAR(255),
        first_name VARCHAR(50),
        last_name VARCHAR(50),
        email VARCHAR(100) UNIQUE
    );

CREATE TABLE
    Categories (
        category_id SERIAL PRIMARY KEY,
        name VARCHAR(50),
        description TEXT,
        parent_category_id INTEGER,
        FOREIGN KEY (parent_category_id) REFERENCES Categories (category_id)
    );

CREATE TABLE
    Vat_Rates (
        vat_rate_id SERIAL PRIMARY KEY,
        rate_name VARCHAR(50) NOT NULL,
        rate_value NUMERIC(5, 2) NOT NULL
    );

CREATE TABLE
    Products (
        product_id SERIAL PRIMARY KEY,
        name VARCHAR(50),
        description TEXT,
        price NUMERIC(10, 2),
        category INTEGER,
        vat_rate INTEGER,
        FOREIGN KEY (vat_rate) REFERENCES Vat_Rates (vat_rate_id),
        FOREIGN KEY (category) REFERENCES Categories (category_id)
    );

CREATE TABLE
    Images (
        image_id SERIAL PRIMARY KEY,
        product_id INTEGER,
        image_url VARCHAR(255),
        FOREIGN KEY (product_id) REFERENCES Products (product_id)
    );

CREATE TABLE
    Orders (
        order_id SERIAL PRIMARY KEY,
        user_id INTEGER,
        order_date DATE,
        order_status VARCHAR(50),
        FOREIGN KEY (user_id) REFERENCES Users (user_id)
    );

CREATE TABLE
    OrderItems (
        order_item_id SERIAL PRIMARY KEY,
        order_id INTEGER,
        product_id INTEGER,
        quantity INTEGER,
        FOREIGN KEY (order_id) REFERENCES Orders (order_id),
        FOREIGN KEY (product_id) REFERENCES Products (product_id)
    );

CREATE TABLE
    Payments (
        payment_id SERIAL PRIMARY KEY,
        order_id INTEGER,
        payment_method VARCHAR(50),
        payment_status VARCHAR(20),
        payment_date DATE,
        FOREIGN KEY (order_id) REFERENCES Orders (order_id)
    );

CREATE TABLE
    Reviews (
        review_id SERIAL PRIMARY KEY,
        product_id INT,
        user_id INT,
        rating INT,
        comment TEXT,
        review_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        FOREIGN KEY (product_id) REFERENCES Products (product_id),
        FOREIGN KEY (user_id) REFERENCES Users (user_id)
    );

CREATE TABLE
    OrderHistory (
        history_id SERIAL PRIMARY KEY,
        order_id INT,
        order_status VARCHAR(20),
        status_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        FOREIGN KEY (order_id) REFERENCES Orders (order_id)
    );

CREATE TABLE
    UserAddresses (
        address_id SERIAL PRIMARY KEY,
        user_id INTEGER,
        address_line VARCHAR(255),
        city VARCHAR(100),
        postal_code VARCHAR(20),
        country VARCHAR(100),
        FOREIGN KEY (user_id) REFERENCES Users (user_id)
    );

CREATE TABLE
    CartItems (
        cart_item_id SERIAL PRIMARY KEY,
        user_id INTEGER,
        product_id INTEGER,
        quantity INTEGER DEFAULT 1,
        date_added TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        FOREIGN KEY (user_id) REFERENCES Users (user_id),
        FOREIGN KEY (product_id) REFERENCES Products (product_id)
    );