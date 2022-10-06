package com.stonks.candidatestracker.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private String lastName;
    private String email;
    private String cpf;

    private boolean isOpenToWork;
    private RoleDto role;

    public UserDto(UserModel user) {
        id = user.getId();
        firstName = user.getFirstName();
        lastName = user.getLastName();
        email = user.getEmail();
        cpf = user.getCpf();
        role = new RoleDto(user.getRoleModel());
    }

}
