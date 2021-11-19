### ANJANA J.P.D
### Index No: 18000134

# PetStore Application

## build and/or deploy the API 

To build the application:

    ./gradlew build

To run the application:

    java -jar build/petstore-runner.jar

## run test suite

To run the test suit:

    ./gradlew test


## run a CURL/WGET command

## Pets
get all the pets

    curl -XGET -H "Content-type: application/json" 'http://localhost:8080/v1/pets'

get a pet by id

    curl -XGET -H "Content-type: application/json" 'http://localhost:8080/v1/pets/1'

create a new pet

    curl -XPOST -H "Content-type: application/json" -d '{"petId":4,"petType":"dog","petName":"shibba","petAge":5}' 'http://localhost:8080/v1/pets/petscreate'

update existing pet

    curl -XPUT -d '{"petId":3,"petType":"Bird","petName":"Chuti Peththappu","petAge":6}' 'http://localhost:8080/v1/pets/update'

delete existing pet

    curl -XDELETE -H "Content-type: application/json" 'http://localhost:8080/v1/pets/delete/4'

search pet

    curl -XDELETE -H "Content-type: application/json" 'http://localhost:8080/v1/pets/search?id=1'

## Pet Types
get all the pet types

    curl -XGET -H "Content-type: application/json" 'http://localhost:8080/v1/petTypes'

get a pet types by id

    curl -XGET -H "Content-type: application/json" 'http://localhost:8080/v1/petTypes/2'

create a new pet types

    curl -XPOST -H "Content-type: application/json" -d '{"typeId" :4,"typeName":"Fish"}' 'http://localhost:8080/v1/petTypes/create'

update existing pet types

    curl -XPUT -H "Content-type: application/json" -d '{"typeId":4,"typeName":"rabbit"}' 'http://localhost:8080/v1/petTypes/update'

delete existing pet types

    curl -XDELETE -H "Content-type: application/json" 'http://localhost:8080/v1/petTypes/delete/4'


