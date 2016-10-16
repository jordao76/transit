package transit.domain.converters

import java.time.*

import javax.persistence.*


@Converter(autoApply=true)
class ZoneIdConverter implements AttributeConverter<ZoneId, String> {

  @Override
  String convertToDatabaseColumn(ZoneId attribute) {
    attribute?.toString()
  }

  @Override
  ZoneId convertToEntityAttribute(String dbData) {
    dbData ? ZoneId.of(dbData) : null
  }

}
