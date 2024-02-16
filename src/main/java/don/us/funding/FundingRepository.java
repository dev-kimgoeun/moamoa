package don.us.funding;

import java.util.List;
import java.sql.Date;
import java.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FundingRepository extends JpaRepository<FundingEntity, Integer>{

	
	@Query(value = "SELECT *"
			+ " FROM funding"
			+ " WHERE state = 0" //나중에 1로 바꿔줘야함!!!
			+ " AND NOW() <= funding_due_date"
			+ " AND monthly_payment_date = DATE_FORMAT(NOW(),'%d')"
			, nativeQuery = true)
	public List<FundingEntity> needPayFundList();


	List<FundingEntity> findBystartmemberno(int startmemberno);
	
		
	@Query("SELECT f FROM FundingEntity f WHERE f.startmemberno = :start_member_no AND f.fundingduedate > :currentDate")
	List<FundingEntity> findBystartmembernoAndfundingduedate(int start_member_no, LocalDate currentDate);
	
	@Query(value = "SELECT *"
			+ " FROM funding"
			+ " WHERE state = 1"
			+ " AND NOW() > funding_due_date"
			, nativeQuery = true)
	public List<FundingEntity> getFundingDueList();

}
