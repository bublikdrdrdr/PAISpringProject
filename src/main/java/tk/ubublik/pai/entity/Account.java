package tk.ubublik.pai.entity;

import lombok.Data;
import tk.ubublik.pai.utility.AccountUtils;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "accounts")
@Data
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn
    private User owner;

    @Column(length = 50)
    private String name;

    @Column
    private Date created;

    @Column
    private boolean blocked;

    @Column
    private boolean deleted;

    @OneToMany(mappedBy = "sender")
    private List<Transaction> sentTransactions;

    @OneToMany(mappedBy = "receiver")
    private List<Transaction> receivedTransactions;

    public String getAccountNumber(){
        return id==null?null:AccountUtils.idToAccountNumber(id);
    }

}
