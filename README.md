# Student Management System

Une application de gestion d'étudiants développée avec Spring Boot, Spring Data JPA et MySQL.

## Prérequis

- Java 17 ou supérieur
- MySQL Server
- Maven

## Configuration

1. Créez une base de données MySQL nommée `studentdb` :
   ```sql
   CREATE DATABASE studentdb;
   ```

2. Mettez à jour les informations de connexion dans `src/main/resources/application.properties` si nécessaire.

## Installation

1. Clonez le dépôt :
   ```bash
   git clone <repository-url>
   cd student-management
   ```

2. Compilez le projet :
   ```bash
   mvn clean install
   ```

3. Exécutez l'application :
   ```bash
   mvn spring-boot:run
   ```

## API Documentation

Une fois l'application démarrée, accédez à la documentation Swagger :
- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **OpenAPI JSON**: http://localhost:8080/api-docs

## Endpoints disponibles

- `GET /api/students` - Récupérer tous les étudiants
- `GET /api/students/{id}` - Récupérer un étudiant par son ID
- `POST /api/students` - Créer un nouvel étudiant
- `DELETE /api/students/{id}` - Supprimer un étudiant
- `GET /api/students/count` - Compter le nombre total d'étudiants
- `GET /api/students/by-year` - Obtenir le nombre d'étudiants par année de naissance

## Structure du projet

```
src/main/java/
├── com/example/studentmanagement/
│   ├── StudentManagementApplication.java  # Classe principale
│   ├── controller/                       # Contrôleurs REST
│   ├── entity/                           # Entités JPA
│   ├── repository/                       # Interfaces de repository
│   └── service/                          # Couche de service
└── resources/
    └── application.properties           # Configuration
```

## Tests

Pour exécuter les tests unitaires :

```bash
mvn test
```

## Licence

MIT
