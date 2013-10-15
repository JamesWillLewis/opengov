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
package za.org.opengov.common.util;

import java.util.Date;

/**
 * 
 * @author James Lewis
 * @deprecated Instead use the Joda library (org.joda.time)
 */
@Deprecated
public class ChronoUtil {

	public static final long MILLIS_IN_SECOND = 1000;
	public static final long MILLIS_IN_MINUTE = 60 * MILLIS_IN_SECOND;
	public static final long MILLIS_IN_HOUR = 60 * MILLIS_IN_MINUTE;
	public static final long MILLIS_IN_DAY = MILLIS_IN_HOUR * 24;

	public static class ChronoDifference {
		public final long diffSeconds;
		public final long diffMinutes;
		public final long diffHours;
		public final long diffDays;

		public ChronoDifference(long diffSeconds, long diffMinutes,
				long diffHours, long diffDays) {
			this.diffSeconds = diffSeconds;
			this.diffMinutes = diffMinutes;
			this.diffHours = diffHours;
			this.diffDays = diffDays;
		}

	}

	public static ChronoDifference getChronoDifference(Date former, Date latter) {
		long diff = latter.getTime() - former.getTime();

		long diffSeconds = diff / MILLIS_IN_SECOND % 60;
		long diffMinutes = diff / MILLIS_IN_MINUTE % 60;
		long diffHours = diff / MILLIS_IN_HOUR % 24;
		long diffDays = diff / MILLIS_IN_DAY;
		
		return new ChronoDifference(diffSeconds, diffMinutes, diffHours, diffDays);
	}

}
