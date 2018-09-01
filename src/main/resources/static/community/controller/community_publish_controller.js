$(document).ready(function(){

    var user_id=localStorage.getItem("user_id");
    user_id="czf";
    var user_name="";

    $.ajax({
        type : 'POST',
        url : '/C_User/info',
        data:{userID:user_id},
        async:false,
        success:function(data){
            user_name=data.nickname;
        },
        error:function(){
            alert("fail");
        }
    });

    //发帖
    $("#publish_post").click(function(){
        var post_name=document.getElementById("articleTitle").value;
        var post_intro=document.getElementById("post_intro").value;
        var post_content=$(".ql-editor").html();
        var post_tags=[];
        var post_id="";
        var tag_elements=$(".tags-selected").find(".tag");
        for(var i=0;i<tag_elements.length;i++){
            post_tags.push(tag_elements[i].getElementsByClassName("text")[0].innerHTML);
        }
        $.ajax({
            type : 'POST',
            url : '/post/createPostID',
            data:{author:user_name},
            async:false,
            success:function(data){
                post_id=data;
            },
            error:function(data){
                alert("fail");
            }
        });
        var temp_para={post_id:post_id,author:user_name,post_name:post_name,post_tag:post_tags,brief_intro:post_intro,content:post_content};
        $.ajax({
            type : 'POST',
            url : '/post/publishArticle',
            contentType:"application/json",
            dataType:"json",
            async:false,
            data :JSON.stringify(temp_para),
            success:function(data){
                alert("success");
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