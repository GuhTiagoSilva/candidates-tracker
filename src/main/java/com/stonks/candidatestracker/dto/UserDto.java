package com.stonks.candidatestracker.dto;

import com.stonks.candidatestracker.models.UserModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String firstName;

    private String lastName;
    private String email;
    private String cpf;

    private boolean isOpenToWork;
    private RoleDto role;
    private String photo;

    public UserDto(UserModel user) {
        id = user.getId();
        firstName = user.getFirstName();
        lastName = user.getLastName();
        email = user.getEmail();
        cpf = user.getCpf();
        role = new RoleDto(user.getRoleModel());
        photo = user.getPhoto();
    }

}
