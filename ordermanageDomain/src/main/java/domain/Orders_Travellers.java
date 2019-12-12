package domain;

public class Orders_Travellers {
    private String orderId,travellerId;

    @Override
    public String toString() {
        return "Orders_Travellers{" +
                "orderId='" + orderId + '\'' +
                ", travellerId='" + travellerId + '\'' +
                '}';
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getTravellerId() {
        return travellerId;
    }

    public void setTravellerId(String travellerId) {
        this.travellerId = travellerId;
    }
}
