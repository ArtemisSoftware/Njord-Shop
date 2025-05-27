# NjordShop

Shop app that includes product listing, searching, detailed view, and a validated form.  
A catalog and form-based application with paginated product listing, advanced search, product detail, and form validation.

## 📌 Overview

The product listing feature loads products from the public API [dummyjson.com](https://dummyjson.com/products), stores them locally, and enables browsing capabilities.  
The form validates all user input locally.

## 🛠️ Tech Stack

- **MVI + Clean Architecture**
- **Kotlin** - Primary programming language
- **Jetpack Compose** - Modern UI toolkit for Android
- **Hilt** - Dependency injection
- **Coroutines & Flow** - For asynchronous operations
- **Retrofit** - For API calls
- **ViewModel & StateFlow** - For state management

- **Retrofit + Moshi** – API communication
- **Room Database + Paging 3** – Local storage with pagination
- **FTS4 (SQLite Full Text Search)** – Advanced search
- **Navigation Compose**
- **Material 3**
- **Coil** – Image loading
- **Compose Test + Turbine + Mockk + AssertK** – For testing

## 🚀 Features

1. **Catalog with Pagination** - Initial API load and local persistence.
2. **Advanced Search** - Real-time local search.
3. **Product Detail** - Extensive product information.
4. **Form** - Input validation form.
5. **Error Handling** - Displays error screens for failed API requests.

## App Preview

`catalog`

Image #1            |  Image #2             |  Image #3           
:-------------------------:|:----------------------------:|:----------------------------:
<img src="images/BeeRace_1.jpg">   | <img src="images/BeeRace_1.jpg">         | <img src="images/BeeRace_1.jpg">

`form`

Image #1            |  Image #2                
:-------------------------:|:----------------------------:
<img src="images/BeeRace_1.jpg">   | <img src="images/BeeRace_1.jpg">  

## Product Flavors

| Flavor | Functionality |
|--------|----------------|
| `catalog` | Listing and detail only |
| `form`    | Validated form only |

