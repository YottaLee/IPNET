$("#submit").on('click', function () {
    //存取申请意向
    var storage = window.localStorage;
    var fileURL = storage.getItem('fileURL');
    $.ajax({
        url: '/evaluation/apply',
        type: 'POST',
        data: {
            patentID: patentID,
            url: fileURL
        },
        success: function () {

            $.ajax({
                url: '/Patent/searchPatentByID',
                type: 'GET',
                data: {
                    patentID: patentID
                },
                success: function (data) {
                    var patent = data.patent_name;
                    var holder = data.patent_holder;
                    var money = 20000;//评估费用为20000
                    //跳入向评估公司申请的支付界面
                    $.ajax({
                        type: "GET",
                        url: "/evaluation/getEvaluationId",
                        success: function (evaluationFirm) {
                            console.log(evaluationFirm);
                            console.log(evaluationFirm.id);
                            console.log(evaluationFirm.name);
                            var transaction = {
                                patentID: patentID,
                                patent: patent,
                                payer_id: data.userId,//付款方
                                payee_id: evaluationFirm.id,//收款方
                                payer: holder,
                                payee: evaluationFirm.name,
                                way: "专利评估",//评估机构只有一个
                                money: money//评估费用
                            };

                            storage.setItem('pay_order', JSON.stringify(transaction));

                            //支付评估费用
                            infoFile("即将跳入支付界面");
                            setTimeout(function () {
                                window.location.href = "/ipnet/pay";
                            }, 2000);
                        },
                        error: function (XMLHttpRequest, textStatus, errorThrown) {
                            console.log(XMLHttpRequest.status + ":" + XMLHttpRequest.statusText);
                        }
                    });

                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    console.log(XMLHttpRequest.status + ":" + XMLHttpRequest.statusText);
                }
            });

        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            console.log(XMLHttpRequest.status + ":" + XMLHttpRequest.statusText);
        }
    });

});

