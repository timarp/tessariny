package pl.ula.tessariny;

import android.animation.Animator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public int idEdit = 0;
    public String focus = "";
    public HashMap<String, EditText> editCtl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        //this.getCurrentFocus()

        editCtl = new HashMap<>();

        editCtl.put("i1", (EditText)findViewById(R.id.i_val));
        editCtl.put("j1", (EditText)findViewById(R.id.j_val));
        editCtl.put("k1", (EditText)findViewById(R.id.k_val));
        editCtl.put("w1", (EditText)findViewById(R.id.w_val));

        editCtl.put("i2", (EditText)findViewById(R.id.i_val2));
        editCtl.put("j2", (EditText)findViewById(R.id.j_val2));
        editCtl.put("k2", (EditText)findViewById(R.id.k_val2));
        editCtl.put("w2", (EditText)findViewById(R.id.w_val2));

//        editCtl.put("i3", (EditText)findViewById(R.id.i_val3));
//        editCtl.put("j3", (EditText)findViewById(R.id.j_val3));
//        editCtl.put("k3", (EditText)findViewById(R.id.k_val3));

        for(final Map.Entry<String, EditText> entry: editCtl.entrySet())
        {
            entry.getValue().setText("0");

            entry.getValue().setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean b) {
                    if(b)
                    {
                        focus = entry.getKey();
                        idEdit = view.getId();
                    }

                }
            });


        }



//
//        for(int i=0; i<10; i++)
//        {
//           // buttons.put("b0", (Button)findViewById(R.id.b0));
//        }
//


    }


    @Override
    public void onClick(View view) {

        if(idEdit==0)
            return;

        View myView = view;
        int cx = myView.getWidth() / 2;
        int cy = myView.getHeight() / 2;
        float finalRadius = (float) Math.hypot(cx, cy);
        Animator anim = ViewAnimationUtils.createCircularReveal(myView, cx, cy, 0, finalRadius);
        anim.start();


        Button b = (Button)view;
        String val = b.getText().toString();

        EditText ed = (EditText) findViewById(idEdit);
        String ctrl = ed.getText().toString();

        switch(val)
        {
            case "Dodaj":
                operation("+");
                break;
            case "Odejmij":
                operation("-");
                break;
            case "Pomnóż":
                operation("*");
                break;

            case "-":
                if(ctrl.contains("-"))
                {
                    ctrl=ctrl.substring(1);
                    ed.setText(ctrl);
                }
                else
                {
                    ed.setText("-"+ctrl);
                }

                break;

            case ".":
                if(ctrl.contains("."))
                {
                    ctrl=ctrl.substring(0, ctrl.indexOf('.'));
                    ed.setText(ctrl);
                }
                else
                {
                    ed.setText(ctrl+".");
                }

                break;
            case "AC":
                for(final Map.Entry<String, EditText> entry: editCtl.entrySet())
                {
                    entry.getValue().setText("0");
                }
                break;
            case "C":
                ed.setText("0");
                break;

            case "Usuń":
                if(ctrl.length()>1)
                    ed.setText(ctrl.substring(0,ctrl.length()-1));
                else
                    ed.setText("0");
                break;

            default:
                if(ctrl.equals("0"))
                    ed.setText(val);
                else
                    ed.setText(ed.getText()+val);

        }

        ed.setSelection(ed.getText().toString().length());



    }


    public void operation(String typeop)
    {
        double i1, i2, i3, j1, j2, j3, k1, k2, k3, w1, w2;

        try
        {
            for(final Map.Entry<String, EditText> entry: editCtl.entrySet())
            {
                if( entry.getValue().getText().toString().length()<1)
                {
                    entry.getValue().setText("0");
                }
            }


            i1 = Double.parseDouble(editCtl.get("i1").getText().toString());
            i2 = Double.parseDouble(editCtl.get("i2").getText().toString());
            // i3 = Double.parseDouble(editCtl.get("i3").getText().toString());

            j1 = Double.parseDouble(editCtl.get("j1").getText().toString());
            j2 = Double.parseDouble(editCtl.get("j2").getText().toString());
            //  j3 = Double.parseDouble(editCtl.get("j3").getText().toString());

            k1 = Double.parseDouble(editCtl.get("k1").getText().toString());
            k2 = Double.parseDouble(editCtl.get("k2").getText().toString());
            //  k3 = Double.parseDouble(editCtl.get("k3").getText().toString());

            w1 = Double.parseDouble(editCtl.get("w1").getText().toString());
            w2 = Double.parseDouble(editCtl.get("w2").getText().toString());

            double w11=w1, w22=w2, x1=i1, y1=j1, z1=k1, x2=i2, y2=j2, z2=k2;



            switch(typeop)
            {
                case "+":
                    i1 = i1 + i2;
                    j1 = j1 + j2;
                    k1 = k1 + k2;
                    w1 = w1 + w2;
                    break;

                case "-":
                    i1 = i1 - i2;
                    j1 = j1 - j2;
                    k1 = k1 - k2;
                    w1 = w1 - w2;
                    break;

                case "*":


                     i1 = w1*x2 + w2*x1 + y1*z2 + z1*y2;
                     j1 = w1*y2 + w2*y1 - x1*z2 - x2*z1;
                     k1 = (w1*z2) + (w2*z1) + (x2*y1) + (x1*y2);
                     w1 = w1*w2 - x1*x2 - z1*z2 + y1*y2;         // wazna kolejnosc! i ze zmienne "tymczasowe" - inaczej nadpisujemyyy!

//                    w1 = w1 * w2;
//                    i1 = i1 * i2;
//                    j1 = j1 * j2;
//                    k1 = k1 * k2;

                    break;

                default:

                    i1 = 0;
                    j1 = 0;
                    k1 = 0;
                    w1 = 0;

            }

            editCtl.get("k1").setText(String.valueOf(k1));
            editCtl.get("j1").setText(String.valueOf(j1));
            editCtl.get("i1").setText(String.valueOf(i1));
            editCtl.get("w1").setText(String.valueOf(w1));

            View myView =  editCtl.get("k1");
            int cx = myView.getWidth() / 2;
            int cy = myView.getHeight() / 2;
            float finalRadius = (float) Math.hypot(cx, cy);
            ViewAnimationUtils.createCircularReveal( editCtl.get("k1"), cx, cy, 0, finalRadius).start();
            ViewAnimationUtils.createCircularReveal( editCtl.get("j1"), cx, cy, 0, finalRadius).start();
            ViewAnimationUtils.createCircularReveal( editCtl.get("i1"), cx, cy, 0, finalRadius).start();
            ViewAnimationUtils.createCircularReveal( editCtl.get("w1"), cx, cy, 0, finalRadius).start();


            editCtl.get("k2").setText("0");
            editCtl.get("j2").setText("0");
            editCtl.get("i2").setText("0");
            editCtl.get("w2").setText("0");


            ViewAnimationUtils.createCircularReveal( editCtl.get("k2"), cx, cy, 0, finalRadius).setDuration(1000).start();
            ViewAnimationUtils.createCircularReveal( editCtl.get("j2"), cx, cy, 0, finalRadius).setDuration(1000).start();
            ViewAnimationUtils.createCircularReveal( editCtl.get("i2"), cx, cy, 0, finalRadius).setDuration(1000).start();
            ViewAnimationUtils.createCircularReveal( editCtl.get("w2"), cx, cy, 0, finalRadius).setDuration(1000).start();



        }
        catch(Exception e)
        {
            new Toast(getApplicationContext()).setText(e.getMessage());
        }
    }



}
