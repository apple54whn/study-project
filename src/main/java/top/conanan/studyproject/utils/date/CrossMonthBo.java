package top.conanan.studyproject.utils.date;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CrossMonthBo {
    private String yearMonth;
    private LocalDateTime firstDayOfMonth;
    private LocalDateTime lastDayOfMonth;
}
