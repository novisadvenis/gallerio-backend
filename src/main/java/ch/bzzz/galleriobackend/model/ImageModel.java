package ch.bzzz.galleriobackend.model;

import javax.persistence.*;

@Entity
@Table(name = "image_table")
public class ImageModel {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "picByte")
    private byte[] imageByte;
    @Column(name = "type")
    private String type;

    public ImageModel(String name, byte[] imageByte, String type, ExifModel exifModel) {
        super();
        this.name = name;
        this.imageByte = imageByte;
        this.type = type;
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
}
