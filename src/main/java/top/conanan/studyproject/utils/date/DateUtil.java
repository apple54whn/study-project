package top.conanan.studyproject.utils.date;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

import static top.conanan.studyproject.utils.date.CrossDayHourBo.*;

@Slf4j
public class DateUtil {


    public static LocalDateTime castDate2LocalDateTime(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static LocalDate castDate2LocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }


    /**
     * 跨年数量，比如[2022，2033]，返回2
     *
     * @param start start，注意传递的 LocalDateTime 具体时分秒！，否则计算不正确
     * @param end   end，注意传递的 LocalDateTime 具体时分秒！，否则计算不正确
     * @return 跨年数量
     */
    public static long crossYear(LocalDateTime start, LocalDateTime end) {
        log.info("start: {}, end: {}", start, end);
        Assert.notNull(start, "start should not be null");
        Assert.notNull(end, "end should not be null");
        Assert.isTrue(!start.isAfter(end), "start is must before or equal end");
        return end.getYear() - start.getYear() + 1;
    }

    public static long crossYear(Date start, Date end) {
        return crossYear(castDate2LocalDateTime(start), castDate2LocalDateTime(end));
    }

    /**
     * 是否跨年
     *
     * @param start start，注意传递的 LocalDateTime 具体时分秒！，否则计算不正确
     * @param end   end，注意传递的 LocalDateTime 具体时分秒！，否则计算不正确
     * @return 是否跨年
     */
    public static boolean isCrossYear(LocalDateTime start, LocalDateTime end) {
        return crossYear(start, end) != 1;
    }

    public static boolean isCrossYear(Date start, Date end) {
        return crossYear(start, end) != 1;
    }

    /**
     * 跨年拆分
     *
     * @param start start，注意传递的 LocalDateTime 具体时分秒！，否则计算不正确
     * @param end   end，注意传递的 LocalDateTime 具体时分秒！，否则计算不正确
     * @return [2022, 2023]
     */
    public static List<CrossYearBo> splitCrossYear(LocalDateTime start, LocalDateTime end) {
        List<CrossYearBo> list = new ArrayList<>();
        long cross = crossYear(start, end);
        for (int i = 0; i < cross; i++) {
            LocalDateTime now = start.plusYears(i);

            CrossYearBo crossYearBo = new CrossYearBo();
            crossYearBo.setYear(String.valueOf(now.getYear()));
            crossYearBo.setFirstDayOfYear(LocalDateTime.of(now.with(TemporalAdjusters.firstDayOfYear()).toLocalDate(), LocalTime.MIN));
            crossYearBo.setLastDayOfYear(LocalDateTime.of(now.with(TemporalAdjusters.lastDayOfYear()).toLocalDate(), LocalTime.MAX));
            list.add(crossYearBo);
        }
        return list;
    }

    public static List<CrossYearBo> splitCrossYear(Date start, Date end) {
        return splitCrossYear(castDate2LocalDateTime(start), castDate2LocalDateTime(end));
    }


    /**
     * 跨月数量，比如[202211，202302]，返回4
     * ChronoUnit.MONTHS.between 主要会存在不满1月时返回0的情况，不好计算
     *
     * @param start start，注意传递的 LocalDateTime 具体时分秒！，否则计算不正确
     * @param end   end，注意传递的 LocalDateTime 具体时分秒！，否则计算不正确
     * @return 跨月数量
     */
    public static long crossMonth(LocalDateTime start, LocalDateTime end) {
        log.info("start: {}, end: {}", start, end);
        Assert.notNull(start, "start should not be null");
        Assert.notNull(end, "end should not be null");
        Assert.isTrue(!start.isAfter(end), "start is must before or equal end");

        return 12L * (end.getYear() - start.getYear()) + end.getMonthValue() - start.getMonthValue() + 1;
    }

    public static long crossMonth(Date start, Date end) {
        return crossMonth(castDate2LocalDateTime(start), castDate2LocalDateTime(end));
    }


    /**
     * 是否跨月
     *
     * @param start start，注意传递的 LocalDateTime 具体时分秒！，否则计算不正确
     * @param end   end，注意传递的 LocalDateTime 具体时分秒！，否则计算不正确
     * @return 是否跨月
     */
    public static boolean isCrossMonth(LocalDateTime start, LocalDateTime end) {
        return crossMonth(start, end) != 1;
    }

    public static boolean isCrossMonth(Date start, Date end) {
        return isCrossMonth(castDate2LocalDateTime(start), castDate2LocalDateTime(end));
    }


