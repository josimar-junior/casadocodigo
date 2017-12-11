package br.com.jj.loja.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.jj.loja.model.Product;

@Repository
public class ProductDAO {

	@PersistenceContext
	private EntityManager manager;

	public void save(Product product) {
		manager.persist(product);
	}

	public List<Product> list() {
		return manager.createQuery("select distinct(p) from Product p join fetch p.prices", Product.class)
				.getResultList();
	}

	public Product find(Integer id) {
		return manager.createQuery("select p from Product p join fetch p.prices where p.id = :id", Product.class)
				.setParameter("id", id).getSingleResult();
	}
}
