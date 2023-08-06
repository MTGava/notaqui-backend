package com.fiap.bytesquad.notaqui.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_EMPRESAS")
public class Companies {

    @Id
    @Column(name = "cnpj")
    private String cnpj;

    @Column(name = "razao")
    private String corporateName;

    @Column(name = "nat_juridica")
    private Integer legalNature;

//    public Companies(BillDTO billDTO) {
//        this.cnpj = billDTO.getCnpj();
//        this.value = billDTO.getValue();
//        this.accessKey = billDTO.getAccessKey();
//    }
}
