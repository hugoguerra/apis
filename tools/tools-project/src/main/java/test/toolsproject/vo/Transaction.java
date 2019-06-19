package test.toolsproject.vo;

public class Transaction {

    private Integer id;
    private String description;

    public Transaction() {}

    public Transaction(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static String getStrTransaction() {
        return "transaction";
    }

    public static Integer getIntTransaction() {
        return Integer.valueOf(10);
    }

    public static Integer sum(Integer num) {
        return num + getIntTransaction();
    }

    public Transaction create(Integer id, String name) {
        return new Transaction(id, name);
    }

    public static void nothingToDo(String message) {
        System.out.println("Nothing to do with " + message);
    }

    public String doTrxOne(String customerId) {
        return "Calling system one by customerId: " + customerId;
    }

    public String doTrxTwo(String otherId) {
        return "Calling system two by otherId: " + otherId;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
