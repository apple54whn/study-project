package top.conanan.studyproject.mapstruct.domain;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CarMapper {

    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);

    @Mapping(source = "id", target = "id2")
    @Mapping(source = "name", target = "name2")
    @Mapping(target = "creationDate", expression = "java(new java.util.Date())")
    CarDto carToCarDto(Car car);

}
