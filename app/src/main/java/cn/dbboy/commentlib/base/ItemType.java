package cn.dbboy.commentlib.base;

public enum ItemType {

    ScaleGradual("缩放渐变", 0), Wechat("微信朋友圈", 3), HeadPile("头像堆叠", 4), Toutiao("头条点赞", 5), HeartFlow("视频飘心", 6),;

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