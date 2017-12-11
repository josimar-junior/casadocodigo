package br.com.jj.loja.cotroller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.async.DeferredResult;

import br.com.jj.loja.model.ShoppingCart;
import br.com.jj.loja.service.IntegrandoComPagamento;

@Controller
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	private ShoppingCart shoppingCart;
	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping(value = "checkout", method = RequestMethod.POST)
	public DeferredResult<String> checkout() {

		BigDecimal total = shoppingCart.getTotal();
		DeferredResult<String> result = new DeferredResult<>();

		IntegrandoComPagamento integrandoComPagamento = new IntegrandoComPagamento(result, total, restTemplate);

		Thread thread = new Thread(integrandoComPagamento);
		thread.start();
		return result;
	}
//	public Callable<String> checkout() {
//		return () -> {
//			BigDecimal total = shoppingCart.getTotal();
//
//			String uriToPay = "http://book-payment.herokuapp.com/payment";
//			try {
//				String response = restTemplate.postForObject(uriToPay, new PaymentData(total), String.class);
//				return "redirect:/payment/success";
//			} catch (HttpClientErrorException exception) {
//				return "redirect:/payment/error";
//			}
//		};
//	}
	

}
