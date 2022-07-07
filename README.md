# INDITEX PRICES
## Configuration
This application uses gradle, so you can build, test and run it with gradle commands.

# Test
Under test directory you should find Unit and Component test for this application. 

In 
`src/test/java/com/fcorrionero/inditex/entrypoint/api/PricesControllerComponentTest.java` 
you will find the scenarios described in problem.

# Use example

Run the application and them you can execute:

``curl http://localhost:8080/prices/all/14-06-2020,10:00/35455/1``

to get product prices.