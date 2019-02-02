package costimator;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import static costimator.sqliteJDBC.dbname;
import java.io.FileOutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.util.Calendar;
import java.util.Date;
import java.text.DateFormatSymbols;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;




/**
 *
 * @author Jobair_Joty
 */
public class FXMLDocumentController implements Initializable {
    
    Date date = new Date();
    Calendar cal = Calendar.getInstance();
    
    @FXML
    private TextField costType;
    @FXML
    private TextField costAmount;
    @FXML
    private TextField incomeType;
    @FXML
    private TextField incomeAmount;
    
    @FXML
    private Tab cid;
    
    @FXML
    private TableView<CostModel> costTable;

    @FXML
    private TableColumn<CostModel, Integer> c_count;

    @FXML
    private TableColumn<CostModel, String> c_costType;

    @FXML
    private TableColumn<CostModel, String> c_costAmount;

    @FXML
    private TableColumn<CostModel, String> c_date;
    @FXML
    private Label dateHeader;
    
    @FXML
    private TabPane tabpane;
    
    
    
    @FXML
    void addCost(ActionEvent event) {
        sqliteJDBC dataBase = new sqliteJDBC();
        String dname = "costimator.db";
        String tableName = "costManager";
        dataBase.createTable(dname,tableName,"costType","costAmount");  
        
            String costType = this.costType.getText();
            String cAmount = this.costAmount.getText();
            int costAmount = Integer.parseInt(cAmount);
		cal.setTime(date);
		int year = cal.get(Calendar.YEAR);
		int m = cal.get(Calendar.MONTH);
                String month = getMonth(m);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		
		String ddate = day +" "+month+","+year;
		
                dataBase.costinsert(costType, costAmount,ddate);
                
                System.out.println(day +" "+month+","+year);
                this.costType.setText("");
                this.costAmount.setText("");
                cost.clear();
                costrefresh();
                totalCost();
                DailyExpense();
                remainBalence();
        
        
		
                
                
        
    }
   
    
    public static String getMonth(int month) {
	    return new DateFormatSymbols().getMonths()[month];
    }
    
   
    
    
   ObservableList<CostModel> cost = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cost.clear();
        costrefresh();
        changeHead();
        income.clear();
        incomeRefresh();
        totalCost();
        totalIncome();
        remainBalence();
        DailyIncome();
        DailyExpense();
        
