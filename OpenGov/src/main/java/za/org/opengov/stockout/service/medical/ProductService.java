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
package za.org.opengov.stockout.service.medical;

import java.io.File;
import java.util.List;

import za.org.opengov.common.service.AbstractService;
import za.org.opengov.stockout.entity.medical.Product;

public interface ProductService extends AbstractService<Product, String> {

	public void saveProduct(Product product);

	public List<Product> getProductsInAlphabeticRange();

	/**
	 * Performs string closeness matching and returns the nearest matching
	 * product to the specified product name.
	 * 
	 * @param productName
	 * @return
	 */
	public Product getClosestMatch(String productName);

	public void populateDatabaseFromCSV(File file, String seperator,
			String textDelimeter);
	
	public String generateProductCode(String name);
	
	public List<Product> getAllProductsMatchingName(Product product);

}
