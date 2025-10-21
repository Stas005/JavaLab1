package util;

import java.util.Date;

public class PlanUtils {
    public static boolean isValidStartDate(Date date) {
        return date != null && date.after(new Date());
    }
}
