```toml
name = 'post event'
method = 'POST'
url = 'http://localhost:8080/event'
sortWeight = 3000000
id = '1cc82b4c-6732-4e51-bbc9-4036961d0ea5'

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
