$(document).ready(function(){

   var search_info=localStorage.getItem("search_info");

   var patent_list=[];

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

});