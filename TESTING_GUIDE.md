# Testing Guide for Postman

## Setup Instructions

### 1. Install and Open Postman

- Download from: https://www.postman.com/downloads/
- Install and open the application

### 2. Import the Collection

1. Click "Import" button (top left)
2. Click "Upload Files"
3. Select `Postman_Collection.json` from the project folder
4. Click "Import"
5. You'll see "Spring Boot RESTful API Assignment" collection in the left sidebar

### 3. Start the Spring Boot Application

```bash
mvn spring-boot:run
```

Wait until you see: "Started RestfulApiApplication in X seconds"

## Testing Each Question

### Question 1: Library Book Management API

#### Test 1: Get All Books

1. Select: `Question 1 - Library API` → `Get All Books`
2. Click "Send"
3. **Expected:** Status 200 OK, 3 books in response
4. **Screenshot:** Save as `Q1_GetAllBooks.png`

#### Test 2: Get Book by ID

1. Select: `Get Book by ID`
2. URL shows: `/api/books/1`
3. Click "Send"
4. **Expected:** Status 200 OK, single book with id=1
5. **Screenshot:** Save as `Q1_GetBookById.png`

#### Test 3: Search Books

1. Select: `Search Books by Title`
2. URL shows: `/api/books/search?title=clean`
3. Click "Send"
4. **Expected:** Status 200 OK, books containing "clean" in title
5. **Screenshot:** Save as `Q1_SearchBooks.png`

#### Test 4: Add New Book

1. Select: `Add New Book`
2. Method: POST
3. Body is already filled with sample data
4. Click "Send"
5. **Expected:** Status 201 Created, new book with id=4
6. **Screenshot:** Save as `Q1_AddBook.png`

#### Test 5: Delete Book

1. Select: `Delete Book`
2. URL shows: `/api/books/1`
3. Click "Send"
4. **Expected:** Status 204 No Content, empty response
5. Verify: Run "Get All Books" again - should show only 2 books
6. **Screenshot:** Save as `Q1_DeleteBook.png`

---

### Question 2: Student Registration API

#### Test 1: Get All Students

1. Select: `Question 2 - Student API` → `Get All Students`
2. Click "Send"
3. **Expected:** Status 200 OK, 5 students
4. **Screenshot:** Save as `Q2_GetAllStudents.png`

#### Test 2: Get Students by Major

1. Select: `Get Students by Major`
2. URL shows: `/api/students/major/Computer Science`
3. Click "Send"
4. **Expected:** Status 200 OK, 3 Computer Science students
5. **Screenshot:** Save as `Q2_GetByMajor.png`

#### Test 3: Filter by GPA

1. Select: `Filter Students by GPA`
2. URL shows: `/api/students/filter?gpa=3.5`
3. Click "Send"
4. **Expected:** Status 200 OK, students with GPA >= 3.5
5. **Screenshot:** Save as `Q2_FilterByGPA.png`

#### Test 4: Register New Student

1. Select: `Register New Student`
2. Method: POST
3. Body shows sample student data
4. Click "Send"
5. **Expected:** Status 201 Created, new student with id=6
6. **Screenshot:** Save as `Q2_RegisterStudent.png`

---

### Question 3: Restaurant Menu API

#### Test 1: Get All Menu Items

1. Select: `Question 3 - Restaurant Menu API` → `Get All Menu Items`
2. Click "Send"
3. **Expected:** Status 200 OK, 8 menu items
4. **Screenshot:** Save as `Q3_GetAllMenuItems.png`

#### Test 2: Get by Category

1. Select: `Get Items by Category`
2. URL shows: `/api/menu/category/Main Course`
3. Click "Send"
4. **Expected:** Status 200 OK, Main Course items only
5. **Screenshot:** Save as `Q3_GetByCategory.png`

#### Test 3: Get Available Items

1. Select: `Get Available Items`
2. URL shows: `/api/menu/available?available=true`
3. Click "Send"
4. **Expected:** Status 200 OK, only available items
5. **Screenshot:** Save as `Q3_GetAvailable.png`

---

### Question 4: E-Commerce Product API

#### Test 1: Get All Products

1. Select: `Question 4 - E-Commerce Product API` → `Get All Products`
2. Click "Send"
3. **Expected:** Status 200 OK, 10 products
4. **Screenshot:** Save as `Q4_GetAllProducts.png`

#### Test 2: Get with Pagination

1. Select: `Get Products with Pagination`
2. URL shows: `/api/products?page=0&limit=5`
3. Click "Send"
4. **Expected:** Status 200 OK, only 5 products
5. **Screenshot:** Save as `Q4_Pagination.png`

#### Test 3: Search Products

1. Select: `Search Products`
2. URL shows: `/api/products/search?keyword=phone`
3. Click "Send"
4. **Expected:** Status 200 OK, products with "phone" in name/description
5. **Screenshot:** Save as `Q4_SearchProducts.png`

#### Test 4: Price Range Filter

1. Select: `Get Products by Price Range`
2. URL shows: `/api/products/price-range?min=100&max=500`
3. Click "Send"
4. **Expected:** Status 200 OK, products between $100-$500
5. **Screenshot:** Save as `Q4_PriceRange.png`

#### Test 5: In-Stock Products

1. Select: `Get In-Stock Products`
2. Click "Send"
3. **Expected:** Status 200 OK, products with stockQuantity > 0
4. **Screenshot:** Save as `Q4_InStock.png`

---

### Question 5: Task Management API

#### Test 1: Get All Tasks

1. Select: `Question 5 - Task Management API` → `Get All Tasks`
2. Click "Send"
3. **Expected:** Status 200 OK, 4 tasks
4. **Screenshot:** Save as `Q5_GetAllTasks.png`

