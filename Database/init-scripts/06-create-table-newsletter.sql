CREATE TABLE subscribers (
 subscribers SERIAL PRIMARY KEY,
    user_id INT,
    email VARCHAR(255),
 FOREIGN KEY (user_id) REFERENCES Users (user_id)
);