package transit.domain

import javax.persistence.*

@Entity class Route {
  @Id String id;
  @ManyToOne Agency agency;
  String shortName;
  String longName;
  String desc;
  int type;
  URI url;
  int color;
  int textColor;
}
