package top.conanan.studyproject.mapstruct.domain;

import java.util.List;

public class Test {

    public static void main(String[] args) {


        UserRole userRole1 = UserRole.builder().userId(10001L).roleId(1L).build();
        UserRole userRole2 = UserRole.builder().userId(10001L).roleId(2L).build();
        UserRole userRole3 = UserRole.builder().userId(10001L).roleId(3L).build();


        Role role1 = Role.builder().id(1L).name("角色1").remark("橘色1").build();
        Role role2 = Role.builder().id(2L).name("角色2").remark("橘色2").build();
        Role role3 = Role.builder().id(3L).name("角色3").remark("橘色3").build();
        Role role4 = Role.builder().id(4L).name("角色4").remark("橘色4").build();


        List<UserRole> userRoleList = List.of(userRole1, userRole2, userRole3);
        List<Role> roleList = List.of(role1, role2, role3, role4);

        List<UserRoleVO> convert = UserMapper.INSTANCE.convert2(roleList, userRoleList);
        System.out.println(convert);


        UserRoleVO convert1 = UserMapper.INSTANCE.convert(role1, userRole2);
        System.out.println(convert1);


    }
}
