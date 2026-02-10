# API Endpoints Quick Reference

Base URL: `http://localhost:8080`

---

## Question 1: Library Book Management API

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/books` | Get all books |
| GET | `/api/books/{id}` | Get book by ID |
| GET | `/api/books/search?title={title}` | Search books by title |
| POST | `/api/books` | Add new book |
| DELETE | `/api/books/{id}` | Delete book |

**Sample Data:** 3 books (Clean Code, Effective Java, Spring in Action)

---

## Question 2: Student Registration API

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/students` | Get all students |
| GET | `/api/students/{studentId}` | Get student by ID |
| GET | `/api/students/major/{major}` | Get students by major |
| GET | `/api/students/filter?gpa={minGpa}` | Filter students by GPA |
| POST | `/api/students` | Register new student |
| PUT | `/api/students/{studentId}` | Update student |

**Sample Data:** 5 students (3 Computer Science, 1 Business, 1 Engineering)

---

## Question 3: Restaurant Menu API

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/menu` | Get all menu items |
| GET | `/api/menu/{id}` | Get menu item by ID |
| GET | `/api/menu/category/{category}` | Get items by category |
| GET | `/api/menu/available?available=true` | Get available items |
| GET | `/api/menu/search?name={name}` | Search items by name |
| POST | `/api/menu` | Add new menu item |
| PUT | `/api/menu/{id}/availability` | Toggle availability |
| DELETE | `/api/menu/{id}` | Delete menu item |

**Sample Data:** 8 items (Appetizer, Main Course, Dessert, Beverage)

---

## Question 4: E-Commerce Product API

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/products` | Get all products |
| GET | `/api/products?page={p}&limit={l}` | Get products (paginated) |
| GET | `/api/products/{productId}` | Get product by ID |
| GET | `/api/products/category/{category}` | Get products by category |
| GET | `/api/products/brand/{brand}` | Get products by brand |
| GET | `/api/products/search?keyword={kw}` | Search products |
| GET | `/api/products/price-range?min={}&max={}` | Filter by price |
| GET | `/api/products/in-stock` | Get in-stock products |
| POST | `/api/products` | Add new product |
| PUT | `/api/products/{productId}` | Update product |
| PATCH | `/api/products/{productId}/stock?quantity={}` | Update stock |
| DELETE | `/api/products/{productId}` | Delete product |

**Sample Data:** 10 products (Electronics, Footwear, Clothing)

---

## Question 5: Task Management API

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/tasks` | Get all tasks |
| GET | `/api/tasks/{taskId}` | Get task by ID |
| GET | `/api/tasks/status?completed={bool}` | Get tasks by status |
| GET | `/api/tasks/priority/{priority}` | Get tasks by priority |
| POST | `/api/tasks` | Create new task |
| PUT | `/api/tasks/{taskId}` | Update task |
| PATCH | `/api/tasks/{taskId}/complete` | Mark as completed |
| DELETE | `/api/tasks/{taskId}` | Delete task |

**Sample Data:** 4 tasks (LOW, MEDIUM, HIGH priorities)

---

## Bonus: User Profile API (with ApiResponse wrapper)

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/users` | Get all users |
| GET | `/api/users/{userId}` | Get user by ID |
| GET | `/api/users/username/{username}` | Get user by username |
| GET | `/api/users/country/{country}` | Get users by country |
| GET | `/api/users/age-range?min={}&max={}` | Get users by age range |
| GET | `/api/users/active?active={bool}` | Get active/inactive users |
| POST | `/api/users` | Create user profile |
| PUT | `/api/users/{userId}` | Update user profile |
| PATCH | `/api/users/{userId}/activate` | Activate user |
| PATCH | `/api/users/{userId}/deactivate` | Deactivate user |
| DELETE | `/api/users/{userId}` | Delete user |

**Sample Data:** 5 users (USA, Canada, UK, Australia)

---

## HTTP Status Codes

| Code | Meaning | When Used |
|------|---------|-----------|
| 200 | OK | Successful GET, PUT, PATCH |
| 201 | Created | Successful POST |
| 204 | No Content | Successful DELETE |
| 404 | Not Found | Resource doesn't exist |

---

## Quick Test Commands (using curl)

### Get All Books
```bash
curl http://localhost:8080/api/books
```

### Add New Book
```bash
curl -X POST http://localhost:8080/api/books ^
  -H "Content-Type: application/json" ^
  -d "{\"title\":\"Test Book\",\"author\":\"Test Author\",\"isbn\":\"123\",\"publicationYear\":2024}"
```

### Get Students by Major
```bash
curl "http://localhost:8080/api/students/major/Computer Science"
```

### Search Products
```bash
curl "http://localhost:8080/api/products/search?keyword=phone"
```

### Get Tasks by Priority
```bash
curl http://localhost:8080/api/tasks/priority/HIGH
```

### Get Users (with ApiResponse)
```bash
curl http://localhost:8080/api/users
```

---

## Total Endpoints: 49

- Question 1: 5 endpoints
- Question 2: 6 endpoints
- Question 3: 8 endpoints
- Question 4: 11 endpoints
- Question 5: 8 endpoints
- Bonus: 11 endpoints

---

## Categories Used

**Question 3 (Menu):**
- Appetizer
- Main Course
- Dessert
- Beverage

**Question 4 (Products):**
- Electronics
- Footwear
- Clothing

**Question 5 (Tasks):**
- LOW
- MEDIUM
- HIGH

---

## Sample Test Scenarios

1. **Library:** Search for "Clean" → Should return "Clean Code"
2. **Student:** Filter GPA >= 3.5 → Should return 3 students
3. **Menu:** Get "Main Course" → Should return 3 items
4. **Product:** Price range $100-$500 → Should return 5 products
5. **Task:** Get HIGH priority → Should return 2 tasks
6. **User:** Get USA users → Should return 2 users

---

## Postman Collection

Import `Postman_Collection.json` for pre-configured requests!

---

**Print this page for quick reference during testing!** 📋
