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

@Entity
@Table(name = "SOWS_STOCKOUT")
public class Stockout {

	private Long uid;
	private Issue issue;
	private Product product;
	private Facility facility;
	private boolean resolved;
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