#### Test 2: Get by Status

1. Select: `Get Tasks by Status`
2. URL shows: `/api/tasks/status?completed=false`
3. Click "Send"
4. **Expected:** Status 200 OK, incomplete tasks only
5. **Screenshot:** Save as `Q5_GetByStatus.png`

#### Test 3: Get by Priority

1. Select: `Get Tasks by Priority`
2. URL shows: `/api/tasks/priority/HIGH`
3. Click "Send"
4. **Expected:** Status 200 OK, HIGH priority tasks
5. **Screenshot:** Save as `Q5_GetByPriority.png`

#### Test 4: Mark as Completed

1. Select: `Mark Task as Completed`
2. Method: PATCH
3. URL shows: `/api/tasks/1/complete`
4. Click "Send"
5. **Expected:** Status 200 OK, task with completed=true
6. **Screenshot:** Save as `Q5_MarkCompleted.png`

---

### Bonus: User Profile API

#### Test 1: Get All Users (with ApiResponse)

1. Select: `Bonus - User Profile API` → `Get All Users`
2. Click "Send"
3. **Expected:** Status 200 OK, response wrapped in ApiResponse format:

```json
{
  "success": true,
  "message": "Users retrieved successfully",
  "data": [...]
}
```

4. **Screenshot:** Save as `Bonus_GetAllUsers.png`

#### Test 2: Get by Country

1. Select: `Get Users by Country`
2. URL shows: `/api/users/country/USA`
3. Click "Send"
4. **Expected:** Status 200 OK, USA users with ApiResponse wrapper
5. **Screenshot:** Save as `Bonus_GetByCountry.png`

#### Test 3: Get by Age Range

1. Select: `Get Users by Age Range`
2. URL shows: `/api/users/age-range?min=20&max=30`
3. Click "Send"
4. **Expected:** Status 200 OK, users aged 20-30 with ApiResponse wrapper
5. **Screenshot:** Save as `Bonus_AgeRange.png`

#### Test 4: Create User

1. Select: `Create User`
2. Method: POST
3. Body shows sample user data
4. Click "Send"
5. **Expected:** Status 201 Created, with ApiResponse wrapper
6. **Screenshot:** Save as `Bonus_CreateUser.png`

#### Test 5: Activate User

1. Select: `Activate User`
2. Method: PATCH
3. URL shows: `/api/users/3/activate`
4. Click "Send"
5. **Expected:** Status 200 OK, user with active=true
6. **Screenshot:** Save as `Bonus_ActivateUser.png`

---

## Additional Tests to Perform

### Test 404 Not Found

1. Try: `GET http://localhost:8080/api/books/999`
2. **Expected:** Status 404 Not Found

### Test Invalid Data

1. Try POST with missing required fields
2. **Expected:** Status 400 Bad Request (or 500 depending on validation)

### Test All HTTP Methods

- ✅ GET - Retrieve data
- ✅ POST - Create new resource
- ✅ PUT - Update entire resource
- ✅ PATCH - Partial update
- ✅ DELETE - Remove resource

---

## Creating Your Own Tests in Postman

### Add a New Request

1. Right-click on a folder (e.g., "Question 1 - Library API")
2. Select "Add Request"
3. Name it (e.g., "Custom Test")
4. Set method (GET, POST, etc.)
5. Enter URL
6. Add body if needed (for POST/PUT)
7. Click "Send"

### Example: Custom Book Search

1. Method: GET
2. URL: `http://localhost:8080/api/books/search?title=java`
3. Click "Send"
4. Should return books with "java" in title

---

## Troubleshooting

### Error: "Could not get any response"

- **Solution:** Make sure Spring Boot application is running
- Check console for "Started RestfulApiApplication"

### Error: "Connection refused"

- **Solution:** Verify port 8080 is not blocked
- Try: `http://localhost:8080/api/books` in browser

### Error: 404 Not Found

- **Solution:** Check URL spelling
- Verify endpoint exists in controller

### Error: 500 Internal Server Error

- **Solution:** Check Spring Boot console for error details
- Verify request body format for POST/PUT requests

---

## Submission Checklist

Before submitting, ensure you have:

- [ ] Tested all 5 questions + bonus (43 total endpoints)
- [ ] Captured screenshots for key tests
- [ ] Verified all status codes (200, 201, 204, 404)
- [ ] Tested POST requests create new resources
- [ ] Tested DELETE requests remove resources
- [ ] Verified ApiResponse wrapper in bonus question
- [ ] Exported Postman collection (File → Export)
- [ ] Committed all code to Git
- [ ] Pushed to branch: `restFull_api_StudentId`

---

## Quick Reference: All Endpoints Count

- Question 1 (Library): 5 endpoints ✅
- Question 2 (Student): 6 endpoints ✅
- Question 3 (Restaurant): 8 endpoints ✅
- Question 4 (E-Commerce): 11 endpoints ✅
- Question 5 (Task): 8 endpoints ✅
- Bonus (User Profile): 11 endpoints ✅

**Total: 49 endpoints**

---

## Tips for Screenshots

1. **Full Window:** Capture entire Postman window
2. **Show Details:** Include URL, method, status code, and response
3. **Clear View:** Zoom in if text is too small
4. **Organize:** Name files clearly (Q1_GetAllBooks.png)
5. **Folder:** Create a "screenshots" folder in project

---

## Export Postman Collection

1. Right-click on "Spring Boot RESTful API Assignment" collection
2. Select "Export"
3. Choose "Collection v2.1"
4. Save as `Postman_Collection_Tested.json`
5. Include in submission

Good luck with your testing! 🚀
