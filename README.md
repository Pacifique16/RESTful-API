# Spring Boot RESTful API Assignment

A comprehensive RESTful API built with Spring Boot featuring e-commerce product management and hierarchical location management with PostgreSQL database integration.

## Features

### E-Commerce Product API
- Complete CRUD operations for products
- Search and filter products by category, brand, price range
- Pagination support
- Stock management
- Duplicate product validation

### Location Hierarchy API
- Hierarchical location management (Province → District → Sector → Cell → Village)
- Parent-child relationship tracking
- Query locations by type
- Get child locations
- Complete CRUD operations

## Technologies Used

- Spring Boot 3.2.0
- Spring Data JPA
- PostgreSQL
- Maven
- Java 17

## Prerequisites

- Java 17 or higher
- Maven 3.6+
- PostgreSQL
- Postman (for testing)

## Database Setup

1. Create PostgreSQL database:
```sql
CREATE DATABASE ecommerce_db;
```

2. Update `src/main/resources/application.properties` with your PostgreSQL password:
```properties
spring.datasource.password=YOUR_PASSWORD
```

## Running the Application

```bash
mvn spring-boot:run
```

Application runs on: `http://localhost:8080`

## API Endpoints

## E-Commerce Product API

### 1. Get All Products
**GET** `/api/products`
```
http://localhost:8080/api/products
```
<img width="1861" height="987" alt="getall products" src="https://github.com/user-attachments/assets/a3eeb7db-5561-45b2-be2d-dd8c5595fe51" />

### 2. Get Products with Pagination
**GET** `/api/products?page={page}&limit={limit}`
```
http://localhost:8080/api/products?page=0&limit=5
```
<img width="1755" height="933" alt="get products with pagination" src="https://github.com/user-attachments/assets/e4425eed-14d7-4497-a514-3786cb08b8fa" />

### 3. Get Product by ID
**GET** `/api/products/{id}`
```
http://localhost:8080/api/products/2
```
<img width="1751" height="802" alt="get product by ID" src="https://github.com/user-attachments/assets/4205a6e0-789c-45a6-af89-a219a678fd88" />

### 4. Get Products by Category
**GET** `/api/products/category/{category}`
```
http://localhost:8080/api/products/category/Electronics
```
<img width="1742" height="938" alt="get product by category" src="https://github.com/user-attachments/assets/bb96535c-946b-4c18-9042-65eff19a311c" />

### 5. Get Products by Brand
**GET** `/api/products/brand/{brand}`
```
http://localhost:8080/api/products/brand/Apple
```
<img width="1746" height="797" alt="get product by brand" src="https://github.com/user-attachments/assets/9ca12d5d-6d54-4689-8151-2f4a6cd41cc3" />

### 6. Search Products by Keyword
**GET** `/api/products/search?keyword={keyword}`
```
http://localhost:8080/api/products/search?keyword=phone
```
<img width="1738" height="765" alt="search product by keyword" src="https://github.com/user-attachments/assets/084907b8-4347-4a1c-97cf-810132248897" />

### 7. Get Products by Price Range
**GET** `/api/products/price-range?min={min}&max={max}`
```
http://localhost:8080/api/products/price-range?min=100&max=500
```
<img width="1752" height="763" alt="get product by price range min=10 max=20" src="https://github.com/user-attachments/assets/67834c39-99b1-4a69-b628-e91190421aa9" />


### 8. Get Products by BOTH Price Range and Brand
**GET** `/api/products/filter?price={price}&brand={brand}`
```
min=100&max=500]http://localhost:8080/api/products/filter?price=999.99&brand=Apple
```
<img width="1757" height="856" alt="search both by price and brand" src="https://github.com/user-attachments/assets/5f93ce5c-6a39-4971-8c0d-2a90873a26fe" />

### 9. Get In-Stock Products
**GET** `/api/products/in-stock`
```
http://localhost:8080/api/products/in-stock
```
<img width="1765" height="896" alt="get in-stock products" src="https://github.com/user-attachments/assets/5d0aee03-083b-445d-a255-46988924be4f" />

### 10. Add New Product
**POST** `/api/products`

**Headers:** `Content-Type: application/json`

