package com.example.amaniprojectwebservicewithandroid;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Customer extends Activity{
	private EditText SSN;
	private EditText Bank_Branch_id;
	private EditText CAddress;
	private EditText CName;
	private EditText phone;
	private Button Insert;
	    private static String SOAP_ACTION = "http://tempuri.org/customer";
	    private static String NAMESPACE = "http://tempuri.org/";
	    private static String METHOD_NAME = "customer";
	    
	                               //
	    private static String URL = "http://10.0.2.2:801/WebService1.asmx";
	    private String wrong="";
	    protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.customer);
		
		
		
		SSN=(EditText)findViewById(R.id.SSN);
		Bank_Branch_id=(EditText)findViewById(R.id.BranchBank);
		CAddress=(EditText)findViewById(R.id.CAddress);
		phone=(EditText)findViewById(R.id.phone);
		CName=(EditText)findViewById(R.id.CName);
		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
		
		
		Insert=(Button)findViewById(R.id.cuinsert);
		Insert.setOnClickListener(new View.OnClickListener() 
         {
            @Override
            public void onClick(View arg0) 
         {
                 final SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);             
                 request.addProperty("SSN",SSN.getText().toString()); 
                 request.addProperty("Bank_Branch_id",Bank_Branch_id.getText().toString()); 
                 request.addProperty("Phone",phone.getText().toString()); 
                 request.addProperty("Name",CName.getText().toString()); 
                 request.addProperty("Addresss",CAddress.getText().toString());                  
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
