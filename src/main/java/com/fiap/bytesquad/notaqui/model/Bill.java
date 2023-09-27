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
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;

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

    @Column(name = "titulo", nullable = false)
    private String title;

    @Column(name = "valor", nullable = false)
    private BigDecimal value;

    @Column(name = "dt_registro", nullable = false)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "matricula", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "cnpj", nullable = false)
    private Company company;
}
