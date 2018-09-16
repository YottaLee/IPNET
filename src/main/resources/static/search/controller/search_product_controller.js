var search_info="";

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

   $(".type_tag").click(function(){
       var type_get=$(this).text();
       var type_give="";
       if(type_get=="发明专利"){
           type_give="Invention";
       }else if(type_get=="实用新型"){
           type_give="Utility";
       }else{
           type_give="Design";
       }
      search_type(type_give);
      $(this).addClass("on");
      $(this).siblings().removeClass("on");
   });

    $(".region_tag").click(function(){
        search_region($(this).text());
        $(this).addClass("on");
        $(this).siblings().removeClass("on");
    });

    $(".state_tag").click(function(){
        var state_get=$(this).text();
        var state_give="";
        if(state_get=="闲置"){
            state_give="free";
        }else if(state_get=="申请许可"){
            state_give="to_be_transfer";
        }else if(state_get=="转让过程中"){
            state_give="transfering";
        }else if(state_get=="申请质押贷款过程中"){
            state_give="to_be_loan";
        }else if(state_get=="质押过程中"){
            state_give="loaning";
        }else if(state_get=="待审核"){
            state_give="to_be_check";
        }else{
            state_give="overdue";
        }
        search_state(state_give);
        $(this).addClass("on");
        $(this).siblings().removeClass("on");
    });

    $("#search_apply_time").click(function(){
       search_apply_time();
    });

    $("#search_valid_time").click(function(){
       search_valid_time();
    });

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
    $("#main").empty();
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
            "                                        <img style='width: 300px;height: 200px;background-size: 100% 100%' width=\"550\" height=\"360\" data-id='"+patent_list[i].patent_id+"' src='"+patent_list[i].url+"' class=\"patent_photo attachment-newspaper-x-recent-post-big size-newspaper-x-recent-post-big wp-post-image\" alt=\"\"/>\n" +
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
    $.ajax({
        type : 'POST',
        url : '/Patent/searchPatentsByType',
        data:{type:type},
        async:false,
        success:function(data){
            patent_list=data;
        },
        error:function(data){

        }
    });
    show_patent_list();
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

function search_apply_time(){
    var begin_para=$("#apply_begin_time").val();
    var end_para=$("#apply_end_time").val();
    begin_para=begin_para.substring(6)+begin_para.substring(0,2)+begin_para.substring(3,5);
    end_para=end_para.substring(6)+end_para.substring(0,2)+end_para.substring(3,5);
    $.ajax({
        type : 'POST',
        url : '/Patent/searchPatentsByApplyDate',
        data:{StartDate:begin_para,endDate:end_para},
        async:false,
        success:function(data){
            patent_list=data;
        },
        error:function(data){

        }
    });
    show_patent_list();
}

function search_valid_time(){
    var begin_para=$("#valid_begin_time").val();
    var end_para=$("#valid_end_time").val();
    begin_para=begin_para.substring(6)+begin_para.substring(0,2)+begin_para.substring(3,5);
    end_para=end_para.substring(6)+end_para.substring(0,2)+end_para.substring(3,5);
    $.ajax({
        type : 'POST',
        url : '/Patent/searchPatentsByApplyDate',
        data:{StartDate:begin_para,endDate:end_para},
        async:false,
        success:function(data){
            patent_list=data;
        },
        error:function(data){

        }
    });
    show_patent_list();
}

function search_state(state){
    $.ajax({
        type : 'POST',
        url : '/Patent/searchPatentsByState',
        data:{state:state},
        async:false,
        success:function(data){
            patent_list=data;
        },
        error:function(data){

        }
    });
    show_patent_list();
}