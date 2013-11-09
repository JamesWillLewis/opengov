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
import za.org.opengov.stockout.entity.Facility;
import za.org.opengov.stockout.entity.medical.Product;

/**
 * Service (business object) class for {@link Product}.
 * 
 * @author James Lewis (james.will.lewis@gmail.com)
 */
public interface ProductService extends AbstractService<Product, String> {

	/**
	 * @deprecated Use the default put() method.
	 */
	public void saveProduct(Product product);

	/**
	 * @deprecated Unused and not implemented.
	 */
	public List<Product> getProductsInAlphabeticRange();

	/**
	 * Performs string closeness matching and returns the nearest matching
	 * product to the specified product name.
	 * 
	 * @param productName
	 *            Name of the product.
	 * @return Product whose name most closely matches the given product name.
	 */
	public Product getClosestMatch(String productName);

	/**
	 * Perform back-fill of the database facility table from a CSV file.
	 * 
	 * @param file
	 *            CSV file of medical product entries.
	 * @param seperator
	 *            Separator token used in CSV file.
	 * @param textDelimeter
	 *            Delimiter token used in CSV file.
	 */
	public void populateDatabaseFromCSV(File file, String seperator,
			String textDelimeter);

	/**
	 * Generate a product code, from the product name, to be used as the UID.
	 * 
	 * @param name
	 *            Product name.
	 * @return Product code.
	 */
	public String generateProductCode(String name);

	/**
	 * Gets all products which are of the same brand-name as the specified
	 * product, but can be of different dosages. Many products are the same
	 * product, but simply a different dosage form and volume.
	 * 
	 * @param product
	 *            The product whose brand-name will be used (the details are
	 *            ignored)
	 * @return List of all products whose name (excluding dosage details)
	 *         matches the given product.
	 */
	public List<Product> getAllProductsMatchingName(Product product);

}
