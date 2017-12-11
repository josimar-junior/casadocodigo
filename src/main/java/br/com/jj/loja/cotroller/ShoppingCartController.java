package br.com.jj.loja.cotroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.jj.loja.dao.ProductDAO;
import br.com.jj.loja.model.BookType;
import br.com.jj.loja.model.Product;
import br.com.jj.loja.model.ShoppingCart;
import br.com.jj.loja.model.ShoppingItem;

@Controller
@RequestMapping("/shopping")
public class ShoppingCartController {

	@Autowired
	private ProductDAO productDAO;
	@Autowired
	private ShoppingCart shoppingCart;
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView add(Integer productId, BookType bookType) {
		ShoppingItem item = createItem(productId, bookType);
		shoppingCart.add(item);
		return new ModelAndView("redirect:/produtos");
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String items() {
		return "shoppingCart/items";
	}
	
	private ShoppingItem createItem(Integer productId, BookType bookType) {
		Product product = productDAO.find(productId);
		return new ShoppingItem(product, bookType);
	}
}
