package transit.domain

import javax.persistence.*


@Entity
class Route {
  @Id String id;
  @ManyToOne Agency agency;
  String shortName;
  String longName;
  String desc;
  String type;
  String url;
  String color;
  String textColor;
}
