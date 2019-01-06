package com.xbiztechventures.lapp.activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.xbiztechventures.lapp.R;
import com.xbiztechventures.lapp.Webservice;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener  {

    Button btnRegister;
    LinearLayout main_layout;
    EditText txtflat, txtlandmark, txtpincode, txtmobile, txtusername, txtemail;
    public static final String PHONE_PATTERN = "^[987]\\d{9}$";
    Webservice webservice ;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
        btnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnRegister :
                boolean result = validateFields(txtmobile.getText().toString().trim(),
                        txtusername.getText().toString().trim(),
                        txtflat.getText().toString().trim(),
                        txtlandmark.getText().toString().trim(),
                        txtpincode.getText().toString().trim(),
                        txtemail.getText().toString().trim());

                if(result){
                    editor.putString("flat",txtflat.getText().toString().trim());
                    editor.putString("landmark",txtlandmark.getText().toString().trim());
                    editor.putString("pincode",txtpincode.getText().toString().trim());
                    editor.putString("username",txtusername.getText().toString().trim());
                    editor.putString("mobile_number",txtmobile.getText().toString().trim());
                    asyncTask();
                }
                break;
            default:
                break;
        }
    }

    void asyncTask(){
        if(isNetworkAvailable()){
            new registerAsync().execute();
        }
        else{
           // showSnackBar();
        }
    }

    private boolean validateFields(String mobile,String username,String flat, String landmark, String pincode,String email){


        if (TextUtils.isEmpty(mobile)) {
            setErrorInputLayout(txtmobile, "Please enter contact no.");
            return false;
        }
        else if (!isValidPhone(mobile)) {
            setErrorInputLayout(txtmobile, "Please enter valid contact number");
            return false;
        }
        else if (TextUtils.isEmpty(username)) {
            setErrorInputLayout(txtusername, "Please enter username");
            return false;
        }
        else if (TextUtils.isEmpty(flat)) {
            setErrorInputLayout(txtflat, "Please enter address");
            return false;
        }
        else if (TextUtils.isEmpty(landmark)) {
            setErrorInputLayout(txtlandmark, "Please enter landmark");
            return false;
        }
        else if (TextUtils.isEmpty(pincode)) {
            setErrorInputLayout(txtpincode, "Please enter pincode");
            return false;
        }
        else if (!isValidPincode(pincode)) {
            setErrorInputLayout(txtpincode, "Please enter valid pincode");
            return false;
        }
        else if (TextUtils.isEmpty(email)) {
            setErrorInputLayout(txtemail, "Please enter email id");
            return false;
        }
        else if (!isValidEmail(email)) {
            setErrorInputLayout(txtemail, "Please enter valid email");
            return false;
        }
        else {
            return true;
        }
    }

    public static void setErrorInputLayout(EditText editText, String msg) {
        editText.setError(msg);
        editText.requestFocus();
    }

    public static boolean isValidEmail(String strEmail) {
        return strEmail != null && android.util.Patterns.EMAIL_ADDRESS.matcher(strEmail).matches();
    }

    public static boolean isValidPhone(String phone) {
        Pattern pattern = Pattern.compile(PHONE_PATTERN);
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }

    public static boolean isValidPincode(String pincode){
        if(pincode.length()==6){
            return  true;
        }
        else{
            return false;
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }



    private void init(){
        webservice = new Webservice();
        main_layout = (LinearLayout) findViewById(R.id.main_layout);
        txtmobile=(EditText)findViewById(R.id.mobile);
        txtusername=(EditText)findViewById(R.id.username);
        txtflat=(EditText)findViewById(R.id.flat);
        txtlandmark=(EditText)findViewById(R.id.landmark);
        txtpincode=(EditText)findViewById(R.id.pincode);
        txtemail=(EditText)findViewById(R.id.email);
        btnRegister=(Button) findViewById(R.id.btnRegister);
        sharedPreferences = getSharedPreferences("USER_INFO",MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }



    public class registerAsync extends AsyncTask<Void,Void,String> {
        ProgressDialog pd ;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(RegisterActivity.this);
            pd.setMessage("Please Wait...");
            pd.show();
        }

        @Override
        protected String doInBackground(Void... voids) {
            String result = webservice.registerUser("register",txtmobile.getText().toString(),txtusername.getText().toString(),txtflat.getText().toString(),txtlandmark.getText().toString(),txtpincode.getText().toString(),txtemail.getText().toString());
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                if(pd.isShowing())
                    pd.dismiss();

                JSONObject object = new JSONObject(s);
                String status = object.get("status").toString();
                if(status.contains("200")){
                    Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(RegisterActivity.this,"Can not register",Toast.LENGTH_SHORT).show();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }


//    private void showSnackBar(){
//        Snackbar snackbar = Snackbar
//                .make(main_layout, "No Internet. Please try again", Snackbar.LENGTH_LONG)
//                .setAction("OK", new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        finish();
//                    }
//                });
//        snackbar.show();
//    }
}
