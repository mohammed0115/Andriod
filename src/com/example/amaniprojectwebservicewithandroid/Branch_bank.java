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

public class Branch_bank extends Activity {
	 private static String SOAP_ACTION = "http://tempuri.org/Insertbank_branch";
	    private static String NAMESPACE = "http://tempuri.org/";
	    private static String METHOD_NAME = "Insertbank_branch";
	    private static String URL = "http://10.0.2.2:801/WebService1.asmx";
		private EditText Code;
		private EditText Bank_Branch_id;
		private EditText Address;
		private Button Ok;
        private String wrong="";
	    @Override
	    protected void onCreate(Bundle savedInstanceState) 
	    {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.branch_bank);
			Code=(EditText)findViewById(R.id.code);
		    Bank_Branch_id=(EditText)findViewById(R.id.Bank_Branch_id);
			Address=(EditText)findViewById(R.id.bAddress);
			StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
			
			
			
			Ok=(Button)findViewById(R.id.insert);
			Ok.setOnClickListener(new View.OnClickListener() 
	         {
	            @Override
	            public void onClick(View arg0) 
	         {
	                 final SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);             
	                 request.addProperty("Bank_Branch_id",Bank_Branch_id.getText().toString()); 
	                 request.addProperty("Address",Address.getText().toString()); 
	                 request.addProperty("code",Code.getText().toString()); 
	                 
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
	                         catch (Exception e)  {
	                        	 wrong=e.getMessage();   
	                                 return false;}}
	                    @Override
	                    protected void onPostExecute(Boolean result) {
	                     //here you can do your UI job

							//here you can do your UI job
	                        if (!result)
	                        	Toast.makeText(getBaseContext(),wrong,Toast.LENGTH_LONG).show();
	                        else 
	       					Toast.makeText(getBaseContext(),obj.toString(),Toast.LENGTH_LONG).show();
	                        super.onPostExecute(result);
	                    }
	                }.execute();
	         }
	        });

	       } 
}
