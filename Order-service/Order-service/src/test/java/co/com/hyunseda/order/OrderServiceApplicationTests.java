package co.com.hyunseda.order;

import co.com.hyunseda.order.domain.dao.IPayDao;
import co.com.hyunseda.order.domain.entity.*;
import co.com.hyunseda.order.domain.service.IItemClientFeign;
import co.com.hyunseda.order.domain.service.OrderFeignClient;
import co.com.hyunseda.order.domain.service.PayService;
import co.com.hyunseda.order.domain.entity.embarkedState;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class OrderServiceApplicationTests {

	@Test
	public void testCancelState() {
		State existingState = mock(State.class);
		cancelledState state = new cancelledState(existingState);
		assertEquals(state, state.cancel());
	}

	@Test
	public void testItemCreation() {
		Product product = new Product();
		Item item = new Item(product, 5);
		assertEquals(product, item.getProduct());
		assertEquals(5, item.getAmount());
	}

	@Test
	public void testPayService() {
		IPayDao payDao = mock(IPayDao.class);
		PayService payService = new PayService();
		payService.setPayDao(payDao);

		Pay pay = new Pay();
		when(payDao.findById(1L)).thenReturn(java.util.Optional.of(pay));

		assertEquals(pay, payService.findById(1L));
	}


}
