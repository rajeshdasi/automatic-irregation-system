# Plot Watering Integration Service

### Reference Documentation
Plot Watering Integration Service is an application which holds Plot and schedules watering for plots by notifying the mock sensor and alerts in case of an error

##### API Design
```[http://localhost:8080/api/v1/plots/{plot-number}]```

customerId : unique ID of the plot

##### Response Structure
```
{
    "plotNumber": 1,
    "plotOwner": "DJ Tillu",
    "plantedCrop": "Maize",
    "waterRequired": 300,
    "timeOfWatering": "2022-12-05T20:58:46.71+05:30"
}
```

#### Gotchas
API could return with 404 status code in the following scenarios
 * plot number does NOT exist in the database

### Project Specifications

##### Dependencies 
 * spring-boot-starter-web
 * spring-boot-starter-data-jpa
 * h2database

##### Build Tool
 * Gradle

##### Instructions to build and run the project
Build the project
```
./gradlew clean build
```

Run the project
```
./gradlew bootRun
```

### Test data
##### Data in Plots table
plotNumber | plotOwner | plantedCrop | waterRequired | timeOfWatering
--- |-----------|-------------|---------------| ---
1 | Prakash   | Rice        | 450           | 2022-12-01T20:58:46.71+05:30
2 | Agarwal   | Cotton      | 150           | 2022-12-01T20:58:46.71+05:30
3 | DJ        | Maize       | 300           | 2022-12-01T20:58:46.71+05:30
