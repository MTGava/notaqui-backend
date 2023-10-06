package com.fiap.bytesquad.notaqui.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_EMPRESAS")
public class Company {

    @Id
    @Column(name = "cnpj")
    private String cnpj;

    @Column(name = "razao")
    private String corporateName;

    @Column(name = "nat_juridica")
    private String legalNature;

    @Column(name = "tipo_empresa")
    private String corporateType;

    @Column(name = "categoria")
    private String category;

    @Column(name = "id_tipo_empresa")
    private String corporateTypeId;

    @JsonIgnore
    @OneToMany(mappedBy = "company")
    private List<Bill> bills = new ArrayList<>();
}
