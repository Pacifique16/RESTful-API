# E-Commerce Product API

A RESTful API built with Spring Boot for managing e-commerce products with PostgreSQL database integration.

## Features

- Complete CRUD operations for products
- Search and filter products by category, brand, price range
- Pagination support
- Stock management
- Duplicate product validation
- PostgreSQL database persistence

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

### 1. Get All Products
**GET** `/api/products`
```
http://localhost:8080/api/products
```

### 2. Get Products with Pagination
**GET** `/api/products?page={page}&limit={limit}`
```
http://localhost:8080/api/products?page=0&limit=5
```
<img width="1861" height="987" alt="getall products" src="https://github.com/user-attachments/assets/6f55da72-5295-4c7e-8718-425f0fed161e" />

### 3. Get Product by ID
**GET** `/api/products/{id}`
```
http://localhost:8080/api/products/1
```
<img width="1751" height="802" alt="get product by ID" src="https://github.com/user-attachments/assets/5254903e-506e-470c-a9cd-ca2f4cc2c1a0" />

### 4. Get Products by Category
**GET** `/api/products/category/{category}`
```
http://localhost:8080/api/products/category/Electronics
```
<img width="1742" height="938" alt="get product by category" src="https://github.com/user-attachments/assets/fc9db362-41f3-4871-baba-3eac01295ae1" />

### 5. Get Products by Brand
**GET** `/api/products/brand/{brand}`
```
http://localhost:8080/api/products/brand/Apple
```
<img width="1746" height="797" alt="get product by brand" src="https://github.com/user-attachments/assets/2a191ed4-30c4-4849-8545-a79ca9e407f4" />

### 6. Search Products by Keyword
**GET** `/api/products/search?keyword={keyword}`
```
http://localhost:8080/api/products/search?keyword=phone
```
<img width="1738" height="765" alt="search product by keyword" src="https://github.com/user-attachments/assets/096183f7-f520-47e0-9bd9-c867412dfe66" />

### 7. Get Products by Price Range
**GET** `/api/products/price-range?min={min}&max={max}`
```
http://localhost:8080/api/products/price-range?min=100&max=500
```
<img width="1752" height="763" alt="get product by price range min=10 max=20" src="https://github.com/user-attachments/assets/0c5b5f75-0ad2-4189-b155-d7f7bb4dc2de" />

### 8. Get In-Stock Products
**GET** `/api/products/in-stock`
```
http://localhost:8080/api/products/in-stock
```
<img width="1765" height="896" alt="get in-stock products" src="https://github.com/user-attachments/assets/27ce86a4-66b6-40ca-8f50-4dce6d5be347" />

### 9. Add New Product
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

<img width="1748" height="767" alt="add new product" src="https://github.com/user-attachments/assets/5db27d7f-672b-41b1-946e-2e44fa3bc937" />


### 10. Update Product
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

<img width="1748" height="797" alt="update product" src="https://github.com/user-attachments/assets/ae7b41d9-45a0-436a-9dcc-0f258b9a4f03" />


### 11. Update Stock Quantity
**PATCH** `/api/products/{id}/stock?quantity={quantity}`
```
http://localhost:8080/api/products/1/stock?quantity=100
```

**Response:** `200 OK` or `404 Not Found`

<img width="1748" height="762" alt="update stock quantity" src="https://github.com/user-attachments/assets/0645c526-835c-419d-846f-dcdd07684946" />


### 12. Delete Product
**DELETE** `/api/products/{id}`
```
http://localhost:8080/api/products/1
```

**Response:** `204 No Content` or `404 Not Found`

## API Screenshots

### 1. Get All Products
![Get All Products](images/getall%20products.png)

### 2. Get Products with Pagination
![Get Products with Pagination](images/get%20products%20with%20pagination.png)

### 3. Get Product by ID
![Get Product by ID](images/get%20product%20by%20ID.png)

### 4. Get Products by Category
![Get Products by Category](images/get%20product%20by%20category.png)

### 5. Get Products by Brand
![Get Products by Brand](images/get%20product%20by%20brand.png)

### 6. Search Products by Keyword
![Search Products by Keyword](images/search%20product%20by%20keyword.png)

### 7. Get Products by Price Range
![Get Products by Price Range](images/get%20product%20by%20price%20range%20min=10%20max=20.png)

### 8. Get In-Stock Products
![Get In-Stock Products](images/get%20in-stock%20products.png)

### 9. Add New Product
![Add New Product](images/add%20new%20product.png)

### 10. Update Product
![Update Product](images/update%20product.png)

### 11. Update Stock Quantity
![Update Stock Quantity](images/update%20stock%20quantity.png)

### 12. Delete Product
![Delete Product](images/delete%20product.png)

## API Screenshots

For all screen shots, check folder `images`



## HTTP Status Codes

- `200 OK` - Successful GET, PUT, PATCH requests
- `201 Created` - Successful POST request
- `204 No Content` - Successful DELETE request
- `404 Not Found` - Resource not found
- `409 Conflict` - Duplicate product name

## Project Structure

```
src/main/java/com/auca/restfulapi/
└── ecommerce/
    ├── model/
    │   └── Product.java
    ├── controller/
    │   └── ProductController.java
    └── repository/
        └── ProductRepository.java
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

## Author

Student ID: 26937
<br>
Name: Pacifique HARERIMANA

This project built for educational purposes.

## Notes

- Product names must be unique
- All data persists in PostgreSQL database
- Tables are automatically created on application startup
- Use `spring.jpa.hibernate.ddl-auto=create-drop` to reset database on restart
