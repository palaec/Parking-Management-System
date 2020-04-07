# APPLICATION REST API DETAILS

POST /addOrUpdateBus
Host: localhost:8080
Request :
{
    "busNumber": "BUS-A11-379",
    "busType": "Doubledecker",
    "busColor": "Green",
    "busCapacity": 30
}

================================================

POST /addOrUpdateDepot
Host: localhost:8080

{
	"name" : "Munich Zob",
	"maxBusCapacity" : 5
}

================================================

PUT /parkBusToDepot/busNumber/BUS-A11-379/depotId/1
Host: localhost:8080

================================================

PUT /removeBusFromDepot/BUS-A11-379
Host: localhost:8080

================================================

GET /getDepotList
Host: localhost:8080

================================================

GET /getBusListByFilter?busColor=Green&busType=Doubledecker&depotName=Berlin&busNumber=A11&busCapacity=30
Host: localhost:8080

================================================

PUT /deleteDepot/1 
Host: localhost:8080

================================================

PUT /deleteDepot/1 
Host: localhost:8080

================================================

PUT /deleteBus/BUS-A11-379
Host: localhost:8080

================================================

GET /getBusListByDepotId/1
Host: localhost:8080

================================================









