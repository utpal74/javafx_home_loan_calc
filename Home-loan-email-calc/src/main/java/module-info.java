module com.calculator.Home_loan_email_calc {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.calculator.Home_loan_email_calc to javafx.fxml;
    exports com.calculator.Home_loan_email_calc;
}
