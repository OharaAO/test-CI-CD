```toml
name = 'post frienship'
method = 'PUT'
url = 'http://localhost:8080/friendship/1'
sortWeight = 3000000
id = 'fb365a1a-1ee1-4c81-a9e0-b7bc632c5358'

[body]
type = 'JSON'
raw = '''
{
  "id": 1,
  "status": "pending",
  "user1": {
    "id": 1,
    "firstName": "Alice",
    "email": "alice@example.com",
    "passwordHash": "hash1",
    "phone": "1234567890",
    "createdAt": "2025-04-15T16:10:56"
  },
  "user2": {
    "id": 2,
    "firstName": "Bob",
    "email": "bob@example.com",
    "passwordHash": "hash2",
    "phone": "0987654321",
    "createdAt": "2025-04-15T16:10:56"
  }
}'''
```
