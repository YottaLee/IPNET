$(document).ready(function(){
    //userid
    var user_id=localStorage.getItem("user_id");

    $.ajax({
        type: 'GET',
        url: '/Patent/getPatentList',
        data: {userId: user_id},
        async: false,
        success: function (data) {
            for(var i=0;i<data.length;i++){
                var new_tr="<tr data-id='"+data[i].patent_id+"' data-name='"+data[i].patent_name+"'>\n" +
                    "                            <td class=\"my_td number\" style=\"font-size: 15px\">"+(i+1)+"</td>\n" +
                    "                            <td class=\"my_td id\" style=\"font-size: 15px\">"+data[i].patent_id+"</td>\n" +
                    "                            <td class=\"my_td name\" style=\"font-size: 15px\">"+data[i].patent_name+"</td>\n" +
                    "                            <td class=\"my_td\" style=\"font-size: 15px\">专利权有效</td>\n" +
                    "                            <td class=\"my_td view_history\" style=\"font-size: 15px;cursor: pointer\"><i\n" +
                    "                                    class=\"fas fa-eye\"></i>&nbsp;点击查看\n" +
                    "                            </td>\n" +
                    "                            <td class=\"my_td generate_report\" style=\"font-size: 15px;cursor: pointer\"><i\n" +
                    "                                    class=\"fas fa-file-alt\"></i>&nbsp;点击生成\n" +
                    "                            </td>\n" +
                    "                        </tr>";
                $("#patent_table_body").append(new_tr);
            }
        },
        error: function (data) {
            console.log("拿取专利失败");
        }
    });
    $(".view_history").click(function(){
        $("#patent_list").hide();
        $("#history").show();
        $("#d7").hide();
        var patent_name=$(this).parent().attr("data-name");
        $("#title").text(patent_name+"历史评估报告");
    });
    $(".generate_report").click(function(){

        var patent_name=$(this).parent().attr("data-name");
        var patent_id=$(this).parent().attr("data-id");
        $("#title").text(patent_name+"评估报告");
        infoFile("即将生成评估报告，请稍候");
        setTimeout(function () {
            $.ajax({
                type: 'GET',
                url: '/evaluation/smartEvaluation',
                data: {patentID: patent_id},
                async: false,
                success:function (data) {
                    // $("#value").text(data);
                    localStorage.setItem("patent_name",patent_name);
                    localStorage.setItem("result",data);
                    window.location.href="/ipnet/assessment_result";
                },
                error:function (data) {
                    console.log(data);
                }
            });
        },1000);
        // $("#patent_list").hide();
        // $("#current").show();
        // $("#d7").hide();
    });
    $(".history_back").click(function () {
        $("#history").hide();
        $("#patent_list").show();
        $("#d7").show();
        $("#title").text("个人专利列表查看");
    });
    // $(".current_back").click(function () {
    //     $("#current").hide();
    //     $("#patent_list").show();
    //     $("#d7").show();
    //     $("#title").text("个人专利列表查看");
    // });
});