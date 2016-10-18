package transit.domain

import javax.persistence.*

import org.springframework.data.repository.*
import org.springframework.stereotype.Repository

@Entity
class Agency {
  @Id String id
  String name
  String url
  String timezone
  String lang
  String phone
  String fareUrl
  String email
  @OneToMany @JoinColumn(name="agency_id") List<Route> routes;
}

@Repository
interface AgencyRepository extends PagingAndSortingRepository<Agency, String> {
}
