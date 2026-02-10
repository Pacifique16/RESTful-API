# Test Comparison - Question 1

## Test 1: Get All Books
**URL:** `http://localhost:8080/api/books`
**Method:** GET
**Expected Response:** Array of 3 books

```json
[
  {
    "id": 1,
    "title": "Clean Code",
    "author": "Robert Martin",
    "isbn": "978-0132350884",
    "publicationYear": 2008
  },
  {
    "id": 2,
    "title": "Effective Java",
    "author": "Joshua Bloch",
    "isbn": "978-0134685991",
    "publicationYear": 2017
  },
  {
    "id": 3,
    "title": "Spring in Action",
    "author": "Craig Walls",
    "isbn": "978-1617294945",
    "publicationYear": 2018
  }
]
```

---

## Test 2: Get Book by ID
**URL:** `http://localhost:8080/api/books/1`
**Method:** GET
**Expected Response:** Single book object

```json
{
  "id": 1,
  "title": "Clean Code",
  "author": "Robert Martin",
  "isbn": "978-0132350884",
  "publicationYear": 2008
}
```

---

## Key Difference

| Test | URL | Returns |
|------|-----|---------|
| Test 1 | `/api/books` | **Array** with 3 books |
| Test 2 | `/api/books/1` | **Single object** (1 book) |

---

## What You Did ✅

You successfully tested **Test 2** (Get Book by ID)!

Now try **Test 1** by removing the `/1` from the URL:
- Change: `http://localhost:8080/api/books/1`
- To: `http://localhost:8080/api/books`

You should see all 3 books in an array!

---

## Quick Postman Tips

1. **Get All Books** - No ID in URL → Returns array
2. **Get Book by ID** - Has `/1` in URL → Returns single object
3. Look at the response - if it starts with `[` it's an array, if it starts with `{` it's a single object
