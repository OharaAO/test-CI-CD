```toml
name = 'post attendance'
method = 'POST'
url = 'http://localhost:8080/attendance'
sortWeight = 3000000
id = 'c3017b01-b20e-40fc-b934-8f3f5a06696a'

[body]
type = 'JSON'
raw = '''
{
  
  "checkInTime": "2025-05-08T09:14:00",
  "event_id" : 1,
  "user_id" : 1,
}'''
```
