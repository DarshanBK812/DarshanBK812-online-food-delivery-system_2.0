# Online Food Delivery System

**Monolithic Spring Boot Application**

## 📖 Overview

This project is a monolithic Online Food Delivery System built using Spring Boot. It allows users to browse restaurants, add food items to their cart, place orders, and track order status.

## 🛠️ Tech Stack

* **Language:** Java 17
* **Framework:** Spring Boot
* **Data Access:** Spring Data JPA (Hibernate)
* **Database:** MySQL
* **Build Tool:** Maven
* **Security:** JWT-based authentication
* **API Testing:** Postman

## 🚀 Features

* **User Authentication & Authorization**

  * User registration and login with JWT tokens
* **Restaurant & Menu Management**

  * Browse restaurants and view menus
* **Shopping Cart**

  * Add, update, and remove items from cart
* **Order Placement & Tracking**

  * Place orders, view order history, and track status
* **Payment Simulation**

  * Simulate payments (e.g., CARD, UPI)
* **Admin Panel**

  * CRUD operations for restaurants and menu items
* **Error Handling**

  * Global exception handling with meaningful error responses
* **Delivery Management**

  * Assign delivery partners to orders and update delivery status

## 🔧 Getting Started

### Prerequisites

* Java 17 JDK
* Maven 3.6+
* MySQL Server

### Installation & Setup

1. **Clone the repository**

   ```bash
   git clone git@github.com:<your-username>/online-food-delivery-system.git
   cd online-food-delivery-system
   ```

2. **Configure MySQL**

   * Create a database named `food_delivery`
   * Update the `src/main/resources/application.properties` with your MySQL credentials:

     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/food_delivery
     spring.datasource.username=YOUR_DB_USERNAME
     spring.datasource.password=YOUR_DB_PASSWORD
     spring.jpa.hibernate.ddl-auto=update
     ```

3. **Build & Run**

   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

4. **API Testing**

   * Import the provided Postman collection `/docs/Online Food Delivery.postman_collection.json` into Postman
   * Test endpoints under `http://localhost:8080/api`

## 📜 Swagger API Documentation

This project integrates Swagger for interactive API documentation. Once the application is running, access the Swagger UI at:

```
http://localhost:8080/swagger-ui/index.html
```

### Setup Instructions

1. **Add Dependency** to `pom.xml`:

   ```xml
   <dependency>
     <groupId>io.springfox</groupId>
     <artifactId>springfox-boot-starter</artifactId>
     <version>3.0.0</version>
   </dependency>
   ```

2. **Enable Swagger** in your Spring Boot application:

   ```java
   @EnableSwagger2
   @SpringBootApplication
   public class FoodDeliveryApplication {
       public static void main(String[] args) {
           SpringApplication.run(FoodDeliveryApplication.class, args);
       }
   }
   ```

3. **Swagger Configuration** (optional customization):

   ```java
   @Configuration
   public class SwaggerConfig {
       @Bean
       public Docket api() {
           return new Docket(DocumentationType.SWAGGER_2)
                   .select()
                   .apis(RequestHandlerSelectors.basePackage("com.fooddelivery.controller"))
                   .paths(PathSelectors.any())
                   .build();
       }
   }
   ```

## 📁 Project Structure

```
com.fooddelivery
├── config            # Security and App configuration
├── controller        # REST controllers
├── dto               # Data Transfer Objects
├── entity            # JPA entities
├── exception         # Custom exception handling
├── repository        # Spring Data JPA repositories
├── service           # Business logic interfaces
├── servicelmpl       # Service implementations
├── delivery          # Delivery partner/entity management
```

## 📐 API Endpoints (Sample)

| Method | Endpoint                           | Description                              |
| ------ | ---------------------------------- | ---------------------------------------- |
| POST   | `/api/auth/register`               | Register a new user                      |
| POST   | `/api/auth/login`                  | Authenticate and receive token           |
| GET    | `/api/restaurants`                 | List all restaurants                     |
| GET    | `/api/restaurants/{id}/menu`       | Get menu for a restaurant                |
| POST   | `/api/cart/add`                    | Add item to cart                         |
| POST   | `/api/orders`                      | Place a new order                        |
| GET    | `/api/orders/{userId}`             | Get user order history                   |
| POST   | `/api/deliveries/{orderId}/assign` | Assign a delivery partner to an order    |
| PUT    | `/api/deliveries/{orderId}/status` | Update delivery status (e.g., DELIVERED) |

## 🤝 Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository
2. Create a new branch (`git checkout -b feature/YourFeature`)
3. Commit your changes (`git commit -m "Add feature"`)
4. Push to the branch (`git push origin feature/YourFeature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
