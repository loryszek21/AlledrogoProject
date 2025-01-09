CREATE TABLE InvoiceData (
    invoice_id SERIAL PRIMARY KEY,
    invoice_type VARCHAR(255),
    nip VARCHAR(255),
    company_name VARCHAR(255),
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    email VARCHAR(255),
    post_code VARCHAR(10),
    street_address VARCHAR(255),
    apartment_number VARCHAR(10),
    city VARCHAR(255),
    user_id INT,
    order_id INT,
    FOREIGN KEY (user_id) REFERENCES Users (user_id),
    FOREIGN KEY (order_id) REFERENCES Orders (order_id)
);