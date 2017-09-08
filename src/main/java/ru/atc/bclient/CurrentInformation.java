package ru.atc.bclient;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import ru.atc.bclient.model.entities.dim.LegalEntity;
import ru.atc.bclient.model.entities.dim.User;

@Component
@SessionScope
@Getter
@Setter
public class CurrentInformation {

  private User user;
  private LegalEntity legalEntity;
}
