package com.lge.keyboard.result;

import java.util.Collection;
import java.util.HashSet;

import com.android.tradefed.config.Option;
import com.android.tradefed.config.Option.Importance;
import com.android.tradefed.config.OptionClass;

@OptionClass(alias = "keyboard-test-result")
public class KeyboardTestResultReporter extends KeyboardTestListener {
    @Option(name = "sender", description = "The envelop-sender address to use for the message", importance = Importance.IF_UNSET)
    private String mSender = null;

    @Option(name = "destination", description = "One or more destination addresses", importance = Importance.IF_UNSET)
    private Collection<String> mDestinations = new HashSet<String>();

    @Option(name = "is_gmail", description = "will you use gmail as mailer?")
    private boolean mIsGmail = true;

    private IEmail mEmail;

    public KeyboardTestResultReporter() {
        mEmail = mIsGmail ? new GMail() : new LgMail();
    }

    public KeyboardTestResultReporter(IEmail email) {
        mEmail = email;
    }

    @Override
    public void invocationEnded(long elapsedTime) {
        super.invocationEnded(elapsedTime);
        // extra behaviors should be declared below
        mEmail.setSender(mSender);
        mEmail.setDestinations(mDestinations);
        mEmail.setSubject("Subject");
        mEmail.setBody("Body");
        mEmail.send();
    }

    @Override
    public void invocationFailed(Throwable cause) {
        super.invocationFailed(cause);
    }
}
