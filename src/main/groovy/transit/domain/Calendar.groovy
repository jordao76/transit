package transit.domain

import java.time.*

import javax.persistence.*

import org.springframework.data.repository.*

import org.springframework.stereotype.Repository

@Entity class Calendar {
  @Id @Column(name="service_id") String id;
  boolean monday;
  boolean tuesday;
  boolean wednesday;
  boolean thursday;
  boolean friday;
  boolean saturday;
  boolean sunday;
  LocalDate startDate;
  LocalDate endDate;
}

@Repository
interface CalendarRepository extends PagingAndSortingRepository<Calendar, String> {
}
