package com.calculator.Home_loan_email_calc;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.css.PseudoClass;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

public class EmiCalculatorController implements Initializable {
	
	@FXML
	private Slider amountId;
	
	@FXML
	private Slider noOfYears;
	
	@FXML
	private TextField rateOfInterest;
	
	@FXML
	private TextField monthlyEmi;
	
	@FXML
	private Label setMoneyLabel;
	
	@FXML
	private Label setTenure;
	
	@FXML
	private Label roiLabel;
	
	private BigDecimal emiPerMonth = new BigDecimal(String.format("%.2f", 0.0));
	
	private BigDecimal calculateMonthlyEmi() {
		Integer P = 1_00_000 * (int) Math.round(amountId.getValue());
		Double R = 0.0;
		
		try {
			if( rateOfInterest.getText() == "" || rateOfInterest.getText() == null) R = 0.0;			
			R = (double) Math.round(Double.parseDouble(rateOfInterest.getText()));
		} catch (NumberFormatException ee) {			
			
		}
		
		Integer N = (int) Math.round(noOfYears.getValue());
		
		R = (R/100)/12;
		N = N * 12;
		
		return new BigDecimal(P * R * Math.pow(1 + R, N) / (Math.pow(1 + R, N) - 1));
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		rateOfInterest.setPrefColumnCount(4);
		amountId.valueProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
				setMoneyLabel.setText(new BigDecimal(Math.round(amountId.getValue())) + " Lakhs");
				emiPerMonth = calculateMonthlyEmi();
				monthlyEmi.setText("Rs. " + emiPerMonth.intValue() + "/");
			}
		});
		
		noOfYears.valueProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
				setTenure.setText(new BigDecimal(Math.round(noOfYears.getValue())) + " Years");
				emiPerMonth = calculateMonthlyEmi();
				monthlyEmi.setText("Rs. " + emiPerMonth.intValue() + "/");
				
			}
		});
		
		rateOfInterest.textProperty().addListener(new ChangeListener<String>() {
			
			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				if( rateOfInterest.getText().matches("[a-zA-Z]+") || rateOfInterest.getText().matches("[ ]+") ) roiLabel.setText("Invalid rate");
				else if( rateOfInterest.getText().isEmpty() ) roiLabel.setText("0.0%");
				else roiLabel.setText(rateOfInterest.getText().trim() + "%");
				emiPerMonth = calculateMonthlyEmi();
				monthlyEmi.setText("Rs. " + emiPerMonth.intValue() + "/");
				
			}
		});
		
	}

}