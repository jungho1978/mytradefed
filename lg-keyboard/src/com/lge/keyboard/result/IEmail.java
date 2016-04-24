package com.lge.keyboard.result;

import java.util.Collection;

public interface IEmail {
    public void setSender(String sender);

    public void setDestinations(Collection<String> destinations);

    public void setSubject(String subject);

    public void setBody(String body);

    public void send();
}
