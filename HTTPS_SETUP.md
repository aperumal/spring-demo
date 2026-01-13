# HTTPS Configuration Summary

## SSL Certificate Generated
- **Location**: `src/main/resources/keystore.p12`
- **Type**: PKCS12
- **Algorithm**: RSA 2048-bit
- **Validity**: 365 days
- **Common Name**: localhost
- **Password**: spring-demo

## Spring Boot HTTPS Configuration
Updated `src/main/resources/application.properties`:
```properties
server.port=8443
server.ssl.key-store=classpath:keystore.p12
server.ssl.key-store-password=spring-demo
server.ssl.key-store-type=PKCS12
server.ssl.key-alias=spring-demo
server.http2.enabled=true
```

## Access Points
- **Spring Boot REST API**: https://localhost:8443/api
- **H2 Console**: https://localhost:8443/h2-console
- **Angular Frontend**: http://localhost:4200 (still HTTP)

### API Endpoints
- **Users**: https://localhost:8443/api/users
- **Products**: https://localhost:8443/api/products
- **Product Inquiries**: https://localhost:8443/api/product-inquiries
- **Inquiries**: https://localhost:8443/api/inquiries
- **Maintenance**: https://localhost:8443/api/maintenance

## Angular Frontend Configuration
Created `client/proxy.conf.json` to proxy API requests to HTTPS backend:
```json
{
  "/api": {
    "target": "https://localhost:8443",
    "secure": false,
    "changeOrigin": true
  }
}
```

Updated `client/angular.json` to use the proxy configuration.

## Certificate Trust Note
Since this is a self-signed certificate for development:
- Browsers will show a security warning
- You can safely ignore it for local development
- When making requests, skip certificate verification (e.g., `-SkipCertificateCheck` in PowerShell)

## Running Both Servers

### Spring Boot (HTTPS)
```bash
cd c:\dev\vscode\spring-demo
mvn spring-boot:run
```
Runs on: https://localhost:8443

### Angular (HTTP)
```bash
cd c:\dev\vscode\spring-demo\client
npm start
```
Runs on: http://localhost:4200
(Proxies /api requests to https://localhost:8443)

## Testing HTTPS Connection
```powershell
$response = Invoke-WebRequest -Uri "https://localhost:8443/api/users" -SkipCertificateCheck
$response.Content | ConvertFrom-Json
```

## Example curl Commands

Get all users (HTTPS):
```bash
curl --insecure https://localhost:8443/api/users
```

Create a user (HTTPS):
```bash
curl --insecure -X POST https://localhost:8443/api/users \
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

Get product inquiries (HTTPS):
```bash
curl --insecure https://localhost:8443/api/product-inquiries
```

## Files Modified
1. `src/main/resources/application.properties` - Added HTTPS configuration
2. `src/main/resources/keystore.p12` - Generated SSL certificate (NEW)
3. `client/proxy.conf.json` - Created proxy configuration (NEW)
4. `client/angular.json` - Updated to use proxy configuration
