package br.com.yacatecuhtli.domain.entry;

import br.com.yacatecuhtli.core.entity.VersionedEntity;
import br.com.yacatecuhtli.domain.account.Account;
import br.com.yacatecuhtli.domain.payment.PaymentType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;
import java.util.Optional;

@Entity
@Table
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Entry extends VersionedEntity<EntryJson> {

    @Getter
    @Id
    @GeneratedValue
    private Integer id;

    @Getter
    @Setter
    @Column(nullable = false)
    private Date issuedAt;

    @Getter
    @Setter
    @Column
    private Date executedAt;

    @Getter
    @Setter
    @Enumerated(value = EnumType.STRING)
    private EntryType type;

    @Getter
    @Setter
    @Column(precision = 10, scale = 2)
    private BigDecimal grossValue;

    @Getter
    @Setter
    @Column(precision = 10, scale = 2)
    private BigDecimal netValue;

    @Getter
    @Setter
    @Column(precision = 10, scale = 2)
    private BigDecimal addition;

    @Getter
    @Setter
    @Column(precision = 10, scale = 2)
    private BigDecimal discount;

    @Getter
    @Setter
    @Column(length = 500)
    private String description;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    private Account account;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private PaymentType paymentType;

    @Override
    public EntryJson toJson() {
        return EntryJson.builder()
                .id(this.id)
                .issuedAt(this.issuedAt)
                .executedAt(this.executedAt)
                .type(this.type)
                .grossValue(this.grossValue)
                .netValue(this.netValue)
                .addition(this.addition)
                .discount(this.discount)
                .description(this.description)
                .account(Optional.ofNullable(this.account).orElseGet(Account::new).toJson())
                .paymentType(Optional.ofNullable(this.paymentType).orElseGet(PaymentType::new).toJson())
                .build();
    }

    @PrePersist
    protected void onCreate() {
        this.issuedAt = Date.from(Instant.now());
    }

}