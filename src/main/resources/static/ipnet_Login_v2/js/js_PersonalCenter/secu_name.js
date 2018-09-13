/*$(document).ready(function () {
    var inputname = document.getElementsByName('s_info');
    $(inputname).ftext();
});*/

function inputName() {
    var str = "<form action=\"\" id=\"form_info_s\" class=\"form2\">" +
        "<div class=\"col-xs-12\">" +
        "<input type=\"text\" name=\"s_info\" data-animation=\"clean-slide\" id=\"name\" data-label=\"用户名\" data-label-bg=\"#82cff0\" data-label-color=\"#fff\" data-mask=\"\"/>" +
        "</div>" +
        "<div class=\"col-xs-12\">" +
        "<button value=\"取消\" class=\"btn-balance\">取消</button>" +
        "<button value=\"确定\" class=\"btn-balance\">确定</button>" +
        "</div>" + "</form>";

    document.getElementById("secu_input").innerHTML = str;
}
/*inputName();*/