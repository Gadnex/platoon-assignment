package net.binarypaper.platoonassignment.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import lombok.Data;

@javax.persistence.Entity
@Data
public class Cic implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CIC_ID")
    @ApiModelProperty(
            value = "The unique ID of a cic",
            example = "1",
            position = 1
    )
    private Long cicId;

    @Column(name = "CIC_TYPE")
    @ApiModelProperty(
            value = "The type of a cic",
            example = "EMAIL",
            required = true,
            position = 2
    )
    private String cicType;

    @ApiModelProperty(
            value = "The subject of a cic",
            example = "Email Subject",
            position = 3
    )
    private String subject;

    @ApiModelProperty(
            value = "The body of a cic",
            example = "Email Body",
            required = true,
            position = 4
    )
    private String body;

    @Column(name = "SOURCE_SYSTEM")
    @ApiModelProperty(
            value = "The source system sending a cic",
            example = "Source System",
            required = true,
            position = 5
    )
    private String sourceSystem;

    @Column(name = "CIC_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+02:00")
    @ApiModelProperty(
            value = "The timestamp that a cic wa sent",
            example = "2018-01-19 14:22:03",
            position = 6
    )
    private Date cicTimestamp = new Date();

    @ManyToOne(optional = false)
    @JoinColumn(name = "ENTITY_ID", foreignKey = @ForeignKey(name = "FK_CIC_ENTITY"))
    @JsonView(value = {View.Detail.class})
    @ApiModelProperty(
            value = "The entity to which the cic is sent",
            position = 7
    )
    private Entity entity;

    @Transient
    @JsonView(value = {View.Add.class, View.List.class})
    @ApiModelProperty(
            value = "The entity ID of the entity to which the cic is sent",
            example = "1",
            position = 8
    )
    private Long entityId;

    public interface View {

        public interface Add {
        }

        public interface List {
        }

        public interface Detail {
        }
    }

}
