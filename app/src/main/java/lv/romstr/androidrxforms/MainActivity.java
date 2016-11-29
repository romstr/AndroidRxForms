package lv.romstr.androidrxforms;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jakewharton.rxbinding.internal.Preconditions;
import rx.Subscription;

import static lv.romstr.androidrxforms.EditTextObservable.*;

public class MainActivity extends AppCompatActivity {

    private Subscription subscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.simpleButton);
        EditText editText = (EditText) findViewById(R.id.firstEditText);
        EditText editText1 = (EditText) findViewById(R.id.secondEditText);
        EditText editText2 = (EditText) findViewById(R.id.thirdEditText);
        EditText editText3 = (EditText) findViewById(R.id.fourthEditText);
        EditText editText4 = (EditText) findViewById(R.id.fifthEditText);
        EditText editText5 = (EditText) findViewById(R.id.sixthEditText);
        EditText editText6 = (EditText) findViewById(R.id.seventhEditText);
        EditText editText7 = (EditText) findViewById(R.id.eightsEditText);

        subscription =
                eachOf(
                        notEmpty(editText),
                        notEmpty(editText1),
                        notEmpty(editText2),
                        notEmpty(editText3),
                        atLeastOneOf(
                                notEmpty(editText4),
                                notEmpty(editText5)),
                        atLeastOneOf(
                                notEmpty(editText6),
                                notEmpty(editText7)))
                        .subscribe(button::setEnabled);

        button.setOnClickListener(v ->
                Toast.makeText(this, "Hello, lambdas & Rx", Toast.LENGTH_LONG).show());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Preconditions
                .checkNotNull(subscription, "Subscription was null")
                .unsubscribe();
    }

}
