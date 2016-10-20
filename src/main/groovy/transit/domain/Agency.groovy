package transit.domain

import javax.persistence.*


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
  @OneToMany @JoinColumn(name="agency_id") List<Route> routes
}
