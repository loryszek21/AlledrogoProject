import requests

url = "http://localhost:8080/verify"

# Token JWT
token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJNdWNoYSIsImlhdCI6MTczMzU5MjYyMywiZXhwIjoxNzMzNjI4NjIzfQ.HhVU0hUD998Y-QTLG0WIJRGHt65GdNNFCIVpaXRyt9U" 

headers = {
    "Authorization": f"Bearer {token}",
    "accept": "*/*"
}

# Wykonanie żądania GET
response = requests.get(url, headers=headers)

# Wyświetlenie odpowiedzi
print("Status code:", response.status_code)
print("Response body:", response.text)