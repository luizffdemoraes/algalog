package com.algaworks.algalog.domain.model;

import com.algaworks.algalog.domain.exception.NegocioException;
import com.sun.istack.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Entrega {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Valid
    @NotNull
    @ManyToOne
    private Cliente cliente;

    @Embedded
    private Destinatario destinatario;

    private BigDecimal taxa;

    @OneToMany(mappedBy = "entrega", cascade = CascadeType.ALL)
    private List<Ocorrencia> ocorrenciaModels = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private StatusEntrega status;

    private OffsetDateTime dataPedido;

    private OffsetDateTime dataFinalizacao;

    public Ocorrencia adicionarOcorrencia(String descricao) {
        Ocorrencia ocorrencia = new Ocorrencia();
        ocorrencia.setDescricao(descricao);
        ocorrencia.setDataRegistro(OffsetDateTime.now());
        ocorrencia.setEntrega(this);

        this.getOcorrenciaModels().add(ocorrencia);

        return ocorrencia;
    }

    public boolean podeSerFinalizada() {
        return StatusEntrega.PENDENTE.equals(getStatus());
    }

    public boolean naoPodeSerFinalizada() {
        return !podeSerFinalizada();
    }

    public void finalizar() {
        if (naoPodeSerFinalizada()) {
            throw new NegocioException("Entrega não pode ser finalizada");
        }
        setStatus(StatusEntrega.FINALIZADA);
        setDataFinalizacao(OffsetDateTime.now());
    }
}
