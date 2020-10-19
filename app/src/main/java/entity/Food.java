package entity;

public class Food {
    int ID;
    int shopID;
    String name;
    int cateID;
    float price;
    String description;
    int imageID;

    public Food(int ID, int shopID, String name, int cateID, float price, String description, int imageID) {
        this.ID = ID;
        this.shopID = shopID;
        this.name = name;
        this.cateID = cateID;
        this.price = price;
        this.description = description;
        this.imageID = imageID;
    }

    public int getID() {
        return ID;
    }

    public int getShopID() {
        return shopID;
    }

    public String getName() {
        return name;
    }

    public int getCateID() {
        return cateID;
    }

    public float getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public int getImageID() {
        return imageID;
    }

    public static Food getRandomFood(){
        return new Food(1
                ,1
                ,"Fried rice"
                ,1
                ,100000
                ,"This is a food"
                ,1);
    }
}
