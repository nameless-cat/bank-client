var datatableApi;

$(document).ready(function () {
  datatableApi = $("#paymentsTable").DataTable({
    "ajax": {
      "url": "/manage/paymentsWithDate",

      "dataSrc": ""
    },

    "paging": false,

    "info": true,

    "columns": [
      {
        "data": "num"
      },
      {
        "data": "date"
      },
      {
        "data": "senderAccount.num"
      },
      {
        "data": "recipientLegalEntity.shortName"
      },
      {
        "data": "recipientAccount.num"
      },
      {
        "data": "contract.name"
      },
      {
        "data": "currencyCode"
      },
      {
        "data": "orderAmount"
      },
      {
        "data": "reason",
        "defaultContent": ""
      },
      {
        "data": "priorityCode"
      },
      {
        "data": "status.name"
      },
      {
        "data": "rejectReason",
        "defaultContent": ""
      },
      {
        "defaultContent": "",
        "render": cancelButtonRender,
        "orderable": false
      }
    ],
    "order": [
      [
        0,
        "desc"
      ]
    ]
  });

  $("button#submit").click(function () {
    getPayments();
  })
});