package ch.bzzz.galleriobackend.model;

import javax.persistence.*;

/**
 * Entity class for Metadata
 */
@Entity
@Table(name = "metadata")
public class MetaDataModel {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "value", columnDefinition = "text")
    private String value;
    @Column(name = "type")
    private String type;


    public MetaDataModel() {
    }

    public MetaDataModel(Long id, String name, String value, String type) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.type = type;
    }

    public MetaDataModel(String name, String value, String type) {
        this.name = name;
        this.value = value;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
