package transit.domain

import java.time.*

import javax.persistence.*


@Entity
class Calendar {
  @Id String id;
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
