-- Sample Users
INSERT INTO users (username, email, password, full_name, phone_number, is_active) 
VALUES 
('john_doe', 'john@example.com', 'pass123', 'John Doe', '555-0101', true),
('jane_smith', 'jane@example.com', 'pass456', 'Jane Smith', '555-0102', true),
('admin_user', 'admin@example.com', 'admin123', 'Admin User', '555-0103', true),
('newuser001', 'newuser001@example.com', 'SecurePass123', 'New User One', '555-0001', true);

-- Sample Products
INSERT INTO products (name, description, price, stock_quantity, sku, category, is_active) 
VALUES 
('Laptop Pro', 'High-performance laptop for professionals', 1299.99, 50, 'LAPTOP-001', 'Electronics', true),
('Wireless Mouse', 'Ergonomic wireless mouse with long battery life', 29.99, 200, 'MOUSE-001', 'Accessories', true),
('USB-C Cable', 'Durable USB-C charging cable', 15.99, 500, 'CABLE-001', 'Accessories', true),
('Monitor 4K', '27-inch 4K monitor with HDR support', 399.99, 30, 'MONITOR-001', 'Electronics', true),
('Mechanical Keyboard', 'RGB mechanical keyboard with Cherry MX switches', 149.99, 100, 'KEYBOARD-001', 'Accessories', true);

-- Sample Inquiries
INSERT INTO inquiries (user_id, subject, description, inquiry_type, status, priority, created_at, updated_at) 
VALUES 
(1, 'Login Issue', 'Cannot login to my account', 'TECHNICAL', 'OPEN', 'HIGH', NOW(), NOW()),
(1, 'Feature Request', 'Request for dark mode', 'FEATURE', 'OPEN', 'LOW', NOW(), NOW()),
(2, 'Billing Question', 'Question about my bill', 'BILLING', 'CLOSED', 'NORMAL', NOW(), NOW()),
(3, 'System Error', 'Getting error 500 on dashboard', 'TECHNICAL', 'OPEN', 'HIGH', NOW(), NOW());

-- Sample Maintenance
INSERT INTO maintenance (title, description, maintenance_type, status, scheduled_date, estimated_duration, affected_systems, created_by, created_at, updated_at) 
VALUES 
('Database Optimization', 'Optimize database indexes', 'PREVENTIVE', 'SCHEDULED', '2026-01-20 02:00:00', 120, 'Database Server', 'admin@example.com', NOW(), NOW()),
('Security Patch', 'Apply latest security patches', 'CORRECTIVE', 'SCHEDULED', '2026-01-15 03:00:00', 60, 'Web Server, API Server', 'admin@example.com', NOW(), NOW()),
('System Backup', 'Full system backup', 'ROUTINE', 'COMPLETED', '2026-01-10 01:00:00', 90, 'All Systems', 'admin@example.com', NOW(), NOW());
