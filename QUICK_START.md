# Quick Start Guide

## Running the Application

### Option 1: Using Maven Command Line
```bash
cd "c:\Users\Pacifique Harerimana\OneDrive\Desktop\AUCA\Semester 8\Web Tech\APIs assignment"
mvn spring-boot:run
```

### Option 2: Using IDE (Eclipse/IntelliJ/VS Code)
1. Open the project folder in your IDE
2. Wait for Maven to download dependencies
3. Run `RestfulApiApplication.java` as Java Application
4. Application will start on http://localhost:8080

## Testing with Postman

### Import the Collection
1. Open Postman
2. Click "Import" button
3. Select the `Postman_Collection.json` file from the project root
4. All endpoints will be imported automatically

### Quick Test Endpoints

**Test if server is running:**
```
GET http://localhost:8080/api/books
```

**Expected Response:** List of 3 books with status 200 OK

## All API Base URLs

- Library API: `http://localhost:8080/api/books`
- Student API: `http://localhost:8080/api/students`
- Restaurant Menu API: `http://localhost:8080/api/menu`
- E-Commerce Product API: `http://localhost:8080/api/products`
- Task Management API: `http://localhost:8080/api/tasks`
- User Profile API: `http://localhost:8080/api/users`

## Common Issues & Solutions

### Issue: Port 8080 already in use
**Solution:** Change port in `application.properties`:
```properties
server.port=8081
```

### Issue: Maven dependencies not downloading
**Solution:** Run:
```bash
mvn clean install
```

### Issue: Java version mismatch
**Solution:** Ensure Java 17 or higher is installed:
```bash
java -version
```

## Git Commands for Submission

### Create and push to branch
```bash
# Initialize git (if not already done)
git init

# Add all files
git add .

# Commit
git commit -m "Complete RESTful API assignment - All 5 questions + bonus"

# Create branch (replace StudentId with your actual student ID)
git checkout -b restFull_api_StudentId

# Add remote (replace with your repository URL)
git remote add origin <your-repository-url>

# Push to branch
git push -u origin restFull_api_StudentId
```

## Testing Checklist

### Question 1 - Library (5 endpoints)
- [ ] GET all books
- [ ] GET book by ID
- [ ] Search books by title
- [ ] POST new book
- [ ] DELETE book

### Question 2 - Student (6 endpoints)
- [ ] GET all students
- [ ] GET student by ID
- [ ] GET students by major
- [ ] Filter students by GPA
- [ ] POST new student
- [ ] PUT update student

### Question 3 - Restaurant Menu (8 endpoints)
- [ ] GET all menu items
- [ ] GET menu item by ID
- [ ] GET items by category
- [ ] GET available items
- [ ] Search items by name
- [ ] POST new menu item
- [ ] PUT toggle availability
- [ ] DELETE menu item

### Question 4 - E-Commerce Product (11 endpoints)
- [ ] GET all products
- [ ] GET products with pagination
- [ ] GET product by ID
- [ ] GET products by category
- [ ] GET products by brand
- [ ] Search products by keyword
- [ ] GET products by price range
- [ ] GET in-stock products
- [ ] POST new product
- [ ] PUT update product
- [ ] PATCH update stock
- [ ] DELETE product

### Question 5 - Task Management (8 endpoints)
- [ ] GET all tasks
- [ ] GET task by ID
- [ ] GET tasks by status
- [ ] GET tasks by priority
- [ ] POST new task
- [ ] PUT update task
- [ ] PATCH mark as completed
- [ ] DELETE task

### Bonus - User Profile (11 endpoints)
- [ ] GET all users (with ApiResponse wrapper)
- [ ] GET user by ID
- [ ] GET user by username
- [ ] GET users by country
- [ ] GET users by age range
- [ ] GET active/inactive users
- [ ] POST new user
- [ ] PUT update user
- [ ] PATCH activate user
- [ ] PATCH deactivate user
- [ ] DELETE user

## Sample Test Data

### Add a Book (POST)
```json
{
  "title": "Effective Java",
  "author": "Joshua Bloch",
  "isbn": "978-0134685991",
  "publicationYear": 2017
}
```

### Add a Student (POST)
```json
{
  "firstName": "Emma",
  "lastName": "Watson",
  "email": "emma.w@example.com",
  "major": "Computer Science",
  "gpa": 3.9
}
```

### Add a Menu Item (POST)
```json
{
  "name": "Spaghetti Bolognese",
  "description": "Italian pasta with meat sauce",
  "price": 13.99,
  "category": "Main Course",
  "available": true
}
```

### Add a Product (POST)
```json
{
  "name": "AirPods Pro",
  "description": "Wireless earbuds with noise cancellation",
  "price": 249.99,
  "category": "Electronics",
  "stockQuantity": 75,
  "brand": "Apple"
}
```

### Add a Task (POST)
```json
{
  "title": "Prepare presentation",
  "description": "Create slides for project demo",
  "completed": false,
  "priority": "HIGH",
  "dueDate": "2024-12-23"
}
```

### Add a User (POST)
```json
{
  "username": "alice_wonder",
  "email": "alice@example.com",
  "fullName": "Alice Wonder",
  "age": 24,
  "country": "UK",
  "bio": "Software engineer",
  "active": true
}
```

## Verification

After running all tests, verify:
1. All GET requests return 200 OK
2. All POST requests return 201 Created
3. All DELETE requests return 204 No Content
4. Invalid IDs return 404 Not Found
5. Bonus question returns ApiResponse wrapper format

## Support

For issues or questions, refer to:
- README.md for detailed endpoint documentation
- Spring Boot documentation: https://spring.io/projects/spring-boot
- Postman documentation: https://learning.postman.com/
