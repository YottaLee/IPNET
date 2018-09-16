$(document).ready(function(){

    var user_id=localStorage.getItem("user_id");

    //关注的帖子列表
    var article_lists=[];
    $.ajax({
        type : 'POST',
        url : '/C_User/getInterestedPost',
        data:{userID:user_id},
        async:false,
        success:function(data){
            article_lists=data;
            // alert(JSON.stringify(data));
        },
        error:function(data){
            alert("fail");
        }
    });
    for(var i=0;i<article_lists.length;i++){
        var new_element="<li id='"+i+"'>\n" +
            "                            <div class=\"fn-flex ais-hits--item\">\n" +
            "                                <div class=\"fn-flex-1 article-list__panel\">\n" +
            "                                    <div class=\"fn__flex\">\n" +
            "                                        <a rel=\"nofollow\" href=\"https://hacpai.com/member/czf1046277348\">\n" +
            "                                            <div class=\"touxiang avatar tooltipped__user\" aria-name=\"czf1046277348\" data-src=\"https://img.hacpai.com/avatar/1533003378805_1535160098840.png?imageView2/1/w/80/h/80/interlace/0/q/100\"></div>\n" +
            "                                        </a>\n" +
            "                                        <div class=\"fn__flex-1 tag_list\">\n" +
            "                                            <span class='tags' style='cursor: pointer'></span>\n"+
            "                                            <span class=\"ft__smaller ft__fade\">• &nbsp;&nbsp;\n" +
            "                                                <a class=\"ft-gray\" href=\"https://hacpai.com/article/1535076511412#comments\" target=\"_self\">\n" +
            "                                                    <span class=\"article-level0\">"+article_lists[i].remark_num+"人</span> 回帖\n" +
            "                                                </a>&nbsp;\n" +
            "                                                <a class=\"ft-gray\" href=\"https://hacpai.com/article/1535076511412\" target=\"_self\">\n" +
            "                                                    <span class=\"article-level0\">"+article_lists[i].visits+"人</span> 浏览\n" +
            "                                                </a>&nbsp;\n" +
            "                                            </span>\n" +
            "                                            <h2 class=\"article-list__title fn__ellipsis\">\n" +
            "                                                <a data-id=\"1535160513461\" data-type=\"0\" rel=\"bookmark\"\n" +
            "                                                   href=\"https://hacpai.com/article/1535160513461\"\n" +
            "                                                   target=\"_self\">"+article_lists[i].post_name+"</a>\n" +
            "                                            </h2>\n" +
            "                                        </div>\n" +
            "                                    </div>\n" +
            "                                    <span class=\"abstract article_content\">"+article_lists[i].brief_intro+"</span>\n" +
            "                                </div>\n" +
            "                            </div>\n" +
            "                            <div class=\"fn-relative\">\n" +
            "                                <span class=\"heat tooltipped tooltipped__n\" aria-label=\"帖子活跃度\" style=\"width:0px\"></span>\n" +
            "                            </div>\n" +
            "                        </li>";
        $("#article_list").append(new_element);
        for(var j=0;j<article_lists[i].post_tag.length;j++){
            var new_tag="<a rel=\"tag\" class=\"tag\">"+article_lists[i].post_tag[j]+"</a>&nbsp;";
            $("#"+i).find(".tags").append(new_tag);
        }
    }

    //关注的用户列表
    var user_list=[];
    $.ajax({
        type : 'POST',
        url : '/C_User/getInterestedUser',
        data:{userID:user_id},
        async:false,
        success:function(data){
            user_list=data;
            // alert(JSON.stringify(data));
        },
        error:function(data){
            alert("fail");
        }
    });
    for(var x=0;x<user_list.length;x++){
        var new_user="<div class=\"ais-hits\">\n" +
            "                        <div class=\"ais-hits--item\">\n" +
            "                            <a style=\"display: flex\">\n" +
            "                                <img class=\"avatar\" src=\""+user_list[x]+"\">\n" +
            "                                <div class=\"fn__flex-1\">\n" +
            "                                    <div class=\"title\">"+user_list[x].nickname+"</div>\n" +
            "                                    <div class=\"meta\">已发帖："+user_list[x].releasedpost+" • 粉丝数："+user_list[x].fans+"</div>\n" +
            "                                </div>\n" +
            "                            </a>\n" +
            "                        </div>\n" +
            "                    </div>";
        $("#hits").append(new_user);
    }

    //关注用户 关注帖子 最近发布
    $.ajax({
        type : 'POST',
        url : '/C_User/getRelease',
        data:{userID:user_id},
        async:false,
        success:function(data){
            document.getElementById("current_release").innerHTML=data.length;
        },
        error:function(data){
            alert("fail");
        }
    });
    $.ajax({
        type : 'POST',
        url : '/C_User/getInterestedPost',
        data:{userID:user_id},
        async:false,
        success:function(data){
            document.getElementById("interest_post").innerHTML=data.length;
        },
        error:function(data){
            alert("fail");
        }
    });
    $.ajax({
        type : 'POST',
        url : '/C_User/getInterestedUser',
        data:{userID:user_id},
        async:false,
        success:function(data){
            document.getElementById("interest_user").innerHTML=data.length;
        },
        error:function(data){
            alert("fail");
        }
    });

    //最近浏览列表
    $.ajax({
        type : 'POST',
        url : '/C_User/getHistory',
        data:{userID:user_id},
        async:false,
        success:function(data){
            for(var i=0;i<data.length;i++){
                var new_history="<li>\n" +
                    "                            <a rel=\"nofollow\" href=\"https://hacpai.com/member/bwing\">\n" +
                    "                                <span class=\"avatar-small slogan tooltipped__user\" aria-name=\"bwing\" data-src=\"https://img.hacpai.com/avatar/1529805476997?imageView2/1/w/52/h/52/format/jpg/interlace/0/q/100\" style=\"background-image: url(\""+data[i].url+"\")\"></span>\n" +
                    "                            </a>\n" +
                    "                            <a rel=\"nofollow\" class=\"title\" href=\"https://hacpai.com/article/1534585133065\">"+data[i].postname+"</a>\n" +
                    "                        </li>";
                $(".module-list").append(new_history);
            }
        },
        error:function(data){
            alert("fail");
        }
    });

    //推荐列表

    //搜索
    $("#search").click(function(){
        var search_content=document.getElementById("algoliaSearch").value;
        localStorage.setItem("search_content",search_content);
        window.location.href="community_search";
    });

    //关注类型切换
    $("#post").click(function(){
        $("#user").removeClass("tabs-sub__item--current");
        $("#post").addClass("tabs-sub__item--current");
        $(".article-list").show();
        $("#hits").hide();
    });
    $("#user").click(function(){
        $("#post").removeClass("tabs-sub__item--current");
        $("#user").addClass("tabs-sub__item--current");
        $(".article-list").hide();
        $("#hits").show();
    });

});

//跳转至已经发布
function goto_already_publish(){
    localStorage.setItem("user_detail_id",localStorage.getItem("user_id"));
    window.location.href="community_person";
}