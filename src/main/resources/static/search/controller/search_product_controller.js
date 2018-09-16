var search_info=localStorage.getItem("search_info");

var patent_list=[];

var row=-1;
var col=0;

$(document).ready(function(){

   $.ajax({
       type : 'POST',
       url : '/Patent/searchPatent',
       data:{info:search_info},
       async:false,
       success:function(data){
           patent_list=data;
       },
       error:function(data){
           alert("fail");
       }
   });

   show_patent_list();

   $("#search_btn").click(function(){
       var search_info=$("#tags").val();
       $.ajax({
           type : 'POST',
           url : '/Patent/searchPatent',
           data:{info:search_info},
           async:false,
           success:function(data){
               patent_list=data;
           },
           error:function(data){

           }
       });
       show_patent_list();
   })

});

function show_patent_list(){
    for(var i=0;i<patent_list.length;i++){
        if(col==0){
            row++;
            var new_row="<div class=\"row\" id='row"+row+"'></div>";
            $("#main").append(new_row);
        }
        var intro=patent_list[i].profile.length<=20?patent_list[i].profile:patent_list[i].profile.substring(0,20);
        var new_col="<div class=\"col-md-4\" id='patent"+i+"'>\n" +
            "                            <article class=\"post-108 post type-post status-publish format-standard has-post-thumbnail hentry category-editorial\">\n" +
            "                                <header class=\"entry-header\">\n" +
            "                                    <div class=\"newspaper-x-image\">\n" +
            "                                        <img width=\"550\" height=\"360\" data-id='"+patent_list[i].patent_id+"' src='"+patent_list[i].url+"' class=\"patent_photo attachment-newspaper-x-recent-post-big size-newspaper-x-recent-post-big wp-post-image\" alt=\"\"/>\n" +
            "                                    </div>\n" +
            "                                    <h4 class=\"entry-title\">"+patent_list[i].patent_id+"</h4>\n" +
            "                                    <h4 class=\"entry-title\">"+patent_list[i].patent_name+"</h4>\n" +
            "                                    <div class=\"newspaper-x-post-meta\">\n" +
            "                                        <div>\n" +
            "                                            <span class=\"newspaper-x-category\">"+patent_list[i].patent_type+"</span>&nbsp;&nbsp;&nbsp;\n" +
            "                                        </div>\n" +
            "                                    </div>\n" +
            "                                    <div class=\"newspaper-x-post-meta\">\n" +
            "                                        <div>\n" +
            "                                            <span class=\"newspaper-x-date\" style=\"color: black;font-weight: bold;margin-left: 0px\">持有人：<span>"+patent_list[i].patent_holder+"</span> </span>\n" +
            "                                        </div>\n" +
            "                                    </div>\n" +
            "                                </header>\n" +
            "                                <div class=\"entry-content\">\n" +
            "                                    <p>"+intro+"</p>\n" +
            "                                </div>\n" +
            "                                <footer class=\"entry-footer\">\n" +
            "                                </footer>\n" +
            "                            </article>\n" +
            "                        </div>";
        $("#row"+row).append(new_col);
        col++;
        if(col==3) col=0;
        $("#patent"+i).find(".patent_photo").click(function(){
            localStorage.setItem("patent_id",$(this).attr("data-id"));
            window.location.href="detail";
        })
    }
    row=0;
    col=0;
}

function search_type(type){

}

function search_region(region){
    $.ajax({
        type : 'POST',
        url : '/Patent/searchPatentByRegion',
        data:{region:region},
        async:false,
        success:function(data){
            patent_list=data;
        },
        error:function(data){

        }
    });
    show_patent_list();
}

function search_apply_time(beginTime,endTime){

}

function search_valid_time(beginTime,endTime){

}