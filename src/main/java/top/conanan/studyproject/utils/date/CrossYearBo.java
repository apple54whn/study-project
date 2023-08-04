package top.conanan.studyproject.utils.date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
public class CrossYearBo {
    private String year;
    private LocalDateTime firstDayOfYear;
    private LocalDateTime lastDayOfYear;
}
