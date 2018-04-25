package tk.ubublik.pai.dto;

import tk.ubublik.pai.entity.Block;

public class BlockDTO extends ConvertibleDTO<Block> {

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
