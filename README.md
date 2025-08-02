# REST Assured API Testing Framework

A comprehensive REST API testing framework built with Java, RestAssured, TestNG, and Cucumber for automated API testing across multiple domains including library management, e-commerce, JIRA, and OAuth authentication.

## 🚀 Project Overview

This framework provides automated testing solutions for various API endpoints with features like:
- **Data-driven testing** with Excel integration
- **BDD approach** using Cucumber
- **POJO-based serialization/deserialization**
- **OAuth authentication testing**
- **File upload/attachment testing**
- **End-to-end workflow testing**

## 📁 Project Structure

```
src/
├── main/java/
│   ├── files/                           # Utility and helper classes
│   │   ├── BodyData.java                # Static JSON body templates
│   │   └── CommanFunctions.java         # Common utility functions
│   ├── Serialization/                   # Request POJO classes
│   │   ├── AddPlaceData.java            # Place API request model
│   │   └── Location.java                # Location coordinate model
│   ├── Deserialization/                 # Response POJO classes
│   │   ├── GetCoursesData.java          # Course API response model
│   │   ├── Courses.java                 # Course collection model
│   │   ├── Api.java                     # API course model
│   │   ├── Mobile.java                  # Mobile course model
│   │   └── WebAutomation.java           # Web automation course model
│   ├── EcommersPOJO/                    # E-commerce API models
│   │   ├── Login.java                   # Login request model
│   │   ├── LoginResponse.java           # Login response model
│   │   ├── OrderDetail.java             # Order detail model
│   │   └── Orders.java                  # Orders collection model
│   └── resources/                       # Framework utilities
│       ├── APIResources.java            # API endpoint enum
│       ├── TestDataBuild.java           # Test data builder
│       └── Utils.java                   # Request specification utilities
├── test/
│   ├── java/
│   │   ├── DynamicJson.java             # Library API testing with data provider
│   │   ├── EcommersEnd2EndTests.java    # Complete e-commerce workflow testing
│   │   ├── JiraAPISTest.java            # JIRA API testing with attachments
│   │   ├── OAuthWithPOJODeSerialization.java # OAuth token and course data testing
│   │   ├── stepDefinations/
│   │   │   └── StepDefinations.java     # Cucumber step definitions
│   │   └── EXCELSection/
│   │       ├── DataDriven.java          # Excel data reading utility
│   │       └── testSample.java          # Excel integration test sample
│   └── resources/
│       └── placeValidation.feature      # Cucumber feature file
```

## 🛠️ Technologies Used

- **Java** - Programming language
- **RestAssured** - REST API testing framework
- **TestNG** - Testing framework with annotations and data providers
- **Cucumber** - BDD testing framework
- **Apache POI** - Excel file handling
- **Jackson** - JSON processing
- **Maven** - Dependency management

## 📋 Test Scenarios Covered

### 1. Library Management API (`DynamicJson.java`)
- **Endpoint**: `http://216.10.245.166/Library/Addbook.php`
- **Features**:
  - Data-driven testing with TestNG DataProvider
  - Dynamic JSON body creation
  - Response validation and ID extraction
  - Multiple test data sets (amr/123, ziad/234, khaled/456)

### 2. E-commerce End-to-End Testing (`EcommersEnd2EndTests.java`)
- **Base URL**: `https://rahulshettyacademy.com`
- **Complete Workflow**:
  1. **Login Flow** - User authentication and token generation
  2. **Add Product** - Product creation with authorization
  3. **Create Order** - Order placement with product details
  4. **Delete Product** - Cleanup and product removal
- **Features**:
  - POJO-based request/response handling
  - Token-based authentication
  - Request/Response specification builders
  - Assertion validation

### 3. JIRA API Testing (`JiraAPISTest.java`)
- **Base URL**: `https://amrkhaled.atlassian.net`
- **Test Cases**:
  - **Bug Creation** - Create issues with custom fields
  - **File Attachment** - Upload attachments to created issues
- **Features**:
  - Basic authentication with encoded tokens
  - Multipart file upload
  - JSON response parsing
  - Test execution priority management

