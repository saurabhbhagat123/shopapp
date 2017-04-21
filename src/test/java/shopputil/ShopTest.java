package shopputil;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.shop.MainApplication;
import com.shop.domain.Shop;
import com.shop.response.NearestShopResponse;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=MainApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class ShopTest {


	@Autowired
	private TestRestTemplate restTemplate;
	
	public void testAddShops(){

		String json="{\"shops\": [{\"shopname\": \"Destination Centre\",\"address\": {\"streetname\": \"Magarpatta City,Hadapsar\",\"city\": \"Pune\",\"state\": \"Maharashtra\",\"postcode\": 411038}}]}";
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> httpEntity =new HttpEntity<String>(json, requestHeaders);
		ResponseEntity<List> responseEntity = restTemplate.postForEntity("http://localhost:8080/shop-app/shops", httpEntity,List.class);

	}


	@Test
	public void testGetNearestShop(){
		String json="{\"shops\": [{\"shopname\": \"Destination Centre\",\"address\": {\"streetname\": \"Magarpatta City,Hadapsar\",\"city\": \"Pune\",\"state\": \"Maharashtra\",\"postcode\": 411028}},{\"shopname\": \"Seasons Mall\",\"address\": {\"streetname\": \"Magarpatta City,Hadapsar\",\"city\": \"Pune\",\"state\": \"Maharashtra\",\"postcode\": 411013}}]}";
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> httpEntity =new HttpEntity<String>(json, requestHeaders);
		restTemplate.postForEntity("/shops", httpEntity,new ArrayList<Shop>().getClass());
		ResponseEntity<NearestShopResponse> responseEntity = restTemplate.getForEntity("/shop?lat=18.526792&long=73.9071402", NearestShopResponse.class);
		NearestShopResponse nearestShop=responseEntity.getBody();
		Assert.assertEquals("Destination Centre", nearestShop.getNearestShop());
		
	}




}
