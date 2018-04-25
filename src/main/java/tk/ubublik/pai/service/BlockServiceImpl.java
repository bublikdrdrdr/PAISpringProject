package tk.ubublik.pai.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import tk.ubublik.pai.dto.BlockDTO;
import tk.ubublik.pai.dto.BlockSearchDTO;
import tk.ubublik.pai.entity.Block;
import tk.ubublik.pai.entity.User;
import tk.ubublik.pai.repository.BlockRepository;
import tk.ubublik.pai.repository.UserRepository;
import tk.ubublik.pai.specification.BlockSpecifications;
import tk.ubublik.pai.validation.Errors;

import java.util.Date;
import java.util.List;

@Service
public class BlockServiceImpl implements BlockService{

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BlockRepository blockRepository;

	@Autowired
	private SecurityService securityService;

	@Override
	public boolean isUserBlocked(Long id) {
		User user = userRepository.getOne(id);
		return blockRepository.isUserBlocked(user, new Date());
	}

	@Override
	public BlockDTO getCurrentBlock() {
		User user = securityService.getAuthenticatedUser();
		List<Block> list = blockRepository.findAll(BlockSpecifications.dateSpecification(user, new Date()));
		if (list.size()==0) return null;
		return new BlockDTO(list.get(0));
	}

	@Override
	public Errors addBlock(BlockDTO blockDTO) {
		return null;
	}

	@Override
	public Errors updateBlock(BlockDTO blockDTO) {
		return null;
	}

	@Override
	public Page<BlockDTO> search(BlockSearchDTO searchDTO) {
		return null;
	}
}
