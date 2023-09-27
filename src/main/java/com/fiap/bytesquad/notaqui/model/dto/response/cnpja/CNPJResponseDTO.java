package com.fiap.bytesquad.notaqui.model.dto.response.cnpja;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class CNPJResponseDTO implements Serializable {
    private static final long serialVersionUID = -1L;

    @NotNull(message = "CNPJ precisa ser preenchido!")
    @JsonProperty("cnpj")
    private String cnpj;

    @NotNull(message = "Razao Social precisa ser preenchido!")
    @JsonProperty("razaoSocial")
    private String corporateName;

    @NotNull(message = "Natureza Juridica precisa ser preenchido!")
    @JsonProperty("naturezaJuridica")
    private String legalNature;

    @NotNull(message = "Tipo de empresa precisa ser preenchido!")
    @JsonProperty("tipoEmpresa")
    private String corporateType;
}
