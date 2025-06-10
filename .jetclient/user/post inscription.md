```toml
name = 'post inscription'
method = 'POST'
url = 'http://localhost:8080/inscription'
sortWeight = 3000000
id = '8ce6e565-c209-4e70-bf10-0ed5f3b2f683'

[body]
type = 'JSON'
raw = '''
{ 
  "firstName": "sam",
  "email" : "z@z.com",
  "passwordHash" : "root",
  "phone" : "1234567890",
  
}'''
```
