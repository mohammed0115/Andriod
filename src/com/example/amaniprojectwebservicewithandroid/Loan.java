package com.example.amaniprojectwebservicewithandroid;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Loan extends Activity{
	//Bank_Branch_id
	private EditText LoanNo;
	private EditText Bank_Branch_id;
	private EditText Amount;
	private EditText type;
	private Button OkLoan;
	private static final String  SOAP_ACTION="http://tempuri.org/loan";
	private static final String  METHOD_NAME="loan";
	private static final String  NAMESPACE="http://tempuri.org";
	private static final String  URL="http://10.0.2.2:801/WebService1.asmx";
	private String wrong="";
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loan);
		

		LoanNo=(EditText)findViewById(R.id.LoanNo);
		Bank_Branch_id=(EditText)findViewById(R.id.Bank_Branch_idloan);
		Amount=(EditText)findViewById(R.id.Amoun);
		type=(EditText)findViewById(R.id.type);
		

		OkLoan=(Button)findViewById(R.id.oklaon);
		OkLoan.setOnClickListener(new View.OnClickListener() 
         {
            @Override
            public void onClick(View arg0) 
         {
                 final SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);             
                 request.addProperty("LoanNo",LoanNo.getText().toString()); 
                 request.addProperty("Amount",Amount.getText().toString()); 
                 request.addProperty("type",type.getText().toString()); 
                 request.addProperty("Bank_Branch_id",Bank_Branch_id.getText().toString());                  
                 final SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER12);
                 envelope.setOutputSoapObject(request);
                 envelope.dotNet = true;
                 new AsyncTask<Void, Void, Boolean>() {
                    Object obj;

                    @Override
                    protected Boolean doInBackground(Void... params) {
                           //here you can do your background network job
                        try{ HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);          
                               androidHttpTransport.call(SOAP_ACTION, envelope);             
                               obj = envelope.getResponse();

                               return true;  }
                         catch (Exception e)  {wrong=e.getMessage();   
                                 return false;}}
                    @Override
                    protected void onPostExecute(Boolean result) {
                   
						//here you can do your UI job
                        if (!result)
                        	Toast.makeText(getApplicationContext(),wrong,Toast.LENGTH_SHORT).show();
                        else 
       					Toast.makeText(getApplicationContext(),obj.toString(),Toast.LENGTH_SHORT).show();
                        super.onPostExecute(result);
                    }
                }.execute();
         }
        });

		
		}
}
