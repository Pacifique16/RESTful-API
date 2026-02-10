# Spring Boot RESTful API Assignment

This project contains implementations for all 5 questions plus the bonus question for the Spring Boot RESTful API assignment.

## Project Structure

```
src/main/java/com/auca/restfulapi/
├── RestfulApiApplication.java (Main Application)
├── library/
│   ├── model/Book.java
│   └── controller/BookController.java
├── student/
│   ├── model/Student.java
│   └── controller/StudentController.java
├── restaurant/
│   ├── model/MenuItem.java
│   └── controller/MenuController.java
├── ecommerce/
│   ├── model/Product.java
│   └── controller/ProductController.java
├── task/
│   ├── model/Task.java
│   └── controller/TaskController.java
└── user/
    ├── model/UserProfile.java
    ├── model/ApiResponse.java
    └── controller/UserProfileController.java
```

## How to Run

1. **Prerequisites:**
   - Java 17 or higher
   - Maven 3.6+
   - Postman (for testing)

2. **Run the application:**
   ```bash
   mvn spring-boot:run
   ```
   Or run the `RestfulApiApplication.java` main class from your IDE.

3. **Application will start on:** `http://localhost:8080`

---

## Question 1: Library Book Management API

### Endpoints

#### 1. Get All Books
- **URL:** `GET http://localhost:8080/api/books`
- **Response:** 200 OK
```json
[
  {
    "id": 1,
    "title": "Clean Code",
    "author": "Robert Martin",
    "isbn": "978-0132350884",
    "publicationYear": 2008
  }
]
```

#### 2. Get Book by ID
- **URL:** `GET http://localhost:8080/api/books/1`
- **Response:** 200 OK or 404 Not Found

#### 3. Search Books by Title
- **URL:** `GET http://localhost:8080/api/books/search?title=clean`
- **Response:** 200 OK

#### 4. Add New Book
- **URL:** `POST http://localhost:8080/api/books`
- **Headers:** `Content-Type: application/json`
- **Body:**
```json
{
  "title": "Design Patterns",
  "author": "Gang of Four",
  "isbn": "978-0201633610",
  "publicationYear": 1994
}
```
- **Response:** 201 Created

#### 5. Delete Book
- **URL:** `DELETE http://localhost:8080/api/books/1`
- **Response:** 204 No Content or 404 Not Found

---

## Question 2: Student Registration API

### Endpoints

#### 1. Get All Students
- **URL:** `GET http://localhost:8080/api/students`
- **Response:** 200 OK

#### 2. Get Student by ID
- **URL:** `GET http://localhost:8080/api/students/1`
- **Response:** 200 OK or 404 Not Found

#### 3. Get Students by Major
- **URL:** `GET http://localhost:8080/api/students/major/Computer Science`
- **Response:** 200 OK

#### 4. Filter Students by GPA
- **URL:** `GET http://localhost:8080/api/students/filter?gpa=3.5`
- **Response:** 200 OK

#### 5. Register New Student
- **URL:** `POST http://localhost:8080/api/students`
- **Headers:** `Content-Type: application/json`
- **Body:**
```json
{
  "firstName": "Alice",
  "lastName": "Johnson",
  "email": "alice.j@example.com",
  "major": "Computer Science",
  "gpa": 3.7
}
```
- **Response:** 201 Created

#### 6. Update Student
- **URL:** `PUT http://localhost:8080/api/students/1`
- **Headers:** `Content-Type: application/json`
- **Body:**
```json
{
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.updated@example.com",
  "major": "Computer Science",
  "gpa": 3.9
}
```
- **Response:** 200 OK or 404 Not Found

---

## Question 3: Restaurant Menu API

### Endpoints

#### 1. Get All Menu Items
- **URL:** `GET http://localhost:8080/api/menu`
- **Response:** 200 OK

#### 2. Get Menu Item by ID
- **URL:** `GET http://localhost:8080/api/menu/1`
- **Response:** 200 OK or 404 Not Found

#### 3. Get Items by Category
- **URL:** `GET http://localhost:8080/api/menu/category/Main Course`
- **Response:** 200 OK

#### 4. Get Available Items
- **URL:** `GET http://localhost:8080/api/menu/available?available=true`
- **Response:** 200 OK

