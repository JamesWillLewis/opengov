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

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "SOWS_STOCKOUT_REPORT")
public class StockoutReport {

	/**
	 * Primary key
	 */
	private Long uid;
	
	/**
	 * Actual stock-out which aggregates the report. There can be many-to-one
	 * stockout-reports to a single stockout.
	 */
	private Stockout stockout;
	
	/**
	 * Person who reported the stock-out.
	 */
	private Subject reporter;
	
	/**
	 * Person who received the stock-out. Typically unused unless the stock-out
	 * report was reported directly to a DOH member and not directly into the
	 * system.
	 */
	private Subject reportee;
	
	/**
	 * Cause/reason for the stock-out (according to the person reporting the
	 * stockout).
	 */
	private String cause;
	/**
	 * If the stock-out was reported directly to a member of the department, and
	 * not directly into the system. If true, the reportee field should be
	 * assigned.
	 */
	private boolean reportedToDOH;

	/**
	 * Date/Time of when the report was received by the server.
	 */
	private Date timestamp;

	public StockoutReport() {
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

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "FK_STOCKOUT")
	public Stockout getStockout() {
		return stockout;
	}

	public void setStockout(Stockout stockout) {
		this.stockout = stockout;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "FK_REPORTER")
	public Subject getReporter() {
		return reporter;
	}

	public void setReporter(Subject reporter) {
		this.reporter = reporter;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "FK_REPORTEE")
	public Subject getReportee() {
		return reportee;
	}

	public void setReportee(Subject reportee) {
		this.reportee = reportee;
	}

	@Column(name = "CAUSE")
	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	@Column(name = "REPORTED_TO_DOH")
	public boolean getReportedToDOH() {
		return reportedToDOH;
	}

	public void setReportedToDOH(boolean reportedToDOH) {
		this.reportedToDOH = reportedToDOH;
	}

	@Column(name = "TIMESTAMP")
	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

}
