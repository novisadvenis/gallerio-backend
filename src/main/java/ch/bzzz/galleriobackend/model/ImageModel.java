package ch.bzzz.galleriobackend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;

/**
 * Entity Class for Image
 */
@Entity
@Table(name = "image")
public class ImageModel {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "image", columnDefinition = "longblob")
    @JsonProperty("image")
    private byte[] imageByte;
    @Column(name = "type")
    private String type;
    @Column(name = "thumbnail", columnDefinition = "mediumblob")
    private byte[] thumbnail;

    @OneToMany(targetEntity = MetaDataModel.class, cascade = CascadeType.ALL)
    @JsonProperty("metadata")
    private List<MetaDataModel> metaDataList;

    public ImageModel(Long id, String name, byte[] imageByte, String type, byte[] thumbnail, List<MetaDataModel> metaDataList) {
        this.id = id;
        this.name = name;
        this.imageByte = imageByte;
        this.type = type;
        this.thumbnail = thumbnail;
        this.metaDataList = metaDataList;
    }

    public ImageModel(String name, byte[] imageByte, String type, byte[] thumbnail, List<MetaDataModel> metaDataList) {
        this.name = name;
        this.imageByte = imageByte;
        this.type = type;
        this.thumbnail = thumbnail;
        this.metaDataList = metaDataList;
    }

    public ImageModel(String name, byte[] imageByte, String type, List<MetaDataModel> metaDataList) {
        this.name = name;
        this.imageByte = imageByte;
        this.type = type;
        this.metaDataList = metaDataList;
    }

    public ImageModel() {
        super();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getImageByte() {
        return imageByte;
    }

    public void setImageByte(byte[] imageByte) {
        this.imageByte = imageByte;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<MetaDataModel> getMetaDataList() {
        return metaDataList;
    }

    public void setMetaDataList(List<MetaDataModel> metaDataList) {
        this.metaDataList = metaDataList;
    }

    public byte[] getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(byte[] thumbnail) {
        this.thumbnail = thumbnail;
    }
}
