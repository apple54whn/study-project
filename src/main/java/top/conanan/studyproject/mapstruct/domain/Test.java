package top.conanan.studyproject.mapstruct.domain;

public class Test {

    public static void main(String[] args) {

        Car car = new Car();
        car.setId("1");
        car.setName("a");
        car.setCode("001");

        CarDto carDto = CarMapper.INSTANCE.carToCarDto(car);

        System.out.println(car);

        System.out.println(carDto);


    }
}
