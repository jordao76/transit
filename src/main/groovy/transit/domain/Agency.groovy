package transit.domain

import java.time.*

import javax.persistence.*

import org.springframework.data.repository.*
import org.springframework.stereotype.Repository


@Entity class Agency {
  @Id String id
  String name
  URI url
  ZoneId timezone
  String lang
  String phone
  URI fareUrl
  String email
  @OneToMany @JoinColumn(name="agency_id") List<Route> routes;
}

@Repository
interface AgencyRepository extends PagingAndSortingRepository<Agency, String> {
}