        //
    }    
    
    public void costrefresh() {
        cost.clear();
        Connection connection  = sqliteJDBC.conn();
        try {
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM costManager");
            while (rs.next()) {
               cost.add(new CostModel(rs.getInt("id"), rs.getString("costType"), rs.getString("costAmount"), rs.getString("date")));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        c_count.setCellValueFactory(new PropertyValueFactory<>("id"));
        c_costType.setCellValueFactory(new PropertyValueFactory<>("costType"));
        c_costAmount.setCellValueFactory(new PropertyValueFactory<>("costAmount"));
        c_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        costTable.setItems(cost);
        //totalCost();
    }
    
    public void changeHead() {
        
        cal.setTime(date);
	int y = cal.get(Calendar.YEAR);
	int m = cal.get(Calendar.MONTH);
        String month = getMonth(m);
	int day = cal.get(Calendar.DAY_OF_MONTH);
        
	String dddate = day +" "+month+","+y;
        
        dateHeader.setText(dddate);
    }
    
    @FXML
    void gotocostTab(ActionEvent event) {
            tabpane.getSelectionModel().select(1);
    }
    
    @FXML
    void goToIncomeTab(ActionEvent event) {
        
        tabpane.getSelectionModel().select(2);
        int a = tabpane.getSelectionModel().getSelectedIndex();
        System.out.println(a);
    }
    
    
    
    
    @FXML
    void addincome(ActionEvent event) {
      sqliteJDBC dataBase = new sqliteJDBC();
        String dname = "costimator.db";
        String tableName = "incomeManager";
        dataBase.createTable(dname,tableName,"incomeType","incomeAmount");  
        
        String incomeType = this.incomeType.getText();
        String iAmount = this.incomeAmount.getText();
        int incomeAmount = Integer.parseInt(iAmount);
		cal.setTime(date);
		int year = cal.get(Calendar.YEAR);
		int m = cal.get(Calendar.MONTH);
                String month = getMonth(m);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		
		String ddate = day +" "+month+","+year;
		
        dataBase.incomeinsert(incomeType, incomeAmount,ddate);
		
                System.out.println(day +" "+month+","+year);
                this.incomeType.setText("");
                this.incomeAmount.setText("");
                income.clear();
                incomeRefresh(); 
                totalIncome();
                DailyIncome();
                remainBalence();
                
    }
    
    
    @FXML
    private TableView<IncomeModel> incomeTable;

    @FXML
    private TableColumn<IncomeModel, Integer> i_count;

    @FXML
    private TableColumn<IncomeModel, String> i_incomeType;

    @FXML
    private TableColumn<IncomeModel, String> i_incomeAmount;

    @FXML
    private TableColumn<IncomeModel, String> i_date;
    
    ObservableList<IncomeModel> income = FXCollections.observableArrayList();
    
    public void incomeRefresh() {
        totalIncome();
        income.clear();
        Connection connection  = sqliteJDBC.conn();
        try {
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM incomeManager");
            while (rs.next()) {
               income.add(new IncomeModel(rs.getInt("id"), rs.getString("incomeType"), rs.getString("incomeAmount"), rs.getString("date")));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        i_count.setCellValueFactory(new PropertyValueFactory<>("id"));
        i_incomeType.setCellValueFactory(new PropertyValueFactory<>("incomeType"));
        i_incomeAmount.setCellValueFactory(new PropertyValueFactory<>("incomeAmount"));
        i_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        incomeTable.setItems(income);
        
    }
    
    @FXML
    private Label total_income_display;
    static int incomeTotal;
    public void totalIncome() {
      String sql = "SELECT SUM(incomeAmount) FROM incomeManager;";
       try (Connection conn = sqliteJDBC.conn()){
            Statement stml = conn.createStatement();
            ResultSet rs = stml.executeQuery(sql);
            while(rs.next()) {
                incomeTotal = rs.getInt(1);
            }
            String a = String.valueOf(incomeTotal);
            total_income_display.setText(a);

           System.out.println(incomeTotal);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    @FXML
    private Label totalCostAmount;
    static int costAmountValue = 0;
    public void totalCost() {
       String sql = "SELECT SUM(costAmount) FROM costManager;";
       try (Connection conn = sqliteJDBC.conn()){
            Statement stml = conn.createStatement();
            ResultSet rs = stml.executeQuery(sql);
            
            while(rs.next()) {
                costAmountValue = rs.getInt(1);
            }
            String a = String.valueOf(costAmountValue);
            totalCostAmount.setText(a);

           System.out.println(costAmountValue);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
       
    }
    
    @FXML
    private Label remainBalence;
    public void remainBalence() {
        int value = incomeTotal-costAmountValue;
        String stringValue = String.valueOf(value);
        remainBalence.setText(stringValue);
    }
    
    
    @FXML
    private Label dailyIncome;

    @FXML
    private Label dailyExpense;
    
    public void DailyIncome() {
        cal.setTime(date);
		int year = cal.get(Calendar.YEAR);
		int m = cal.get(Calendar.MONTH);
                String month = getMonth(m);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		
		String ddate = day +" "+month+","+year;
                System.out.println(ddate);
        String sql = "SELECT SUM(incomeAmount) FROM incomeManager WHERE date ='"+ddate+"'";
       try (Connection conn = sqliteJDBC.conn()){
            Statement stml = conn.createStatement();
            ResultSet rs = stml.executeQuery(sql);
            int dincome = 0;
            while(rs.next()) {
                dincome = rs.getInt(1);
            }
            String a = String.valueOf(dincome);
            dailyIncome.setText(a);

           System.out.println(dailyIncome);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    
    public void DailyExpense() {
        cal.setTime(date);
		int year = cal.get(Calendar.YEAR);
		int m = cal.get(Calendar.MONTH);
                String month = getMonth(m);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		
		String ddate = day +" "+month+","+year;
                System.out.println(ddate);
        String sql = "SELECT SUM(costAmount) FROM costManager WHERE date ='"+ddate+"'";
       try (Connection conn = sqliteJDBC.conn()){
            Statement stml = conn.createStatement();
            ResultSet rs = stml.executeQuery(sql);
            int dcost = 0;
            while(rs.next()) {
                dcost = rs.getInt(1);
            }
            String a = String.valueOf(dcost);
            dailyExpense.setText(a);

           System.out.println(dailyIncome);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    
@FXML
private Label CostErrorMessage;

@FXML
private Label incomeErrorMessage;
    


    @FXML
    void generatePdf(ActionEvent event) {
        try (Connection conn = sqliteJDBC.conn()){
            Statement stml = conn.createStatement();
            String sql = "SELECT * FROM incomeManager;";
            ResultSet queryset = stml.executeQuery(sql);
            
            Document my_pdf_report = new Document();
                PdfWriter.getInstance(my_pdf_report, new FileOutputStream("COSTIMATOR.pdf"));
                my_pdf_report.open();            
                //we have four columns in our table
                PdfPTable my_report_table = new PdfPTable(4);
                PdfPTable cost_report_table = new PdfPTable(4);
                //create a cell object
                PdfPCell table_cell;
                Paragraph Header = new Paragraph("Costimator");
                Header.setAlignment(Element.ALIGN_CENTER);
                my_pdf_report.add(Header);
                my_pdf_report.add(new Paragraph(" "));
                my_pdf_report.add(new Paragraph(" "));
                my_pdf_report.add(new Paragraph(" "));
                
                Paragraph incomeP = new Paragraph("Income");
                incomeP.setAlignment(Element.ALIGN_CENTER);
                my_pdf_report.add(incomeP);
                my_pdf_report.add(new Paragraph(" "));
                
                
                while (queryset.next()) {                
                                String id = queryset.getString("id");
                                table_cell=new PdfPCell(new Phrase(id));
                                my_report_table.addCell(table_cell);
                                
                                
                                String incomeType=queryset.getString("incomeType");
                                table_cell=new PdfPCell(new Phrase(incomeType));
                                my_report_table.addCell(table_cell);
                                
                                String amount=queryset.getString("incomeAmount");
                                table_cell=new PdfPCell(new Phrase(amount));
                                my_report_table.addCell(table_cell);
                                
                                String date=queryset.getString("date");
                                table_cell=new PdfPCell(new Phrase(date));
                                my_report_table.addCell(table_cell);
                }
                my_pdf_report.add(my_report_table);
                my_pdf_report.add(new Paragraph(" "));
                my_pdf_report.add(new Paragraph(" "));
                my_pdf_report.add(new Paragraph(" "));
                String sqlquery = "SELECT * FROM costManager;";
                ResultSet query = stml.executeQuery(sqlquery);
                
                
                Paragraph costP = new Paragraph("Cost");
                costP.setAlignment(Element.ALIGN_CENTER);
                my_pdf_report.add(costP);
                my_pdf_report.add(new Paragraph(" "));
                while (query.next()) {                
                                String id = query.getString("id");
                                table_cell=new PdfPCell(new Phrase(id));
                                cost_report_table.addCell(table_cell);
                                
                                
                                String incomeType=query.getString("costType");
                                table_cell=new PdfPCell(new Phrase(incomeType));
                                cost_report_table.addCell(table_cell);
                                
                                String amount=query.getString("costAmount");
                                table_cell=new PdfPCell(new Phrase(amount));
                                cost_report_table.addCell(table_cell);
                                
                                String date=query.getString("date");
                                table_cell=new PdfPCell(new Phrase(date));
                                cost_report_table.addCell(table_cell);
                }
                /* Attach report table to PDF */
                 
                my_pdf_report.add(cost_report_table); 
                my_pdf_report.close();
                
                /* Close all DB related objects */
                queryset.close();
                //stmt.close(); 
                conn.close();  
                
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    
    

}