    /**
     * 跨月拆分
     *
     * @param start start，注意传递的 LocalDateTime 具体时分秒！，否则计算不正确
     * @param end   end，注意传递的 LocalDateTime 具体时分秒！，否则计算不正确
     * @return [202211, 202212, 202301, 202302]
     */
    public static List<CrossMonthBo> splitCrossMonth(LocalDateTime start, LocalDateTime end) {
        List<CrossMonthBo> list = new ArrayList<>();
        long cross = crossMonth(start, end);
        for (int i = 0; i < cross; i++) {
            LocalDateTime now = start.plusMonths(i);

            CrossMonthBo crossMonthBo = new CrossMonthBo();
            crossMonthBo.setYearMonth(now.getYear() + StringUtils.leftPad(String.valueOf(now.getMonthValue()), 2, "0"));

            if (i == 0) {
                crossMonthBo.setFirstDayOfMonth(start);
            }

            if (i == cross - 1) {
                crossMonthBo.setLastDayOfMonth(end);
            }

            if (Objects.isNull(crossMonthBo.getFirstDayOfMonth())) {
                crossMonthBo.setFirstDayOfMonth(LocalDateTime.of(now.with(TemporalAdjusters.firstDayOfMonth()).toLocalDate(), LocalTime.MIN));
            }

            if (Objects.isNull(crossMonthBo.getLastDayOfMonth())) {
                crossMonthBo.setLastDayOfMonth(LocalDateTime.of(now.with(TemporalAdjusters.lastDayOfMonth()).toLocalDate(), LocalTime.MAX));
            }

            list.add(crossMonthBo);
        }
        return list;
    }

    public static List<CrossMonthBo> splitCrossMonth(Date start, Date end) {
        return splitCrossMonth(castDate2LocalDateTime(start), castDate2LocalDateTime(end));
    }


    /**
     * 跨天数量，比如[20221111，20221112]，返回2
     *
     * @param start start，注意传递的 LocalDateTime 具体时分秒！，否则计算不正确
     * @param end   end，注意传递的 LocalDateTime 具体时分秒！，否则计算不正确
     * @return 跨天数量
     */
    public static long crossDay(LocalDate start, LocalDate end) {
        log.info("start: {}, end: {}", start, end);
        Assert.notNull(start, "start should not be null");
        Assert.notNull(end, "end should not be null");
        Assert.isTrue(!start.isAfter(end), "start is must before or equal end");
        // long l = Duration.between(start, end).toDays() + 1;
        return ChronoUnit.DAYS.between(start, end) + 1;
    }


    public static long crossDay(Date start, Date end) {
        return crossDay(castDate2LocalDate(start), castDate2LocalDate(end));
    }


    public static CrossDayHourBo crossDayHour(LocalDateTime start, LocalDateTime end) {
        CrossDayHourBo crossDayHourBo = new CrossDayHourBo();

        long crossDay = crossDay(start.toLocalDate(), end.toLocalDate());

        if (start.getHour() == WORK_MORNING_START_HOUR && end.getHour() == WORK_AFTERNOON_END_HOUR) {
            // 早上开始，下午结束
            crossDayHourBo.setDayOfSum(crossDay);
            crossDayHourBo.setHourOfSum(0L);
        } else if (start.getHour() == WORK_MORNING_START_HOUR && end.getHour() == WORK_MORNING_END_HOUR) {
            // 早上开始，早上结束，1.5
            crossDayHourBo.setDayOfSum(crossDay - 1);
            crossDayHourBo.setHourOfSum(WORK_MORNING_END_HOUR - WORK_MORNING_START_HOUR);
        } else if (start.getHour() == WORK_AFTERNOON_START_HOUR && end.getHour() == WORK_AFTERNOON_END_HOUR) {
            // 下午开始，下午结束，1.5
            crossDayHourBo.setDayOfSum(crossDay - 1);
            crossDayHourBo.setHourOfSum(WORK_AFTERNOON_END_HOUR - WORK_AFTERNOON_START_HOUR);
        } else if (start.getHour() == WORK_AFTERNOON_START_HOUR && end.getHour() == WORK_MORNING_END_HOUR) {
            // 下午开始，早上结束
            crossDayHourBo.setDayOfSum(crossDay - 1);
            crossDayHourBo.setHourOfSum(0L);
        }

        return crossDayHourBo;
    }


