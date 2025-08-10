# 

# \# Loan Origination System (LOS) – Spring Boot + MySQL

# 

# A backend application for processing loan applications with automated system checks, agent assignments, and mock notifications.

# 

# ---

# 

# \##  Tech Stack

# 

# \* \*\*Java 17+\*\*

# \* \*\*Spring Boot 3+\*\*

# \* \*\*Spring Data JPA\*\*

# \* \*\*MySQL\*\*

# \* \*\*Lombok\*\*

# \* \*\*Spring Scheduler\*\*

# \* \*\*Maven\*\*

# 

# ---

# 

# \##  Setup Instructions

# 

# \### 1. Clone the Repository

# 

# ```bash

# git clone https://github.com/<your-username>/<your-repo>.git

# cd <your-repo>

# ```

# 

# \### 2. Install MySQL Locally

# 

# Ensure MySQL is running and you have credentials ready.

# 

# Create a new database:

# 

# ```sql

# CREATE DATABASE loan\_db;

# ```

# 

# \### 3. Configure `application.properties`

# 

# Edit `src/main/resources/application.properties`: 

# 

# Update with your Username and Password





# 

# ```properties

# &nbsp;

# spring.datasource.username=root

# spring.datasource.password=your\_password

# 

# \# Enable scheduled tasks

# 

# \### 4. Install Maven Dependencies

# 

# \### 5. Run the Application

# 

# App will start on:

# 

# ```

# http://localhost:8080

# ```

# 

# ---

# 

# \## Testing

# 

# Use the provided \*\*Postman collection\*\* (`Turno.postman\_collection.json`) in the repo to test all APIs.

# 

# --- 

# 

# \### Additional Documentation



# The repository includes a Word document (workflow\_screenshots.docx) that illustrates the complete workflow.

# It contains step-by-step screenshots of API requests in sequence, from loan submission to final approval or rejection.





# 

# \##  Notes

# 

# \* Loans are auto-processed every 10 seconds by a background job.

# \* Under review loans are assigned to agents automatically.

# \* Notifications are mocked via logs.

# 

# ---



