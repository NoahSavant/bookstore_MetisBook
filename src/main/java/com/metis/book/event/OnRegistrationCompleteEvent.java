package com.metis.book.event;

import java.util.Locale;

import org.springframework.context.ApplicationEvent;

import com.metis.book.model.user.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OnRegistrationCompleteEvent extends ApplicationEvent {

	private static final long serialVersionUID = 1L;
	private final String appUrl;
    private final Locale locale;
    private final User user;

    public OnRegistrationCompleteEvent(final User user, final Locale locale, final String appUrl) {
        super(user);
        this.user = user;
        this.locale = locale;
        this.appUrl = appUrl;
        log.info("In OnRegistrationCompleteEvent");
    }

    public String getAppUrl() {
        return appUrl;
    }

    public Locale getLocale() {
        return locale;
    }

    public User getUser() {
        return user;
    }


}
