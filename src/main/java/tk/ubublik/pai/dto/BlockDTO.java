package tk.ubublik.pai.dto;

import tk.ubublik.pai.entity.Block;

import java.util.Date;

public class BlockDTO extends ConvertibleDTO<Block> {

	public Long id;
	public Long userId;
	public Date start;
	public Date end;
	public String reason;
	public Boolean canceled;

	public BlockDTO() {
	}

	public BlockDTO(Block entity) {
		//super(entity);
	}

	@Override
	Block toEntity() {
		return null;
	}
}
