package br.com.jj.loja.cotroller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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
	
//	@InitBinder
//	protected void initBinder(WebDataBinder binder) {
//		binder.setValidator(new ProductValidator());
//	}
	
	@RequestMapping(method = RequestMethod.POST, name = "saveProduct")
	public ModelAndView save(@Valid Product product, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		
		if(bindingResult.hasErrors())
			return registerProduct(product);
		
		productDAO.save(product);
		redirectAttributes.addFlashAttribute("sucesso", "Produto cadastrado com sucesso.");
		return new ModelAndView("redirect:produtos");
	}
	
	@RequestMapping("/registrar")
	public ModelAndView registerProduct(Product product) {
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
