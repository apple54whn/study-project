package top.conanan.studyproject.mapstruct.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRoleVO {

    private Long userId;

    private Long roleId;

    private String roleName;

    private String remark;
}
