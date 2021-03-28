package com.inditex.pricesws;

import com.inditex.pricesws.dtos.Price;
import com.inditex.pricesws.services.PriceServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
class PricesWsApplicationTests {

	@Autowired
	private PriceServiceImpl priceService;

	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	// Test 1: peticion a las 10:00 del día 14 del producto 35455   para la brand 1 (ZARA)
	@Test
	//@org.junit.Test
	public void findFirstByBrandProductDateTest1(){
		Price priceExpected = new Price(1, LocalDateTime.parse("2020-06-14 00:00:00", formatter), LocalDateTime.parse("2020-12-31 23:59:59", formatter), 1, 35455, 0, 35.50, "EUR");
		LocalDateTime searchDate = LocalDateTime.parse("2020-06-14 10:00:00", formatter);
		Price price = priceService.findFirstByBrandProductDate(1, 35455, searchDate);
		assertEquals(priceExpected, price);
	}

	// Test 2: peticion a las 16:00 del día 14 del producto 35455   para la brand 1 (ZARA)
	@Test
	public void findFirstByBrandProductDateTest2(){
		Price priceExpected = new Price(1, LocalDateTime.parse("2020-06-14 15:00:00", formatter), LocalDateTime.parse("2020-06-14 18:30:00", formatter), 2, 35455, 1, 25.45, "EUR");
		LocalDateTime searchDate = LocalDateTime.parse("2020-06-14 16:00:00", formatter);
		Price price = priceService.findFirstByBrandProductDate(1, 35455, searchDate);
		assertEquals(priceExpected, price);
	}

	// Test 3: peticion a las 21:00 del día 14 del producto 35455   para la brand 1 (ZARA)
	@Test
	public void findFirstByBrandProductDateTest3(){
		Price priceExpected = new Price(1, LocalDateTime.parse("2020-06-14 00:00:00", formatter), LocalDateTime.parse("2020-12-31 23:59:59", formatter), 1, 35455, 0, 35.50, "EUR");
		LocalDateTime searchDate = LocalDateTime.parse("2020-06-14 21:00:00", formatter);
		Price price = priceService.findFirstByBrandProductDate(1, 35455, searchDate);
		assertEquals(priceExpected, price);
	}

	// Test 4: peticion a las 10:00 del día 15 del producto 35455   para la brand 1 (ZARA)
	@Test
	public void findFirstByBrandProductDateTest4(){
		Price priceExpected = new Price(1, LocalDateTime.parse("2020-06-15 00:00:00", formatter), LocalDateTime.parse("2020-06-15 11:00:00", formatter), 3, 35455, 1, 30.50, "EUR");
		LocalDateTime searchDate = LocalDateTime.parse("2020-06-15 10:00:00", formatter);
		Price price = priceService.findFirstByBrandProductDate(1, 35455, searchDate);
		assertEquals(priceExpected, price);
	}

	// Test 5: peticion a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA)
	@Test
	public void findFirstByBrandProductDateTest5(){
		Price priceExpected = new Price(1, LocalDateTime.parse("2020-06-15 16:00:00", formatter), LocalDateTime.parse("2020-12-31 23:59:59", formatter), 4, 35455, 1, 38.95, "EUR");
		LocalDateTime searchDate = LocalDateTime.parse("2020-06-16 21:00:00", formatter);
		Price price = priceService.findFirstByBrandProductDate(1, 35455, searchDate);
		assertEquals(priceExpected, price);
	}

	@Test
	public void getPricesTest() {
		List<Price> pricesCheck = new ArrayList<>();
		pricesCheck.add(new Price(1, LocalDateTime.parse("2020-06-14 00:00:00", formatter), LocalDateTime.parse("2020-12-31 23:59:59", formatter), 1, 35455, 0, 35.50, "EUR"));
		pricesCheck.add(new Price(1, LocalDateTime.parse("2020-06-14 15:00:00", formatter), LocalDateTime.parse("2020-06-14 18:30:00", formatter), 2, 35455, 1, 25.45, "EUR"));
		pricesCheck.add(new Price(1, LocalDateTime.parse("2020-06-15 00:00:00", formatter), LocalDateTime.parse("2020-06-15 11:00:00", formatter), 3, 35455, 1, 30.50, "EUR"));
		pricesCheck.add(new Price(1, LocalDateTime.parse("2020-06-15 16:00:00", formatter), LocalDateTime.parse("2020-12-31 23:59:59", formatter), 4, 35455, 1, 38.95, "EUR"));

		List<Price> prices = priceService.getPrices();
		assertNotNull(prices);
		assertEquals(4, prices.size());
		assertArrayEquals(pricesCheck.toArray(), prices.toArray());
	}

	@Test
	public void getPriceByIdTest() {
		Price priceExpected = new Price(1, LocalDateTime.parse("2020-06-15 00:00:00", formatter), LocalDateTime.parse("2020-06-15 11:00:00", formatter), 3, 35455, 1, 30.50, "EUR");
		Price price = priceService.getPriceById(3);
		assertNotNull(price);
		assertEquals(priceExpected, price);
	}

	@Test
	public void updateOrSavePriceTest(){
		Price priceExpected = new Price(1, LocalDateTime.parse("2020-06-15 00:00:00", formatter), LocalDateTime.parse("2020-06-15 11:00:00", formatter), 5, 35455, 1, 30.50, "EUR");
		priceService.updateOrSavePrice(priceExpected);
		Price price = priceService.getPriceById(5);
		assertEquals(5, priceService.getPrices().size());
		assertNotNull(price);
		assertEquals(priceExpected, price);
		priceService.deletePricebyId(5);
	}

	@Test
	public void deletePricebyIdTest() {
		Price priceExpected = new Price(1, LocalDateTime.parse("2020-06-15 00:00:00", formatter), LocalDateTime.parse("2020-06-15 11:00:00", formatter), 6, 35455, 1, 30.50, "EUR");
		priceService.updateOrSavePrice(priceExpected);
		Price price = priceService.getPriceById(6);
		assertEquals(5, priceService.getPrices().size());
		assertEquals(priceExpected, price);
		priceService.deletePricebyId(6);
		assertEquals(4, priceService.getPrices().size());
	}

	@Test
	public void getPricesByProductIdTest(){
		List<Price> prices = priceService.getPricesByProductId(35455);
		assertNotNull(prices);
		assertEquals(4, prices.size());
	}

	@Test
	public void getPricesByProductIdNoExistTest(){
		List<Price> prices = priceService.getPricesByProductId(35456);
		assertNotNull(prices);
		assertEquals(0, prices.size());
	}

	@Test
	public void findByBrandProductDateTest(){
		LocalDateTime searchDate = LocalDateTime.parse("2020-06-14 10:00:00", formatter);
		List<Price> prices = priceService.findByBrandProductDate(1, 35455, searchDate);
		assertNotNull(prices);
		assertEquals(1, prices.size());
	}

	@Test
	public void findByBrandProductDateNoExistTest(){
		LocalDateTime searchDate = LocalDateTime.parse("2020-06-14 10:00:00", formatter);
		List<Price> prices = priceService.findByBrandProductDate(0, 35456, searchDate);
		assertNotNull(prices);
		assertEquals(0, prices.size());
	}


}
