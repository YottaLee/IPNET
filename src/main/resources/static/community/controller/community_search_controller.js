var search_post_result=[];
var search_user_result=[];

function search(search_content){
    $.ajax({
        type : 'POST',
        url : '/post/searchArticle',
        data:{keywords:search_content},
        async:false,
        success:function(data){
            search_post_result=data;
        },
        error:function(data){
            alert("fail");
        }
    });
    $.ajax({
        type : 'POST',
        url : '/C_User/search',
        data:{keywords:search_content},
        async:false,
        success:function(data){
            search_user_result=data;
        },
        error:function(data){
            alert("fail");
        }
    });
    for(var i=0;i<search_post_result.length;i++){
        var new_post="<div class=\"ais-hits--item posts\" id='post"+i+"'>\n" +
            "                            <a>\n" +
            "                                <div class=\"title\">"+search_post_result[i].post_name+"</div>\n" +
            "                                <div class=\"meta tags\" style='cursor: pointer'></div>\n" +
            "                            </a>\n" +
            "                        </div>";
        $("#search_result").append(new_post);
        for(var j=0;j<search_post_result[i].post_tag.length;j++){
            var new_tag="<a rel=\"tag\" class=\"tag\">"+search_post_result[i].post_tag[j]+"</a>&nbsp;";
            $("#post"+i).find(".tags").append(new_tag);
        }
    }
    for(var x=0;x<search_user_result.length;x++){
        var new_user="<div class=\"ais-hits\">\n" +
            "                        <div class=\"ais-hits--item users\">\n" +
            "                            <a style=\"display: flex\">\n" +
            "                                <img class=\"avatar\" src=\""+search_user_result[x]+"\">\n" +
            "                                <div class=\"fn__flex-1\">\n" +
            "                                    <div class=\"title\">"+search_user_result[x].nickname+"</div>\n" +
            "                                    <div class=\"meta\">已发帖："+search_user_result[x].releasedpost+" • 粉丝数："+search_user_result[x].fans+"</div>\n" +
            "                                </div>\n" +
            "                            </a>\n" +
            "                        </div>\n" +
            "                    </div>";
        $("#search_result").append(new_user);
    }
}

$(document).ready(function(){

    var user_id=localStorage.getItem("user_id");
    user_id="czf";

    var search_content=localStorage.getItem("search_content");
    search(search_content);

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
        search(search_content);
    });

    //搜索结果类型切换
    $("#post").click(function(){
        $("#user").removeClass("tabs-sub__item--current");
        $("#post").addClass("tabs-sub__item--current");
        $(".posts").show();
        $(".users").hide();
        document.getElementById("search_number").value=search_post_result.length
    });
    $("#user").click(function(){
        $("#post").removeClass("tabs-sub__item--current");
        $("#user").addClass("tabs-sub__item--current");
        $(".posts").hide();
        $(".users").show();
        document.getElementById("search_number").value=search_user_result.length
    });

});

//跳转至已经发布
function goto_already_publish(){
    localStorage.setItem("user_detail_id",localStorage.getItem("user_id"));
    window.location.href="community_person";
}