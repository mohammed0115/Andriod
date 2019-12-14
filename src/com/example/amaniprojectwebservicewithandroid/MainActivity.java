package com.example.amaniprojectwebservicewithandroid;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
Button customer;
Button loan;
Button bank;
Button bank_branch;
Button accoun;
Button customer_account;
Button customer_loan;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		bank=(Button)findViewById(R.id.Bank);
		bank.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent i=new Intent(MainActivity.this,Bank.class);
				startActivity(i);
				
			}});
		
		
		bank_branch=(Button)findViewById(R.id.branch);
		bank_branch.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent i=new Intent(MainActivity.this,Branch_bank.class);
				startActivity(i);
				
			}});
		accoun=(Button)findViewById(R.id.account);
		accoun.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent i=new Intent(MainActivity.this,account.class);
				startActivity(i);
				
			}});
		customer=(Button)findViewById(R.id.customer);
		customer.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent i=new Intent(MainActivity.this,Customer.class);
				startActivity(i);
				
			}});
		loan=(Button)findViewById(R.id.loan);
		loan.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent i=new Intent(MainActivity.this,Loan.class);
				startActivity(i);
				
				
			}});

		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
