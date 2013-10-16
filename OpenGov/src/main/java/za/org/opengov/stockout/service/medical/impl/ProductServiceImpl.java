/*
 *  This file is part of OpenGov.
 *
 *  OpenGov is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  OpenGov is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with OpenGov.  If not, see <http://www.gnu.org/licenses/>.
 */
package za.org.opengov.stockout.service.medical.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;

import org.hibernate.criterion.Restrictions;
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
import za.org.opengov.stockout.service.FacilityService;
import za.org.opengov.stockout.service.medical.MedicineService;
import za.org.opengov.stockout.service.medical.ProductService;

/**
 * Concrete implementation of {@link ProductService}.
 * 
 * @author James Lewis (james.will.lewis@gmail.com)
 */
@Service("productService")
@Transactional
public class ProductServiceImpl extends
		AbstractServiceImpl<ProductDao, Product, String> implements
		ProductService {

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
			matcher.addStringMatchable(new ProductDetailsWrapper(p));
			matcher.addStringMatchable(new ProductNameWrapper(p));
		}

		Product matched = ((ProductWrapper) matcher
				.getClosestMatch(productName)).getProduct();

		return matched;
	}
	
	public class ProductWrapper{
		
		protected Product product;
		
		public ProductWrapper(Product product) {
			this.product = product;
		}
		
		public Product getProduct() {
			return product;
		}
		
	}

	private class ProductNameWrapper extends ProductWrapper implements StringMatchable {

		public ProductNameWrapper(Product product) {
			super(product);
		}
		
		@Override
		public String getStringToMatch() {
			return product.getName();
		}

	}
	
	private class ProductDetailsWrapper extends ProductWrapper  implements StringMatchable {

		public ProductDetailsWrapper(Product product) {
			super(product);
		}

		@Override
		public String getStringToMatch() {
			return product.getName() + " " + product.getDescription();
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
			parser = new CSVParser(new FileInputStream(file), seperator,
					textDelimeter);

			for (List<String> row : parser.getRows()) {

				String medicineName = row.get(0);
				String dosage = row.get(1);
				String fullName = row.get(8);

				// add the medicine if it isn't in the DB already
				Medicine m = medicineService.findByName(medicineName);
				if (m == null) {
					m = new Medicine();
					m.setName(medicineName.trim().toUpperCase());
					medicineService.put(m);
				}

				ArrayList<String> terminateWords = new ArrayList<String>();
				
				terminateWords.add("TAB");
				terminateWords.add("SOL");
				terminateWords.add("ORAL");
				terminateWords.add("CAP");
				terminateWords.add("INJ");
				terminateWords.add("SUS");
				terminateWords.add("POW");
				terminateWords.add("PAED");
				terminateWords.add("CSV");

				String productName = "";
				String suffix = "";
				boolean appendToName = true;

				// split full name into product name and description
				String[] tokens = fullName.split(" ");
				for (int i = 0; i < tokens.length; i++) {
					if (!tokens[i].isEmpty()) {
						if (!appendToName) {
							suffix += tokens[i] + " ";
						} else {
							// inspect token to see if it terminates the product
							// name
							if (Character.isDigit(tokens[i].charAt(0))
									|| terminateWords.contains(tokens[i])) {
								appendToName = false;
								suffix += tokens[i] + " ";
								continue;
							}
							productName += tokens[i] + " ";
						}
					}
				}

				productName = productName.trim();
				suffix = suffix.trim();

				Product p = new Product();
				p.setUid(generateProductCode(fullName));
				p.setMedicine(m);
				p.setName(productName);
				p.setDescription(suffix);

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

	@Override
	public List<Product> getAllProductsMatchingName(Product product) {
		return dao.findByCriteria(Restrictions.like("name", product.getName()));
	}

}
