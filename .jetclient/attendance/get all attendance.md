```toml
name = 'get all attendance'
method = 'GET'
url = 'http://localhost:8080/attendance'
sortWeight = 1000000
id = '6c2cfa61-6039-4a68-ae6c-8e91496baf35'

[body]
type = 'JSON'
raw = '''
{
  
  "checkInTime": "2025-04-15T16:00:56",
  "event": {
    "id": 1,
    "title": "Metz - Reims",
    "description": "Match amical",
    "startDate": "2025-04-15T14:10:56.000+00:00",
    "location": "Paris",
    "maxCapacity": 100,
    "organizer": {
      "id": 1,
      "firstName": "Alice",
      "email": "alice@example.com",
      "passwordHash": "hash1",
      "phone": "1234567890",
      "createdAt": "2025-04-15T16:10:56"
    }
  }
}'''
```
