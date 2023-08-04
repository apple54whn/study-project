package top.conanan.studyproject.utils.date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Data
public class HolidayBo {

    private LocalDateTime start;
    private LocalDateTime end;
    private DayType dayType;
    private Boolean isHoliday;
    // ....

}

@Getter
@AllArgsConstructor
enum DayType {
    MORNING(1, "早上"),
    AFTERNOON(2, "下午");

    private final Integer code;

    private final String desc;
}
