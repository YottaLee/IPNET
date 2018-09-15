$(document).ready(function(){

    var user_id=localStorage.getItem("user_id");

    var pay_order=JSON.parse(localStorage.getItem("pay_order"));
    $("#patent_id").text(pay_order.patentID);
    $("#patent_name").text(pay_order.patent);
    $("#payer").text(pay_order.payer);
    $("#payee").text(pay_order.payee);
    $("#way").text(pay_order.way);
    $("#money").text(pay_order.money);

    var date = new Date();
    var seperator1 = "-";
    var seperator2 = ":";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
        + " " + date.getHours() + seperator2 + date.getMinutes()
        + seperator2 + date.getSeconds();
    $("#time").text(currentdate);

    $.ajax({
        type : 'POST',
        url : '/userInfo/getAllAccountId',
        data:{userId:user_id},
        async:false,
        success:function(data){
            for(var i=0;i<data.length;i++){
                var new_element="<div class=\"cover atvImg pay\" >\n" +
                    "                <div class=\"atvImg-layer\" style='display: none' data-img=\"https://i.imgur.com/R8xnkBw.png\" data-number='"+data[i]+"'></div>\n" +
                    "            </div>";
                $("#pay_card_list").append(new_element);
            }
        },
        error:function(data){

        }
    });

    $.ajax({
        type : 'POST',
        url : '/userInfo/getAllAccountId',
        data:{userId:pay_order.payee},
        async:false,
        success:function(data){
            for(var i=0;i<data.length;i++){
                var new_element="<div class=\"cover atvImg receive\" >\n" +
                    "                <div class=\"atvImg-layer\" style='display: none' data-img=\"https://i.imgur.com/R8xnkBw.png\" data-number='"+data[i]+"'></div>\n" +
                    "            </div>";
                $("#receive_card_list").append(new_element);
            }
        },
        error:function(data){

        }
    });

});