$(document).ready(function () {
    var myDate=new Date();
    var date=myDate.getFullYear()+"/"+(myDate.getMonth()+1)+"/"+myDate.getDate();
    var time=myDate.getHours()+":"+myDate.getMinutes();
    $("#date").text(date);
    $("#time").text(time);
    $("#year").text(myDate.getFullYear());
    $("#month").text(myDate.getMonth()+1);
    $("#day").text(myDate.getDate());
    var name=localStorage.getItem("patent_name");
    var result=localStorage.getItem("result");
    $("#title").text(name+"评估报告");
    $("#value").text(result);
    $(".current_back").click(function () {
        window.location.href="/ipnet/assessment";
    });
});