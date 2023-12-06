package com.serverSide.javaSpringBoot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String role;
    private Set<RolesDto>rolesDtoSet;
//    private String passwordSuccessfullyUpdated;
//    private Set<ProfileUpdateDto> profileUpdateDto;

}
