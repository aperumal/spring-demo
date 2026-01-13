# Product Inquiry REST API Documentation

## Base URL
```
http://localhost:8080/api
```

---

## Products API

### Create Product
- **Endpoint**: `POST /products`
- **Request Body**:
```json
{
  "name": "Laptop Pro",
  "description": "High-performance laptop for professionals",
  "price": 1299.99,
  "stockQuantity": 50,
  "sku": "LAPTOP-001",
  "category": "Electronics",
  "isActive": true
}
```
- **Response**: 201 Created with Product object

### Get Product by ID
- **Endpoint**: `GET /products/{id}`
- **Response**: Product object or 404 Not Found

### Get All Products
- **Endpoint**: `GET /products`
- **Response**: Array of Product objects

### Get Active Products
- **Endpoint**: `GET /products/active/list`
- **Response**: Array of active Product objects

### Get Products by Category
- **Endpoint**: `GET /products/category/{category}`
- **Response**: Array of Product objects in category

### Get Active Products by Category
- **Endpoint**: `GET /products/category/{category}/active`
- **Response**: Array of active products in category

### Get Product by SKU
- **Endpoint**: `GET /products/sku/{sku}`
- **Response**: Product object or 404 Not Found

### Get Product by Name
- **Endpoint**: `GET /products/name/{name}`
- **Response**: Product object or 404 Not Found

### Update Product
- **Endpoint**: `PUT /products/{id}`
- **Request Body**: Product object (fields to update)
- **Response**: Updated Product object or 404 Not Found

### Delete Product
- **Endpoint**: `DELETE /products/{id}`
- **Response**: 204 No Content

### Get Product Statistics
- **Endpoint**: `GET /products/stats/overview`
- **Response**:
```json
{
  "total": 50,
  "active": 45
}
```

---

## Product Inquiries API

### Create Product Inquiry
- **Endpoint**: `POST /product-inquiries`
- **Request Body**:
```json
{
  "product": {
    "id": 1
  },
  "user": {
    "id": 1
  },
  "subject": "Price Information",
  "message": "What is the current price for bulk orders?",
  "inquiryType": "PRICE",
  "status": "PENDING",
  "priority": "NORMAL"
}
```
- **Inquiry Types**: `PRICE`, `AVAILABILITY`, `SPECIFICATIONS`, `QUALITY`, `DELIVERY`, `OTHER`
- **Status Values**: `PENDING`, `ANSWERED`, `RESOLVED`
- **Priority Values**: `LOW`, `NORMAL`, `HIGH`, `URGENT`
- **Response**: 201 Created with ProductInquiry object

### Get Inquiry by ID
- **Endpoint**: `GET /product-inquiries/{id}`
- **Response**: ProductInquiry object or 404 Not Found

### Get All Inquiries
- **Endpoint**: `GET /product-inquiries`
- **Response**: Array of ProductInquiry objects

### Get Inquiries by Product ID
- **Endpoint**: `GET /product-inquiries/product/{productId}`
- **Response**: Array of ProductInquiry objects for the product

### Get Inquiries by User ID
- **Endpoint**: `GET /product-inquiries/user/{userId}`
- **Response**: Array of ProductInquiry objects from the user

### Get Inquiries by Status
- **Endpoint**: `GET /product-inquiries/status/{status}`
- **Values**: `PENDING`, `ANSWERED`, `RESOLVED`
- **Response**: Array of ProductInquiry objects

### Get Pending Inquiries
- **Endpoint**: `GET /product-inquiries/status/pending`
- **Response**: Array of pending ProductInquiry objects

### Get Answered Inquiries
- **Endpoint**: `GET /product-inquiries/status/answered`
- **Response**: Array of answered ProductInquiry objects

### Get Resolved Inquiries
- **Endpoint**: `GET /product-inquiries/status/resolved`
- **Response**: Array of resolved ProductInquiry objects

### Get Inquiries by Priority
- **Endpoint**: `GET /product-inquiries/priority/{priority}`
- **Values**: `LOW`, `NORMAL`, `HIGH`, `URGENT`
- **Response**: Array of ProductInquiry objects

### Get Inquiries by Type
- **Endpoint**: `GET /product-inquiries/type/{inquiryType}`
- **Values**: `PRICE`, `AVAILABILITY`, `SPECIFICATIONS`, `QUALITY`, `DELIVERY`, `OTHER`
- **Response**: Array of ProductInquiry objects

### Get Inquiries by Product and Status
- **Endpoint**: `GET /product-inquiries/product/{productId}/status/{status}`
- **Response**: Array of ProductInquiry objects

### Get Inquiries by User and Status
- **Endpoint**: `GET /product-inquiries/user/{userId}/status/{status}`
- **Response**: Array of ProductInquiry objects

