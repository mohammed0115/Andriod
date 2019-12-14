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

public class account extends Activity {

    private static String SOAP_ACTION = "http://tempuri.org/InsertAccount";
    private static String NAMESPACE = "http://tempuri.org/";
    private static String METHOD_NAME = "InsertAccount";
    private static String URL = "http://10.0.2.2:801/WebService1.asmx";
	private EditText Bank_Branch_id;
	private EditText AccNo;
	private EditText TypeLoan;
	private EditText Balance;
	private Button Ok;
	
	private String wrong ="";
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.account);
		
		AccNo=(EditText)findViewById(R.id.AccNo);
		Bank_Branch_id=(EditText)findViewById(R.id.acBank_Branch_id);
		TypeLoan=(EditText)findViewById(R.id.TypeAcc);
		Balance=(EditText)findViewById(R.id.acBalance);
		
		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
		
		Ok=(Button)findViewById(R.id.AcOK);
		
		Ok.setOnClickListener(new View.OnClickListener() 
         {   
            @Override
            public void onClick(View arg0) 
         {
                 final SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);             
                 request.addProperty("AccNo",AccNo.getText().toString()); 
                 request.addProperty("TypeLoan",TypeLoan.getText().toString()); 
                 request.addProperty("Bank_Branch_id",Bank_Branch_id.getText().toString()); 
                 request.addProperty("Balance",Balance.getText().toString());                    
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

              					//Toast.makeText(getApplicationContext(),obj.toString(),Toast.LENGTH_SHORT).show();
                               return true;  }
                         catch (Exception e)  {wrong=e.getMessage()+"  "+e.getStackTrace();   
                                 return false;}}
                    @Override
                    protected void onPostExecute(Boolean result) {
                     //here you can do your UI job
                    	  if (!result) 
             					Toast.makeText(getApplicationContext(),wrong,Toast.LENGTH_SHORT).show();
                              else 
             					Toast.makeText(getApplicationContext(),obj .toString(),Toast.LENGTH_SHORT).show();
                              super.onPostExecute(result);
                        super.onPostExecute(result);
                    }
                }.execute();
         }
        });
	
	
	
	}
}