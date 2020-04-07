# Technology Stack
• Java 8
• Spring Boot 2.2
• Hibernate 
• In-memory database (H2)

# This application involves
1. RESTful API (put , post , get)
2. Transaction management
3. Custom Exception handling
4. Custom Annotation for validation
5. Combination Filter search using Specification
6. Other necessary Validations.
7. Hibernate , one to many and many to one mapping.  

## USE CASE

# Bus
• Each bus should have a unique plate number of pattern (BUS-XXX-XXX), where X is an
alpha-numeric character. It should not be possible to create a bus with an existing plate
number. 
• A bus can be of one of the two types: Regular OR Doubledecker
• A bus can have one of the two colors: Green OR Orange
• Each bus has a passenger capacity in the range of 0-70 

# Depot
The Depot is a place where buses can be parked. Each depot should have:
• Name - alphanumeric string
• Bus capacity - the maximum amount of buses that can be parked at the same time in the
depot. 
• Buses - collection of buses that are currently parked in the depot

# Functionality
It should be possible to add/edit/delete buses and depots using RESTful API
It should be possible to park/remove buses in/from depots using RESTful API
It should be possible to filter buses by combination with the following criteria:
1) Plate number
2) Type
3) Color
4) Amount of passengers
5) By the depot name

