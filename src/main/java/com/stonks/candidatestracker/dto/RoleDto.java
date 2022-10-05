package com.stonks.candidatestracker.dto;

import com.stonks.candidatestracker.models.RoleModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private long id;
    private String authority;

    public RoleDto(RoleModel role) {
        id = role.getId();
        authority = role.getAuthority();
    }

}