#### 5. Search Menu Items by Name
- **URL:** `GET http://localhost:8080/api/menu/search?name=chicken`
- **Response:** 200 OK

#### 6. Add New Menu Item
- **URL:** `POST http://localhost:8080/api/menu`
- **Headers:** `Content-Type: application/json`
- **Body:**
```json
{
  "name": "Pizza Margherita",
  "description": "Classic Italian pizza",
  "price": 11.99,
  "category": "Main Course",
  "available": true
}
```
- **Response:** 201 Created

#### 7. Toggle Item Availability
- **URL:** `PUT http://localhost:8080/api/menu/1/availability`
- **Response:** 200 OK or 404 Not Found

#### 8. Delete Menu Item
- **URL:** `DELETE http://localhost:8080/api/menu/1`
- **Response:** 204 No Content or 404 Not Found

---

## Question 4: E-Commerce Product API

### Endpoints

#### 1. Get All Products (with pagination)
- **URL:** `GET http://localhost:8080/api/products`
- **URL with pagination:** `GET http://localhost:8080/api/products?page=0&limit=5`
- **Response:** 200 OK

#### 2. Get Product by ID
- **URL:** `GET http://localhost:8080/api/products/1`
- **Response:** 200 OK or 404 Not Found

#### 3. Get Products by Category
- **URL:** `GET http://localhost:8080/api/products/category/Electronics`
- **Response:** 200 OK

#### 4. Get Products by Brand
- **URL:** `GET http://localhost:8080/api/products/brand/Apple`
- **Response:** 200 OK

#### 5. Search Products by Keyword
- **URL:** `GET http://localhost:8080/api/products/search?keyword=phone`
- **Response:** 200 OK

#### 6. Get Products by Price Range
- **URL:** `GET http://localhost:8080/api/products/price-range?min=100&max=500`
- **Response:** 200 OK

#### 7. Get In-Stock Products
- **URL:** `GET http://localhost:8080/api/products/in-stock`
- **Response:** 200 OK

#### 8. Add New Product
- **URL:** `POST http://localhost:8080/api/products`
- **Headers:** `Content-Type: application/json`
- **Body:**
```json
{
  "name": "iPad Pro",
  "description": "Apple tablet",
  "price": 799.99,
  "category": "Electronics",
  "stockQuantity": 25,
  "brand": "Apple"
}
```
- **Response:** 201 Created

#### 9. Update Product
- **URL:** `PUT http://localhost:8080/api/products/1`
- **Headers:** `Content-Type: application/json`
- **Body:** (Full product object)
- **Response:** 200 OK or 404 Not Found

#### 10. Update Stock Quantity
- **URL:** `PATCH http://localhost:8080/api/products/1/stock?quantity=100`
- **Response:** 200 OK or 404 Not Found

#### 11. Delete Product
- **URL:** `DELETE http://localhost:8080/api/products/1`
- **Response:** 204 No Content or 404 Not Found

---

## Question 5: Task Management API

### Endpoints

#### 1. Get All Tasks
- **URL:** `GET http://localhost:8080/api/tasks`
- **Response:** 200 OK

#### 2. Get Task by ID
- **URL:** `GET http://localhost:8080/api/tasks/1`
- **Response:** 200 OK or 404 Not Found

#### 3. Get Tasks by Status
- **URL:** `GET http://localhost:8080/api/tasks/status?completed=false`
- **Response:** 200 OK

#### 4. Get Tasks by Priority
- **URL:** `GET http://localhost:8080/api/tasks/priority/HIGH`
- **Response:** 200 OK

#### 5. Create New Task
- **URL:** `POST http://localhost:8080/api/tasks`
- **Headers:** `Content-Type: application/json`
- **Body:**
```json
{
  "title": "Code review",
  "description": "Review pull requests",
  "completed": false,
  "priority": "MEDIUM",
  "dueDate": "2024-12-22"
}
```
- **Response:** 201 Created

#### 6. Update Task
- **URL:** `PUT http://localhost:8080/api/tasks/1`
- **Headers:** `Content-Type: application/json`
- **Body:** (Full task object)
- **Response:** 200 OK or 404 Not Found

