package costimator;

/**
 *
 * @author Jobair_Joty
 */
public class CostModel {
    Integer id;
    String costType;
    String costAmount;
    String date;

    public CostModel(Integer id, String costType, String costAmount, String date) {
        this.id = id;
        this.costType = costType;
        this.costAmount = costAmount;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCostType() {
        return costType;
    }

    public void setCostType(String costType) {
        this.costType = costType;
    }

    public String getCostAmount() {
        return costAmount;
    }

    public void setCostAmount(String costAmount) {
        this.costAmount = costAmount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    
}
