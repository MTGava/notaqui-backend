package com.fiap.bytesquad.notaqui.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fiap.bytesquad.notaqui.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO implements Serializable {
    private static final long serialVersionUID = -1L;

    @JsonProperty(value = "matricula")
    @Size(max = 10, message = "Limite máximo de caracteres ultrapassado")
    @NotNull(message = "Matrícula precisa ser preenchida!")
    private String login;

    @JsonProperty(value = "nome")
    @Size(max = 25, message = "Limite máximo de caracteres ultrapassado")
    @NotNull(message = "Nome precisa ser preenchido!")
    private String name;

    @JsonProperty("cargo")
    @NotNull(message = "Cargo precisa ser preenchido!")
    private String role;

    public UserDTO(User user) {
        this.login = user.getLogin();
        this.name = user.getName();
        this.role = user.getRole();
    }
}
