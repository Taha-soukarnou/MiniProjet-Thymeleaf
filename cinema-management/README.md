# Cinema Management Application

Application Java Spring MVC pour gerer les films, les seances et les billets vendus.

## Technologies

- Java 17+
- Spring Boot
- Spring MVC
- Spring Data JPA
- Hibernate
- Thymeleaf
- Maven
- H2 Database, avec exemple MySQL dans `application.properties`
- Bootstrap 5

## Fonctionnalites

- CRUD films avec filtre par genre
- CRUD seances avec choix du film, salle, version, date, heure et capacite
- Filtre des seances par salle et date
- Vente, annulation, suppression et liste des billets
- Filtre des billets par statut
- Statistiques:
  - taux de remplissage par seance
  - recettes par film
- Donnees de test chargees au demarrage avec `CommandLineRunner`

## Lancer le projet

1. Ouvrir un terminal dans ce dossier:

   ```bash
   cd C:\Users\PC\.codex\memories\cinema-management
   ```

2. Compiler le projet:

   ```bash
   mvn clean package
   ```

3. Demarrer l'application:

   ```bash
   mvn spring-boot:run
   ```

4. Ouvrir l'application:

   ```text
   http://localhost:8080
   ```

5. Console H2:

   ```text
   http://localhost:8080/h2-console
   JDBC URL: jdbc:h2:mem:cinemadb
   User: sa
   Password: laisser vide
   ```

## Utiliser MySQL

Dans `src/main/resources/application.properties`, remplacer la configuration H2 par:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/cinema_db?createDatabaseIfNotExist=true&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
```

Puis relancer:

```bash
mvn spring-boot:run
```
