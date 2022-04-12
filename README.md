# rest-api-cars

#### Run

```   
./gradlew bootRun
```

#### Create a car entry

```   
curl -v -X POST localhost:8080/cars -H 'Content-Type: application/json' -d '{"make":"vw","model":"golf"}'
```

#### Get cars

```   
curl -v -X GET localhost:8080/cars
```

#### Get car

```   
curl -v -X GET localhost:8080/cars/1
```

#### Delete car

```   
curl -v -X DELETE localhost:8080/cars/1
```

