
package com.example.myapplication;
        import androidx.annotation.RequiresApi;
        import androidx.appcompat.app.AppCompatActivity;
        import org.mariuszgromada.math.mxparser.*;
        import android.os.Build;
        import android.os.Bundle;
        import android.text.SpannableStringBuilder;
        import android.view.View;
        import android.widget.EditText;
        import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    private EditText display;


    private double memoryToken = 0;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        display = findViewById(R.id.text);
        display.setShowSoftInputOnFocus(false);
        display.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                if(getString(R.string.display).equals(display.getText().toString()))
                {
                    display.setText("");
                }
            }
        });

    }
    private void updateText(String strAdd)
    {
        String old = display.getText().toString();
        int cursorPos = display.getSelectionStart();
        String leftS = old.substring(0,cursorPos);
        String rightS = old.substring(cursorPos);
        if (getString(R.string.display).equals(display.getText().toString()))
        {
            display.setText(strAdd);
            display.setSelection(cursorPos + 1);
        }
        else{
            display.setText(String.format("%s%s%s", leftS,strAdd,rightS));
            display.setSelection(cursorPos + 1);
        }
        if(display.getText().toString().length() == 10)
        {
            int textLen = display.getText().length();
            String str = display.getText().toString();
            str = str.substring(0, textLen - 1);
            display.setText(str);
            display.setSelection(cursorPos -1);
        }
    }

    public void ZeroC (View view)
    {
        updateText("0");
    }
    public void OneC (View view)
    {
        updateText("1");
    }
    public void TwoC(View view)
    {
        updateText("2");
    }
    public void ThreeC(View view)
    {
        updateText("3");
    }
    public void FourC(View view)
    {
        updateText("4");
    }
    public void FiveC(View view)
    {
        updateText("5");
    }
    public void SixC(View view)
    {
        updateText("6");
    }
    public void SevenC(View view)
    {
        updateText("7");
    }
    public void EightC(View view)
    {
        updateText("8");
    }
    public void NineC(View view)
    {
        updateText("9");
    }
    public void MultiplyC(View view)
    {
        updateText("*");
        int cursorPos = display.getSelectionStart();
        int textLen = display.getText().length();
        String str = display.getText().toString();
        if(str.endsWith("**") ) {
            str = str.substring(0, textLen - 1);
            display.setText(str);
            display.setSelection(cursorPos-1);
        }
    }
    public void SubstractC(View view)
    {
        updateText("-");
        int cursorPos = display.getSelectionStart();
        int textLen = display.getText().length();
        String str = display.getText().toString();
        if(str.endsWith("--") ) {
            str = str.substring(0, textLen - 1);
            display.setText(str);
            display.setSelection(cursorPos-1);
        }
    }
    public void DivideC(View view)
    {
        updateText("/");
        int cursorPos = display.getSelectionStart();
        int textLen = display.getText().length();
        String str = display.getText().toString();
        if(str.endsWith("//") ) {
            str = str.substring(0, textLen - 1);
            display.setText(str);
            display.setSelection(cursorPos-1);
        }
    }
    public void AddC(View view)
    {
        updateText("+");
        int cursorPos = display.getSelectionStart();
        int textLen = display.getText().length();
        String str = display.getText().toString();
        if(str.endsWith("++") ) {
            str = str.substring(0, textLen - 1);
            display.setText(str);
            display.setSelection(cursorPos-1);
        }
    }
    public void ClearC(View view)
    {
        display.setText("");
    }
    public void DoteC(View view)
    {
        updateText(".");
        int cursorPos = display.getSelectionStart();
        int textLen = display.getText().length();
        String str = display.getText().toString();
        if(str.endsWith("..") ) {
            str = str.substring(0, textLen - 1);
            display.setText(str);
            display.setSelection(cursorPos-1);
        }
    }
    public void CosC(View view)
    {
        updateText("cos(");
        int cursorPos = display.getSelectionStart();
        int textLen = display.getText().length();
        String str = display.getText().toString();

        display.setText(str);
        display.setSelection(textLen);
        // Math.cos();
    }
    public void SinC(View view)
    {
        updateText("sin(");
        int cursorPos = display.getSelectionStart();
        int textLen = display.getText().length();
        String str = display.getText().toString();

        display.setText(str);
        display.setSelection(textLen);

    }
    public void TanC(View view)
    {
        updateText("tan(");
        int cursorPos = display.getSelectionStart();
        int textLen = display.getText().length();
        String str = display.getText().toString();

        display.setText(str);
        display.setSelection(textLen);

    }
    public void LogC(View view)
    {
        updateText("log(,");
        int cursorPos = display.getSelectionStart();
        int textLen = display.getText().length();
        String str = display.getText().toString();

        display.setText(str);
        display.setSelection(textLen);

    }

    public void LnC(View view)
    {
        updateText("ln(");
        int cursorPos = display.getSelectionStart();
        int textLen = display.getText().length();
        String str = display.getText().toString();

        display.setText(str);
        display.setSelection(textLen);

    }
    public void FacC(View view)
    {
        updateText("!");
        int cursorPos = display.getSelectionStart();
        int textLen = display.getText().length();
        String str = display.getText().toString();

        display.setText(str);
        display.setSelection(textLen);

    }
    public void StepC(View view)
    {
        updateText("^(-1)");
        int cursorPos = display.getSelectionStart();
        int textLen = display.getText().length();
        String str = display.getText().toString();

        display.setText(str);
        display.setSelection(textLen);

    }
    public void EeeC(View view)
    {
        updateText("e");
        int cursorPos = display.getSelectionStart();
        int textLen = display.getText().length();
        String str = display.getText().toString();

        display.setText(str);
        display.setSelection(textLen);

    }
    public void ProC(View view)
    {
        updateText("%");
        int cursorPos = display.getSelectionStart();
        int textLen = display.getText().length();
        String str = display.getText().toString();

        display.setText(str);
        display.setSelection(textLen);

    }
    public void ParC(View view)
    {
        int cursorPos = display.getSelectionStart();
        int openPar = 0;
        int closePar = 0;
        int textL = display.getText().length();

        for (int i = 0; i< cursorPos;i++)
        {
            if(display.getText().toString().substring(i,i+1).equals("("))
            {
                openPar +=1;
            }
            if(display.getText().toString().substring(i,i+1).equals(")"))
            {
                closePar +=1;
            }
        }
        if(openPar==closePar|| display.getText().toString().substring(textL -1, textL).equals("(")) {
            updateText("(");
        }
        else  if(closePar<openPar && !display.getText().toString().substring(textL -1, textL).equals("(")) {
            updateText(")");

        }
        display.setSelection(cursorPos + 1);
    }
    public void ExpC(View view)
    {
        updateText("^");
    }
    public void EqualC(View view)
    {
        String userExp = display.getText().toString();
        userExp = userExp.replaceAll("÷", "/");
        userExp = userExp.replaceAll("×", "*");
        Expression exp = new Expression(userExp);
        String res = String.valueOf(exp.calculate());
        display.setText(res);
        display.setSelection(res.length());
        //if(updateText("1"))
        //{
        // display.setText("");
        // }
    }
    public void BackspaceC(View view)
    {
        int cursorPos = display.getSelectionStart();
        int textLen = display.getText().length();
        if(cursorPos!= 0 && textLen != 0)
        {
            //SpannableStringBuilder selection = (SpannableStringBuilder) display.getText();
            //selection.replace(cursorPos -1, cursorPos,"");

            String str = display.getText().toString();
            if(str.endsWith("NaN") ||   str.endsWith("ln(")) {
                str = str.substring(0, textLen - 3);
                display.setText(str);
                display.setSelection(cursorPos-3);
            }
            else if (str.endsWith("sin(") || str.endsWith("cos(") || str.endsWith("tan(") )
            {
                str = str.substring(0, textLen - 4);
                display.setText(str);
                display.setSelection(cursorPos-4);
            }
            else if (str.endsWith("log(,") )
            {
                str = str.substring(0, textLen - 5);
                display.setText(str);
                display.setSelection(cursorPos-5);
            }
            else{
                str = str.substring(0, cursorPos - 1) + str.substring(cursorPos, textLen);
                display.setText(str);
                display.setSelection(cursorPos-1);
            }



        }

    }

    public void MemoryReadC(View view) {
        updateText(Double.toString(memoryToken));

        int cursorPos = display.getSelectionStart();
        int textLen = display.getText().length();
        String str = display.getText().toString();
        str = str.substring(0, cursorPos-1) + str.substring(0, textLen);
        display.setText(str);
        display.setSelection(textLen);
    }

    public void MemoryClearC(View view) {
        memoryToken = 0;
    }

    public void MemoryPlusC(View view) {
        String s ="";
        String userExp = display.getText().toString();
        if(isNum(userExp))
        {

            s = userExp;
            memoryToken = Double.parseDouble(s);
        }



    }
    public static boolean isNum (String userExp)
    {
        try {
            double d = Double.parseDouble(userExp);
        } catch (NumberFormatException| NullPointerException nnn){
            return false;
        }
        return true;
    }
    public void MemoryMinusC(View view) {
        memoryToken -= Double.parseDouble(display.getText().toString());
    }
}