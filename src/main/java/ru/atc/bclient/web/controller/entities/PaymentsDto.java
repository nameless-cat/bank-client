package ru.atc.bclient.web.controller.entities;

import com.fasterxml.jackson.annotation.JsonView;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import ru.atc.bclient.model.entities.fct.PaymentOrder;
import ru.atc.bclient.web.controller.view.View;

@Getter
@Setter
public class PaymentsDto {
  @JsonView(View.PaymentsWithDate.class)
  private List<PaymentOrder> payments;
}
