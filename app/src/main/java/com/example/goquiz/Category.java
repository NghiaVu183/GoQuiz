package com.example.goquiz;

public class Category {//implements Parcelable {
    private String name;
    private int image;
    private String description;

    public Category(String name, int image, String description) {
        this.name = name;
        this.image = image;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeString(this.name);
//        dest.writeInt(this.image);
//        dest.writeString(this.description);
//    }
//
//    protected CategoriesClass(Parcel in) {
//        this.name = in.readString();
//        this.image = in.readInt();
//        this.description = in.readString();
//    }
//
//    public static final Parcelable.Creator<CategoriesClass> CREATOR = new Parcelable.Creator<CategoriesClass>() {
//        @Override
//        public CategoriesClass createFromParcel(Parcel source) {
//            return new CategoriesClass(source);
//        }
//
//        @Override
//        public CategoriesClass[] newArray(int size) {
//            return new CategoriesClass[size];
//        }
//    };

}
