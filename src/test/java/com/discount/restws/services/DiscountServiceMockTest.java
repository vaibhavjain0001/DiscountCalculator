package com.discount.restws.services;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import javax.ws.rs.BadRequestException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(locations =  "classpath:application.properties" )
public class DiscountServiceMockTest {
	
	
	DiscountServiceImpl discountService = new DiscountServiceImpl();
	
	@Mock
	private Environment env;
	
	@Before
	public void setUp() throws Exception {
	    // Initialize mocks created above
	    MockitoAnnotations.initMocks(this);
	    when(env.getProperty("discount.slabs")).thenReturn("0;5000;10000;20000");  
	    when(env.getProperty("discount.rates")).thenReturn("0;10;20;25");
	}
	
	@Test
	public void testFindDiscountForLargePurchaseAmount() {
		assertEquals(discountService.getDiscount(26000.0, env).getDiscount(), 
				4000.0, 0);
	}
	
	@Test
	public void testFindDiscountForSmallPurchaseAmount() {
		assertEquals(discountService.getDiscount(4000.0, env).getDiscount(), 
				0.0, 0);
	}
	
	@Test(expected = BadRequestException.class)
	public void testFindDiscountForZeroPurchaseAmount() {
		discountService.getDiscount(0.0, env);
	}
	
}
