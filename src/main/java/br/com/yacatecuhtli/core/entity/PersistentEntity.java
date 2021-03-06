package br.com.yacatecuhtli.core.entity;

import br.com.yacatecuhtli.core.json.JsonRepresentation;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Access(AccessType.FIELD)
@ToString
@EqualsAndHashCode
public abstract class PersistentEntity<J extends JsonRepresentation> {

    public abstract Integer getId();

    public abstract J toJson();

}
