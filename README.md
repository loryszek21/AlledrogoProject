###### AlledrogoProject\Database>

###### docker compose up -d

po tej komendzie baza zostanie wystartowana w tle

i można zacząc korzystać z aplikacji 

# Alledrogo Project

## Wymagania
- **Node.js** w wersji co najmniej `v20.13.x`. [Pobierz tutaj](https://nodejs.org/).
- **Docker Desktop** do uruchamiania bazy danych. [Pobierz tutaj](https://www.docker.com/get-started/).
- **IntelliJ IDEA** (lub inne IDE obsługujące projekty Spring Boot) do uruchamiania backendu.

## Instrukcja Instalacji

### 1. Frontend
W terminalu w Visual Studio Code przejdź do katalogu `frontend`. Przykład:

#### cd AlledrogoProject/frontend

#### a) Instalacja zależności:

Uruchamiamy komendę: npm install

#### 2. Uruchomienie frontendu  
Uruchamiamy komendę: npm run dev

Po tej operacji frontend wystartuje na porcie `3000`. Strona będzie dostępna pod adresem:  
[http://localhost:3000/home](http://localhost:3000/home).  

## 3. Uruchomienie backendu  
Backend uruchamiamy w **IntelliJ IDEA** (lub innym IDE obsługującym projekty Spring Boot). Wystarczy otworzyć projekt backendu i uruchomić go przyciskiem **Run**. Backend będzie dostępny pod adresem:  
[http://localhost:8080/hello](http://localhost:8080/hello).  

## 4. Uruchomienie bazy danych  
Aby uruchomić bazę danych, potrzebujemy **Docker Desktop**, który można pobrać z [tej strony](https://www.docker.com/get-started/).  
W terminalu przechodzimy do katalogu `Database`, przykładowa ścieżka:  
`AlledrogoProject\Database>`  
i uruchamiamy komendę: docker compose up -d

Po tej komendzie baza danych wystartuje w tle, a aplikacja będzie gotowa do użycia.  

## Podsumowanie  
Frontend działa na porcie `3000`, a backend na porcie `8080`.  
