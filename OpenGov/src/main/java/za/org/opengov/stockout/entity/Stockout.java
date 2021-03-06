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
package za.org.opengov.stockout.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import za.org.opengov.common.entity.Issue;
import za.org.opengov.stockout.entity.medical.Product;

/**
 * Domain entity for Facility concept.
 * Annotated to allow object-relational mapping using JPA/Hibernate.
 * 
 * Represents a Facility, which could be a pharmacy, hospital, clinic, etc. 
 * 
 * @author James Lewis (james.will.lewis@gmail.com)
 */
@Entity
@Table(name = "SOWS_STOCKOUT")
public class Stockout {

	/**
	 * Primary key
	 */
	private Long uid;
	
	/**
	 * {@link Issue} reference, in terms of the OpenGov issue resolution protocol.
	 * There is a one-to-one relationship to {@link Issue}. 
	 * Each {@link Stockout} is assigned to an Issue instance. 
	 */
	private Issue issue;
	/**
	 * Product which is out-of-stock.
	 */
	private Product product;
	/**
	 * Facility where the stock-out occurred. 
	 */
	private Facility facility;
	/**
	 * True if the stock-out has been resolved (i.e. the associated issue state
	 * is set to resolved or closed)
	 */
	private boolean resolved;
	/**
	 * All the reports for this particular stock-out. 
	 */
	private Set<StockoutReport> stockoutReports = new HashSet<StockoutReport>();

	public Stockout() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "UID", unique = true, nullable = false)
	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	@OneToOne
	@JoinColumn(name = "FK_ISSUE")
	public Issue getIssue() {
		return issue;
	}

	public void setIssue(Issue issue) {
		this.issue = issue;
	}

	@ManyToOne
	@JoinColumn(name = "FK_PRODUCT")
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@ManyToOne
	@JoinColumn(name = "FK_FACILITY")
	public Facility getFacility() {
		return facility;
	}

	public void setFacility(Facility facility) {
		this.facility = facility;
	}
	
	@Column(name="IS_RESOLVED")
	public boolean getResolved() {
		return resolved;
	}

	public void setResolved(boolean resolved) {
		this.resolved = resolved;
	}
	
	@OneToMany(mappedBy="stockout", cascade=CascadeType.ALL)
	public Set<StockoutReport> getStockoutReports() {
		return stockoutReports;
	}
	
	public void setStockoutReports(Set<StockoutReport> stockoutReports) {
		this.stockoutReports = stockoutReports;
	}

}
