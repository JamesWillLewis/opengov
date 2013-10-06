package za.org.opengov.stockout.service.medical.impl;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import za.org.opengov.common.service.impl.AbstractServiceImpl;
import za.org.opengov.common.util.StringMatchable;
import za.org.opengov.common.util.StringMatcher;
import za.org.opengov.stockout.dao.medical.ProductDao;
import za.org.opengov.stockout.entity.medical.Product;
import za.org.opengov.stockout.service.medical.ProductService;

@Service("productService")
@Transactional
public class ProductServiceImpl extends AbstractServiceImpl<ProductDao, Product, String> implements ProductService {

	@Autowired
	public ProductServiceImpl(ProductDao dao) {
		super(dao);
		// TODO Auto-generated constructor stub
	}

	@Autowired
	private ProductDao productDao;

	@Override
	public List<Product> getProductsInAlphabeticRange() {
		return null;
	}

	@Override
	public Product getClosestMatch(final String productName) {
		List<Product> products = productDao.findAll();

		StringMatcher matcher = new StringMatcher();

		for (final Product p : products) {
			matcher.addStringMatchable(new ProductWrapper(p));
		}

		Product matched = ((ProductWrapper) matcher
				.getClosestMatch(productName)).getProduct();

		return matched;
	}

	private class ProductWrapper implements StringMatchable {

		private Product product;

		public ProductWrapper(Product product) {
			this.product = product;
		}

		public Product getProduct() {
			return product;
		}

		@Override
		public String getStringToMatch() {
			return product.getName();
		}

	}

	@Override
	public void saveProduct(Product product) {
		product.setUid(product.getUid().toUpperCase());
		productDao.saveOrUpdate(product);
	}

	@Override
	public void populateDatabaseFromCSV(File file, String seperator,
			String textDelimeter) {
		// TODO Auto-generated method stub
		
	}



}
