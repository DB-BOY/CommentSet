package cn.dbboy.commentlib.base;

public enum ItemType {

    ScaleGradual("缩放渐变", 0), HeadPile("头像堆叠", 5), Wechat("微信朋友圈", 4), Toutiao("头条点赞", 3), HeartFlow("飘心", 2),;

    private int value;
    private String name;

    ItemType(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public int value() {
        return this.value;
    }

    @Override
    public String toString() {
        return name;
    }
}