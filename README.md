### **English - README for Event Management API**
# **Event Management API**

This project is a RESTful API for managing events, users, and reservations. It is built with **Spring Boot** and **Spring Security** and uses **JWT-based authentication**.

## **Features**
- User registration and login
- Event management (Create, Delete, Fetch)
- Event reservations by users
- Role-based access control (`ADMIN`, `USER`)
- JWT token authentication

## **Requirements**
Ensure you have the following installed:
- **JDK 17** or later
- **Maven**
- **MySQL** (or any preferred database)

## **Installation & Setup**

### **1. Clone the repository**
```bash
git clone <repository_url>
cd event-management-api
```

### **2. Database Configuration**
Create a new MySQL database and update `application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/event_db
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

### **3. Run the application**
```bash
mvn spring-boot:run
```

## **API Endpoints**

### **Authentication**
| Method | Endpoint | Description |
|------|---------|-------------|
| `POST` | `/auth/register` | Register a new user |
| `POST` | `/auth/login` | Login and receive JWT token |

### **User Management**
| Method | Endpoint | Description |
|------|---------|-------------|
| `GET` | `/api/user/profile` | Get user profile |
| `PUT` | `/api/user/profile` | Update user profile |

🔒 **Access:** `USER`, `ADMIN`

### **Event Management**
| Method | Endpoint | Description |
|------|---------|-------------|
| `GET` | `/events` | Fetch all events |
| `GET` | `/events/{id}` | Fetch a single event |
| `POST` | `/events` | Create a new event (`ADMIN` only) |
| `DELETE` | `/events/{id}` | Delete an event (`ADMIN` only) |

🔒 **Access:**  
- `USER`: Can view events  
- `ADMIN`: Can create and delete events  

### **Reservation Management**
| Method | Endpoint | Description |
|------|---------|-------------|
| `POST` | `/api/reservations/{eventId}` | Book an event |
| `DELETE` | `/api/reservations/{reservationId}` | Cancel a reservation |
| `GET` | `/api/reservations/my` | View user reservations |

🔒 **Access:** `USER`

## **Authentication & Authorization**
For protected routes, include the JWT token in the `Authorization` header:

```http
Authorization: Bearer <your_token_here>
```

## **Contributing**
To contribute:
1. **Fork** the repository.
2. Create a **new branch** (`feature/new-feature`).
3. **Commit** your changes.
4. Submit a **Pull Request**.

## **License**
This project is licensed under the **MIT License**.

---

### **Italiano - Documentazione API di Gestione Eventi**
# **Event Management API**

Questo progetto è un'API RESTful per la gestione di eventi, utenti e prenotazioni. È sviluppato con **Spring Boot** e **Spring Security** e utilizza **JWT per l'autenticazione**.

## **Caratteristiche**
- Registrazione e accesso degli utenti
- Gestione degli eventi (Creazione, Eliminazione, Recupero)
- Prenotazione degli eventi da parte degli utenti
- Controllo degli accessi basato sui ruoli (`ADMIN`, `USER`)
- Autenticazione con token JWT

## **Requisiti**
Assicurati di avere installato:
- **JDK 17** o successivo
- **Maven**
- **MySQL** (o qualsiasi altro database preferito)

## **Installazione e Configurazione**

### **1. Clonare il repository**
```bash
git clone <repository_url>
cd event-management-api
```

### **2. Configurazione del database**
Crea un nuovo database MySQL e aggiorna `application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/event_db
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

### **3. Avviare l'applicazione**
```bash
mvn spring-boot:run
```

---

### **فارسی - مستندات API مدیریت رویدادها**
# **API مدیریت رویدادها**

این پروژه یک API **RESTful** برای مدیریت رویدادها، کاربران و رزروها است. این سیستم با **Spring Boot** و **Spring Security** توسعه داده شده و از **JWT** برای احراز هویت استفاده می‌کند.

## **ویژگی‌ها**
- ثبت‌نام و ورود کاربران
- مدیریت رویدادها (ایجاد، حذف و مشاهده)
- رزرو رویدادها توسط کاربران
- کنترل سطح دسترسی (`ADMIN`, `USER`)
- احراز هویت با **JWT**

## **نیازمندی‌ها**
- **JDK 17** یا بالاتر
- **Maven**
- **MySQL** (یا هر دیتابیس دیگر)

## **نصب و راه‌اندازی**

### **1. کلون کردن پروژه**
```bash
git clone <repository_url>
cd event-management-api
```

### **2. تنظیمات دیتابیس**
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/event_db
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

### **3. اجرای برنامه**
```bash
mvn spring-boot:run
```