### Filter Inquiries by Status and Priority
- **Endpoint**: `GET /product-inquiries/filter?status={status}&priority={priority}`
- **Query Parameters**:
  - `status`: `PENDING`, `ANSWERED`, `RESOLVED`
  - `priority`: `LOW`, `NORMAL`, `HIGH`, `URGENT`
- **Response**: Array of filtered ProductInquiry objects

### Update Inquiry
- **Endpoint**: `PUT /product-inquiries/{id}`
- **Request Body**: ProductInquiry object (fields to update)
- **Response**: Updated ProductInquiry object or 404 Not Found

### Answer Inquiry
- **Endpoint**: `PUT /product-inquiries/{id}/answer`
- **Request Body**:
```json
{
  "response": "Thank you for your inquiry. The bulk price for orders over 100 units is $999.99 per unit."
}
```
- **Response**: Updated ProductInquiry object with status ANSWERED

### Resolve Inquiry
- **Endpoint**: `PUT /product-inquiries/{id}/resolve`
- **Response**: Updated ProductInquiry object with status RESOLVED

### Delete Inquiry
- **Endpoint**: `DELETE /product-inquiries/{id}`
- **Response**: 204 No Content

### Get Inquiry Statistics
- **Endpoint**: `GET /product-inquiries/stats/overview`
- **Response**:
```json
{
  "total": 25,
  "pending": 10,
  "answered": 8,
  "resolved": 7
}
```

---

## Database Tables Schema

### products
- `id` (BIGINT PRIMARY KEY AUTO_INCREMENT)
- `name` (VARCHAR NOT NULL)
- `description` (TEXT)
- `price` (DECIMAL)
- `stock_quantity` (INT)
- `sku` (VARCHAR)
- `category` (VARCHAR)
- `is_active` (BOOLEAN DEFAULT true)
- `created_at` (TIMESTAMP)
- `updated_at` (TIMESTAMP)

### product_inquiries
- `id` (BIGINT PRIMARY KEY AUTO_INCREMENT)
- `product_id` (BIGINT FOREIGN KEY)
- `user_id` (BIGINT FOREIGN KEY)
- `subject` (VARCHAR NOT NULL)
- `message` (TEXT)
- `inquiry_type` (VARCHAR)
- `status` (VARCHAR DEFAULT 'PENDING')
- `priority` (VARCHAR DEFAULT 'NORMAL')
- `response` (TEXT)
- `response_date` (TIMESTAMP)
- `created_at` (TIMESTAMP)
- `updated_at` (TIMESTAMP)
- `resolved_at` (TIMESTAMP)

---

## Example Usage

### Create a product
```bash
curl -X POST http://localhost:8080/api/products \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Wireless Headphones",
    "description": "Premium noise-cancelling wireless headphones",
    "price": 249.99,
    "stockQuantity": 75,
    "sku": "HEADPHONES-001",
    "category": "Audio",
    "isActive": true
  }'
```

### Create a product inquiry
```bash
curl -X POST http://localhost:8080/api/product-inquiries \
  -H "Content-Type: application/json" \
  -d '{
    "product": {"id": 1},
    "user": {"id": 1},
    "subject": "Availability Question",
    "message": "When will this product be back in stock?",
    "inquiryType": "AVAILABILITY",
    "status": "PENDING",
    "priority": "HIGH"
  }'
```

### Get pending inquiries
```bash
curl http://localhost:8080/api/product-inquiries/status/pending
```

### Answer an inquiry
```bash
curl -X PUT http://localhost:8080/api/product-inquiries/1/answer \
  -H "Content-Type: application/json" \
  -d '{"response": "The product will be back in stock on January 20, 2026."}'
```

### Get inquiries for a specific product
```bash
curl http://localhost:8080/api/product-inquiries/product/1
```

### Get all product statistics
```bash
curl http://localhost:8080/api/products/stats/overview
```

### Get inquiry statistics
```bash
curl http://localhost:8080/api/product-inquiries/stats/overview
```

### Filter inquiries by status and priority
```bash
curl "http://localhost:8080/api/product-inquiries/filter?status=PENDING&priority=HIGH"
```

### Get inquiries by product and status
```bash
curl http://localhost:8080/api/product-inquiries/product/1/status/ANSWERED
```

### Update an inquiry
```bash
curl -X PUT http://localhost:8080/api/product-inquiries/1 \
  -H "Content-Type: application/json" \
  -d '{
    "priority": "URGENT",
    "status": "ANSWERED"
  }'
```

### Resolve an inquiry
```bash
curl -X PUT http://localhost:8080/api/product-inquiries/1/resolve \
  -H "Content-Type: application/json"
```

---

## CORS Configuration
- Allows requests from `http://localhost:4200` (Angular frontend)
- All controllers have `@CrossOrigin` annotation

## Access H2 Console
- **URL**: `http://localhost:8080/h2-console`
- **JDBC URL**: `jdbc:h2:mem:testdb`
- **Username**: `sa`
- **Password**: (leave blank)
