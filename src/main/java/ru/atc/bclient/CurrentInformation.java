package ru.atc.bclient;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import ru.atc.bclient.model.entities.dim.LegalEntity;
import ru.atc.bclient.model.entities.dim.User;

@Component
@SessionScope
public class CurrentInformation {
    private User user;
    private LegalEntity legalEntity;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LegalEntity getLegalEntity() {
        return legalEntity;
    }

    public void setLegalEntity(LegalEntity legalEntity) {
        this.legalEntity = legalEntity;
    }
}
