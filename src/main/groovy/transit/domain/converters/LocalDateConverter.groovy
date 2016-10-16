package transit.domain.converters

import java.sql.*
import java.time.*

import javax.persistence.*

@Converter(autoApply=true)
class LocalDateConverter implements AttributeConverter<LocalDate, Date> {

  @Override
  Date convertToDatabaseColumn(LocalDate attribute) {
    attribute ? Date.valueOf(attribute) : null
  }

  @Override
  LocalDate convertToEntityAttribute(Date dbData) {
    dbData?.toLocalDate()
  }

}
