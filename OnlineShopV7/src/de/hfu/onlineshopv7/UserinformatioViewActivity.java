package de.hfu.onlineshopv7;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.xmlpull.v1.XmlSerializer;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.util.Xml;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;


public class UserinformatioViewActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinformatio_view);
        
        final EditText nameEditText = (EditText) findViewById(R.id.nameField);
        final EditText eMailEditText = (EditText) findViewById(R.id.emailField);
        final EditText postalCodeEditText = (EditText) findViewById(R.id.postalCodeField);
        final EditText telephoneEditText = (EditText) findViewById(R.id.telephoneField);
        
        
        
        ImageButton HFULink;
        HFULink = (ImageButton) findViewById(R.id.linkHFU);
        HFULink.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent browserIntent = new Intent( Intent.ACTION_VIEW, Uri.parse("http://www.hs-furtwangen.de"));
				startActivity(browserIntent);
				
			}
		});
        Button reset;
        reset = (Button) findViewById(R.id.reset);
        reset.setOnClickListener(new OnClickListener() {
			
        	
			@Override
			public void onClick(View v) {
				nameEditText.setText("");
				eMailEditText.setText("");
				postalCodeEditText.setText("");
				telephoneEditText.setText("");
			}
		});
        
        
        Button submit;
        submit = (Button) findViewById(R.id.submit);
  
        
        submit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				try {
					
					File newxmlfile = new File("/storage/sdcard0/external_sd/" + nameEditText.getText().toString() +".xml");
					try {
					    newxmlfile.createNewFile();
					} catch (IOException e) {
					     Log.e("IOException", "Exception in create new File(");
					}
					
					FileOutputStream fos = null;
					try{
						fos = new FileOutputStream(newxmlfile);

					} catch(FileNotFoundException e) {
					Log.e("FileNotFoundException",e.toString());
					}
					
					//getApplicationContext().openFileOutput(newxmlfile);
					
					XmlSerializer xmls = Xml.newSerializer();
					//StringWriter sw = new StringWriter();
					
					xmls.setOutput(fos,"UTF-8");
					
					// Start xml document
					xmls.startDocument("UTF-8", true);
					xmls.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);
					  
					// Open tag
					xmls.startTag(null, "user-data");
					// Values
					xmls.startTag("", "name");
					xmls.text(nameEditText.getText().toString());
					xmls.endTag("", "name");
					xmls.startTag("", "email");
					xmls.text(eMailEditText.getText().toString());
					xmls.endTag("", "email");
					xmls.startTag("", "postal-code");
					xmls.text(postalCodeEditText.getText().toString());
					xmls.endTag("", "postal-code");
					xmls.startTag("", "telephone");
					xmls.text(telephoneEditText.getText().toString());
					xmls.endTag("", "telephone");
					// End tag
					xmls.endTag(null, "user-data");
					xmls.endDocument();
					xmls.flush();
					fos.close();
					
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
        
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.userinformatio_view, menu);
        return true;
    }
    
}
