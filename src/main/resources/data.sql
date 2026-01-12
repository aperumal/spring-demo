-- Sample Users
INSERT INTO users (username, email, first_name, last_name) VALUES
('john_doe', 'john@example.com', 'John', 'Doe'),
('jane_smith', 'jane@example.com', 'Jane', 'Smith'),
('bob_wilson', 'bob@example.com', 'Bob', 'Wilson');

-- Sample Products
INSERT INTO products (name, description, price, stock) VALUES
('Laptop', 'High-performance laptop', 999.99, 10),
('Monitor', '4K Display Monitor', 299.99, 25),
('Keyboard', 'Mechanical Gaming Keyboard', 129.99, 50),
('Mouse', 'Wireless Mouse', 49.99, 100);

-- Sample Orders
INSERT INTO orders (user_id, total_amount, status) VALUES
(1, 1299.98, 'COMPLETED'),
(2, 349.98, 'PENDING'),
(3, 179.98, 'SHIPPED');

-- Sample Order Items
INSERT INTO order_items (order_id, product_id, quantity, unit_price) VALUES
(1, 1, 1, 999.99),
(1, 3, 1, 129.99),
(2, 2, 1, 299.99),
(3, 4, 2, 49.99);
