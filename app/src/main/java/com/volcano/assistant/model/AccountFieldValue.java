package com.volcano.assistant.model;

import com.parse.FindCallback;
import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.volcano.assistant.backend.ParseManager;

import java.util.List;

/**
 * AcountFieldValue that contain one account object, one field object and value
 */
@ParseClassName("AccountFieldValue")
public class AccountFieldValue extends ParseObject {
    private static final String ACCOUNT     = "account";
    private static final String FIELD       = "field";
    private static final String VALUE       = "value";

    public static void findInBackground(Account account, final FindCallback<AccountFieldValue> callback) {
        final ParseQuery<AccountFieldValue> query = getQuery();
        query.whereEqualTo(ACCOUNT, account);
        query.include(FIELD);
        query.findInBackground(new FindCallback<AccountFieldValue>() {
            @Override
            public void done(List<AccountFieldValue> accountFieldValues, ParseException e) {
                callback.done(accountFieldValues, e);
            }
        });
    }

    public static ParseQuery<AccountFieldValue> getQuery() {
        final ParseQuery<AccountFieldValue> query = ParseQuery.getQuery(AccountFieldValue.class);
        if (ParseManager.isLocalDatabaseActive()) {
            query.fromLocalDatastore();
        }

        return query;
    }

    public void save(final SaveCallback callback) {
        final SaveCallback saveCallback = new SaveCallback() {
            @Override
            public void done(ParseException e) {
                callback.done(e);
            }
        };

        if (ParseManager.isLocalDatabaseActive()) {
            pinInBackground(saveCallback);
        }
        else {
            saveInBackground(saveCallback);
        }
    }

    public Account getAccount() {
        return (Account) getParseObject(ACCOUNT);
    }

    public void setAccount(Account account) {
        put(ACCOUNT, account);
    }

    public Field getField() {
        return (Field) getParseObject(FIELD);
    }

    public void setField(Field field) {
        put(FIELD, field);
    }

    public String getValue() {
        return getString(VALUE);
    }

    public void setValue(String value) {
        put(VALUE, value);
    }

}
