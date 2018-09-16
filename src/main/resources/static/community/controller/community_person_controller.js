function is_in_interest_list(user_id,user_detail_id){
    $.ajax({
        type : 'POST',
        url : '/C_User/getInterestedUser',
        data:{userID:user_id},
        async:false,
        success:function(data){
            for(var i=0;i<data.length;i++){
                if(user_detail_id==data[i].userid)
                    return true;
            }
            return false;
        },
        error:function(data){
            alert("fail");
        }
    })
}

$(document).ready(function(){

    var user_detail_id=localStorage.getItem("user_detail_id");
    var user_id=localStorage.getItem("user_id");

    //个人信息展示
    $.ajax({
        type : 'POST',
        url : '/C_User/info',
        data:{userID:user_detail_id},
        async:false,
        success:function(data){
            $("#user_photo").attr("src",data.url);
            $("#user_name").text(data.nickname);
            if(user_detail_id==user_id){
                $("#interest_user").hide();
            }else if(is_in_interest_list(user_id,user_detail_id)){
                document.getElementById("interest_use").innerText="取消关注";
                $("#cancel_interest").show();
                $("#interest").hide();
            }else{
                document.getElementById("interest_use").innerText="关注";
                $("#cancel_interest").hide();
                $("#interest").show();
            }
            $("#release_num").text("发帖数："+data.releasedpost);
            $("#fans_num").text("粉丝数："+data.fans);
        },
        error:function(){
            alert("fail");
        }
    });

    //个人帖子列表
    $.ajax({
        type : 'POST',
        url : '/C_User/getRelease',
        data:{userID:user_detail_id},
        async:false,
        success:function(data){
            for(var i=0;i<data.length;i++){
                var new_post="<li class=\"fn-flex\" id=\""+i+"\">\n" +
                    "                            <div class=\"fn-flex-1 home-flex-space\">\n" +
                    "                                <h2>\n" +
                    "                                    <a rel=\"bookmark\" href=\"https://hacpai.com/article/1487303561541\" target=\"_self\">"+data[i].post_name+"</a>\n" +
                    "                                </h2>\n" +
                    "                                <span class='tag_list'>/span>\n" +
                    "                                <span class=\"ft-smaller ft-gray\">&nbsp;•&nbsp;\n" +
                    "                                    <a rel=\"nofollow\" class=\"ft-gray\" href=\"https://hacpai.com/article/1487303561541#comments\">\n" +
                    "                                        <span class=\"article-level3\">"+data[i].remark_num+"</span> 回帖\n" +
                    "                                    </a>&nbsp;•&nbsp;\n" +
                    "                                    <span class=\"article-level2\">"+data[i].visits+"</span>浏览&nbsp;•&nbsp;\n" +
                    "                                    <span>"+data[i].publish_time+"</span>\n" +
                    "                                </span>\n" +
                    "                            </div>\n" +
                    "                            <div>\n" +
                    "                                <a class=\"count ft-gray tooltipped tooltipped__w\"  aria-label=\"回帖数\" href=\"https://hacpai.com/article/1487303561541\" target=\"_self\">\"+data[i].remark_num+\"</a>\n" +
                    "                            </div>\n" +
                    "                        </li>";
                $("#post_list").append(new_post);
                for(var j=0;j<data[i].post_tag.length;j++){
                    var new_tag="<a rel=\"tag\" class=\"tag\">"+data[i].post_tag[j]+"</a>&nbsp;";
                    $("#"+i).find(".tag_list").append(new_tag);
                }
            }
        },
        error:function(data){
            alert("fail");
        }
    });

    //搜索
    $("#search").click(function(){
        var search_content=document.getElementById("algoliaSearch").value;
        localStorage.setItem("search_content",search_content);
        window.location.href="community_search";
    });

    //关注or取消关注
    $("#interest_user").click(function(){
        if(document.getElementById("interest_user").innerText=="关注"){
            $("#cancel_interest").show();
            $("#interest").hide();
            document.getElementById("interest_user").innerText="取消关注";
        }else{
            $("#cancel_interest").hide();
            $("#interest").show();
            document.getElementById("interest_user").innerText="关注";
        }
    })

});

//跳转至已经发布
function goto_already_publish(){
    localStorage.setItem("user_detail_id",localStorage.getItem("user_id"));
    window.location.href="community_person";
}