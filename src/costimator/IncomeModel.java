/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package costimator;

/**
 *
 * @author Jobair_Joty
 */
public class IncomeModel {
    Integer id;
    String incomeType;
    String incomeAmount;
    String date;

    public IncomeModel(Integer id, String incomeType, String incomeAmount, String date) {
        this.id = id;
        this.incomeType = incomeType;
        this.incomeAmount = incomeAmount;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIncomeType() {
        return incomeType;
    }

    public void setIncomeType(String incomeType) {
        this.incomeType = incomeType;
    }

    public String getIncomeAmount() {
        return incomeAmount;
    }

    public void setIncomeAmount(String incomeAmount) {
        this.incomeAmount = incomeAmount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    
}
