package transit.domain

import javax.persistence.*


@Entity
class Route {
  @Id String id
  @ManyToOne Agency agency
  String shortName
  String longName
  String desc
  String type // TODO: enum?
  String url
  String color
  String textColor
  @OneToMany @JoinColumn(name="route_id") List<Trip> trips
}
