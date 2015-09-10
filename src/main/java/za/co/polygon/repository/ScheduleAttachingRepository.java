package za.co.polygon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.polygon.domain.ScheduleAttaching;

@Repository
public interface ScheduleAttachingRepository extends JpaRepository<ScheduleAttaching, Long> {

}
