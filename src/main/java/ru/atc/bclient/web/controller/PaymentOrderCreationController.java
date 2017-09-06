package ru.atc.bclient.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.atc.bclient.CurrentInformation;
import ru.atc.bclient.model.service.*;
import ru.atc.bclient.web.controller.utils.InvalidOrderException;
import ru.atc.bclient.model.entities.dim.Account;
import ru.atc.bclient.model.entities.dim.Contract;
import ru.atc.bclient.model.entities.dim.LegalEntity;
import ru.atc.bclient.model.entities.fct.PaymentOrder;
import ru.atc.bclient.web.controller.utils.PaymentOrderCreator;


import java.util.List;

@Controller
public class PaymentOrderCreationController {

    @Autowired
    private LegalEntityService legalEntityService;

    @Autowired
    private ContractService contractService;

    @Autowired
    private PaymentOrderCreator paymentOrderCreator;

    @Autowired
    private CurrentInformation currentInformation;

    @Autowired
    private AccountService accountService;

    @Autowired
    private PaymentOrderService paymentOrderService;

    @Autowired
    private PaymentOrderStatusService paymentOrderStatusService;

    @Autowired
    private AccountBalanceService accountBalanceService;


    @GetMapping(value = "/manage/create_payment/new")
    public String accounts() {
        if(!paymentOrderService.isAvailable()){
            return "redirect:/payment_creation_not_available";
        }
        //LegalEntity legalEntity = legalEntityService.getByIdWithAccounts(currentInformation.getUser().getId(), id);
        LegalEntity legalEntity = legalEntityService.getById(currentInformation.getLegalEntity().getId());
        paymentOrderCreator.reset();
        currentInformation.setLegalEntity(legalEntity);
        paymentOrderCreator.setSenderLegalEntity(legalEntity);
        //return createModelAndViewForPaymentOrder();
        return "redirect:/manage/create_payment";
    }



    @RequestMapping(value = "manage/payment_fields/contract", method = RequestMethod.GET)
    public ModelAndView toContracts() {
        ModelAndView modelAndView = new ModelAndView();
        LegalEntity senderLegalEntity = paymentOrderCreator.getSenderLegalEntity();
        modelAndView.addObject("contracts", contractService.getByIssuerId(senderLegalEntity.getId()));
        return modelAndView;
    }



    @RequestMapping(value = "/manage/create_payment/setContract", method = RequestMethod.GET)
    public String setContract(@RequestParam("contractId") int id) {
        Contract contract = contractService.getOne(id);
        paymentOrderCreator.setContract(contract);
        paymentOrderCreator.setRecipientLegalEntity(contract.getSingerLegalEntity());
        paymentOrderCreator.setRecipientAccount(null);
        return "redirect:/manage/create_payment";

    }


    @RequestMapping(value = "manage/payment_fields/senderAccount", method = RequestMethod.GET)
    public ModelAndView toSenderAccounts() {
        ModelAndView modelAndView = new ModelAndView();
        LegalEntity senderLegalEntity = paymentOrderCreator.getSenderLegalEntity();
        List<Account> accounts = accountService.getAllActiveByLegalEntityId(senderLegalEntity.getId());
        modelAndView.addObject("accounts", accounts);
        modelAndView.addObject("service", accountBalanceService);
        modelAndView.addObject("method", "setSenderAccount");
        modelAndView.setViewName("manage/payment_fields/account");
        return modelAndView;
    }

    @RequestMapping(value = "/manage/create_payment/setSenderAccount", method = RequestMethod.GET)
    public String setSenderAccount(@RequestParam("accountId") int id) {
        Account account = accountService.getById(id);
        paymentOrderCreator.setSenderAccount(account);
        return "redirect:/manage/create_payment";
    }

    @RequestMapping(value = "manage/payment_fields/recipientAccount", method = RequestMethod.GET)
    public ModelAndView toRecipientAccounts() {
        ModelAndView modelAndView = new ModelAndView();
        LegalEntity recipientLegalEntity = paymentOrderCreator.getRecipientLegalEntity();
        if (recipientLegalEntity == null) {
            modelAndView.addObject("message", "Необходимо выбрать юр. лицо получателя");
            modelAndView.addObject("link", "/manage/create_payment");
            modelAndView.setViewName("errorPage");
            return modelAndView;
        }
        //recipientLegalEntity = legalEntityService.getById(recipientLegalEntity.getId()); //чтобы получить accounts
        List<Account> accounts = accountService.getAllActiveByLegalEntityId(recipientLegalEntity.getId());
        accounts.remove(paymentOrderCreator.getSenderAccount());
        modelAndView.addObject("accounts", accounts);
        modelAndView.addObject("method", "setRecipientAccount");
        modelAndView.setViewName("manage/payment_fields/account");
        return modelAndView;
    }

    @RequestMapping(value = "/manage/create_payment/setRecipientAccount", method = RequestMethod.GET)
    public String setRecipientAccount(@RequestParam("accountId") int id) {
        Account account = accountService.getById(id);
        paymentOrderCreator.setRecipientAccount(account);
        return "redirect:/manage/create_payment";
    }

    @RequestMapping(value = "manage/payment_fields/recipientLegalEntity", method = RequestMethod.GET)
    public ModelAndView toRecipientLegalEntity() {
        ModelAndView modelAndView = new ModelAndView();
        List<LegalEntity> all = legalEntityService.findAll();
        modelAndView.addObject("legalEntities", all);
        return modelAndView;
    }

    @RequestMapping(value = "/manage/create_payment/setRecipientLegalEntity", method = RequestMethod.GET)
    public String setRecipientLegalEntity(@RequestParam("legalEntityId") int id) {
        paymentOrderCreator.setRecipientLegalEntity(legalEntityService.getByIdWithAccounts(id));
        paymentOrderCreator.setContract(null);
        paymentOrderCreator.setRecipientAccount(null);
        return "redirect:/manage/create_payment";
    }

    @PostMapping(value = "/manage/create_payment/check")
    public ModelAndView checkPayment(@RequestParam(name = "amount") String amount,
                                     @RequestParam(name = "reason") String reason) {
        ModelAndView modelAndView = new ModelAndView();
        paymentOrderCreator.setReason(reason);
        paymentOrderCreator.setOrderAmount(amount);
        PaymentOrder paymentOrder;
        try {
            paymentOrder = paymentOrderCreator.create();
            if (!paymentOrderService.isAvailable()){
                return new ModelAndView("redirect:/payment_creation_not_available");
            }
            paymentOrderService.save(paymentOrder);
            modelAndView = new ModelAndView("redirect:/manage/payments");

        } catch (InvalidOrderException e) {
            modelAndView.addObject("message", e.getMessage());
            modelAndView.addObject("link", "/manage/create_payment");
            modelAndView.setViewName("errorPage");
        }
        return modelAndView;
    }

    @GetMapping(value = "/manage/create_payment")
    private ModelAndView createPayment() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("order", paymentOrderCreator);

        modelAndView.setViewName("manage/create_payment");
        return modelAndView;
    }

    @GetMapping("/payment_creation_not_available")
    public ModelAndView paymentCreationNotAvailable(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", "Создание платежных поручений временно не доступно");
        modelAndView.addObject("link", "/manage/payments");
        modelAndView.setViewName("errorPage");
        return modelAndView;
    }
}
