package tk.ubublik.pai.dto;

public abstract class ConvertibleDTO<E> {

	public ConvertibleDTO() {}

	public ConvertibleDTO(E entity){
		throw new UnsupportedOperationException("You forgot to implement this");
	}

	abstract E toEntity();
}
