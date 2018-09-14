function nTabs(appro, thisObj, Num){
    if(thisObj.className == "active")return;
    var tabObj = thisObj.parentNode.id;
    var tabList = document.getElementById(tabObj).getElementsByTagName("li");
    for(i=0; i <tabList.length; i++)
    {
        if (i == Num)
        {
            thisObj.className = "active";
            document.getElementById(tabObj+"_Content"+i).style.display = "block";
        }else{
            tabList[i].className = "normal";
            document.getElementById(tabObj+"_Content"+i).style.display = "none";
        }
    }
    if(appro == "registerEmailPerson" && Num == 0){
        tabRegisterEmail(0);
    } else if(appro == "registerEmailEnterprise" && Num == 1){
        tabRegisterEmail(1);
    } else if(appro == "registerPerson" && Num == 0){
        tabRegisterPerson(0);
    } else if(appro == "registerEnterprise" && Num == 1){
        tabRegisterEnterprise();
    }
}

var show_king_id = 1;
function show_king_list(e,k)
{
    if(show_king_id == k) return true;
    o = document.getElementById("a"+show_king_id);
    o.className = "bg";
    e.className = " ";
    show_king_id = k;
}
var show_kinga_id = 1;
function show_kinga_list(f,l)
{
    if(show_kinga_id == l) return true;
    o = document.getElementById("b"+show_kinga_id);
    o.className = "bg";
    f.className = " ";
    show_kinga_id = l;
}
