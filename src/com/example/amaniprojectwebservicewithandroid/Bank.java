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

public class Bank extends Activity {
	private EditText Code;
	private EditText Name;
	private EditText Address;
	private Button Ok;
	private static final String  SOAP_ACTION="http://tempuri.org/Insertbank";
	private static final String  METHOD_NAME="Insertbank";
	private static final String  NAMESPACE="http://tempuri.org";
	private static final String  URL="http://10.0.2.2:801/WebService1.asmx";
	private String wrong="";
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bank);
	
		
		Code=(EditText)findViewById(R.id.Code);
		Name=(EditText)findViewById(R.id.Name);
		Address=(EditText)findViewById(R.id.Address);
		Ok=(Button)findViewById(R.id.Ok);
		
		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());	
    	
    	Ok=(Button)findViewById(R.id.Ok);
    	Ok.setOnClickListener(new View.OnClickListener() 
         {
    		
            @Override
            public void onClick(View arg0) 
         {
                SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);             
                request.addProperty("code",Code.getText().toString()); 
                request.addProperty("Name",Name.getText().toString()); 
                request.addProperty("Addresss",Address.getText().toString());                   
                final SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER12);
                envelope.setOutputSoapObject(request);
                envelope.dotNet = true;
                
                
                
                new AsyncTask<Void, Void, Boolean>() {
                    Object obj;
                    

                    @Override
                    protected Boolean doInBackground(Void... params) {
                           //here you can do your background network job
                        try{ HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);   
                        androidHttpTransport.debug=true;
                   
                               androidHttpTransport.call(SOAP_ACTION, envelope);   
                               
                             
                               obj = envelope.bodyIn;

                               return true;  }
                         catch (Exception e)  {
                        	 wrong=e.getMessage();
                        	 //e.printStackTrace();
                                 return false;}}
                    @Override
                    protected void onPostExecute(Boolean result) {
                   
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




