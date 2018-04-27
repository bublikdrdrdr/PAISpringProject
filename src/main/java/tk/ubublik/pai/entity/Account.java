package tk.ubublik.pai.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.ubublik.pai.utility.AccountUtils;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "accounts")
@Data
@NoArgsConstructor
@AllArgsConstructor
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

    public Account(User owner, String name, Date created, boolean blocked, boolean deleted) {
        this.owner = owner;
        this.name = name;
        this.created = created;
        this.blocked = blocked;
        this.deleted = deleted;
    }
}
