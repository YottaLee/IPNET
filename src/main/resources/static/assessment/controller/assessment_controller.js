$(document).ready(function(){
    $(".view_history").click(function(){
        $("#patent_list").hide();
        $("#history").show();
        $("#d7").hide();
        $("#title").text("XXXXX专利历史评估报告");
    });
    $(".generate_report").click(function(){
        $("#patent_list").hide();
        $("#current").show();
        $("#d7").hide();
        $("#title").text("XXXXX专利评估报告");
    });
    $(".history_back").click(function () {
        $("#history").hide();
        $("#patent_list").show();
        $("#d7").show();
        $("#title").text("奈瑞佛国际制药公司专利列表查看");
    });
    $(".current_back").click(function () {
        $("#current").hide();
        $("#patent_list").show();
        $("#d7").show();
        $("#title").text("奈瑞佛国际制药公司专利列表查看");
    })
});