package com.inditex.pricesws;

import com.inditex.pricesws.models.entities.PriceEntity;
import com.inditex.pricesws.services.PriceServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith({SpringExtension.class})
@SpringBootTest
class PricesWsApplicationTests {

	@Autowired
	private PriceServiceImpl priceService;

	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	// Test 1: peticion a las 10:00 del día 14 del producto 35455   para la brand 1 (ZARA)
	@Test
	//@org.junit.Test
	void findFirstByBrandProductDateTest1(){
		PriceEntity priceExpected = new PriceEntity(1, LocalDateTime.parse("2020-06-14 00:00:00", formatter), LocalDateTime.parse("2020-12-31 23:59:59", formatter), 1, 35455, 0, 35.50, "EUR");
		LocalDateTime searchDate = LocalDateTime.parse("2020-06-14 10:00:00", formatter);
		PriceEntity price = priceService.findFirstByBrandProductDate(1, 35455, searchDate);
		assertEquals(priceExpected, price);
	}

	// Test 2: peticion a las 16:00 del día 14 del producto 35455   para la brand 1 (ZARA)
	@Test
	void findFirstByBrandProductDateTest2(){
		PriceEntity priceExpected = new PriceEntity(1, LocalDateTime.parse("2020-06-14 15:00:00", formatter), LocalDateTime.parse("2020-06-14 18:30:00", formatter), 2, 35455, 1, 25.45, "EUR");
		LocalDateTime searchDate = LocalDateTime.parse("2020-06-14 16:00:00", formatter);
		PriceEntity price = priceService.findFirstByBrandProductDate(1, 35455, searchDate);
		assertEquals(priceExpected, price);
	}

	// Test 3: peticion a las 21:00 del día 14 del producto 35455   para la brand 1 (ZARA)
	@Test
	void findFirstByBrandProductDateTest3(){
		PriceEntity priceExpected = new PriceEntity(1, LocalDateTime.parse("2020-06-14 00:00:00", formatter), LocalDateTime.parse("2020-12-31 23:59:59", formatter), 1, 35455, 0, 35.50, "EUR");
		LocalDateTime searchDate = LocalDateTime.parse("2020-06-14 21:00:00", formatter);
		PriceEntity price = priceService.findFirstByBrandProductDate(1, 35455, searchDate);
		assertEquals(priceExpected, price);
	}

	// Test 4: peticion a las 10:00 del día 15 del producto 35455   para la brand 1 (ZARA)
	@Test
	void findFirstByBrandProductDateTest4(){
		PriceEntity priceExpected = new PriceEntity(1, LocalDateTime.parse("2020-06-15 00:00:00", formatter), LocalDateTime.parse("2020-06-15 11:00:00", formatter), 3, 35455, 1, 30.50, "EUR");
		LocalDateTime searchDate = LocalDateTime.parse("2020-06-15 10:00:00", formatter);
		PriceEntity price = priceService.findFirstByBrandProductDate(1, 35455, searchDate);
		assertEquals(priceExpected, price);
	}

	// Test 5: peticion a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA)
	@Test
	void findFirstByBrandProductDateTest5(){
		PriceEntity priceExpected = new PriceEntity(1, LocalDateTime.parse("2020-06-15 16:00:00", formatter), LocalDateTime.parse("2020-12-31 23:59:59", formatter), 4, 35455, 1, 38.95, "EUR");
		LocalDateTime searchDate = LocalDateTime.parse("2020-06-16 21:00:00", formatter);
		PriceEntity price = priceService.findFirstByBrandProductDate(1, 35455, searchDate);
		assertEquals(priceExpected, price);
	}

	// Test 5: peticion a las 15:00 :00 del día 16 del producto 35455   para la brand 1 (ZARA)
	@Test
	void findFirstByBrandProductDateTest6(){
		PriceEntity priceExpected = new PriceEntity(1, LocalDateTime.parse("2020-06-14 15:00:00", formatter), LocalDateTime.parse("2020-06-14 18:30:00", formatter), 2, 35455, 1, 25.45, "EUR");
		LocalDateTime searchDate = LocalDateTime.parse("2020-06-14 15:00:00", formatter);
		PriceEntity price = priceService.findFirstByBrandProductDate(1, 35455, searchDate);
		assertEquals(priceExpected, price);
	}

