# Change Service
This service is a simple service that returns the minimum or maximum number of coins needed 
to make change for a given amount.
The default coin values are 1 cent, 5 cents, 10 cents and 25 cents.
Each coin supply is of 1000 units. 
Those values of coin supply can be changed in the environment variables in the docker-compose file.

## DP Algorithm for a limited supply of coins  
It uses a Dynamic Programming algorithm to calculate the minimum or maximum number of coins needed 
to make change for a given amount and a **limited supply of coins**.

**This solution is not easy to find on the internet.**

## Complexity
The time complexity of the algorithm is **O(W * C * k)**, 
where **W** is the amount, **C** is the number of distinct coins
and **k** is the number of the supply of each coin. For simplicity, we can consider **k** is
equal for every coin.

The space complexity is **O(W + C*k)**.

It is a pseudo-polynomial solution, because we consider the amount as a number, 
but not as the size of its memory representation.  

## Run Applilcation
```docker-compose up -d```

## API
* **GET** /change/{amount}?max={true|false}
  * **amount**: The amount to make change.
  * **max**: If true, it returns the maximum number of coins needed to make change for the given amount.
  Otherwise, it returns the minimum number of coins needed to make change for the given amount.

## Examples
* ```curl -s localhost:8080/change/1```
* ```curl -s localhost:8080/change/1\?max=true```
* ```curl -s localhost:8080/change/2```
* ```curl -s localhost:8080/change/3```