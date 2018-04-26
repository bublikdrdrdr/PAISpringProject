package tk.ubublik.pai.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "transactions")
@Data
@NoArgsConstructor
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

    public Transaction(Account sender, Account receiver, Long amount, String message, Date date, TransactionStatus status) {
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
        this.message = message;
        this.date = date;
        this.status = status;
    }
}
