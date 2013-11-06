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
package za.org.opengov.common.entity;

/**
 * Enumerated class which represenents different states which an issue can
 * undergo.
 * 
 * <ul>
 * <li><strong>OPEN</strong> The issue has been opened, but not yet assigned.</li>
 * <li><strong>ASSIGNED</strong> The issue has been assigned to a staff member,
 * but not yet in progress.</li>
 * <li><strong>IN_PROGRESS</strong> The issue is currently being resolved by a
 * staff member.</li>
 * <li><strong>RESOLVED</strong> The issue has been resolved, but not yet
 * closed, and still under investigation.</li>
 * <li><strong>CLOSED</strong> The issue has been closed, either because it was
 * deemed false or has been resolved.</li>
 * </ul>
 * 
 * @author James Lewis (james.will.lewis@gmail.com)
 */
public enum IssueState {
	/**
	 * The issue has been opened, but not yet assigned.
	 */
	OPEN("Pending..."),
	/**
	 * The issue has been assigned to a staff member, but not yet in progress.
	 */
	ASSIGNED("Assigned..."),
	/**
	 * The issue is currently being resolved by a staff member.
	 */
	IN_PROGRESS("In Progress..."),
	/**
	 * The issue has been resolved, but not yet closed, and still under
	 * investigation
	 */
	RESOLVED("Resolved."),
	/**
	 * The issue has been closed, either because it was deemed false or has been
	 * resolved.
	 */
	CLOSED("Closed.");

	/**
	 * Human-readable form of the issue. 
	 */
	private String readableText;

	/**
	 * 
	 * @param readableText Human readable text to set
	 */
	private IssueState(String readableText) {
		this.readableText = readableText;
	}

	/**
	 * @return Human-readable string.
	 */
	@Override
	public String toString() {
		return readableText;
	}

}
