package rbt.redblacktree;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity{
    private Tree tree;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tree=new Tree();
        Button sort=(Button)findViewById(R.id.sort);
        Button delete=(Button)findViewById(R.id.delete);
        sort.setOnClickListener(new sortListener());
        delete.setOnClickListener(new deleteListener());
        TextView text=(TextView)findViewById(R.id.result);
        text.setMovementMethod(new ScrollingMovementMethod());
    }

    public class sortListener implements View.OnClickListener {
        @Override
        public void onClick(View v){
            PowerManager pm = (PowerManager)getSystemService(Context.POWER_SERVICE);
            PowerManager.WakeLock wl=pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,"Tag");
            wl.acquire();
            EditText oneNumber=(EditText)findViewById(R.id.oneNumber);
            TextView text=(TextView)findViewById(R.id.result);
            try {
                double n=Double.parseDouble(oneNumber.getText().toString());
                tree.insert(n);
                String result=tree.printall();
                text.setText(result);
                oneNumber.setText("");
                wl.release();
           }catch (NumberFormatException e){
                Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
           }
        }
    }

    public class deleteListener implements View.OnClickListener{
        @Override
        public void onClick(View v){
            PowerManager pm = (PowerManager)getSystemService(Context.POWER_SERVICE);
            PowerManager.WakeLock wl=pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,"Tag");
            wl.acquire();
            EditText oneNumber=(EditText)findViewById(R.id.oneNumber);
            TextView text=(TextView)findViewById(R.id.result);
            try {
                double n=Double.parseDouble(oneNumber.getText().toString());
                tree.delete(n);
                String result=tree.printall();
                text.setText(result);
                oneNumber.setText("");
                wl.release();
            }catch (NumberFormatException e){
                Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
            }
        }
    }
}
