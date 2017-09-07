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