    /**
     * 判断时间区间 A 和 B 是否存在交叉（已经包括边界重合）
     *
     * @param start1
     * @param end1
     * @param start2
     * @param end2
     * @return
     */
    public static boolean checkOverlap(LocalDateTime start1, LocalDateTime end1, LocalDateTime start2, LocalDateTime end2) {
        return !(start1.isAfter(end2) || end1.isBefore(start2));

        // 下面的不推荐了
        // if ((currBegin.compareTo(begin) <= 0 && currEnd.compareTo(begin) >= 0)
        //         || (currBegin.compareTo(end) <= 0 && currEnd.compareTo(end) >= 0)
        //         || (currBegin.compareTo(begin) >= 0 && currEnd.compareTo(end) <= 0)) {
        //     return true;
        // }
    }

    public static boolean checkOverlap(Date start1, Date end1, Date start2, Date end2) {
        return checkOverlap(castDate2LocalDateTime(start1), castDate2LocalDateTime(end1), castDate2LocalDateTime(start2), castDate2LocalDateTime(end2));
    }


    /**
     * 计算实际天数（排除假期等）
     *
     * @param start
     * @param end
     * @return
     */
    public static CrossDayHourBo calcDay(LocalDateTime start, LocalDateTime end) {
        // 假期应该按照半天存储时间段！如1月1日9:00~13:00 元旦假期；1月1日14:00~18:00 元旦假期；3月8日14:00~18:00 妇女节；
        // 列出start年份到end年份的所有假期
        // 循环假期，并使用 checkOverlap 来保留存在重合的假期并求和（注意进制问题）
        // crossDayHour 求得的值减去上步的结果即可

        HolidayBo holidayBo = new HolidayBo();
        holidayBo.setStart(LocalDateTime.of(2023, 3, 8, 14, 0, 0));
        holidayBo.setEnd(LocalDateTime.of(2023, 3, 8, 18, 0, 0));
        holidayBo.setIsHoliday(true);
        holidayBo.setDayType(DayType.AFTERNOON);


        List<HolidayBo> holidayBoList = List.of(holidayBo);

        long halfDay = 0;
        for (HolidayBo h : holidayBoList) {
            boolean b = checkOverlap(h.getStart(), h.getEnd(), start, end);
            if (b) {
                halfDay++;
            }
        }

        CrossDayHourBo holidayCrossDayHourBo = new CrossDayHourBo(halfDay);
        CrossDayHourBo crossDayHourBo = crossDayHour(start, end);
        crossDayHourBo.sub(holidayCrossDayHourBo);
        return crossDayHourBo;
    }


    public static void testOverlap(LocalDateTime startA, LocalDateTime endA,
                                   LocalDateTime startB, LocalDateTime endB) {
        boolean isOverlap = checkOverlap(startA, endA, startB, endB);
        System.out.println("时间区间 [" + startA + ", " + endA + "] 和 [" + startB + ", " + endB + "] 是否存在交叉: " + isOverlap);
    }


