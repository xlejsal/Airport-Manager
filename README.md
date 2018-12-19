# Airport-Manager

Team project for the university course PA165 at FI MUNI

## It appears the security service doesn't work when the project is run via mvn cargo:run. The service however works when the project is run via IDE. We have no idea why is that the case.

### Login
User can access only login page, index page, flight, airplane and destination lists.

Admin can access everything.
#### User
username: user

password: password
#### Admin
username: admin

password: adminPassword

### Curl commands
##### Get a destination: `curl http://localhost:8080/pa165/rest/1`
##### Get destinations around a city:`curl http://localhost:8080/pa165/rest/city/Brno`
##### Get destinations in a country: `curl http://localhost:8080/pa165/rest/country/Czechia`
##### Create a destination: `curl -d "{\"airportCode\":\"HEL\",\"city\":\"Helsinki\",\"country\":\"Finland\"}" -H "Content-Type: application/json" http://localhost:8080/pa165/rest`
##### Update a destination: `curl -d "{\"id\":\"1\",\"airportCode\":\"KLM\",\"city\":\"East Vienna\",\"country\":\"Austria\"}" -H "Content-Type: application/json" -X PUT http://localhost:8080/pa165/rest`
##### Delete a destination: `curl -X DELETE http://localhost:8080/pa165/rest/4`


### Team members:
* Vojtěch Lejsal (učo 445629)
* Štěpán Beneš (učo 433725)
* Branislav Kotrč (učo 433718)

### Task description:
> Create an information system managing flight records at an airport. The system should allow the users to enter records about stewards, airplanes and destinations. It should also be possible to update and delete these records. A destination should contain at least the information about the location of an airport (country, city). Airplane details should contain the capacity of the plane and its name (and possibly its type as well). A steward is described by his first and last name. The system should also allow us to record a flight. When recording a flight, it is necessary to set the departure and arrival times, the origin, the destination and the plane. The system should also check that the plane does not have another flight scheduled during the time of the this flight. It should also be possible to assign (remove) stewards to (from) a flight while checking for the steward's availability. The ultimate goal is to have a system capable of listing flights ordered by date and displaying the flight details (origin, destination, departure time, arrival time, plane, list of stewards).

### Class diagram
![Class diagram](https://cdn.discordapp.com/attachments/506608005849153546/506608224007356428/ClassDiagram.png)

### Usecase diagram
![Usecase diagram](https://cdn.discordapp.com/attachments/506608005849153546/506608098757312543/Usecase_diagram.png)
