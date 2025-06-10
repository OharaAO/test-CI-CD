```toml
name = 'get event by id'
method = 'GET'
url = 'http://localhost:8080/event/1'
sortWeight = 1000000
id = '352b24c3-ccef-46db-9c38-576da51f85db'

[body]
type = 'JSON'
raw = '''
{
  'startDate': "2025-04-03T00:00:00.000+00:00",
  'title': "Match",
  'description': "Metz-Reims,",
  'location': "Corrida Collosseum"
}'''
```
