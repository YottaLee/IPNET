$(document).ready(function(){

    var patent_id=localStorage.getItem("patent_id");

    $.ajax({
        type : 'POST',
        url : '/Patent/searchPatentByID',
        data:{patentID:patent_id},
        async:false,
        success:function(data){
            $("#patent_photo").attr("src",data.url);
            $("#patent_type").text(data.patent_type);
            $("#patent_name").text(data.patent_id+"    "+data.patent_name);
            $("#patent_number").text(data.patent_id);
            $("#patent_pool_id").text(data.pool_id);
            $("#patentowner").text(data.patent_holder);
            $("#patent_state").text(data.state);
            $("#apply_time").text(data.apply_date);
            $("#enable_time").text(data.valid_period);
            $("#patent_region").text(data.region);
            $("#patent_intro").text(data.profile);
        },
        error:function(data){
            alert("fail");
        }
    });
    // $.ajax({
    //     type : 'POST',
    //     url : '/Patent/recommendPatent',
    //     data:{},
    //     async:false,
    //     success:function(data){
    //         for(var i=0;i<data.length;i++){
    //             var patent_intro=data[i].profile.length<=20?data[i].profile:data[i].profile.substring(0,20);
    //             var new_element="<div class=\"item newspaper-x-image\" id='recommend"+i+"' data-id='"+data[i].patent_id+"'>\n" +
    //                 "                                    <img width=\"550\" height=\"360\" src=\""+data[i].url+"\" class=\"attachment-newspaper-x-recent-post-big size-newspaper-x-recent-post-big wp-post-image\" alt=\"\"/>\n" +
    //                 "                                    <div class=\"newspaper-x-related-post-title\">\n" +
    //                 "                                        <a href=\"https://colorlib.com/newspaper-x/2017/05/10/nunc-hendrerit-egestas-amus-ad-arcu-im-usa/\">"+data[i].patent_name+"</a>\n" +
    //                 "                                    </div>\n" +
    //                 "                                    <div class=\"newspaper-x-related-posts-date\">"+patent_intro+"</div>\n" +
    //                 "                                </div>";
    //             $("#owlCarousel-107").append(new_element);
    //             $("#recommend"+i).click(function(){
    //                 localStorage.setItem("patent_id",$(this).getAttribute("data-id"));
    //                 window.location.href="detail";
    //             })
    //         }
    //     },
    //     error:function(data){
    //         alert("fail");
    //     }
    // });

});