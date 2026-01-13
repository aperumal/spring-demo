# REST API Documentation - Inquiry & Maintenance System

## Base URL
```
http://localhost:8080/api
```

## User Management API

### Create User
- **Endpoint**: `POST /users`
- **Request Body**:
```json
{
  "username": "john_doe",
  "email": "john@example.com",
  "password": "pass123",
  "fullName": "John Doe",
  "phoneNumber": "555-0101",
  "isActive": true
}
```
- **Response**: 201 Created with User object

### Get User by ID
- **Endpoint**: `GET /users/{id}`
- **Response**: User object or 404 Not Found

### Get All Users
- **Endpoint**: `GET /users`
- **Response**: Array of User objects

### Get Active Users
- **Endpoint**: `GET /users/active/list`
- **Response**: Array of active User objects

### Get User by Username
- **Endpoint**: `GET /users/username/{username}`
- **Response**: User object or 404 Not Found

### Get User by Email
- **Endpoint**: `GET /users/email/{email}`
- **Response**: User object or 404 Not Found

### Update User
- **Endpoint**: `PUT /users/{id}`
- **Request Body**: User object (fields to update)
- **Response**: Updated User object or 404 Not Found

### Delete User
- **Endpoint**: `DELETE /users/{id}`
- **Response**: 204 No Content

### Check Username Exists
- **Endpoint**: `GET /users/check/username/{username}`
- **Response**: `{ "exists": true/false }`

---

## Inquiry Management API

### Create Inquiry
- **Endpoint**: `POST /inquiries`
- **Request Body**:
```json
{
  "user": {
    "id": 1
  },
  "subject": "Login Issue",
  "description": "Cannot login to my account",
  "inquiryType": "TECHNICAL",
  "status": "OPEN",
  "priority": "HIGH"
}
```
- **Response**: 201 Created with Inquiry object

### Get Inquiry by ID
- **Endpoint**: `GET /inquiries/{id}`
- **Response**: Inquiry object or 404 Not Found

### Get All Inquiries
- **Endpoint**: `GET /inquiries`
- **Response**: Array of Inquiry objects

### Get Inquiries by User ID
- **Endpoint**: `GET /inquiries/user/{userId}`
- **Response**: Array of Inquiry objects for the user

### Get Inquiries by Status
- **Endpoint**: `GET /inquiries/status/{status}`
- **Values**: `OPEN`, `CLOSED`, `IN_PROGRESS`
- **Response**: Array of Inquiry objects

### Get Open Inquiries
- **Endpoint**: `GET /inquiries/status/open`
- **Response**: Array of open Inquiry objects

### Get Closed Inquiries
- **Endpoint**: `GET /inquiries/status/closed`
- **Response**: Array of closed Inquiry objects

### Get Inquiries by User and Status
- **Endpoint**: `GET /inquiries/user/{userId}/status/{status}`
- **Response**: Array of Inquiry objects

### Get Inquiries by Priority
- **Endpoint**: `GET /inquiries/priority/{priority}`
- **Values**: `LOW`, `NORMAL`, `HIGH`, `URGENT`
- **Response**: Array of Inquiry objects

### Get Inquiries by Type
- **Endpoint**: `GET /inquiries/type/{inquiryType}`
- **Values**: `TECHNICAL`, `BILLING`, `FEATURE`, `GENERAL`
- **Response**: Array of Inquiry objects

### Update Inquiry
- **Endpoint**: `PUT /inquiries/{id}`
- **Request Body**: Inquiry object (fields to update)
- **Response**: Updated Inquiry object or 404 Not Found

### Resolve Inquiry
- **Endpoint**: `PUT /inquiries/{id}/resolve`
- **Request Body**:
```json
{
  "response": "Issue has been resolved. Your password has been reset."
}
```
- **Response**: Updated Inquiry object with status CLOSED

### Delete Inquiry
- **Endpoint**: `DELETE /inquiries/{id}`
- **Response**: 204 No Content

### Get Inquiry Statistics
- **Endpoint**: `GET /inquiries/stats/total`
- **Response**: 
```json
{
  "total": 10,
  "open": 5
}
```

---

## Maintenance Management API

### Create Maintenance
- **Endpoint**: `POST /maintenance`
- **Request Body**:
```json
{
  "title": "Database Optimization",
  "description": "Optimize database indexes",
  "maintenanceType": "PREVENTIVE",
  "status": "SCHEDULED",
  "scheduledDate": "2026-01-20T02:00:00",
  "estimatedDuration": 120,
  "affectedSystems": "Database Server",
  "createdBy": "admin@example.com"
}
```
- **Response**: 201 Created with Maintenance object

### Get Maintenance by ID
- **Endpoint**: `GET /maintenance/{id}`
- **Response**: Maintenance object or 404 Not Found

### Get All Maintenance
- **Endpoint**: `GET /maintenance`
- **Response**: Array of Maintenance objects

### Get Maintenance by Status
- **Endpoint**: `GET /maintenance/status/{status}`
- **Values**: `SCHEDULED`, `IN_PROGRESS`, `COMPLETED`, `CANCELLED`
- **Response**: Array of Maintenance objects

### Get Scheduled Maintenance
- **Endpoint**: `GET /maintenance/status/scheduled`
- **Response**: Array of scheduled Maintenance objects (ordered by date)

### Get In-Progress Maintenance
- **Endpoint**: `GET /maintenance/status/in-progress`
- **Response**: Array of in-progress Maintenance objects

