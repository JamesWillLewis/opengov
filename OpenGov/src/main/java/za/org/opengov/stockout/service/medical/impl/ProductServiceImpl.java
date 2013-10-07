package za.org.opengov.stockout.service.medical.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import za.org.opengov.common.service.impl.AbstractServiceImpl;
import za.org.opengov.common.util.CSVParser;
import za.org.opengov.common.util.StringMatchable;
import za.org.opengov.common.util.StringMatcher;
import za.org.opengov.stockout.dao.medical.ProductDao;
import za.org.opengov.stockout.entity.Facility;
import za.org.opengov.stockout.entity.medical.Medicine;
import za.org.opengov.stockout.entity.medical.Product;
import za.org.opengov.stockout.service.medical.MedicineService;
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
	
	@Autowired
	private MedicineService medicineService;

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
		
		CSVParser parser;
		try {
			parser = new CSVParser(new FileInputStream(file),
					seperator, textDelimeter);
			
			for (List<String> row : parser.getRows()) {
				
				String medicineName = row.get(0);
				String dosage = row.get(1);
				String productName = row.get(8);
				
				//add the medicine if it isn't in the DB already
				Medicine m = medicineService.findByName(medicineName);
				if(m==null){
					m = new Medicine();
					m.setName(medicineName.trim().toUpperCase());
					medicineService.put(m);
				}
				
				Product p = new Product();
				p.setUid(generateProductCode(productName));
				p.setMedicine(m);
				p.setName(productName);
				
				put(p);
				
				
			}
		} catch (FileNotFoundException e) {
			System.err.println("Could not load CSV file: " + file.getPath());
		}

		
	}
	
	@Override
	public String generateProductCode(String name) {

		name = name.trim();
		
		int stringPartLength = 5;
		String stringPart = "";

		// capture string part
		if (stringPartLength > name.length()) {
			stringPart = name;
		} else {
			stringPart = name.substring(0, stringPartLength);
		}
		
		stringPart = stringPart.toUpperCase();

		int numberPart = 1;
		String wholeID = stringPart;

		Product sameIDProduct = get(wholeID);
		while (sameIDProduct != null) {
			wholeID = stringPart + numberPart;
			numberPart++;
			sameIDProduct = get(wholeID);
		}

		return wholeID;
	}



}
