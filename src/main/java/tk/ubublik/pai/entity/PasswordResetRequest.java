package tk.ubublik.pai.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "password_reset_requests")
@Data
public class PasswordResetRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne(optional = false)
	@JoinColumn
	private User user;

	@Column(nullable = false)
	private Date date;

	@Column(nullable = false)
	private String hash;
}
