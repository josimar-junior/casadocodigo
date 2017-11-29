package br.com.jj.loja.cotroller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.jj.loja.dao.ProductDAO;
import br.com.jj.loja.model.BookType;
import br.com.jj.loja.model.Product;

@Controller
@Transactional
@RequestMapping("/produtos")
public class ProductsController {

	@Autowired
	private ProductDAO productDAO;
	
	@RequestMapping(method = RequestMethod.POST)
	public String save(Product product, RedirectAttributes redirectAttributes) {
		productDAO.save(product);
		redirectAttributes.addFlashAttribute("sucesso", "Produto cadastrado com sucesso.");
		return "redirect:produtos";
	}
	
	@RequestMapping("/registrar")
	public ModelAndView registerProduct() {
		ModelAndView modelAndView = new ModelAndView("products/register-product");
		modelAndView.addObject("types", BookType.values());
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView listProducts() {
		ModelAndView modelAndView = new ModelAndView("products/list-product");
		modelAndView.addObject("products", productDAO.list());
		return modelAndView;
	}
	
}
