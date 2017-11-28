package br.com.jj.loja.cotroller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.jj.loja.dao.ProductDAO;
import br.com.jj.loja.model.BookType;
import br.com.jj.loja.model.Product;

@Controller
@Transactional
public class ProductsController {

	@Autowired
	private ProductDAO productDAO;
	
	@RequestMapping("/produtos")
	public String save(Product product) {
		productDAO.save(product);
		return "";
	}
	
	@RequestMapping("produtos/registrar")
	public ModelAndView registerProduct() {
		ModelAndView modelAndView = new ModelAndView("products/register-product");
		modelAndView.addObject("types", BookType.values());
		return modelAndView;
	}
}
