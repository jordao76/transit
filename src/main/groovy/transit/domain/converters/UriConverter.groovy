package transit.domain.converters

import javax.persistence.*

@Converter(autoApply=true)
class UriConverter implements AttributeConverter<URI, String> {

  @Override
  String convertToDatabaseColumn(URI attribute) {
    attribute?.toString()
  }

  @Override
  URI convertToEntityAttribute(String dbData) {
    dbData ? URI.create(dbData) : null
  }

}
