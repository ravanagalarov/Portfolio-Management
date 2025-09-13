# Portfolio Management System

A Spring Boot REST API for managing personal portfolios with JWT authentication.

## Prerequisites

- Java 17 or higher
- Maven 3.6 or higher
- MySQL 8.0 or higher

## Setup Instructions

### 1. Database Setup
1. Install MySQL and create a database:
```sql
CREATE DATABASE portfolio_db;
```

2. Update database credentials in `src/main/resources/application.properties` if needed:
```properties
spring.datasource.username=root
spring.datasource.password=your_password
```

### 2. Running the Application

#### Option 1: Using Maven Wrapper (Recommended)
```bash
# Navigate to the Portfolio directory
cd Portfolio

# Run the application
./mvnw spring-boot:run
```

#### Option 2: Using Maven (if installed)
```bash
# Navigate to the Portfolio directory
cd Portfolio

# Clean and compile
mvn clean compile

# Run the application
mvn spring-boot:run
```

### 3. API Endpoints

#### Authentication
- `POST /api/auth/register` - Register a new user
- `POST /api/auth/login` - Login and get JWT token

#### Portfolio Management (Requires Authentication)
- `GET /api/auth/portfolio/**` - Portfolio operations
- `GET /api/auth/education/**` - Education management
- `GET /api/auth/experience/**` - Experience management
- `GET /api/auth/project/**` - Project management
- `GET /api/auth/skills/**` - Skills management

#### Admin Operations
- `GET /api/auth/admin` - Admin-only operations

## Security Features

- JWT-based authentication
- Password encryption using BCrypt
- Role-based access control (USER, ADMIN)
- CORS configuration for cross-origin requests

## Default Configuration

- Server runs on port 8080
- JWT token expires in 5 hours
- Database auto-creates tables on startup
- Debug logging enabled for development

## Troubleshooting

1. **Database Connection Issues**: Ensure MySQL is running and credentials are correct
2. **Port Already in Use**: Change `server.port` in application.properties
3. **JWT Token Issues**: Check if the secret key is properly configured
4. **CORS Issues**: Verify CORS configuration in SecurityConfig

## API Testing

Use tools like Postman or curl to test the API:

```bash
# Register a new user
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{"username":"testuser","password":"password123"}'

# Login
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"testuser","password":"password123"}'
```