### 4. OAuth Authentication Testing (`OAuthWithPOJODeSerialization.java`)
- **Endpoints**: 
  - Token: `https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token`
  - Data: `https://rahulshettyacademy.com/oauthapi/getCourseDetails`
- **Features**:
  - OAuth 2.0 client credentials flow
  - POJO deserialization of complex JSON responses
  - List validation and comparison
  - Course data extraction and verification

### 5. BDD Testing with Cucumber (`StepDefinations.java`, `placeValidation.feature`)
- **Gherkin Scenarios**: Place API validation
- **Features**:
  - Scenario outline with examples
  - Parameterized testing
  - Request/Response specification
  - Multiple HTTP method support (POST, GET)

### 6. Excel Data Integration (`DataDriven.java`)
- **File Path**: `D:\Intern\RestAssuredPractice\RestAssuredCourse.xlsx`
- **Features**:
  - Dynamic test case data retrieval
  - Excel sheet iteration
  - Cell type handling (String/Numeric)
  - Test case name-based data filtering

## 🏗️ Framework Architecture

### Core Components

#### 1. **Utility Classes** (`files` package)
- **`BodyData.java`** - Static JSON templates for API requests
  ```java
  public static String addBookBody(String isbn, String aisle) {
      return "{ \"name\":\"Learn Appium Automation with Java\", " +
             "\"isbn\":\"" + isbn + "\", \"aisle\":\"" + aisle + "\" }";
  }
  ```
- **`CommanFunctions.java`** - Common utility functions for JSON processing

#### 2. **POJO Models for Serialization** (`Serialization` package)
- **`AddPlaceData.java`** - Complete place data model with location, accuracy, types
- **`Location.java`** - Geographical coordinates (lat/lng) model

#### 3. **POJO Models for Deserialization** (`Deserialization` package)
- **`GetCoursesData.java`** - Root response model for course API
- **`Courses.java`** - Container for different course categories
- **`Api.java`, `Mobile.java`, `WebAutomation.java`** - Individual course models

#### 4. **E-commerce POJOs** (`EcommersPOJO` package)
- **`Login.java` / `LoginResponse.java`** - Authentication models
- **`OrderDetail.java` / `Orders.java`** - Order management models

#### 5. **Framework Utilities** (`resources` package)
- **`APIResources.java`** - Centralized endpoint management
- **`TestDataBuild.java`** - Dynamic test data creation
- **`Utils.java`** - Request specification builders with logging

## 🔧 Key Features

### POJO-Based Approach
The framework extensively uses Plain Old Java Objects (POJOs) for:
- **Type Safety**: Compile-time validation of request/response structures
- **Maintainability**: Easy to modify and extend data models
- **Readability**: Clear representation of API contracts

### Dynamic Test Data Generation
```java
public AddPlaceData addPlacePayLoad(String name, String language, String address) {
    AddPlaceData p = new AddPlaceData();
    p.setAccuracy(50);
    p.setName(name);
    p.setLanguage(language);
    // ... additional setup
    return p;
}
```

### Centralized Endpoint Management
```java
public enum APIResources {
    addPlaceAPI("/maps/api/place/add/json"),
    getPlaceAPI("/maps/api/place/get/json"),
    deletePlaceAPI("/maps/api/place/delete/json");
}
```

## 📊 Data Models Overview

### Serialization Models (Request Objects)

#### Place API Model
```java
AddPlaceData {
    Location location;      // Geographical coordinates
    int accuracy;          // Location accuracy
    String name;           // Place name
    String phoneNumber;    // Contact number
    String address;        // Physical address
    List<String> types;    // Place categories
    String website;        // Website URL
    String language;       // Language preference
}
```

### Deserialization Models (Response Objects)

#### Course API Response Model
```java
GetCoursesData {
    String instructor;     // Course instructor name
    String url;           // Course URL
    Courses courses;      // Course collections
    String expertise;     // Instructor expertise
    String services;      // Available services
}

Courses {
    List<Api> api;                    // API testing courses
    List<Mobile> mobile;              // Mobile testing courses  
    List<WebAutomation> webAutomation; // Web automation courses
}
```

