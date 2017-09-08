var clearBtn = function () {
  $("input#date").val("");
  getBalance();
};

var getBalance = function () {
  var url = "/manage/account/balanceWithDate";

  $.ajax({
    type: "GET",
    url: url,
    data: $("#balanceForm").serialize(), // serializes the form's elements.
    success: function (data) {
      $("#bal-cur").text(data.currentBalance.amount);

      if (data.balanceByDate) {
        $("#bal-by-date").text("баланс на " + data.balanceByDate.date + ": "
            + data.balanceByDate.amount);

      } else {
        $("#bal-by-date").text("");
      }
    }
  });
};

var getPayments = function () {
  var url = "/manage/paymentsWithDate";

  $.ajax({
    type: "GET",
    url: url,
    data: $("#paymentsForm").serialize(), // serializes the form's elements.
    success: function (json) {
      var data = [];

      for (var i = 0; i < json.payments.length; i++) {
        data.push(json.payments[i]);
      }
      datatableApi.clear().rows.add(data).draw();
    }
  });
};

var cancelButtonRender = function (data, type, row) {
  return '<button type="button" onclick="cancelOrder(' + row.id + ')">Отменить</button>'
};

var cancelOrder = function (paymentId) {
  var url = "/manage/payments/cancel/";

  $.ajax({
    type: "PUT",
    url: url + paymentId,
    success: function() {
      getPayments();
    }
  });
};