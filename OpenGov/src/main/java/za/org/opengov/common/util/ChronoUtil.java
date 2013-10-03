package za.org.opengov.common.util;

import java.util.Date;

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
			super();
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
