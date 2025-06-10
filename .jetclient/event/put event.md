```toml
name = 'put event'
method = 'PUT'
url = 'http://localhost:8080/event/1'
sortWeight = 4000000
id = '40729101-3909-4835-a5e1-202200a61983'

[body]
type = 'JSON'
raw = '''
{
    "title": "Conférence sur le développement durable",
    "description": "Une conférence sur les enjeux environnementaux et les solutions innovantes",
    "startDate": "2023-12-15T14:30:00",
    "location": "Paris, Centre de Congrès",
    "maxCapacity": 200,
    "organizer": {
        "id": 1
    }
}'''
```
