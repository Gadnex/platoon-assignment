package net.binarypaper.platoonassignment.entity;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

@javax.persistence.Entity
@Data
public class Entity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ENTITY_ID")
    @ApiModelProperty(
            value = "The unique identifier of an entity",
            example = "1",
            position = 1
    )
    private Long entityId;

    @Column(name = "ENTITY_NAME")
    @ApiModelProperty(
            value = "The name of an entity",
            example = "Willy Gadney",
            required = true,
            position = 2
    )
    private String entityName;

    @Column(name = "EMAIL_ADDRESS")
    @ApiModelProperty(
            value = "The email address of an entity",
            example = "gadnex@gmail.com",
            required = true,
            position = 3
    )
    private String emailAddress;

}