	// Test 7: peticion a las 15:00:01 del día 14 del producto 35455   para la brand 1 (ZARA)
	@Test
	void findFirstByBrandProductDateTest7(){
		PriceEntity priceExpected = new PriceEntity(1, LocalDateTime.parse("2020-06-14 15:00:00", formatter), LocalDateTime.parse("2020-06-14 18:30:00", formatter), 2, 35455, 1, 25.45, "EUR");
		LocalDateTime searchDate = LocalDateTime.parse("2020-06-14 15:00:01", formatter);
		PriceEntity price = priceService.findFirstByBrandProductDate(1, 35455, searchDate);
		assertEquals(priceExpected, price);
	}

	@Test
	void getPricesTest() {
		List<PriceEntity> pricesExpect = new ArrayList<>();
		pricesExpect.add(new PriceEntity(1, LocalDateTime.parse("2020-06-14 00:00:00", formatter), LocalDateTime.parse("2020-12-31 23:59:59", formatter), 1, 35455, 0, 35.50, "EUR"));
		pricesExpect.add(new PriceEntity(1, LocalDateTime.parse("2020-06-14 15:00:00", formatter), LocalDateTime.parse("2020-06-14 18:30:00", formatter), 2, 35455, 1, 25.45, "EUR"));
		pricesExpect.add(new PriceEntity(1, LocalDateTime.parse("2020-06-15 00:00:00", formatter), LocalDateTime.parse("2020-06-15 11:00:00", formatter), 3, 35455, 1, 30.50, "EUR"));
		pricesExpect.add(new PriceEntity(1, LocalDateTime.parse("2020-06-15 16:00:00", formatter), LocalDateTime.parse("2020-12-31 23:59:59", formatter), 4, 35455, 1, 38.95, "EUR"));

		List<PriceEntity> prices = priceService.getPrices();
		assertNotNull(prices);
		assertEquals(4, prices.size());
		assertArrayEquals(pricesExpect.toArray(), prices.toArray());
	}

	@Test
	void getPriceByIdTest() {
		PriceEntity priceExpected = new PriceEntity(1, LocalDateTime.parse("2020-06-15 00:00:00", formatter), LocalDateTime.parse("2020-06-15 11:00:00", formatter), 3, 35455, 1, 30.50, "EUR");
		PriceEntity price = priceService.getPriceById(3);
		assertNotNull(price);
		assertEquals(priceExpected, price);
	}

	@Test
	void updateOrSavePriceTest(){
		PriceEntity priceExpected = new PriceEntity(1, LocalDateTime.parse("2020-06-15 00:00:00", formatter), LocalDateTime.parse("2020-06-15 11:00:00", formatter), 5, 35455, 1, 30.50, "EUR");
		priceService.updateOrSavePrice(priceExpected);
		PriceEntity price = priceService.getPriceById(5);
		assertEquals(5, priceService.getPrices().size());
		assertNotNull(price);
		assertEquals(priceExpected, price);
		priceService.deletePriceById(5);
	}

	@Test
	void deletePricebyIdTest() {
		PriceEntity priceExpected = new PriceEntity(1, LocalDateTime.parse("2020-06-15 00:00:00", formatter), LocalDateTime.parse("2020-06-15 11:00:00", formatter), 6, 35455, 1, 30.50, "EUR");
		priceService.updateOrSavePrice(priceExpected);
		PriceEntity price = priceService.getPriceById(6);
		assertEquals(5, priceService.getPrices().size());
		assertEquals(priceExpected, price);
		priceService.deletePriceById(6);
		assertEquals(4, priceService.getPrices().size());
	}

	@Test
	void getPricesByProductIdTest(){
		List<PriceEntity> prices = priceService.getPricesByProductId(35455);
		assertNotNull(prices);
		assertEquals(4, prices.size());
	}

	@Test
	void getPricesByProductIdNoExistTest(){
		List<PriceEntity> prices = priceService.getPricesByProductId(35456);
		assertNotNull(prices);
		assertEquals(0, prices.size());
	}

	@Test
	void findByBrandProductDateTest(){
		LocalDateTime searchDate = LocalDateTime.parse("2020-06-14 10:00:00", formatter);
		List<PriceEntity> prices = priceService.findByBrandProductDate(1, 35455, searchDate);
		assertNotNull(prices);
		assertEquals(1, prices.size());
	}

	@Test
	void findByBrandProductDateNoExistTest(){
		LocalDateTime searchDate = LocalDateTime.parse("2020-06-14 10:00:00", formatter);
		List<PriceEntity> prices = priceService.findByBrandProductDate(0, 35456, searchDate);
		assertNotNull(prices);
		assertEquals(0, prices.size());
	}
}
