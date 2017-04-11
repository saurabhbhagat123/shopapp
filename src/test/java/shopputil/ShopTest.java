package shopputil;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.shop.MainApplication;
import com.shop.domain.Shop;
import com.shop.response.NearestShopResponse;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MainApplication.class)
@WebIntegrationTest
public class ShopTest {


	
	public void testAddShops(){

		RestTemplate restTemplate=new RestTemplate();
		String json="{\"shops\": [{\"shopname\": \"Destination Centre\",\"address\": {\"streetname\": \"Magarpatta City,Hadapsar\",\"city\": \"Pune\",\"state\": \"Maharashtra\",\"postcode\": 411038}}]}";
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> httpEntity =new HttpEntity<String>(json, requestHeaders);
		ResponseEntity<List<Shop>> responseEntity = (ResponseEntity<List<Shop>>) restTemplate.postForEntity("http://localhost:8080/shop-app/shops", httpEntity,new ArrayList<Shop>().getClass());

	}


	@Test
	public void testGetNearestShop(){
		RestTemplate restTemplate=new RestTemplate();
		String json="{\"shops\": [{\"shopname\": \"Destination Centre\",\"address\": {\"streetname\": \"Magarpatta City,Hadapsar\",\"city\": \"Pune\",\"state\": \"Maharashtra\",\"postcode\": 411038}},{\"shopname\": \"Seasons Mall\",\"address\": {\"streetname\": \"Magarpatta City,Hadapsar\",\"city\": \"Pune\",\"state\": \"Maharashtra\",\"postcode\": 411013}}]}";
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> httpEntity =new HttpEntity<String>(json, requestHeaders);
		restTemplate.postForEntity("http://localhost:8080/shop-app/shops", httpEntity,new ArrayList<Shop>().getClass());
		ResponseEntity<NearestShopResponse> responseEntity = restTemplate.getForEntity("http://localhost:8080/shop-app/shop?lat=18.526792&long=73.9071402", NearestShopResponse.class);
		NearestShopResponse nearestShop=responseEntity.getBody();
		Assert.assertEquals("Destination Centre", nearestShop.getNearestShop());
		
	}




}
