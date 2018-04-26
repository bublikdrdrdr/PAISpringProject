package tk.ubublik.pai.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import tk.ubublik.pai.dto.BlockDTO;
import tk.ubublik.pai.dto.BlockSearchDTO;
import tk.ubublik.pai.entity.Block;
import tk.ubublik.pai.entity.Role;
import tk.ubublik.pai.entity.User;
import tk.ubublik.pai.repository.BlockRepository;
import tk.ubublik.pai.repository.UserRepository;
import tk.ubublik.pai.specification.BlockSpecifications;
import tk.ubublik.pai.validation.Errors;
import tk.ubublik.pai.validation.Validator;

import java.util.Date;
import java.util.List;

@Service
public class BlockServiceImpl implements BlockService{

	private UserRepository userRepository;
	private BlockRepository blockRepository;
	private SecurityService securityService;

	@Autowired
	public BlockServiceImpl(UserRepository userRepository, BlockRepository blockRepository, SecurityService securityService) {
		this.userRepository = userRepository;
		this.blockRepository = blockRepository;
		this.securityService = securityService;
	}

	@Override
	public boolean isUserBlocked(Long id) {
		User user = userRepository.getOne(id);
		return blockRepository.isUserBlocked(user, new Date());
	}

	@Override
	@PreAuthorize("hasRole('USER')")
	public BlockDTO getCurrentBlock() {
		User user = securityService.getAuthenticatedUser();
		List<Block> list = blockRepository.findAll(BlockSpecifications.dateSpecification(user, new Date()));
		if (list.size()==0) return null;
		return new BlockDTO(list.get(0));
	}

	@Override
	@PreAuthorize("hasRole('MODERATOR')")
	public Errors addBlock(BlockDTO blockDTO) {
		Errors errors = Errors.emptyInstance();
		User user = userRepository.getOne(blockDTO.userId);
		User moderator = securityService.getAuthenticatedUser();
		checkEndDate(blockDTO.end, errors);
		Block block = new Block(new Date(), blockDTO.end, user, moderator, blockDTO.reason, false);
		if (errors.isEmpty()) blockRepository.save(block);
		return errors;
	}

	@Override
	@PreAuthorize("hasRole('MODERATOR')")
	public Errors updateBlock(BlockDTO blockDTO) {
		Block block = blockRepository.getFetched(blockDTO.id);
		Errors errors = Errors.emptyInstance();
		if (!block.getAdmin().getId().equals(securityService.getAuthenticatedUser().getId())
				&& !securityService.hasRole(Role.ADMIN)) throw new AccessDeniedException("Admin authorities required");
		checkEndDate(blockDTO.end, errors);
		if (blockDTO.end!=null) block.setEnd(blockDTO.end);
		if (blockDTO.reason!=null) block.setReason(blockDTO.reason);
		if (blockDTO.canceled!=null) block.setCanceled(blockDTO.canceled);
		if (errors.isEmpty()) blockRepository.save(block);
		return errors;
	}

	@Override
	@PreAuthorize("hasRole('MODERATOR')")
	public Page<BlockDTO> search(BlockSearchDTO searchDTO) {
		User user = null, admin = null;
		Date now = null;
		if (searchDTO.userId!=null) user = userRepository.getOne(searchDTO.userId);
		if (searchDTO.adminId!=null) admin = userRepository.getOne(searchDTO.adminId);
		if (searchDTO.active!=null && searchDTO.active) now = new Date();
		return blockRepository.findAll(BlockSpecifications.userAdminSpecification(user, admin, now),
				searchDTO.getPageable()).map(BlockDTO::new);
	}

	private boolean checkEndDate(Date date, Errors errors){
		if (date==null) {
			errors.add(Validator.ValidationHelper.required("end"));
			return false;
		} else if (date.before(new Date())) {
			errors.add(Validator.ValidationHelper.wrap("end", "range"));
			return false;
		}
		return true;
	}
}
