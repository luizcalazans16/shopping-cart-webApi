package br.com.calazans.shoppingcart.app;

import br.com.calazans.shoppingcart.app.core.dto.ProductDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getProduct() {
        final String productId = "1";
        final ProductDto dto = restTemplate.getForObject("/v1/product/".concat(productId), ProductDto.class);
        Assert.assertNotNull(dto);
        Assert.assertEquals(dto.getDescription(), "Control Xbox One S - branco");
    }

}
