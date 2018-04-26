package tk.ubublik.pai.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tk.ubublik.pai.entity.Account;
import tk.ubublik.pai.entity.User;

public interface AccountRepository extends JpaRepository<Account, Long>, JpaSpecificationExecutor<Account> {

    @Query(value = "select a from Account a where a.owner = :user",
    countQuery = "select count(a.id) from Account a where a.owner = :user")
    Page<Account> findByUser(@Param("user") User user, Pageable pageable);

    @Query("select a from Account a join fetch a.owner where a.id =  :id")
    Account getFetched(@Param("id") long id);

    @Query("select  a from Account a where a.owner = :user and a.name like :name")
    Account getByName(@Param("user") User user, @Param("name") String name);

    long countAccountByOwner(User owner);
}
