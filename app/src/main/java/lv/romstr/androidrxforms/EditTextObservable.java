package lv.romstr.androidrxforms;

import android.widget.EditText;

import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.Arrays;
import java.util.List;

import rx.Observable;

/**
 * Created by Roman on 29.11.16..
 */
public class EditTextObservable {

    public static Observable<Boolean> notEmpty(EditText field) {
        return RxTextView
                .textChanges(field)
                .map(input -> input.length() > 0);
    }

    public static Observable<Boolean> atLeastOneOf(Observable<Boolean>... observables) {
        List<Observable<Boolean>> observableList = Arrays.asList(observables);
        return Observable
                .combineLatest(
                        observableList,
                        EditTextObservable::withLogicalOr);
    }

    public static Observable<Boolean> eachOf(Observable<Boolean>... observables) {
        List<Observable<Boolean>> observableList = Arrays.asList(observables);
        return Observable
                .combineLatest(
                        observableList,
                        EditTextObservable::withLogicalAnd);
    }

    private static Boolean withLogicalAnd(Object[] arguments) {
        Boolean result = true;
        for (Object argument : arguments) {
            result = result && (Boolean) argument;
        }
        return result;
    }

    private static Boolean withLogicalOr(Object[] arguments) {
        Boolean result = false;
        for (Object argument : arguments) {
            result = result || (Boolean) argument;
        }
        return result;
    }

}
