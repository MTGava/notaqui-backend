package com.fiap.bytesquad.notaqui.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fiap.bytesquad.notaqui.model.Company;
import com.fiap.bytesquad.notaqui.model.dto.response.cnpja.CNPJResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDTO implements Serializable {
    private static final long serialVersionUID = -1L;

    @Size(max = 20, message = "Limite máximo de caracteres ultrapassado")
    @NotNull(message = "CNPJ precisa ser preenchido!")
    @JsonProperty(value = "cnpj")
    private String cnpj;

    @Size(max = 100, message = "Limite máximo de caracteres ultrapassado")
    @NotNull(message = "Razão social precisa ser preenchida!")
    @JsonProperty(value = "razaoSocial")
    private String corporateName;

    @Size(max = 400, message = "Limite máximo de caracteres ultrapassado")
    @NotNull(message = "Natureza jurídica precisa ser preenchida!")
    @JsonProperty("naturezaJuridica")
    private String legalNature;

    @Size(max = 400, message = "Limite máximo de caracteres ultrapassado")
    @NotNull(message = "Tipo de empresa precisa ser preenchido!")
    @JsonProperty("tipoEmpresa")
    private String corporateType;

    @Size(max = 100, message = "Limite máximo de caracteres ultrapassado")
    @NotNull(message = "Categoria de empresa precisa ser preenchida!")
    @JsonProperty("categoria")
    private String category;

    @Size(max = 100, message = "Limite máximo de caracteres ultrapassado")
    @NotNull(message = "ID Tipo de empresa precisa ser preenchido!")
    @JsonProperty("idTipoEmpresa")
    private String corporateTypeId;

    public CompanyDTO(Company company) {
        this.cnpj = company.getCnpj();
        this.corporateName = company.getCorporateName();
        this.legalNature = company.getLegalNature();
        this.corporateType = company.getCorporateType();
        this.category = company.getCategory();
        this.corporateTypeId = company.getCorporateTypeId();
    }

    public CompanyDTO(CNPJResponseDTO responseDTO) {
        this.cnpj = responseDTO.getCnpj();
        this.corporateName = responseDTO.getCorporateName();
        this.corporateType = responseDTO.getCorporateType();
        this.legalNature = responseDTO.getLegalNature();
        this.corporateTypeId = responseDTO.getCorporateTypeId();
        this.category = responseDTO.getCategory();

    }
}