    public static void main(String[] args) {

//        System.out.println(crossDayHour(LocalDateTime.of(2023, 3, 7, 9, 0), LocalDateTime.of(2023, 3, 11, 18, 0)));
//        System.out.println(calcDay(LocalDateTime.of(2023, 3, 7, 9, 0), LocalDateTime.of(2023, 3, 11, 18, 0)));


//        // 交叉的情况
//        testOverlap(LocalDateTime.of(2023, 8, 1, 10, 0), LocalDateTime.of(2023, 8, 1, 14, 0),
//                LocalDateTime.of(2023, 8, 1, 12, 0), LocalDateTime.of(2023, 8, 1, 16, 0));
//
//        // A 包含 B 的情况
//        testOverlap(LocalDateTime.of(2023, 8, 1, 9, 0), LocalDateTime.of(2023, 8, 1, 17, 0),
//                LocalDateTime.of(2023, 8, 1, 10, 0), LocalDateTime.of(2023, 8, 1, 16, 0));
//
//        // B 包含 A 的情况
//        testOverlap(LocalDateTime.of(2023, 8, 1, 11, 0), LocalDateTime.of(2023, 8, 1, 15, 0),
//                LocalDateTime.of(2023, 8, 1, 10, 0), LocalDateTime.of(2023, 8, 1, 16, 0));
//
//        // 不交叉的情况
//        testOverlap(LocalDateTime.of(2023, 8, 1, 10, 0), LocalDateTime.of(2023, 8, 1, 12, 0),
//                LocalDateTime.of(2023, 8, 1, 14, 0), LocalDateTime.of(2023, 8, 1, 16, 0));
//
//        // 边界重合的情况
//        testOverlap(LocalDateTime.of(2023, 8, 1, 10, 0), LocalDateTime.of(2023, 8, 1, 12, 0),
//                LocalDateTime.of(2023, 8, 1, 12, 0), LocalDateTime.of(2023, 8, 1, 14, 0));
//
//
        LocalDateTime start = LocalDateTime.of(2023, 8, 10, 0, 0, 0);
        LocalDateTime end = LocalDateTime.of(2023, 10, 20, 0, 0, 0);
//
//        System.out.println(crossDay(start.toLocalDate(), end.toLocalDate()));
//        System.out.println(crossDayHour(start, end));


//        System.out.println(crossYear(start, end));
//        System.out.println(splitCrossYear(start, end));

        System.out.println(crossMonth(start, end));
//        System.out.println(isCrossMonth(start, end));
        System.out.println(splitCrossMonth(start, end));


        System.out.println("=========================");

//        Calendar instance1 = Calendar.getInstance();
//        Date start2 = instance1.getTime();
//
//        Calendar instance2 = Calendar.getInstance();
//        instance2.add(Calendar.HOUR, 12);
//        Date end2 = instance2.getTime();
//
//        System.out.println(crossDay(start2, end2));


    }


//    /**
//     * 跨月拆分
//     * @param start
//     * @param end
//     * @return
//     */
//    public static List<List<LocalDateTime>> splitCrossMonthLocal(Date start, Date end) {
//        List<List<LocalDateTime>> dateList = new ArrayList<>();
//        ZoneId zoneId = ZoneId.systemDefault();
//
//        long between = betweenMonths(start, end);
//
//        LocalDateTime startLocal = start.toInstant().atZone(zoneId).toLocalDateTime();
//        LocalDateTime endLocal = end.toInstant().atZone(zoneId).toLocalDateTime();
//
//        if (between == 1) {
//            List<LocalDateTime> one = new ArrayList<>();
//            one.add(startLocal);
//            one.add(endLocal);
//            dateList.add(one);
//            return dateList;
//        }
//
//        for (long i = 0; i < between; i++) {
//
//            if (i == 0) {
//                List<LocalDateTime> first = new ArrayList<>();
//                LocalDateTime lastDayOfMonth = startLocal.with(TemporalAdjusters.lastDayOfMonth());
//                first.add(startLocal);
//                first.add(lastDayOfMonth);
//                dateList.add(first);
//                continue;
//            }
//            if (i == between - 1) {
//                List<LocalDateTime> last = new ArrayList<>();
//                LocalDateTime firstDayOfMonth = endLocal.with(TemporalAdjusters.firstDayOfMonth());
//                last.add(firstDayOfMonth);
//                last.add(endLocal);
//                dateList.add(last);
//                continue;
//            }
//
//            List<LocalDateTime> middleList = new ArrayList<>();
//            LocalDateTime currLocal = startLocal.plusMonths(i);
//            LocalDateTime firstDayOfMonth = currLocal.with(TemporalAdjusters.firstDayOfMonth());
//            LocalDateTime lastDayOfMonth = currLocal.with(TemporalAdjusters.lastDayOfMonth());
//            middleList.add(firstDayOfMonth);
//            middleList.add(lastDayOfMonth);
//            dateList.add(middleList);
//        }
//        return dateList;
//    }
//
//    public static long betweenDays(Date start, Date end) {
//        ZoneId zoneId = ZoneId.systemDefault();
//
//        LocalDateTime startLocal = start.toInstant().atZone(zoneId).toLocalDateTime();
//        LocalDateTime endLocal = end.toInstant().atZone(zoneId).toLocalDateTime();
//
//        Duration duration = Duration.between(startLocal, endLocal);
//        long days = duration.toDays(); //相差的天数
//        return days;
//    }
//
//
//    /**
//     *
//     * @param start
//     * @param end
//     * @return 几个月
//     */
//    public static long betweenMonths(Date start, Date end) {
//        ZoneId zoneId = ZoneId.systemDefault();
//
//        LocalDateTime startLocal = start.toInstant().atZone(zoneId).toLocalDateTime();
//        LocalDateTime endLocal = end.toInstant().atZone(zoneId).toLocalDateTime();
//
//        long between = ChronoUnit.MONTHS.between(startLocal, endLocal);
//        return between + 2;
//    }

}
