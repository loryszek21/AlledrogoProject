INSERT INTO Users(username, password, first_name, last_name, email) VALUES ('User1', 'admin', 'UserName', 'UserLastName', 'user@email.com' );

INSERT INTO Vat_Rates (rate_name, rate_value)
VALUES ('Standard', 23.00);

INSERT INTO Categories (name, description, parent_category_id)
VALUES ('Electronics', 'Devices and gadgets', NULL);


INSERT INTO Products (name, description, price, category, vat_rate)
VALUES
    ('Smartphone', 'Latest model smartphone', 999.99, 1, 1),
    ('Laptop', 'High performance laptop', 1999.99, 1, 1),
    ('Tablet', 'Portable touchscreen tablet', 499.99, 1, 1),
    ('Smartwatch', 'Wearable smartwatch with fitness features', 199.99, 1, 1),
    ('Headphones', 'Noise-cancelling headphones', 149.99, 1, 1);