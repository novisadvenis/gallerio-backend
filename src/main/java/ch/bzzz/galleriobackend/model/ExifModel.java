package ch.bzzz.galleriobackend.model;

public class ExifModel {
    private Integer xResolution;
    private Integer yResolution;
    private String resolutionUnit;
    private String userComment;
    private Integer exifWidth;
    private Integer exifHeight;


    public ExifModel(Integer xResolution, Integer yResolution, String resolutionUnit, String userComment, Integer exifWidth, Integer exifHeight) {
        this.xResolution = xResolution;
        this.yResolution = yResolution;
        this.resolutionUnit = resolutionUnit;
        this.userComment = userComment;
        this.exifWidth = exifWidth;
        this.exifHeight = exifHeight;
    }

    public ExifModel(ExifModel exifModel) {
        this.xResolution = exifModel.getXResolution();
        this.yResolution = exifModel.getYResolution();
        this.resolutionUnit = exifModel.getResolutionUnit();
        this.userComment = exifModel.getUserComment();
        this.exifWidth = exifModel.getExifWidth();
        this.exifHeight = exifModel.getExifHeight();
    }

    public Integer getXResolution() {
        return xResolution;
    }

    public void setXResolution(Integer xResolution) {
        this.xResolution = xResolution;
    }

    public Integer getYResolution() {
        return yResolution;
    }

    public void setYResolution(Integer yResolution) {
        this.yResolution = yResolution;
    }

    public String getResolutionUnit() {
        return resolutionUnit;
    }

    public void setResolutionUnit(String resolutionUnit) {
        this.resolutionUnit = resolutionUnit;
    }

    public String getUserComment() {
        return userComment;
    }

    public void setUserComment(String userComment) {
        this.userComment = userComment;
    }

    public Integer getExifWidth() {
        return exifWidth;
    }

    public void setExifWidth(Integer exifWidth) {
        this.exifWidth = exifWidth;
    }

    public Integer getExifHeight() {
        return exifHeight;
    }

    public void setExifHeight(Integer exifHeight) {
        this.exifHeight = exifHeight;
    }

}
