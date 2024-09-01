# Stock Market Data Retrieval System

This project is a Stock Market Data Retrieval System developed using Spring Boot. It fetches real-time stock data from the Alpha Vantage API, including current price, market capitalization, 52-week high and low, opening price, and dividend rate for various companies.

## Features

- Retrieves detailed stock market data using the Alpha Vantage API.
- Handles multiple stock symbols dynamically.
- Provides error handling and informative feedback for unavailable data or API errors.
- Uses Spring Boot's WebClient for asynchronous API calls.

## Technology Stack

- **Spring Boot**: Framework used for building the application.
- **WebClient**: To make asynchronous HTTP requests.
- **Alpha Vantage API**: Source for stock market data.
- **Maven**: Dependency management and build automation.

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.6+
- A valid Alpha Vantage API key

### Setup

1. Clone the repository:
   ```bash
    https://github.com/sutikshan-sikarwar/stock-quotes-system.git 
   ```
2. Navigate to the project directory:
   ```bash
     cd stock-market-data-retrieval
    ```
3.  Alpha Vantage API key
    ```bash
    alphavantage.apikey=SHLEFSMEOJHNU8B9
    ```
4. Build the project using Maven:
  ```bash
mvn clean install
```
5. Run the application:
   ```bash
   mvn spring-boot:run
    ```
6. Usage
   ```bash
   GET /api/stocks/{symbol}
    ```
    