**Body:**
```json
{
  "name": "iPhone 15",
  "description": "Latest Apple smartphone",
  "price": 999.99,
  "category": "Electronics",
  "stockQuantity": 50,
  "brand": "Apple"
}
```

**Response:** `201 Created`

<img width="1748" height="767" alt="add new product" src="https://github.com/user-attachments/assets/25683e87-3c71-4362-968c-6e4a6f61cb43" />

### 11. Update Product
**PUT** `/api/products/{id}`

**Headers:** `Content-Type: application/json`

**Body:**
```json
{
  "name": "iPhone 15 Pro",
  "description": "Latest Apple flagship smartphone",
  "price": 1199.99,
  "category": "Electronics",
  "stockQuantity": 30,
  "brand": "Apple"
}
```

**Response:** `200 OK` or `404 Not Found`

<img width="1748" height="797" alt="update product" src="https://github.com/user-attachments/assets/8d76948a-955a-4b2c-aad5-e2ff4e2be320" />

### 12. Update Stock Quantity
**PATCH** `/api/products/{id}/stock?quantity={quantity}`
```
http://localhost:8080/api/products/1/stock?quantity=100
```

**Response:** `200 OK` or `404 Not Found`

<img width="1748" height="762" alt="update stock quantity" src="https://github.com/user-attachments/assets/c2143946-04e3-40cd-9528-0bd5032f67a7" />


### 13. Delete Product
**DELETE** `/api/products/{id}`
```
http://localhost:8080/api/products/1
```

**Response:** `204 No Content` or `404 Not Found`

<img width="1762" height="743" alt="delete product" src="https://github.com/user-attachments/assets/b231ba27-9d5e-4f71-9ea8-2bf8f53645ea" />

## Location Hierarchy API

### 1. Get All Locations
**GET** `/api/locations`

### 2. Get Location by ID
**GET** `/api/locations/{id}`

### 3. Get Locations by Type
**GET** `/api/locations/type/{type}`
- Types: PROVINCE, DISTRICT, SECTOR, CELL, VILLAGE

### 4. Get Child Locations
**GET** `/api/locations/children/{parentId}`

### 5. Save Location
**POST** `/api/locations/save?parentId={parentId}`
```json
{
  "code": "WP",
  "name": "Western",
  "type": "PROVINCE"
}
```

### 6. Update Location
**PUT** `/api/locations/{id}`

### 7. Delete Location
**DELETE** `/api/locations/{id}`



## HTTP Status Codes

- `200 OK` - Successful GET, PUT, PATCH requests
- `201 Created` - Successful POST request
- `204 No Content` - Successful DELETE request
- `404 Not Found` - Resource not found
- `409 Conflict` - Duplicate product name

## Project Structure

```
src/main/java/com/auca/restfulapi/
├── ecommerce/
│   ├── model/
│   │   └── Product.java
│   ├── controller/
│   │   └── ProductController.java
│   ├── repository/
│   │   └── ProductRepository.java
│   └── service/
│       └── ProductService.java
└── academic/
    ├── model/
    │   ├── Location.java
    │   └── ELocationType.java
    ├── controller/
    │   └── LocationController.java
    ├── repository/
    │   └── LocationRepository.java
    └── service/
        └── LocationService.java
```

## Database Schema

**Table:** `product`

| Column | Type | Constraints |
|--------|------|-------------|
| product_id | BIGINT | PRIMARY KEY, AUTO_INCREMENT |
| name | VARCHAR | NOT NULL, UNIQUE |
| description | VARCHAR | |
| price | DOUBLE | |
| category | VARCHAR | |
| stock_quantity | INTEGER | |
| brand | VARCHAR | |

**Table:** `location`

| Column | Type | Constraints |
|--------|------|-------------|
| id | UUID | PRIMARY KEY |
| code | VARCHAR | NOT NULL, UNIQUE |
| name | VARCHAR | NOT NULL |
| type | VARCHAR | ENUM (PROVINCE, DISTRICT, SECTOR, CELL, VILLAGE) |
| parent_id | UUID | FOREIGN KEY → location(id) |

## Author

Student ID: 26937
<br>
Name: Pacifique HARERIMANA

## Notes

- Product names must be unique
- All data persists in PostgreSQL database
- Tables are automatically created on application startup
- Use `spring.jpa.hibernate.ddl-auto=create-drop` to reset database on restart
