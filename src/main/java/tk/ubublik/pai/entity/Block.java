package tk.ubublik.pai.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "blocks")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Block {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Date start;

    @Column(nullable = false)
    private Date end;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn
    private User admin;

    @Column
    private String reason;

    @Column
    private boolean canceled;

    public Block(Date start, Date end, User user, User admin, String reason, boolean canceled) {
        this.start = start;
        this.end = end;
        this.user = user;
        this.admin = admin;
        this.reason = reason;
        this.canceled = canceled;
    }
}
