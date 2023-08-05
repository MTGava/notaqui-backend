package com.fiap.bytesquad.notaqui.model;

import com.fiap.bytesquad.notaqui.model.dto.BillDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_CONTAS")
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_conta")
    private Integer id;

    @Column(name = "cnpj")
    private String cnpj;

    @Column(name = "valor")
    private BigDecimal value;

    @Column(name = "ch_acesso")
    private Integer accessKey;

    public Bill(BillDTO billDTO) {
        this.cnpj = billDTO.getCnpj();
        this.value = billDTO.getValue();
        this.accessKey = billDTO.getAccessKey();
    }
}
