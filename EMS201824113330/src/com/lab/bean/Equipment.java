package com.lab.bean;

/**
 * @author 201824113330
 * @create 2020-12-04-14:48
 * @description
 */
public class Equipment {
    private String id;
    private String name;
    private String specification;
    private Double price;
    private String buyDate;
    private String position;
    private String imgPath;
    private String manager;

    //private DecimalFormat df = new DecimalFormat("#.00");

    public Equipment() {
    }

    public Equipment(String id, String name, String specification, Double price, String buyDate, String position, String imgPath, String manager) {
        this.id = id;
        this.name = name;
        this.specification = specification;
        this.price = price;
        this.buyDate = buyDate;
        this.position = position;
        this.imgPath = imgPath;
        this.manager = manager;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public Double getPrice() {
        //return Double.valueOf(df.format(price));
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(String buyDate) {
        this.buyDate = buyDate;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    @Override
    public String toString() {
        return "Equipment{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", specification='" + specification + '\'' +
                ", price=" + price +
                ", buyDate=" + buyDate +
                ", position='" + position + '\'' +
                ", imgPath='" + imgPath + '\'' +
                ", manager='" + manager + '\'' +
                '}';
    }
}
