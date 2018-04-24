package tk.ubublik.pai.service;

import org.springframework.data.domain.Page;
import tk.ubublik.pai.validation.Errors;
import tk.ubublik.pai.dto.BlockDTO;
import tk.ubublik.pai.dto.BlockSearchDTO;
import tk.ubublik.pai.entity.Block;
import tk.ubublik.pai.entity.User;

public interface BlockService extends SearchableService<BlockDTO, BlockSearchDTO> {

	boolean isUserBlocked(User user);
	BlockDTO getCurrentBlock();
	Errors addBlock(BlockDTO blockDTO);
	Errors updateBlock(BlockDTO blockDTO);
}
