package com.fiap.bytesquad.notaqui.model;

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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
    @Column(name = "id_conta", nullable = false)
    private Integer id;

    @Column(name = "cnpj", nullable = false)
    private String cnpj;

    @Column(name = "valor", nullable = false)
    private BigDecimal value;

    @Column(name = "chave", nullable = false)
    private String accessKey;

    @Column(name = "arquivo", nullable = true)
    private String archive;

    @Column(name = "extensao", nullable = true)
    private String extension;

    @ManyToOne
    @JoinColumn(name = "matricula", nullable = false)
    private User user;
}