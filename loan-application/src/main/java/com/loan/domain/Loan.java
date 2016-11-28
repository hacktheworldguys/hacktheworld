package com.loan.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.deser.Deserializers;

import org.eclipse.persistence.oxm.annotations.XmlInverseReference;

import java.math.BigDecimal;

import javax.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(indexes = {
        @Index(name = "idx_amount", columnList = "amount"),
        @Index(name = "idx_term", columnList = "term")})
@EqualsAndHashCode(callSuper = true)
@Data
public class Loan extends BaseEntity {

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private int term;

    @Enumerated(EnumType.STRING)
    private CreditStatus creditStatus;

    @Column(nullable = false)
    private String ipAddress;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    @XmlInverseReference(mappedBy="loan")
    private Customer customer;

    public enum CreditStatus {
        APPROVED,
        REJECTED;

        public boolean isApproved() {
            return APPROVED == this;
        }
    }
}
