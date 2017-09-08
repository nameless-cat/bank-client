package ru.atc.bclient.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.atc.bclient.CurrentInformation;
import ru.atc.bclient.model.entities.dim.LegalEntity;
import ru.atc.bclient.model.entities.fct.AccountBalance;
import ru.atc.bclient.model.service.AccountBalanceService;
import ru.atc.bclient.model.service.AccountService;
import ru.atc.bclient.model.service.LegalEntityService;
import ru.atc.bclient.model.service.PaymentOrderService;

import java.sql.Date;

@Controller
public class LegalEntitiesController {

  @Autowired
  private LegalEntityService legalEntityService;
  @Autowired
  private AccountBalanceService accountBalanceService;
  @Autowired
  private CurrentInformation currentInformation;

  @Autowired
  private PaymentOrderService paymentOrderService;

  @Autowired
  private AccountService accountService;

  @RequestMapping(value = "manage/accounts", method = RequestMethod.GET)
  public ModelAndView accounts(
      @RequestParam(name = "legalEntityId", required = false) Integer legalEntityId) {
    LegalEntity legalEntity;
    if (legalEntityId == null) {
      legalEntity = currentInformation.getLegalEntity();
      legalEntityId = legalEntity.getId();
    } else {
      legalEntity = legalEntityService.getById(legalEntityId);
    }
    ModelAndView modelAndView = new ModelAndView();

    currentInformation.setLegalEntity(legalEntity);
    modelAndView.addObject("accounts", accountService.getAllByLegalEntityId(legalEntityId));
    modelAndView.setViewName("manage/account");
    return modelAndView;
  }

//    @GetMapping(value = "/manage/payments")
//    public ModelAndView payments() {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("legalEntityId", currentInformation.getLegalEntity().getId());
//        modelAndView.setViewName("manage/payments");
//        return modelAndView;
//    }





  @RequestMapping(value = "/manage/show_payments", method = RequestMethod.GET)
  public String toPayments(@RequestParam("legalEntityId") int id) {
    ModelAndView modelAndView = new ModelAndView();
    LegalEntity legalEntity = legalEntityService.getByIdWithAccounts(id);
    currentInformation.setLegalEntity(legalEntity);
    return "redirect:/manage/payments";
  }

    @RequestMapping(value = "/manage/payments", method = RequestMethod.GET)
    public ModelAndView paymentsWithDate(
            @RequestParam(value = "dateBegin", required = false) String dateBeginString,
            @RequestParam(value = "dateEnd", required = false) String dateEndString) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            Date dateBegin = Date.valueOf(dateBeginString);
            Date dateEnd = Date.valueOf(dateEndString);
            modelAndView.addObject("payments", paymentOrderService.getBetweenDateBySenderLegalEntityId(currentInformation.getLegalEntity().getId(), dateBegin, dateEnd));
        }catch (IllegalArgumentException e){

        }
        modelAndView.addObject("legalEntityId", currentInformation.getLegalEntity().getId());
        modelAndView.setViewName("manage/payments");
        return modelAndView;
    }

//    @GetMapping(value = "/manage/account")
//    public ModelAndView accountsGet(ModelAndView modelAndView){
//        return modelAndView;
//    }

  @RequestMapping(value = "/manage/account/balance", method = RequestMethod.GET)
  public ModelAndView getBalance(@RequestParam("accountId") int accountId) {
    ModelAndView modelAndView = new ModelAndView();
    AccountBalance newestByAccountId = accountBalanceService.getNewestByAccountId(accountId);
    modelAndView.addObject("currentBalance", newestByAccountId);
    modelAndView.setViewName("manage/balance");
    return modelAndView;
  }


}
