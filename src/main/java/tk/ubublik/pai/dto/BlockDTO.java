package tk.ubublik.pai.dto;

import tk.ubublik.pai.entity.Block;
import tk.ubublik.pai.entity.User;

import java.util.Date;

public class BlockDTO extends ConvertibleDTO<Block> {

	public Long id;
	public Long userId;
	public Long adminId;
	public Date start;
	public Date end;
	public String reason;
	public Boolean canceled;

	public BlockDTO() {
	}

	public BlockDTO(Long id, Long userId, Long adminId, Date start, Date end, String reason, Boolean canceled) {
		this.id = id;
		this.userId = userId;
		this.start = start;
		this.end = end;
		this.reason = reason;
		this.canceled = canceled;
	}

	public BlockDTO(Block entity) {
		this(entity.getId(), entity.getUser()==null?null:entity.getUser().getId(), entity.getAdmin()==null?null:entity.getAdmin().getId(),
				entity.getStart(), entity.getEnd(), entity.getReason(), entity.isCanceled());
	}

	@Override
	Block toEntity() {
		return new Block(id, start, end, getFromId(userId), getFromId(adminId), reason, canceled==null?false:canceled);
	}
}
