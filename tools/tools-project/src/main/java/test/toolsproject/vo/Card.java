package test.toolsproject.vo;

public class Card {

    private String customerId;
    private String otherId;
    private String proxy;
    private CardStatusEnum status;

    public Card() {}

    public Card(String customerId, String otherId, String proxy, CardStatusEnum status) {
        this.customerId = customerId;
        this.otherId = otherId;
        this.proxy = proxy;
        this.status = status;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getOtherId() {
        return otherId;
    }

    public void setOtherId(String otherId) {
        this.otherId = otherId;
    }

    public String getProxy() {
        return proxy;
    }

    public void setProxy(String proxy) {
        this.proxy = proxy;
    }

    public CardStatusEnum getStatus() {
        return status;
    }

    public void setStatus(CardStatusEnum status) {
        this.status = status;
    }

    public Card getCardByProxy(String proxy){
        return proxy.contains("1")
                    ? new Card("123456", "abcdef", "proxy1", CardStatusEnum.ACTIVE)
                                    : new Card("", "uojvnhs", "proxy2", CardStatusEnum.ACTIVE);
    }

    @Override
    public String toString() {
        return "Card{" +
                "customerId='" + customerId + '\'' +
                ", proxy='" + proxy + '\'' +
                ", status=" + status +
                '}';
    }
}