### Get Completed Maintenance
- **Endpoint**: `GET /maintenance/status/completed`
- **Response**: Array of completed Maintenance objects

### Get Maintenance by Type
- **Endpoint**: `GET /maintenance/type/{maintenanceType}`
- **Values**: `PREVENTIVE`, `CORRECTIVE`, `ROUTINE`, `EMERGENCY`
- **Response**: Array of Maintenance objects

### Get Maintenance by Date Range
- **Endpoint**: `GET /maintenance/date-range?startDate={startDate}&endDate={endDate}`
- **Parameters**:
  - `startDate`: ISO 8601 format (e.g., 2026-01-15T00:00:00)
  - `endDate`: ISO 8601 format (e.g., 2026-01-31T23:59:59)
- **Response**: Array of Maintenance objects in date range

### Update Maintenance
- **Endpoint**: `PUT /maintenance/{id}`
- **Request Body**: Maintenance object (fields to update)
- **Response**: Updated Maintenance object or 404 Not Found

### Start Maintenance
- **Endpoint**: `PUT /maintenance/{id}/start`
- **Response**: Updated Maintenance object with status IN_PROGRESS

### Complete Maintenance
- **Endpoint**: `PUT /maintenance/{id}/complete`
- **Request Body**:
```json
{
  "actualDuration": 115
}
```
- **Response**: Updated Maintenance object with status COMPLETED

### Delete Maintenance
- **Endpoint**: `DELETE /maintenance/{id}`
- **Response**: 204 No Content

### Get Maintenance Statistics
- **Endpoint**: `GET /maintenance/stats/overview`
- **Response**:
```json
{
  "total": 15,
  "scheduled": 5,
  "completed": 8
}
```

---

## Database Tables Schema

### users
- `id` (BIGINT PRIMARY KEY AUTO_INCREMENT)
- `username` (VARCHAR UNIQUE NOT NULL)
- `email` (VARCHAR NOT NULL)
- `password` (VARCHAR NOT NULL)
- `full_name` (VARCHAR)
- `phone_number` (VARCHAR)
- `is_active` (BOOLEAN DEFAULT true)
- `created_at` (TIMESTAMP)
- `updated_at` (TIMESTAMP)

### inquiries
- `id` (BIGINT PRIMARY KEY AUTO_INCREMENT)
- `user_id` (BIGINT FOREIGN KEY)
- `subject` (VARCHAR NOT NULL)
- `description` (TEXT)
- `inquiry_type` (VARCHAR)
- `status` (VARCHAR DEFAULT 'OPEN')
- `priority` (VARCHAR DEFAULT 'NORMAL')
- `response` (LONGTEXT)
- `created_at` (TIMESTAMP)
- `updated_at` (TIMESTAMP)
- `resolved_at` (TIMESTAMP)

### maintenance
- `id` (BIGINT PRIMARY KEY AUTO_INCREMENT)
- `title` (VARCHAR NOT NULL)
- `description` (TEXT)
- `maintenance_type` (VARCHAR)
- `status` (VARCHAR DEFAULT 'SCHEDULED')
- `scheduled_date` (TIMESTAMP)
- `completed_date` (TIMESTAMP)
- `estimated_duration` (INT)
- `actual_duration` (INT)
- `affected_systems` (VARCHAR)
- `notes` (LONGTEXT)
- `created_by` (VARCHAR)
- `created_at` (TIMESTAMP)
- `updated_at` (TIMESTAMP)

---

## Access H2 Console
- **URL**: `http://localhost:8080/h2-console`
- **JDBC URL**: `jdbc:h2:mem:testdb`
- **Username**: `sa`
- **Password**: (leave blank)

## CORS Configuration
- Allows requests from `http://localhost:4200` (Angular frontend)
- All controllers have `@CrossOrigin` annotation

---

## Example Usage

### Create a user
```bash
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{
    "username": "testuser",
    "email": "test@example.com",
    "password": "password123",
    "fullName": "Test User",
    "phoneNumber": "555-1234",
    "isActive": true
  }'
```

### Create an inquiry
```bash
curl -X POST http://localhost:8080/api/inquiries \
  -H "Content-Type: application/json" \
  -d '{
    "user": {"id": 1},
    "subject": "System not working",
    "description": "The application is crashing",
    "inquiryType": "TECHNICAL",
    "status": "OPEN",
    "priority": "HIGH"
  }'
```

### Create maintenance
```bash
curl -X POST http://localhost:8080/api/maintenance \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Server Update",
    "description": "Update production server",
    "maintenanceType": "PREVENTIVE",
    "status": "SCHEDULED",
    "scheduledDate": "2026-01-20T02:00:00",
    "estimatedDuration": 90,
    "affectedSystems": "Web Server",
    "createdBy": "admin"
  }'
```

### Get all inquiries
```bash
curl http://localhost:8080/api/inquiries
```

### Get inquiry statistics
```bash
curl http://localhost:8080/api/inquiries/stats/total
```

### Get maintenance by date range
```bash
curl "http://localhost:8080/api/maintenance/date-range?startDate=2026-01-01T00:00:00&endDate=2026-01-31T23:59:59"
```

### Start a maintenance task
```bash
curl -X PUT http://localhost:8080/api/maintenance/1/start \
  -H "Content-Type: application/json"
```

### Complete maintenance with duration
```bash
curl -X PUT http://localhost:8080/api/maintenance/1/complete \
  -H "Content-Type: application/json" \
  -d '{"actualDuration": 95}'
```
