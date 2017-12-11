package br.com.jj.loja.cotroller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.jj.loja.dao.ProductDAO;
import br.com.jj.loja.infra.FileSaver;
import br.com.jj.loja.model.BookType;
import br.com.jj.loja.model.Product;

@Controller
@Transactional
@RequestMapping("/produtos")
public class ProductsController {

	@Autowired
	private ProductDAO productDAO;

	@Autowired
	private FileSaver fileSaver;

	// @InitBinder
	// protected void initBinder(WebDataBinder binder) {
	// binder.setValidator(new ProductValidator());
	// }

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView save(MultipartFile summary, @Valid Product product, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {

		if (bindingResult.hasErrors())
			return registerProduct(product);

		System.out.println(summary.getName() + ";" + summary.getOriginalFilename());

		String webPath = fileSaver.write("uploaded-images", summary);

		product.setSummaryPath(webPath);

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

	@RequestMapping("/{id}")
	public ModelAndView show(@PathVariable("id") Integer id) {
		ModelAndView modelAndView = new ModelAndView("products/show");
		Product product = productDAO.find(id);
		modelAndView.addObject("product", product);
		return modelAndView;
	}

}