#### 7. Mark Task as Completed
- **URL:** `PATCH http://localhost:8080/api/tasks/1/complete`
- **Response:** 200 OK or 404 Not Found

#### 8. Delete Task
- **URL:** `DELETE http://localhost:8080/api/tasks/1`
- **Response:** 204 No Content or 404 Not Found

---

## Bonus Question: User Profile API

### Endpoints

#### 1. Get All Users
- **URL:** `GET http://localhost:8080/api/users`
- **Response:** 200 OK
```json
{
  "success": true,
  "message": "Users retrieved successfully",
  "data": [...]
}
```

#### 2. Get User by ID
- **URL:** `GET http://localhost:8080/api/users/1`
- **Response:** 200 OK or 404 Not Found

#### 3. Get User by Username
- **URL:** `GET http://localhost:8080/api/users/username/john_doe`
- **Response:** 200 OK or 404 Not Found

#### 4. Get Users by Country
- **URL:** `GET http://localhost:8080/api/users/country/USA`
- **Response:** 200 OK

#### 5. Get Users by Age Range
- **URL:** `GET http://localhost:8080/api/users/age-range?min=20&max=30`
- **Response:** 200 OK

#### 6. Get Active/Inactive Users
- **URL:** `GET http://localhost:8080/api/users/active?active=true`
- **Response:** 200 OK

#### 7. Create User
- **URL:** `POST http://localhost:8080/api/users`
- **Headers:** `Content-Type: application/json`
- **Body:**
```json
{
  "username": "new_user",
  "email": "newuser@example.com",
  "fullName": "New User",
  "age": 27,
  "country": "France",
  "bio": "New member",
  "active": true
}
```
- **Response:** 201 Created

#### 8. Update User
- **URL:** `PUT http://localhost:8080/api/users/1`
- **Headers:** `Content-Type: application/json`
- **Body:** (Full user object)
- **Response:** 200 OK or 404 Not Found

#### 9. Activate User
- **URL:** `PATCH http://localhost:8080/api/users/1/activate`
- **Response:** 200 OK or 404 Not Found

#### 10. Deactivate User
- **URL:** `PATCH http://localhost:8080/api/users/1/deactivate`
- **Response:** 200 OK or 404 Not Found

#### 11. Delete User
- **URL:** `DELETE http://localhost:8080/api/users/1`
- **Response:** 200 OK or 404 Not Found

---

## Testing with Postman

1. **Import Collection:** Create a new collection in Postman
2. **Set Base URL:** `http://localhost:8080`
3. **Test each endpoint** following the examples above
4. **Check Status Codes:** Verify proper HTTP status codes (200, 201, 204, 404)

### Sample Test Scenarios

**Question 1 - Library:**
- Get all books
- Search for "Clean Code"
- Add a new book
- Delete a book

**Question 2 - Student:**
- Get all students
- Filter by Computer Science major
- Filter students with GPA >= 3.5
- Register a new student

**Question 3 - Restaurant:**
- Get all menu items
- Filter by "Main Course" category
- Get only available items
- Toggle availability

**Question 4 - E-Commerce:**
- Get products with pagination (page=0, limit=5)
- Search for "phone"
- Filter by price range (100-500)
- Get in-stock products

**Question 5 - Task:**
- Get all tasks
- Filter by HIGH priority
- Get incomplete tasks
- Mark task as completed

**Bonus - User Profile:**
- Get all users (check ApiResponse wrapper)
- Search by country "USA"
- Filter by age range (20-30)
- Activate/deactivate user

---

## HTTP Status Codes Used

- **200 OK:** Successful GET, PUT, PATCH requests
- **201 Created:** Successful POST requests
- **204 No Content:** Successful DELETE requests
- **404 Not Found:** Resource not found

---

## Technologies Used

- Spring Boot 3.2.0
- Spring Web
- Java 17
- Maven

---

## Author

Student ID: [Your Student ID]

## Submission

Branch name: `restFull_api_StudentId`

---

## Notes

- All data is stored in-memory using ArrayList
- Data will be reset when the application restarts
- No database or service layer is used (as per requirements)
- All endpoints have been tested and work correctly
