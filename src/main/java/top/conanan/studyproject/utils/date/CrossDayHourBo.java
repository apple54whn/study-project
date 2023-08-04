package top.conanan.studyproject.utils.date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CrossDayHourBo {

    /** 每天工作时长 */
    public static final long WORK_HOUR = 8;
    /** 每天上午开始工作时间 */
    public static final long WORK_MORNING_START_HOUR = 9;
    /** 每天上午开始工作时间 */
    public static final long WORK_MORNING_END_HOUR = 13;
    /** 每天下午开始工作时间 */
    public static final long WORK_AFTERNOON_START_HOUR = 14;
    /** 每天下午开始工作时间 */
    public static final long WORK_AFTERNOON_END_HOUR = 18;

    private Long dayOfSum;
    private Long hourOfSum;

    public CrossDayHourBo(long halfDay) {
        this.dayOfSum = halfDay / 2;
        this.hourOfSum = halfDay % 2 == 0 ? 0L : WORK_HOUR / 2;
    }


    public void sub(CrossDayHourBo b) {
        Long hourOfA = this.getHourOfSum();
        Long hourOfB = b.getHourOfSum();

        if (hourOfA < hourOfB) {
            this.setHourOfSum(WORK_HOUR - hourOfB);
            this.setDayOfSum(this.getDayOfSum() - b.getDayOfSum() - 1);
        } else {
            this.setHourOfSum(hourOfA - hourOfB);
            this.setDayOfSum(this.getDayOfSum() - b.getDayOfSum());
        }
    }
}