#### E-commerce Models
```java
Login {
    String userEmail;     // User login email
    String userPassword;  // User password
}

LoginResponse {
    String token;         // JWT authentication token
    String userId;        // Unique user identifier
    String message;       // Response message
}

Orders {
    List<OrderDetail> orders; // Collection of order details
}
```
### Request/Response Specifications
```java
RequestSpecification req = new RequestSpecBuilder()
    .setBaseUri("https://rahulshettyacademy.com")
    .setContentType(ContentType.JSON)
    .addHeader("authorization", token)
    .addFilter(RequestLoggingFilter.logRequestTo(log))
    .addFilter(ResponseLoggingFilter.logResponseTo(log))
    .build();
```

## 🎯 Advanced Features

### 1. **Template-Based JSON Bodies**
```java
// Static templates with parameter substitution
public static String addBookBody(String isbn, String aisle) {
    return "{ \"name\":\"Learn Appium Automation with Java\", " +
           "\"isbn\":\"" + isbn + "\", \"aisle\":\"" + aisle + "\" }";
}
```

### 2. **Complex Object Serialization**
```java
// Building complex nested objects
AddPlaceData place = new AddPlaceData();
Location location = new Location();
location.setLat("-38.43324");
location.setLng("60.43324");
place.setLocation(location);

List<String> types = Arrays.asList("shop1", "shop2", "shop3");
place.setTypes(types);
```

### 3. **Automated Response Deserialization**
```java
// Direct JSON to POJO conversion
GetCoursesData response = given()
    .queryParam("access_token", accessToken)
    .when()
    .get("/getCourseDetails")
    .as(GetCoursesData.class);

// Access nested data
List<WebAutomation> webCourses = response.getCourses().getWebAutomation();
```

### 4. **Comprehensive Logging**
- Request/Response logging to console and files
- JSON path extraction and validation
- Custom assertion messages

### 5. **Multi-Environment Support**
- Configurable base URIs
- Environment-specific test data
- Flexible authentication mechanisms
### Data-Driven Testing
- TestNG DataProvider for multiple test iterations
- Excel integration for external test data  
- Parameterized Cucumber scenarios
- Dynamic POJO generation based on test parameters

### Authentication Methods
- **Basic Authentication**: JIRA API with encoded credentials
- **OAuth 2.0**: Client credentials flow
- **Token-based**: E-commerce API with JWT tokens

### Response Validation
- Status code verification
- JSON path assertions
- Header validation
- Content type checking

## 🚦 Getting Started

### Prerequisites
- Java 8 or higher
- Maven 3.6+
- Excel file with test data (for data-driven tests)

## 📊 Test Reporting

- **Console Logging**: Detailed request/response logs
- **File Logging**: `logging.txt` for persistent logs
- **TestNG Reports**: HTML reports with test results
- **Cucumber Reports**: BDD-style reporting

## 🔗 API Endpoints Tested

| Service | Endpoint | Method | Purpose |
|---------|----------|---------|---------|
| Library | `/Library/Addbook.php` | POST | Add books to library |
| E-commerce | `/api/ecom/auth/login` | POST | User authentication |
| E-commerce | `/api/ecom/product/add-product` | POST | Add products |
| E-commerce | `/api/ecom/order/create-order` | POST | Create orders |
| E-commerce | `/api/ecom/product/delete-product/{id}` | DELETE | Remove products |
| JIRA | `/rest/api/3/issue` | POST | Create bugs/issues |
| JIRA | `/rest/api/3/issue/{key}/attachments` | POST | Add attachments |
| OAuth | `/oauth2/resourceOwner/token` | POST | Get access token |
| OAuth | `/getCourseDetails` | GET | Retrieve course data |
| Places | `/maps/api/place/add/json` | POST | Add places |

## 📈 Best Practices Implemented

- **Separation of Concerns**: Separate classes for different API domains
- **Reusable Components**: Common functions and utilities
- **Data Externalization**: Excel-based test data management
- **Specification Builders**: Reusable request/response specifications
- **POJO Pattern**: Type-safe JSON handling
- **Logging**: Comprehensive request/response logging
- **Assertions**: Multiple validation layers

---

*This framework demonstrates comprehensive API testing capabilities with modern tools and best practices for reliable, maintainable, and scalable test automation.*
