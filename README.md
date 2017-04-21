# shopapi

Shopapi project contains api for adding shops to the memory,getting nearest shop based on customer's lattitude and longitude.

Whenever a shop is added the service calls the Google Maps API. The Google Maps API responds
with the longitude and latitude, which allows the shop data to be updates with longitude and latitude.
A customer, using their geolocation on their phone, wants to find the store that is closest to them.
The Shops API will have the customer’s longitude and latitude, but also the longitude and latitude of
each shop to do the calculation.
The customer does a RESTful GET to the Shops API, providing their current longitude and latitude
(e.g. URL request params), and gets back the address, longitude and latitude of the shop nearest to
them.

1)Adding Shops
Request:

Rest url: http://localhost:8080/shop-app/shops
Method:POST
Content-Type: application/json

Request Body:

{
	"shops": [{
		"shopname": "Destination Centre",
		"address": {
			"streetname": "Magarpatta City,Hadapsar",
			"city": "Pune",
			"state": "Maharashtra",
			"postcode": 411038
		}
	}]
}

Response:No response for unique shops added in memory
If same shop are added twice ,replaces the older version with newer version of shop and return response of older shop that was replaced


2)Getting Nearest Shop

Request:

Rest url: http://localhost:8080/shop-app/shop?lat=18.526792&long=73.9071402
Method:GET

Response:Returns the nearest shop based on lattitude and longitude of customer passed in URL.

{
"nearestShop": "Destination Centre",
"address": " Magarpatta City,Hadapsar Pune Maharashtra 411038 ",
"lattitude": "18.5158057",
"longitude": "73.9271644"
}


