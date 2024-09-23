# Change Service
This service is a simple service that returns the minimum or maximum number of coins needed 
to make change for a given amount.

## DP Algorithm for a limited supply of coins  
It uses a Dynamic Programming algorithm to calculate the minimum or maximum number of coins needed 
to make change for a given amount and a **limited supply of coins**.

**There is no such solution publicly available.**



## Run Applilcation
```docker-compose up -d```

## Examples
* ```curl -s localhost:8080/change/1```
* ```curl -s localhost:8080/change/1?max=true```
* ```curl -s localhost:8080/change/10```
* ```curl -s localhost:8080/change/3```