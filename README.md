
# 🍽️ Restaurant E-Commerce Platform

A full-stack e-commerce web application for restaurant management with separate user and admin interfaces, built with Spring Boot and Angular.

## 📝 Description

This restaurant e-commerce platform provides a comprehensive solution for managing restaurant orders, products, and categories. The application features role-based access control with distinct user and administrator experiences:

* **Users** can browse products by category, place orders, and track their order history with detailed product information
* **Administrators** have full CRUD operations on products, categories, and can view all system orders and user activities
* **Bilingual Support** with English and Arabic error messages
* **Secure Authentication** using JWT tokens
* **RESTful API** with comprehensive Swagger documentation
* **Pagination Support** for efficient data handling
* **Product Details Management** including manufacturing info, colors, and specifications

## 🏗️ Project Structure

### Backend (Spring Boot)

```
src/main/java/com/example/project905/
├── Config/
│   ├── ExceptionConfig.java          # Global exception handling
│   ├── SwaggerConfig.java            # API documentation configuration
│   └── SecurityConfig.java           # JWT security configuration
├── Controller/
│   ├── AuthController.java           # Authentication endpoints
│   ├── CategoryController.java       # Category management
│   ├── ProductController.java        # Product management
│   ├── OrderController.java          # Order processing
│   ├── ProductDetailsController.java # Product specifications
│   ├── ChefController.java           # Chef information
│   └── ContactInfoController.java    # Contact management
├── Dto/
│   ├── CategoryDto.java
│   ├── ProductDto.java
│   ├── ChefDto.java
│   ├── UserDto.java
│   ├── OrderDto.java
│   ├── ProductDetailsDto.java
│   └── UserOrdersHistoryDto.java
├── Model/
│   ├── User.java
│   ├── Product.java
│   ├── Category.java
│   ├── Order.java
│   ├── Chef.java
│   └── ProductDetails.java
├── Repository/
│   └── [JPA Repositories for all entities]
├── Service/
│   ├── AuthService.java
│   ├── CategoryService.java
│   ├── ProductService.java
│   ├── OrderService.java
│   ├── ProductDetailsService.java
│   └── ChefService.java
└── ServiceImpl/
    └── [Service implementations]
```

### Frontend (Angular)

```
src/
├── app/
│   ├── components/          # Reusable UI components
│   ├── modules/             # Feature modules
│   └── services/            # API integration services
```

## 🔧 Technologies & APIs Used

### Backend Technologies
* **Spring Boot 3.x** - Main framework
* **Spring Security** - Authentication & authorization
* **Spring Data JPA** - Database operations
* **JWT (JSON Web Tokens)** - Secure authentication
* **Hibernate** - ORM framework
* Orcale - Database
* **Swagger/OpenAPI 3.0** - API documentation
* **Lombok** - Reduce boilerplate code
* **Bean Validation (Jakarta)** - Input validation
* **MapStruct** - DTO mapping

### Frontend Technologies
* **Angular 15+** - Frontend framework
* **TypeScript** - Programming language
* **RxJS** - Reactive programming
* **Angular Router** - Navigation
* **Angular Forms** - Form handling
* **HttpClient** - API communication

### Key APIs & Endpoints

#### Authentication APIs
```
POST /auth/signup          # User registration
POST /auth/login           # User authentication
```

#### Category Management APIs
```
GET  /CategoryController/getAll           # Get all categories
POST /CategoryController/save             # Create category
PUT  /CategoryController/update           # Update category
DELETE /CategoryController/delete/{id}    # Delete category
GET  /CategoryController/orderByName      # Sort by name
```

#### Product Management APIs
```
GET  /ProductController/getAll                     # Get paginated products
GET  /ProductController/search                     # Search products
GET  /ProductController/getByCategory              # Filter by category
POST /ProductController/save                       # Create product
PUT  /ProductController/update                     # Update product
DELETE /ProductController/delete/{id}              # Delete product
```

#### Order Management APIs
```
POST /api/orders/create-order              # Place new order
GET  /api/orders/my-orders                 # User order history
GET  /api/orders                           # All orders (admin)
GET  /api/orders/users-history             # All users with orders
DELETE /api/orders/{id}                    # Delete order
```

#### Product Details APIs
```
POST /ProductDetailsController/save                    # Add product details
PUT  /ProductDetailsController/update                  # Update details
GET  /ProductDetailsController/getByProduct/{id}       # Get product details
DELETE /ProductDetailsController/delete/{id}           # Delete details
```

#### Chef Management APIs
```
GET  /ChefController/getAllChefs           # Get all chefs
POST /ChefController/saveAll               # Add multiple chefs
```

## ✨ Features

### User Features
* ✅ User registration and login
* ✅ Browse products by categories
* ✅ Search products by keyword
* ✅ View detailed product information (manufacturing, specs, etc.)
* ✅ Place orders and receive order numbers
* ✅ View personal order history
* ✅ Pagination for product browsing

### Admin Features
* ✅ Full product management (CRUD operations)
* ✅ Category management
* ✅ View all system orders
* ✅ View all user order histories
* ✅ Add/Update product details
* ✅ Chef information management
* ✅ User management capabilities

### System Features
* ✅ JWT-based authentication
* ✅ Role-based access control (USER/ADMIN)
* ✅ Bilingual error messages (English/Arabic)
* ✅ Global exception handling
* ✅ Input validation
* ✅ Swagger API documentation
* ✅ CORS configuration for Angular frontend
* ✅ Pagination support

## 🚀 Getting Started

### Prerequisites
* Java 17 or higher
* Node.js 16+ and npm
* orcale
* Maven

### Backend Setup

1. Clone the repository
```bash
git clone <repository-url>
cd project905
```

2. Configure database in `application.properties`
```properties
spring.datasource.driver-class-name: oracle.jdbc.OracleDriver
spring.datasource.url: jdbc:oracle:thin:@//localhost:1521/orclpdb
spring.datasource.username=your_username
spring.datasource.password=your_password
```

3. Run the application
```bash
mvn spring-boot:run
```

The backend will start on `http://localhost:9090`

### Frontend Setup

1. Navigate to frontend directory
```bash
cd frontend
```

2. Install dependencies
```bash
npm install
```

3. Run the development server
```bash
ng serve
```

The frontend will start on `http://localhost:4200`

## 📚 API Documentation

Once the application is running, access the Swagger UI documentation at:
```
http://localhost:9090/swagger-ui.html
```

## 👤 Author

**Amr Ahmed**
* Email: amrhamed456@gmail.com
* LinkedIn: [amr-ahmed-550a3a340](https://www.linkedin.com/in/amr-ahmed-550a3a340)


## 🤝 Contributing

Contributions, issues, and feature requests are welcome!

