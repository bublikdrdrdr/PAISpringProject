package tk.ubublik.pai.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "blocks")
@Data
public class Block {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Date start;

    @Column
    private Date end;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn
    private User admin;

    @Column
    private String reason;

}
