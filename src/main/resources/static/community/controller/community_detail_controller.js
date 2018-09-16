$(document).ready(function(){

    var user_id=localStorage.getItem("user_id");
    var user_name="";
    var user_url="";

    $.ajax({
        type : 'POST',
        url : '/C_User/info',
        data:{userID:user_id},
        async:false,
        success:function(data){
            $("#user_photo").css("background","url('"+data.url+"')");
            $("#user_name").text(data.nickname);
            user_name=data.nickname;
            user_url=data.url;
        },
        error:function(data){
            alert("fail");
        }
    });

    var post_id=localStorage.getItem("post_detail_id");
    var author_id="";
    var remarks=[];
    $.ajax({
        type : 'POST',
        url : '/post/readArticle',
        data:{post_id:post_id,reader:user_id},
        async:false,
        success:function(data){
            author_id=data.author;
            $("#post_name").text(data.post_name);
            for(var i=0;i<data.post_tag.length;i++){
                var new_tag="<a rel=\"tag\" class=\"tag\">"+data.post_tag[i]+"</a>&nbsp;";
                $("#tags_list").append(new_tag);
            }
            $("#post_content").html(data.content);
            $("#reply_num").text(data.remark_num);
            $("#see_num").text(data.visits);
            $("#publish_time").text(data.publish_time);
            remarks=data.remark_content;
        },
        error:function(data){
            alert("fail");
        }
    });

    for(var j=0;j<remarks.length;j++){
        var temp_user_url="";
        var temp_user_name="";
        $.ajax({
            type : 'POST',
            url : '/C_User/info',
            data:{userID:remarks[j].reviewer},
            async:false,
            success:function(data){
                temp_user_name=data.nickname;
                temp_user_url=data.url;
            },
            error:function(data){
                alert("fail");
            }
        });
        var new_remark="<li class=\" cmt-selected\">\n" +
            "                        <div class=\"fn-flex\">\n" +
            "                            <div>\n" +
            "                                <a rel=\"nofollow\">\n" +
            "                                    <div class=\"avatar tooltipped__user user_photo\" aria-name=\"Vanessa\" style='background: url('"+temp_user_url+"')'>\n" +
            "                                    </div>\n" +
            "                                </a>\n" +
            "                            </div>\n" +
            "                            <div class=\"fn-flex-1\">\n" +
            "                                <div class=\"comment-get-comment list\">\n" +
            "                                </div>\n" +
            "                                <div class=\"fn-clear comment-info\">\n" +
            "\t\t\t\t\t\t\t\t<span class=\"fn-left ft-smaller\">\n" +
            "\t\t\t\t\t\t\t\t<a rel=\"nofollow\" class=\"ft-a-title tooltipped__user\">"+temp_user_name+"</a>\n" +
            "\t\t\t\t\t\t\t\t<span class=\"ft-fade\">•\n" +
            "\t\t\t\t\t\t\t\t<time class=\"tooltipped tooltipped__n\">"+remarks[j].remark_time+"</time>\n" +
            "\t\t\t\t\t\t\t\t</span>\n" +
            "\t\t\t\t\t\t\t\t<span class=\"articleCommentThankCnt\">\n" +
            "\t\t\t\t\t\t\t\t</span>\n" +
            "\t\t\t\t\t\t\t\t<span class=\"cmt-via ft-fade hover-show fn-hidden\"></span>\n" +
            "\t\t\t\t\t\t\t\t</span>\n" +
            "                                    <span class=\"fn-right\">\n" +
            "\t\t\t\t\t\t\t\t</span>\n" +
            "                                </div>\n" +
            "                                <div class=\"content-reset comment\">\n" +
            "                                    <p>"+remarks[j].remark_content+"</p>\n" +
            "                                </div>\n" +
            "                            </div>\n" +
            "                        </div>\n" +
            "                    </li>";
        $("#remark_list").append(new_remark);
    }

    $("#articleCommentSubmitBtn").click(function(){
        var temp_content=$(".ql-editor").html();
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
        var new_remark="<li id=\"my_comment\" class=\" cmt-selected\">\n" +
            "                        <div class=\"fn-flex\">\n" +
            "                            <div>\n" +
            "                                <a rel=\"nofollow\">\n" +
            "                                    <div class=\"avatar tooltipped__user user_photo\" aria-name=\"Vanessa\" style='background: url('"+user_url+"')'>\n" +
            "                                    </div>\n" +
            "                                </a>\n" +
            "                            </div>\n" +
            "                            <div class=\"fn-flex-1\">\n" +
            "                                <div class=\"comment-get-comment list\">\n" +
            "                                </div>\n" +
            "                                <div class=\"fn-clear comment-info\">\n" +
            "\t\t\t\t\t\t\t\t<span class=\"fn-left ft-smaller\">\n" +
            "\t\t\t\t\t\t\t\t<a rel=\"nofollow\" class=\"ft-a-title tooltipped__user\">"+"czf"+"</a>\n" +
            "\t\t\t\t\t\t\t\t<span class=\"ft-fade\">•\n" +
            "\t\t\t\t\t\t\t\t<time class=\"tooltipped tooltipped__n\">"+currentdate+"</time>\n" +
            "\t\t\t\t\t\t\t\t</span>\n" +
            "\t\t\t\t\t\t\t\t<span class=\"articleCommentThankCnt\">\n" +
            "\t\t\t\t\t\t\t\t</span>\n" +
            "\t\t\t\t\t\t\t\t<span class=\"cmt-via ft-fade hover-show fn-hidden\"></span>\n" +
            "\t\t\t\t\t\t\t\t</span>\n" +
            "                                    <span class=\"fn-right\">\n" +
            "\t\t\t\t\t\t\t\t</span>\n" +
            "                                </div>\n" +
            "                                <div class=\"content-reset comment\">"+temp_content+"</div>\n" +
            "                            </div>\n" +
            "                        </div>\n" +
            "                    </li>";
        $("#remark_list").append(new_remark);
        // $("#my_comment").find(".comment").html(temp_content);
        $(".editor-content").slideToggle("slow");
        $(".editor-bg").hide();
        $.ajax({
            type : 'POST',
            url : '/post/remark',
            data:{post_id:post_id,reviewer:user_id,remark_content:temp_content},
            async:false,
            success:function(data){

            },
            error:function(data){
                alert("fail");
            }
        });
    });

    //搜索
    $("#search").click(function(){
        var search_content=document.getElementById("algoliaSearch").value;
        localStorage.setItem("search_content",search_content);
        window.location.href="community_search";
    });

});

//跳转至已经发布
function goto_already_publish(){
    localStorage.setItem("user_detail_id",localStorage.getItem("user_id"));
    window.location.href="community_person";
}