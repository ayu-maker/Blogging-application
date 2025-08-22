# Blogging Application 📝

A simple **Spring Boot Blogging Application** that allows users to create, view, and manage blog posts with images.  
It is built using **Spring Boot**, **Thymeleaf**, **Spring Data JPA**, and **MySQL**.

---

## 🚀 Features
- ✍️ Create, update, and delete blog posts   
- 🔍 Search posts by title or content  
- 📂 Organized post listing with fragments (`postcontainer`, `view_posts`)  
- 📬 Contact form (stores messages in database)  
- 🔐 Role-based authentication (Admin / Guest)  
- 📅 Posts sorted by latest first  

---

## 🛠️ Tech Stack
- **Backend:** Spring Boot (Spring MVC, Spring Data JPA, Spring Security)  
- **Frontend:** Thymeleaf, HTML, CSS, JavaScript  
- **Database:** MySQL  
- **Build Tool:** Maven  
- **Version Control:** Git & GitHub  

---

## ⚙️ Setup Instructions

### 1. Clone the repository
```bash
git clone https://github.com/ayu-maker/Blogging-application.git
cd Blogging-application


Create a MySQL database (e.g. blog_db)

Update application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/blog_db
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update

## Run the application using this command in terminal
mvn spring-boot:run

