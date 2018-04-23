package tk.ubublik.pai.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "transactions")
@Data
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Account sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Account receiver;

    @Column
    private Long amount;

    @Column
    private String message;

    @Column
    private Date date;

    @Enumerated(value = EnumType.STRING)
    private TransactionStatus status;
}
