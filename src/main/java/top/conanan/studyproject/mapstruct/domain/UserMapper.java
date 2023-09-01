package top.conanan.studyproject.mapstruct.domain;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper(typeConversionPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mappings(@Mapping(target = "roleName", source = "source1.name"))
    UserRoleVO convert(Role source1, UserRole source2);

    default boolean isMatchingIds(Role source1, UserRole source2) {
        return source1.getId().equals(source2.getRoleId());
    }

    ArrayList<UserRoleVO> convert2(List<Role> source1, List<UserRole> source2);

}
